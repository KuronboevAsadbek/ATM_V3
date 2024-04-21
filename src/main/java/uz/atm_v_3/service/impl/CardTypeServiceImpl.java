package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CardTypeRequestDTO;
import uz.atm_v_3.dto.response.CardTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.exception.CardTypeException;
import uz.atm_v_3.mapping.CardTypeMapper;
import uz.atm_v_3.model.CardType;
import uz.atm_v_3.repository.CardTypeRepository;
import uz.atm_v_3.repository.CurrencyTypeRepository;
import uz.atm_v_3.service.CardTypeService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardTypeServiceImpl implements CardTypeService {

    private final CurrencyTypeRepository currencyTypeRepository;
    private final Gson gson;
    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final ClientInfoService clientInfoService;
    private final CardTypeMapper cardTypeMapper;
    private final CardTypeRepository cardTypeRepository;

    @Override
    public CardTypeResponseDTO createCardType(CardTypeRequestDTO cardTypeRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CardType cardType = cardTypeMapper.toEntity(cardTypeRequestDTO);
            cardTypeRepository.save(cardType);
            LOG.info("Card Type created: {}", gson.toJson(cardType));
            return cardTypeMapper.toDto(cardType);
        } catch (Exception e) {
            LOG.error("Card Type not created: {}", e.getMessage());
            throw new CardTypeException("Error creating card type: " + e.getMessage());
        }
    }

    @Override
    public CardTypeResponseDTO updateCardType(CardTypeRequestDTO cardTypeRequestDTO, Long id, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CardType cardType = cardTypeRepository.findById(id)
                    .orElseThrow(() -> new CardTypeException("Card Type not found"));
            cardTypeMapper.updateFromDto(cardTypeRequestDTO, cardType);
            LOG.info("Card Type updated: {}", gson.toJson(cardType));
            return cardTypeMapper.toDto(cardType);
        } catch (Exception e) {
            LOG.error("Card Type not updated: {}", e.getMessage());
            throw new CardTypeException("Error updating card type: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO deleteCardType(Long id, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CardType cardType = cardTypeRepository.findById(id)
                    .orElseThrow(() -> new CardTypeException("Card Type not found"));
            cardTypeRepository.delete(cardType);
            LOG.info("Card Type deleted: {}", gson.toJson(cardType));
            return new ResponseDTO("Card Type deleted");
        } catch (Exception e) {
            LOG.error("Card Type not deleted: {}", e.getMessage());
            throw new CardTypeException("Error deleting card type: " + e.getMessage());
        }
    }

    @Override
    public List<CardTypeResponseDTO> getAllCardTypes(HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            List<CardType> cardTypes = cardTypeRepository.findAll();
            return cardTypeMapper.toDto(cardTypes);
        } catch (Exception e) {
            LOG.error("Card Types not found: {}", e.getMessage());
            throw new CardTypeException("Error getting card types: " + e.getMessage());
        }
    }
}
