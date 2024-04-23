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

@Service
@RequiredArgsConstructor
@Slf4j
public class CardHistoryServiceImpl implements CardHistoryService {

    private final CardHistoryRepository cardHistoryRepository;
    private final CardHolderRepository cardHolderRepository;
    private final CardRepository cardRepository;

    private final ClientInfoService clientInfoService;
    private final CheckCard checkCard;

    @Override
    public List<CardHistoryResponseDTO> getCardHistoryByCardNumber(CardHistoryRequestDTO cardHistoryRequestDTO, HttpServletRequest httpServletRequest) {
        try {

            Card card1 = cardRepository.findCardByCardNumber(cardHistoryRequestDTO.getCardNumber());
            List<CardHistoryResponseDTO> cardHistoryResponseDTO = new ArrayList<>();
            clientInfoService.getLogger(httpServletRequest);
            List<CardHistory> cardHistoryList = cardHistoryRepository.findAllByCardId(card1.getId());
            for (CardHistory cardHistory : cardHistoryList) {
                CardHistoryResponseDTO cardHistoryResponseDTO1 = new CardHistoryResponseDTO();
                if (cardHistory.getToCard() != null) {
                    cardHistoryResponseDTO1.setReceiverCardNumber(cardHistory.getToCard().getCardNumber());
                    cardHistoryResponseDTO1.setReceiverCardHolderName(cardHistory.getToCard().getCardHolder()
                            .getName() + " " + cardHistory.getToCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Fill out card");
                }
                if (cardHistory.getFromCard() != null) {
                    cardHistoryResponseDTO1.setSenderCardNumber(cardHistory.getFromCard().getCardNumber());
                    cardHistoryResponseDTO1.setSenderCardHolderName(cardHistory.getFromCard().getCardHolder()
                            .getName() + " " + cardHistory.getFromCard().getCardHolder().getSurname());
                    cardHistoryResponseDTO1.setOperationType("Cash out card");
                }
                if (cardHistory.getFromCard()!=null && cardHistory.getToCard()!=null){
                    cardHistoryResponseDTO1.setOperationType("Transfer money");
                }
                cardHistoryResponseDTO1.setId(cardHistory.getId());
                cardHistoryResponseDTO1.setAmount(cardHistory.getAmount());
                cardHistoryResponseDTO1.setCommission(cardHistory.getCommission());
                cardHistoryResponseDTO1.setDate(String.valueOf(cardHistory.getDate()));
                cardHistoryResponseDTO.add(cardHistoryResponseDTO1);
            }
            return cardHistoryResponseDTO;
        } catch (Exception e) {
            log.error("Card History Not Found: {}", e.getMessage());
            throw new CardHistoryException("Error getting card history: " + e.getMessage());
        }
    }

    @Override
    public CardHistoryResponseDTO getCardHistoryBySenderCardNumber(CardHistoryRequestDTO
                                                                           cardHistoryRequestDTO, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public CardHistoryResponseDTO getCardHistoryByPeriod(CardHistoryRequestDTO cardHistoryRequestDTO, String
            startDate, String endDate, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public CardHistoryResponseDTO getReceiverCardHistory(CardHistoryRequestDTO
                                                                 cardHistoryRequestDTO, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public CardHistoryResponseDTO getSendCardHistory(CardHistoryRequestDTO
                                                             cardHistoryRequestDTO, HttpServletRequest httpServletRequest) {
        return null;
    }
}
