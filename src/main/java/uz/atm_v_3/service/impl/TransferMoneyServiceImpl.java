package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.TransferRequestDTO;
import uz.atm_v_3.dto.response.TransferResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.TransferMoneyService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferMoneyServiceImpl implements TransferMoneyService {

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final Gson gson;
    private final ClientInfoService clientInfoService;
    private final CardRepository cardRepository;
    private final CheckCard cardCheck;


    @Override
    public TransferResponseDTO transferMoney(TransferRequestDTO transferRequestDTO, HttpServletRequest request) {
        Card cardFrom = cardRepository.findCardByCardNumber(transferRequestDTO.getFromCardNumber());
        Card cardTo = cardRepository.findCardByCardNumber(transferRequestDTO.getToCardNumber());
        if (cardFrom == null || cardTo == null) {
            throw new CardException("Card blocked or not found");
        }
        cardCheck.checkTransferCard(transferRequestDTO, cardFrom, cardTo);
        double amountDouble = Double.parseDouble(transferRequestDTO.getAmount());
        double commission = amountDouble * 0.01;
        String balanceWithoutCommasCardFrom = cardFrom.getBalance().replaceAll(",", ".");
        String balanceWithoutCommasCardTo = cardTo.getBalance().replaceAll(",", ".");
        double cardBalanceFrom = Double.parseDouble(balanceWithoutCommasCardFrom);
        double cardBalanceTo = Double.parseDouble(balanceWithoutCommasCardTo);
        String cardBalance1;
        String cardBalance2;
        try {
            clientInfoService.getLogger(request);
            if (cardFrom.getCardType().equals(CardType.HUMO) && cardTo.getCardType().equals(CardType.HUMO)) {
                commission = 0;
            }
            cardBalanceFrom -= amountDouble + commission;
            cardBalanceTo += amountDouble;
            cardBalance1 = String.format("%.2f", cardBalanceFrom);
            cardBalance2 = String.format("%.2f", cardBalanceTo);
            cardFrom.setBalance(String.valueOf(cardBalance1));
            cardTo.setBalance(String.valueOf(cardBalance2));
            cardRepository.save(cardFrom);
            cardRepository.save(cardTo);
            LOG.info("Money transferred: {}", gson.toJson(cardFrom));
            return TransferResponseDTO.builder()
                    .fromCardNumber("Card From: " + cardFrom.getCardNumber())
                    .toCardNumber("Card To: " + cardTo.getCardNumber())
                    .status(true)
                    .message("Money transferred")
                    .amount(amountDouble + "")
                    .build();
        } catch (Exception e) {
            LOG.error("Money Not Transferred: {}", e.getMessage());
            throw new CardException("Error transferMoney card balance: " + e.getMessage());
        }
    }
}
