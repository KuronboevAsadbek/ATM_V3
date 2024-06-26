package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.exception.CardTypeException;
import uz.atm_v_3.mapping.CardMapper;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.model.CardType;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.repository.CardTypeRepository;
import uz.atm_v_3.service.GetCardService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.ArrayList;
import java.util.List;

/**
 * The GetCardServiceImpl class encapsulates methods for getting card information.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetCardServiceImpl implements GetCardService {
    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final Gson gson;
    private final ClientInfoService clientInfoService;
    private final CardRepository cardRepository;
    private final EntityManager entityManager;

    /**
     * Gets a card by its ID.
     *
     * @param id      The ID of the card to get.
     * @param request The HTTP servlet request object.
     * @return The DTO representing the card.
     * @throws CardException If an error occurs during card retrieval.
     */
    @Override
    public CardResponseDTO getCard(Long id, HttpServletRequest request) {
        try {

            clientInfoService.getLogger(request);
            Card card = cardRepository.findById(id)
                    .orElseThrow(() -> new CardException("Card not found"));

            return setCardFields(card);
        } catch (Exception e) {

            throw new CardException("Error getting card: " + e.getMessage());
        }
    }

    /**
     * Gets all cards.
     *
     * @param request The HTTP servlet request object.
     * @return The DTO representing all cards.
     * @throws CardException If an error occurs during card retrieval.
     */
    @Override
    public List<CardResponseDTO> getAllCards(HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            List<Card> cards = cardRepository.findAll();
            List<CardResponseDTO> cardResponseDTOS = setAllCardsFields(cards);
            LOG.info("Cards found: {}", gson.toJson(cards));
            return cardResponseDTOS;
        } catch (Exception e) {

            throw new CardException("Error getting cards: " + e.getMessage());
        }
    }

    /**
     * Gets all cards by the card holder's PINFL.
     *
     * @param pinFL   The PINFL of the card holder.
     * @param request The HTTP servlet request object.
     * @return The DTO representing all cards by the card holder's PINFL.
     * @throws CardException If an error occurs during card retrieval.
     */
    @Override
    public List<CardResponseDTO> getAllCardsByCardHolderPINFL(String pinFL, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            String sql = ("""
                    SELECT c.id               AS id,
                           c.balance          AS cardBalance,
                           c.card_number      AS cardNumber,
                           c.card_expire_date AS cardExpireDate,
                           c.card_cvc         AS cardCVC,
                           c.card_pin         AS cardPin,
                           ct.name            AS cardTypeName,
                           c.is_active        AS cardStatus,
                           ch.name            AS cardHolderName,
                           ch.address         AS cardHolderAddress,
                           ch.phone_number    AS cardHolderPhoneNumber
                    FROM card c
                             JOIN card_holder ch ON c.card_holder_id = ch.id
                             JOIN card_type ct ON c.card_type_id = ct.id
                    WHERE ch.pin_fl = :pinfl
                    """);
            Query query = entityManager.createNativeQuery(sql, CardResponseDTO.class);
            query.setParameter("pinfl", pinFL);
            List<CardResponseDTO> cardResponseDTOS = query.getResultList();
            LOG.info("Cards found By PINFL:  {}", gson.toJson(cardResponseDTOS));
            return cardResponseDTOS;
        } catch (Exception e) {

            throw new CardException("Error getting cards: " + e.getMessage());

        }
    }

    private List<CardResponseDTO> setAllCardsFields(List<Card> card){
        List<CardResponseDTO>  cardResponseDTOS = new ArrayList<>();
        for (int i = 0; i < card.size(); i++) {
            CardResponseDTO cardResponseDTO = new CardResponseDTO();
            cardResponseDTO.setCardExpireDate(card.get(i).getCardExpireDate());
            cardResponseDTO.setCardHolderName(card.get(i).getCardHolder().getName() + " "
                    + card.get(i).getCardHolder().getSurname());
            cardResponseDTO.setCardHolderPhoneNumber(card.get(i).getCardHolder().getPhoneNumber());
            cardResponseDTO.setCardHolderAddress(card.get(i).getCardHolder().getAddress());
            cardResponseDTO.setCardBalance(card.get(i).getBalance());
            cardResponseDTO.setCardCVC(card.get(i).getCardCVC());
            cardResponseDTO.setCardNumber(card.get(i).getCardNumber());
            cardResponseDTO.setId(card.get(i).getId());
            cardResponseDTO.setCardPin(card.get(i).getCardPin());
            cardResponseDTO.setCardStatus(card.get(i).getIsActive());
            cardResponseDTO.setCardExpireDate(card.get(i).getCardExpireDate());
            cardResponseDTO.setCardTypeName((card.get(i).getCardType().getName()));
            cardResponseDTOS.add(cardResponseDTO);
        }
        return cardResponseDTOS;
    }

    private CardResponseDTO setCardFields(Card card){
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setCardExpireDate(card.getCardExpireDate());
        cardResponseDTO.setCardHolderName(card.getCardHolder().getName() + " "
                + card.getCardHolder().getSurname());
        cardResponseDTO.setCardHolderPhoneNumber(card.getCardHolder().getPhoneNumber());
        cardResponseDTO.setCardHolderAddress(card.getCardHolder().getAddress());
        cardResponseDTO.setCardBalance(card.getBalance());
        cardResponseDTO.setCardCVC(card.getCardCVC());
        cardResponseDTO.setCardNumber(card.getCardNumber());
        cardResponseDTO.setId(card.getId());
        cardResponseDTO.setCardPin(card.getCardPin());
        cardResponseDTO.setCardStatus(card.getIsActive());
        cardResponseDTO.setCardExpireDate(card.getCardExpireDate());
        cardResponseDTO.setCardTypeName(card.getCardType().getName());
        return cardResponseDTO;
    }
}
