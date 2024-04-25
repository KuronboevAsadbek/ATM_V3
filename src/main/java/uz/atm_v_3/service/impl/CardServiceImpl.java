package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.exception.CardException;
import uz.atm_v_3.mapping.CardMapper;
import uz.atm_v_3.model.Card;
import uz.atm_v_3.model.CardHolder;
import uz.atm_v_3.model.CardType;
import uz.atm_v_3.repository.CardHolderRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.repository.CardTypeRepository;
import uz.atm_v_3.service.CardService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.time.LocalDate;
import java.util.Random;

/**
 * The CardServiceImpl class encapsulates methods for updating and creating cards.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final Gson gson;
    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final ClientInfoService clientInfoService;
    private final CardRepository cardRepository;
    private final CardHolderRepository cardHolderRepository;
    private final CardMapper cardMapper;
    private final CardTypeRepository cardTypeRepository;


    /**
     * Updates the card information.
     *
     * @param id             The ID of the card to be updated.
     * @param cardRequestDTO The DTO containing information about the card to be updated.
     * @param request        The HTTP servlet request object.
     * @return The DTO representing the updated card.
     * @throws CardException If the card is not found.
     */
    @Override
    public ResponseDTO updateCard(Long id, CardRequestDTO cardRequestDTO, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            Card card = cardRepository.findById(id)
                    .orElseThrow(() -> new CardException("Card not found"));

            cardMapper.updateFromDto(cardRequestDTO, card);
            card = cardRepository.save(card);
            LOG.info("Card Edited: {}", gson.toJson(card));
            return new ResponseDTO("Card edited");
        } catch (Exception e) {
            LOG.error("Card Not Edited: {}", e.getMessage());
            throw new CardException("Error editing card: " + e.getMessage());
        }
    }

    /**
     * Creates a new card.
     *
     * @param cardRequestDTO The DTO containing information about the card to be created.
     * @param request        The HTTP servlet request object.
     * @return The DTO representing the created card.
     * @throws CardException If an error occurs during card creation.
     */
    @Override
    public CardResponseDTO creatNewCard(CardRequestDTO cardRequestDTO, HttpServletRequest request) {
        try {
            CardType cardType = cardTypeRepository.findById(cardRequestDTO.getCardTypeId())
                    .orElseThrow(() -> new CardException("Card Type not found"));
            CardHolder cardHolder = cardHolderRepository.findById(cardRequestDTO.getCardHolderId())
                    .orElseThrow(() -> new CardException("Card Holder not found"));
            clientInfoService.getLogger(request);
            Card card = cardMapper.toEntity(cardRequestDTO);
            card.setCardType(cardType);
            card.setCardHolder(cardHolder);

            int min = 1000;
            int max = 9999;
            Random random = new Random();
            String cardCVC = random.ints(100, 999).findFirst().getAsInt() + "";
            String newCard = cardType.getNumber() + "" + (random.ints(min, max).findFirst().getAsInt() + ""
                    + (random.ints(min, max).findFirst()).getAsInt());
            card.setCardNumber(newCard);
            card.setCardExpireDate(String.valueOf(LocalDate.now().plusYears(cardType.getExpirationYear())));
            card.setIsActive(Boolean.TRUE);
            // If the currency type is not UZS, set the card CVC.
            if (!cardType.getCurrencyType().getName().equals("UZS")) {
                card.setCardCVC(cardCVC);
            }
            card.setBalance("0");
            CardResponseDTO cardResponseDTO;
            cardResponseDTO = cardMapper.toDto(card);
            cardResponseDTO.setCardHolderName(cardHolder.getName() + " " + cardHolder.getSurname());

            cardResponseDTO.setCardTypeName(cardType.getName());
            cardResponseDTO.setCardStatus(true);
            cardResponseDTO.setCardHolderPhoneNumber(cardHolder.getPhoneNumber());
            cardResponseDTO.setCardBalance("0");
            cardResponseDTO.setCardHolderAddress(cardHolder.getAddress());
            card.setCheckCardQuantity(0);
            cardRepository.save(card);
            cardResponseDTO.setId(card.getId());
            LOG.info("Card created: {}", gson.toJson(cardResponseDTO));
            return cardResponseDTO;
        }catch (Exception e){

            throw new CardException("Error creating card: " + e.getMessage());
        }
    }


}
