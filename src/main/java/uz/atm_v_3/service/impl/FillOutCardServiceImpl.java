package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.response.FillOutResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.model.CardHistory;
import uz.atm_v_3.repository.CardHistoryRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.FillOutCardService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.Date;

/**
 * The FillOutCardServiceImpl class encapsulates methods for filling out a card.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class FillOutCardServiceImpl implements FillOutCardService {

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final Gson gson;
    private final ClientInfoService clientInfoService;
    private final CardRepository cardRepository;
    private final CheckCard cardCheck;
    private final CheckCard checkCard;
    private final CardHistoryRepository cardHistoryRepository;


    /**
     * Fills out a card.
     *
     * @param fillOutRequestDTO The DTO containing information about the card to be filled out.
     * @param request           The HTTP servlet request object.
     * @return The DTO representing the filled out card.
     * @throws CardException If an error occurs during filling out.
     */
    @Override
    @Transactional
    public FillOutResponseDTO fillOutCardBalance(FillOutRequestDTO fillOutRequestDTO, HttpServletRequest request) {
        try {
            CardHistory cardHistory = new CardHistory();
            Card card = cardRepository.findCardByCardNumber(fillOutRequestDTO.getCardNumber());
            // Check if the card is blocked or not found and throw an exception if it is.
            if (card == null) {
                throw new CardException("Card blocked or not found");
            }
            clientInfoService.getLogger(request);
            double amountDouble = Double.parseDouble(fillOutRequestDTO.getAmount());
            if (checkCard.checkPin(fillOutRequestDTO.getCardPin(), card))
                cardCheck.checkingForFillOut(fillOutRequestDTO, card);
            String balanceWithoutCommas = card.getBalance().replaceAll(",", ".");
            double cardBalance = Double.parseDouble(balanceWithoutCommas);
            String cardBalance2;

            double commission = amountDouble * 0.01;
            cardBalance += amountDouble - commission;
            cardBalance2 = String.format("%.2f", cardBalance);
            card.setBalance(String.valueOf(cardBalance2));

            cardHistory.setToCard(card);
            cardHistory.setAmount(String.valueOf(amountDouble));
            cardHistory.setCommission(String.valueOf(commission));
            Date date = new Date();

            cardHistoryRepository.save(cardHistory);
            cardRepository.save(card);

            LOG.info("Card balance filled: {}", gson.toJson(card));

            // If the request is a cheque request, return the filled amount and commission.
            if (fillOutRequestDTO.isChequeRequest()) {
                return FillOutResponseDTO.builder()
                        .message("Card balance filled")
                        .balance(card.getBalance())
                        .filledAmount(String.valueOf(amountDouble))
                        .commission(String.valueOf(commission))
                        .build();
            }else {
                return FillOutResponseDTO.builder()
                        .message("Card balance filled")
                        .build();
            }
        } catch (Exception e) {
            throw new CardException("Error Fill Card Balance: " + e.getMessage());
        }
    }
}
