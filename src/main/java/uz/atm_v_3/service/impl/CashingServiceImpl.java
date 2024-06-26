package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;
import uz.atm_v_3.dto.response.FillOutResponseDTO;
import uz.atm_v_3.exception.BankNoteException;
import uz.atm_v_3.exception.CardException;
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

/**
 * The CashingServiceImpl class encapsulates methods for cashing a card.
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class CashingServiceImpl implements CashingService {

    private final CheckCard checkCard;
    private final CardRepository cardRepository;
    private final ClientInfoService clientInfoService;
    private final BanknoteTypeRepository banknoteTypeRepository;
    private final CardHistoryRepository cardHistoryRepository;

    /**
     * Cashes a card.
     *
     * @param cashingRequestDTO  The DTO containing information about the card to be cashed.
     * @param httpServletRequest The HTTP servlet request object.
     * @return The DTO representing the cashed card.
     * @throws BankNoteException If an error occurs during cashing.
     */
    @Override
    public CashingResponseDTO cashingCard(CashingRequestDTO cashingRequestDTO,
                                          HttpServletRequest httpServletRequest) {
        try {

            clientInfoService.getLogger(httpServletRequest);
            CardHistory cardHistory = new CardHistory();
            Date date = new Date();
            Card card = cardRepository.findCardByCardNumber(cashingRequestDTO.getCardNumber());

            // Check if the card is blocked or not found and throw an exception if it is.
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

            // Check if the card type is UZS and the amount is a multiple of 5000 and throw an exception if it is not.
            if (card.getCardType().getCurrencyType().getName().equals("UZS") && amountDouble % 5000 != 0) {
                throw new BankNoteException("Amount must be multiple of 5000");
            }
            // Check if the card balance is less than the amount plus the commission and throw an exception if it is.
            if (cardBalance < amountDouble + commission) {
                throw new CardException("Not enough money in the card: " + card.getCardNumber());
            }
            // Check if the card type is HUMO and set the commission to 0 if it is.
            if (cashingRequestDTO.getCashingBanknoteType().equals("MIXED")) {
                List<BanknoteType> banknoteTypeRepositoryList = banknoteTypeRepository.
                        getAllByCurrencyType_Id(card.getCardType().getCurrencyType().getId());
                banknoteTypeRepositoryList.sort((o1, o2) -> o2.getNominal() - o1.getNominal());
                for (BanknoteType banknoteType : banknoteTypeRepositoryList) {
                    // Check if the amount is greater than or equal to the banknote type nominal and set the quantity to 0 if it is.
                    if (amountDouble >= banknoteType.getNominal()) {
                        int count = (int) (amountDouble / banknoteType.getNominal());
                        // Check if the count is greater than the banknote type quantity and set the count to the quantity if it is.
                        if (count > banknoteType.getQuantity()) {
                            count = banknoteType.getQuantity();
                        }
                        amountDouble -= count * banknoteType.getNominal();
                        banknoteType.setQuantity(banknoteType.getQuantity() - count);
                        // Check if the amount is greater than the banknote type nominal and the quantity is 0 and throw an exception if it is.
                    } else if (amountDouble > banknoteType.getNominal() && banknoteType.getQuantity() == 0) {
                        throw new BankNoteException("ATM is empty");
                    }

                }
                // Check if the amount is not 0 and throw an exception if it is.
                if (amountDouble != 0) {
                    throw new BankNoteException("Choose another amount");
                }
                // Check if the cashing banknote type is SMALL and set the banknote type list to the banknote type list sorted by nominal.
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
            if (cashingRequestDTO.isChequeRequest()) {
                return CashingResponseDTO.builder()
                        .message("Card cashed")
                        .balance(card.getBalance())
                        .cashedAmount(String.valueOf(amountDouble))
                        .commission(String.valueOf(commission))
                        .build();
            } else {
                return CashingResponseDTO.builder()
                        .message("Card cashed")
                        .build();
            }

        } catch (Exception e) {
            throw new BankNoteException("Error while cashing card: " + e.getMessage());
        }
    }
}


