package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CardHolderRequestDTO;
import uz.atm_v_3.dto.response.CardHolderResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.exception.CardHolderException;
import uz.atm_v_3.mapping.CardHolderMapper;
import uz.atm_v_3.model.CardHolder;
import uz.atm_v_3.repository.CardHolderRepository;
import uz.atm_v_3.service.CardHolderService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardHolderServiceImpl implements CardHolderService {


    private final Gson gson;
    private final static Logger LOG= LoggerFactory.getLogger(CardHolderServiceImpl.class);

    private final CardHolderRepository cardHolderRepository;
    private final ClientInfoService clientInfoService;
    private final CardHolderMapper cardHolderMapper;


    @Override
    public CardHolderResponseDTO createCardHolder(CardHolderRequestDTO cardHolderRequestDTO,
                                                  HttpServletRequest httpServletRequest) {
        try {
           clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderMapper.toEntity(cardHolderRequestDTO);
            cardHolderRepository.save(cardHolder);
            LOG.info("Card Holder saved: {}", gson.toJson(cardHolder));
            return cardHolderMapper.toDto(cardHolder);

        }catch (Exception e){
            LOG.error("Card Holder Not Created: {}", e.getMessage());
            throw new CardHolderException("Error creating card : " + e.getMessage());
        }
    }

    @Override
    public CardHolderResponseDTO getCardHolder(Long id, HttpServletRequest httpServletRequest){
        try {
            clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderRepository.findById(id)
                    .orElseThrow(() -> new CardHolderException("Card Holder not found"));
            return cardHolderMapper.toDto(cardHolder);
        } catch (Exception e) {
            LOG.error("Card Holder Not Found: {}", e.getMessage());
            throw new CardHolderException("Error getting card holder: " + e.getMessage());
        }
    }

    @Override
    public List<CardHolderResponseDTO> getAllCardHolders(HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            List<CardHolder> cardHolders = cardHolderRepository.findAll();
            return cardHolderMapper.toDto(cardHolders);
        } catch (Exception e) {
            LOG.error("Card Holders Not Found: {}", e.getMessage());
            throw new CardHolderException("Error getting card holders: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO updateCardHolder(Long id, CardHolderRequestDTO cardHolderRequestDTO,
                                        HttpServletRequest httpServletRequest) {
        try {

            clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderRepository.findById(id)
                    .orElseThrow(() -> new CardHolderException("Card Holder not found"));
            cardHolderMapper.updateFromDto(cardHolderRequestDTO, cardHolder);
            cardHolderRepository.save(cardHolder);
            LOG.info("Card Holder updated: {}", gson.toJson(cardHolder));
            return new ResponseDTO("Card Holder updated");
        }catch (Exception e){
            LOG.error("Card Holder Not Updated: {}", e.getMessage());
            throw new CardHolderException("Error updating card holder: " + e.getMessage());
        }
    }

    @Override
    public void deleteCardHolder(Long id, HttpServletRequest httpServletRequest) {
        try {

            clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderRepository.findById(id)
                    .orElseThrow(() -> new CardHolderException("Card Holder not found"));
            cardHolderRepository.delete(cardHolder);
            LOG.info("Card Holder deleted: {}", gson.toJson(cardHolder));
        } catch (Exception e) {
            LOG.error("Card Holder Not Deleted: {}", e.getMessage());
            throw new CardHolderException("Error deleting card holder: " + e.getMessage());
        }
    }


}
