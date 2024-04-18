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
import uz.atm_v_3.repository.CardHolderRepository;
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.CardService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.time.LocalDate;
import java.util.Random;

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


    @Override
    public CardResponseDTO createCard(CardRequestDTO cardRequestDTO, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            Card card = cardMapper.toEntity(cardRequestDTO);
            CardHolder cardHolder = cardHolderRepository.findById(cardRequestDTO.getCardHolder().getId())
                    .orElseThrow(() -> new CardException("Card Holder not found"));
            int min = 1000;
            int max = 9999;
            Random random = new Random();
            String cardCVC = random.ints(100, 999).findFirst().getAsInt() + "";

            LocalDate date = LocalDate.now();
            CardResponseDTO cardResponseDTO;
            if (cardRequestDTO.getCardType().equals("UZCARD")) {
                String newCard = "86001011" + "" + (random.ints(min, max).findFirst().getAsInt() + ""
                        + (random.ints(min, max).findFirst()).getAsInt());
                card.setCardType(CardType.UZCARD);
                card.setBalance("0");
                card.setIsActive(Boolean.TRUE);
                // card expire date for 3 years
                card.setCardExpireDate(date.plusYears(3).toString());
                card.setCardNumber(newCard);
            }
            if (cardRequestDTO.getCardType().equals("HUMO")) {
                String newCard = "98601011" + "" + (random.ints(min, max).findFirst().getAsInt() + ""
                        + (random.ints(min, max).findFirst()).getAsInt());
                card.setCardType(CardType.HUMO);
                card.setBalance("0");
                card.setIsActive(Boolean.TRUE);
                // card expire date for 3 years
                card.setCardExpireDate(date.plusYears(3).toString());
                card.setCardNumber(newCard);
            }
            if (cardRequestDTO.getCardType().equals("VISA")) {
                String newCard = "56141011" + "" + (random.ints(min, max).findFirst().getAsInt() + ""
                        + (random.ints(min, max).findFirst()).getAsInt());
                card.setCardType(CardType.VISA);
                card.setBalance("0");
                card.setIsActive(Boolean.TRUE);
                card.setCardCVC(cardCVC);
                // card expire date for 3 years
                card.setCardExpireDate(date.plusYears(3).toString());
                card.setCardNumber(newCard);
            }
            cardRepository.save(card);
            cardResponseDTO = cardMapper.toDto(card);
            cardResponseDTO.setCardHolderName(cardHolder.getName() + " " + cardHolder.getSurname());
            cardResponseDTO.setCardStatus(true);
            cardResponseDTO.setCardHolderPhoneNumber(cardHolder.getPhoneNumber());
            cardResponseDTO.setCardBalance("0");
            cardResponseDTO.setCardHolderAddress(cardHolder.getAddress());

            LOG.info("Card saved: {}", gson.toJson(card));
            return cardResponseDTO;


        } catch (Exception e) {
            LOG.error("Card Not Created: {}", e.getMessage());
            throw new CardException("Error creating card: " + e.getMessage());
        }
    }


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


}
