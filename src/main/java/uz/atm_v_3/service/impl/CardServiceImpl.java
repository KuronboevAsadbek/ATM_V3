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
import uz.atm_v_3.repository.CardRepository;
import uz.atm_v_3.service.CardService;
import uz.atm_v_3.utils.CardType;


import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final ClientInfoService clientInfoService;
    private final Gson gson;
    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardResponseDTO createCard(CardRequestDTO cardRequestDTO, HttpServletRequest request) {
        try {
            Card card = cardMapper.toEntity(cardRequestDTO);
            int min = 1000;
            int max = 9999;
            Random random = new Random();
            Date date = new Date();

           if (cardRequestDTO.getCardType().equals("uzcard")){

             String newCard = "8600" + random.ints(min, max).findFirst() + "" + (random.ints(min, max)
                     .findFirst() + "" + (random.ints(min, max).findFirst()));
               card.setCardCVC(String.valueOf(random.ints(100, 999)
                       .findFirst()));
               card.setCardType(CardType.UZCARD);
               card.setBalance("0");
               card.setIsActive(Boolean.TRUE);
               card.setCardExpireDate(date + "" + (date.getYear() + 3));
               card.setCardNumber(newCard);
           }
            cardRepository.save(card);
            LOG.info("Card saved: {}", gson.toJson(card));
            return cardMapper.toDto(card);


        } catch (Exception e) {
            LOG.error("Card Not Created: {}", e.getMessage());
            throw new CardException("Error creating card: " + e.getMessage());
        }
    }

    @Override
    public CardResponseDTO getCard(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<CardResponseDTO> getAllCards(HttpServletRequest request) {
        return List.of();
    }

    @Override
    public List<CardResponseDTO> getAllCardsByCardHolderPINFL(String pinFL, HttpServletRequest request) {
        return List.of();
    }

    @Override
    public List<CardResponseDTO> getAllCardsByCardHolderPassportSeriesAndNumber(String passportSeries, String passportNumber, HttpServletRequest request) {
        return List.of();
    }

    @Override
    public ResponseDTO updateCard(Long id, CardRequestDTO cardRequestDTO, HttpServletRequest request) {
        return null;
    }
}
