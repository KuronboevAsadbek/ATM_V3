package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CurrencyTypeRequestDTO;
import uz.atm_v_3.dto.response.CurrencyTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.exception.CurrencyTypeException;
import uz.atm_v_3.mapping.CurrencyTypeMapper;
import uz.atm_v_3.model.CurrencyType;
import uz.atm_v_3.repository.CurrencyTypeRepository;
import uz.atm_v_3.service.CurrencyTypeService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;
import java.util.List;

/**
 * The CurrencyTypeServiceImpl class encapsulates methods for creating, updating, and deleting currency types and getting all currency types.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyTypeServiceImpl implements CurrencyTypeService {

    private final CurrencyTypeRepository currencyTypeRepository;
    private final Gson gson;
    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);
    private final ClientInfoService clientInfoService;
    private final CurrencyTypeMapper currencyTypeMapper;

    /**
     * Creates a new currency type.
     *
     * @param currencyTypeRequestDTO The DTO containing information about the currency type to be created.
     * @param request                The HTTP servlet request object.
     * @return The DTO representing the created currency type.
     * @throws CurrencyTypeException If an error occurs during currency type creation.
     */
    @Override
    public CurrencyTypeResponseDTO creteCurrencyType(CurrencyTypeRequestDTO currencyTypeRequestDTO, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            CurrencyType currencyType = currencyTypeMapper.toEntity(currencyTypeRequestDTO);
            currencyTypeRepository.save(currencyType);
            LOG.info("Currency Type created: {}", gson.toJson(currencyType));
            return currencyTypeMapper.toDto(currencyType);
        } catch (Exception e) {

            throw new CurrencyTypeException("Error creating currency type: " + e.getMessage());
        }
    }

    /**
     * Updates a currency type.
     *
     * @param currencyTypeRequestDTO The DTO containing information about the currency type to be updated.
     * @param id                     The ID of the currency type to be updated.
     * @param request                The HTTP servlet request object.
     * @return The DTO representing the updated currency type.
     * @throws CurrencyTypeException If the currency type is not found.
     */
    @Override
    public CurrencyTypeResponseDTO updateCurrencyType(CurrencyTypeRequestDTO currencyTypeRequestDTO, Long id, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            CurrencyType currencyType = currencyTypeRepository.findById(id)
                    .orElseThrow(() -> new CurrencyTypeException("Currency Type not found"));
            currencyTypeMapper.updateFromDto(currencyTypeRequestDTO, currencyType);
            currencyTypeRepository.save(currencyType);
            LOG.info("Currency Type updated: {}", gson.toJson(currencyType));
            return currencyTypeMapper.toDto(currencyType);
        } catch (Exception e) {

            throw new CurrencyTypeException("Error updating currency type: " + e.getMessage());
        }
    }

    /**
     * Deletes a currency type.
     *
     * @param id      The ID of the currency type to be deleted.
     * @param request The HTTP servlet request object.
     * @return The DTO representing the deleted currency type.
     * @throws CurrencyTypeException If the currency type is not found.
     */
    @Override
    public ResponseDTO deleteCurrencyType(Long id, HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            CurrencyType currencyType = currencyTypeRepository.findById(id)
                    .orElseThrow(() -> new CurrencyTypeException("Currency Type not found"));
            currencyTypeRepository.delete(currencyType);
            LOG.info("Currency Type deleted: {}", gson.toJson(currencyType));
            return new ResponseDTO("Currency Type deleted");
        } catch (Exception e) {

            throw new CurrencyTypeException("Error deleting currency type: " + e.getMessage());
        }
    }

    /**
     * Gets all currency types.
     *
     * @param request The HTTP servlet request object.
     * @return The list of DTOs representing the currency types.
     * @throws CurrencyTypeException If the currency types are not found.
     */
    @Override
    public List<CurrencyTypeResponseDTO> getAllCurrencyTypes(HttpServletRequest request) {
        try {
            clientInfoService.getLogger(request);
            List<CurrencyType> currencyTypes = currencyTypeRepository.findAll();
            return currencyTypeMapper.toDto(currencyTypes);
        } catch (Exception e) {

            throw new CurrencyTypeException("Error getting currency types: " + e.getMessage());

        }
    }
}
