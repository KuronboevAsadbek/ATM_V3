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

/**
 * The CardHolderServiceImpl class encapsulates methods for creating, updating, and deleting card holders.
 */

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardHolderServiceImpl implements CardHolderService {


    private final Gson gson;
    private final static Logger LOG= LoggerFactory.getLogger(CardHolderServiceImpl.class);

    private final CardHolderRepository cardHolderRepository;
    private final ClientInfoService clientInfoService;
    private final CardHolderMapper cardHolderMapper;



    /**
     * Creates a new card holder.
     *
     * @param cardHolderRequestDTO The DTO containing information about the card holder to be created.
     * @param httpServletRequest The HTTP servlet request object.
     * @return The DTO representing the created card holder.
     * @throws CardHolderException If an error occurs during card holder creation.
     */
    @Override
    public CardHolderResponseDTO createCardHolder(CardHolderRequestDTO cardHolderRequestDTO,
                                                  HttpServletRequest httpServletRequest) {
        try {
            // Retrieve the logger from clientInfoService using the provided HttpServletRequest
            // Note: The retrieved logger is not used in this method.
           clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderMapper.toEntity(cardHolderRequestDTO);
            cardHolderRepository.save(cardHolder);
            LOG.info("Card Holder saved: {}", gson.toJson(cardHolder));
            return cardHolderMapper.toDto(cardHolder);

        }catch (Exception e){

            // Throw a custom exception with a meaningful message
            throw new CardHolderException("Error creating card : " + e.getMessage());
        }
    }

    /**
     * Retrieves a card holder by ID.
     *
     * @param id The ID of the card holder to retrieve.
     * @param httpServletRequest The HTTP servlet request object.
     * @return The DTO representing the retrieved card holder.
     * @throws CardHolderException If an error occurs during card holder retrieval.
     */
    @Override
    public CardHolderResponseDTO getCardHolder(Long id, HttpServletRequest httpServletRequest){
        try {
            clientInfoService.getLogger(httpServletRequest);

            // Retrieve the card holder by ID or throw an exception if not found
            CardHolder cardHolder = cardHolderRepository.findById(id)
                    .orElseThrow(() -> new CardHolderException("Card Holder not found"));
            return cardHolderMapper.toDto(cardHolder);
        } catch (Exception e) {

            throw new CardHolderException("Error getting card holder: " + e.getMessage());
        }
    }

    /**
     * Retrieves all card holders.
     *
     * @param request The HTTP servlet request object.
     * @return The list of DTOs representing the retrieved card holders.
     * @throws CardHolderException If an error occurs during card holder retrieval.
     */
    @Override
    public List<CardHolderResponseDTO> getAllCardHolders(HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            List<CardHolder> cardHolders = cardHolderRepository.findAll();
            return cardHolderMapper.toDto(cardHolders);
        } catch (Exception e) {

            throw new CardHolderException("Error getting card holders: " + e.getMessage());
        }
    }

    /**
     * Updates a card holder.
     *
     * @param id The ID of the card holder to update.
     * @param cardHolderRequestDTO The DTO containing information about the card holder to update.
     * @param httpServletRequest The HTTP servlet request object.
     * @return The response DTO indicating the card holder was updated.
     * @throws CardHolderException If an error occurs during card holder update.
     */
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

            throw new CardHolderException("Error updating card holder: " + e.getMessage());
        }
    }

/**
     * Deletes a card holder.
     *
     * @param id The ID of the card holder to delete.
     * @param httpServletRequest The HTTP servlet request object.
     * @throws CardHolderException If an error occurs during card holder deletion.
     */
    @Override
    public void deleteCardHolder(Long id, HttpServletRequest httpServletRequest) {
        try {

            clientInfoService.getLogger(httpServletRequest);
            CardHolder cardHolder = cardHolderRepository.findById(id)
                    .orElseThrow(() -> new CardHolderException("Card Holder not found"));
            cardHolderRepository.delete(cardHolder);
            LOG.info("Card Holder deleted: {}", gson.toJson(cardHolder));
        } catch (Exception e) {

            throw new CardHolderException("Error deleting card holder: " + e.getMessage());
        }
    }


}
