package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CardHistoryRequestDTO;
import uz.atm_v_3.dto.response.CardHistoryResponseDTO;
import uz.atm_v_3.exception.CardHistoryException;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.model.CardHistory;
import uz.atm_v_3.repository.CardHistoryRepository;
import uz.atm_v_3.repository.CardHolderRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.CardHistoryService;
import uz.atm_v_3.service.checkAndInfo.CheckCard;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * The CardHistoryServiceImpl class encapsulates methods for getting card history by card number, sender card number,
 * period, receiver card history, and sender card history.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CardHistoryServiceImpl implements CardHistoryService {

    private final CardHistoryRepository cardHistoryRepository;
    private final CardHolderRepository cardHolderRepository;
    private final CardRepository cardRepository;

    private final ClientInfoService clientInfoService;
    private final CheckCard checkCard;

    /**
     * Gets the card history by card number.
     *
     * @param cardHistoryRequestDTO The DTO containing information about the card history to be retrieved.
     * @param httpServletRequest    The HTTP servlet request object.
     * @return The DTO representing the card history.
     */
    @Override
    public List<CardHistoryResponseDTO> getCardHistoryByCardNumber(CardHistoryRequestDTO cardHistoryRequestDTO,
                                                                   HttpServletRequest httpServletRequest) {
        try {

            Card card1 = cardRepository.findCardByCardNumber(cardHistoryRequestDTO.getCardNumber());
            List<CardHistoryResponseDTO> cardHistoryResponseDTO = new ArrayList<>();
            clientInfoService.getLogger(httpServletRequest);
            List<CardHistory> cardHistoryList = cardHistoryRepository.findAllByCardId(card1.getId());

            // Iterate through the list of card history objects and create a new CardHistoryResponseDTO object for each one.
            for (CardHistory cardHistory : cardHistoryList) {
                CardHistoryResponseDTO cardHistoryResponseDTO1 = new CardHistoryResponseDTO();
                // Set the receiver card number, receiver card holder name, and operation type if the toCard field is not null.
                if (cardHistory.getToCard() != null) {
                    cardHistoryResponseDTO1.setReceiverCardNumber(cardHistory.getToCard().getCardNumber());
                    cardHistoryResponseDTO1.setReceiverCardHolderName(cardHistory.getToCard().getCardHolder()
                            .getName() + " " + cardHistory.getToCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Fill out card");
                }
                // Set the sender card number, sender card holder name, and operation type if the fromCard field is not null.
                if (cardHistory.getFromCard() != null) {
                    cardHistoryResponseDTO1.setSenderCardNumber(cardHistory.getFromCard().getCardNumber());
                    cardHistoryResponseDTO1.setSenderCardHolderName(cardHistory.getFromCard().getCardHolder()
                            .getName() + " " + cardHistory.getFromCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Cash out card");
                }
                // Set the operation type to "Transfer money" if both the fromCard and toCard fields are not null.
                if (cardHistory.getFromCard()!=null && cardHistory.getToCard()!=null){
                    cardHistoryResponseDTO1.setOperationType("Transfer money");
                }
                cardHistoryResponseDTO1.setId(cardHistory.getId());
                cardHistoryResponseDTO1.setAmount(cardHistory.getAmount());
                cardHistoryResponseDTO1.setCommission(cardHistory.getCommission());
                cardHistoryResponseDTO1.setDate(cardHistory.getDate());
                cardHistoryResponseDTO.add(cardHistoryResponseDTO1);
            }
            return cardHistoryResponseDTO;
        } catch (Exception e) {

            throw new CardHistoryException("Error getting card history: " + e.getMessage());
        }
    }


    /**
     * Gets the card history by period.
     *
     * @param cardHistoryRequestDTO The DTO containing information about the card history to be retrieved.
     * @param startDate             The start date of the period.
     * @param endDate               The end date of the period.
     * @param httpServletRequest     The HTTP servlet request object.
     * @return The DTO representing the card history.
     */
    @Override
    public List<CardHistoryResponseDTO> getCardHistoryByPeriod(CardHistoryRequestDTO cardHistoryRequestDTO, String startDate, String endDate, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            Card card = cardRepository.findCardByCardNumber(cardHistoryRequestDTO.getCardNumber());
            List<CardHistoryResponseDTO> cardHistoryResponseDTO = new ArrayList<>();
            startDate = startDate + "00:00:00";
            endDate = endDate + "23:59:59";

            List<CardHistory> cardHistoryList = cardHistoryRepository.findAllByDateAndCardId(startDate, endDate, card.getId());
            // Iterate through the list of card history objects and create a new CardHistoryResponseDTO object for each one.
            for (CardHistory cardHistory : cardHistoryList) {
                CardHistoryResponseDTO cardHistoryResponseDTO1 = new CardHistoryResponseDTO();
                // Set the receiver card number, receiver card holder name, and operation type if the toCard field is not null.
                if (cardHistory.getToCard() != null) {
                    cardHistoryResponseDTO1.setReceiverCardNumber(cardHistory.getToCard().getCardNumber());
                    cardHistoryResponseDTO1.setReceiverCardHolderName(cardHistory.getToCard().getCardHolder()
                            .getName() + " " + cardHistory.getToCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Fill out card");
                }
                // Set the sender card number, sender card holder name, and operation type if the fromCard field is not null.
                if (cardHistory.getFromCard() != null) {
                    cardHistoryResponseDTO1.setSenderCardNumber(cardHistory.getFromCard().getCardNumber());
                    cardHistoryResponseDTO1.setSenderCardHolderName(cardHistory.getFromCard().getCardHolder()
                            .getName() + " " + cardHistory.getFromCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Cash out card");
                }
                // Set the operation type to "Transfer money" if both the fromCard and toCard fields are not null.
                if (cardHistory.getFromCard() != null && cardHistory.getToCard() != null) {
                    cardHistoryResponseDTO1.setOperationType("Transfer money");
                }
                cardHistoryResponseDTO1.setId(cardHistory.getId());
                cardHistoryResponseDTO1.setAmount(cardHistory.getAmount());
                cardHistoryResponseDTO1.setCommission(cardHistory.getCommission());
                cardHistoryResponseDTO1.setDate(cardHistory.getDate());
                cardHistoryResponseDTO.add(cardHistoryResponseDTO1);
            }
            return cardHistoryResponseDTO;
        } catch (Exception e) {
            throw new CardHistoryException("Error getting card history: " + e.getMessage());
        }
    }


}
