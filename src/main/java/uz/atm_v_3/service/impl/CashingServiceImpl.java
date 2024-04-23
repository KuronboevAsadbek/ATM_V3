package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;
import uz.atm_v_3.exception.BankNoteException;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.exception.CardTypeException;
import uz.atm_v_3.exception.CheckPinException;
import uz.atm_v_3.model.BanknoteType;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.model.CardHistory;
import uz.atm_v_3.repository.BanknoteTypeRepository;
import uz.atm_v_3.repository.CardHistoryRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.CashingService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashingServiceImpl implements CashingService {

    private final CheckCard checkCard;
    private final CardRepository cardRepository;
    private final ClientInfoService clientInfoService;
    private final BanknoteTypeRepository banknoteTypeRepository;
    private final CardHistoryRepository cardHistoryRepository;

    @Override
    public CashingResponseDTO cashingCard(CashingRequestDTO cashingRequestDTO,
                                          HttpServletRequest httpServletRequest) {
        try {

            clientInfoService.getLogger(httpServletRequest);
            CardHistory cardHistory = new CardHistory();
            Date date = new Date();
            Card card = cardRepository.findCardByCardNumber(cashingRequestDTO.getCardNumber());

            if (card == null) {
                throw new CardException("Card blocked or not found");
            }
            checkCard.checkCardForCashing(cashingRequestDTO, card);
            checkCard.checkPin(cashingRequestDTO.getCardPin(), card);
            double amountDouble = Double.parseDouble(cashingRequestDTO.getAmount());
            double commission = amountDouble * 0.01;
            double cashingAmount = amountDouble;
            String balanceWithoutCommas = card.getBalance().replaceAll(",", ".");
            double cardBalance = Double.parseDouble(balanceWithoutCommas);
            String cardBalance2;

            if (card.getCardType().getCurrencyType().getName().equals("UZS") && amountDouble % 5000 != 0) {
                throw new BankNoteException("Amount must be multiple of 5000");
            }

            if (cardBalance < amountDouble + commission) {
                throw new CardException("Not enough money in the card: " + card.getCardNumber());
            }
            if (cashingRequestDTO.getCashingBanknoteType().equals("MIXED")) {
                List<BanknoteType> banknoteTypeRepositoryList = banknoteTypeRepository.
                        getAllByCurrencyType_Id(card.getCardType().getCurrencyType().getId());
                banknoteTypeRepositoryList.sort((o1, o2) -> o2.getNominal() - o1.getNominal());
                for (BanknoteType banknoteType : banknoteTypeRepositoryList) {
                    if (amountDouble >= banknoteType.getNominal()) {
                        int count = (int) (amountDouble / banknoteType.getNominal());
                        if (count > banknoteType.getQuantity()) {
                            count = banknoteType.getQuantity();
                        }
                        amountDouble -= count * banknoteType.getNominal();
                        banknoteType.setQuantity(banknoteType.getQuantity() - count);
                    } else if (amountDouble > banknoteType.getNominal() && banknoteType.getQuantity() == 0) {
                        throw new BankNoteException("ATM is empty");
                    }

                }
                if (amountDouble != 0) {
                    throw new BankNoteException("Choose another amount");
                }
            } else if (cashingRequestDTO.getCashingBanknoteType().equals("SMALL")) {
                List<BanknoteType> banknoteTypeList = banknoteTypeRepository.getAllByCashingTypeName("SMALL");
                banknoteTypeList.sort((o1, o2) -> o2.getNominal() - o1.getNominal());
                for (BanknoteType banknoteType : banknoteTypeList) {
                    if (amountDouble >= banknoteType.getNominal()) {
                        int count = (int) (amountDouble / banknoteType.getNominal());
                        if (count > banknoteType.getQuantity()) {
                            count = banknoteType.getQuantity();
                        }
                        amountDouble -= count * banknoteType.getNominal();
                        banknoteType.setQuantity(banknoteType.getQuantity() - count);
                    } else if (amountDouble > banknoteType.getNominal() && banknoteType.getQuantity() == 0) {
                        throw new BankNoteException("Choose another amount");
                    }
                }
                if (amountDouble != 0) {
                    throw new BankNoteException("Choose another amount");
                }

            }
            cardBalance -= cashingAmount + commission;
            cardBalance2 = String.format("%.2f", cardBalance);
            card.setBalance(String.valueOf(cardBalance2));
            cardRepository.save(card);
            cardHistory.setFromCard(card);
            cardHistory.setAmount(String.valueOf(cashingAmount));
            cardHistory.setCommission(String.valueOf(commission));

            cardHistoryRepository.save(cardHistory);
            return CashingResponseDTO.builder()
                    .message("Cashing is successful")
                    .cashedAmount(cashingRequestDTO.getAmount())
                    .commission(String.valueOf(commission))
                    .balance(card.getBalance())
                    .build();

        } catch (Exception e) {
            throw new BankNoteException("Error while cashing card: " + e.getMessage());
        }
    }
}


