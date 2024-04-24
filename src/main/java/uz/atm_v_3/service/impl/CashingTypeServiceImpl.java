package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.CashingTypeRequestDTO;
import uz.atm_v_3.dto.response.CashingTypeResponseDTO;
import uz.atm_v_3.exception.CashingTypeException;
import uz.atm_v_3.mapping.CashingTypeMapper;
import uz.atm_v_3.model.CashingType;
import uz.atm_v_3.repository.CashingTypeRepository;
import uz.atm_v_3.service.CashingTypeService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;
import java.util.List;

/**
 * The CashingTypeServiceImpl class encapsulates methods for creating, updating, and deleting cashing types.
 */

@Service
@RequiredArgsConstructor
public class CashingTypeServiceImpl implements CashingTypeService {

    private final CashingTypeRepository cashingTypeRepository;
    private final CashingTypeMapper cashingTypeMapper;
    private final ClientInfoService clientInfoService;

    /**
     * Creates a new cashing type.
     *
     * @param cashingTypeRequestDTO The DTO containing information about the cashing type to be created.
     * @param httpServletRequest     The HTTP servlet request object.
     * @return The DTO representing the created cashing type.
     * @throws CashingTypeException If the cashing type is not found.
     */
    @Override
    public CashingTypeResponseDTO createCashingType(CashingTypeRequestDTO cashingTypeRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeMapper.toEntity(cashingTypeRequestDTO);
            CashingType cashingType1 = cashingTypeRepository.findByName(cashingTypeRequestDTO.getName());
            // Check if the cashing type already exists in the database and throw an exception if it does.
            if (cashingType1 != null){
                throw new CashingTypeException("Cashing type already exists");
            }else {
                cashingTypeRepository.save(cashingType);
                return cashingTypeMapper.toDto(cashingType);
            }
        }catch (Exception e){
            throw new CashingTypeException("Cashing type not created: " + e.getMessage());
        }
    }

    /**
     * Updates a cashing type.
     *
     * @param id                   The ID of the cashing type to be updated.
     * @param cashingTypeRequestDTO The DTO containing information about the cashing type to be updated.
     * @param httpServletRequest     The HTTP servlet request object.
     * @return The DTO representing the updated cashing type.
     * @throws CashingTypeException If the cashing type is not found.
     */
    @Override
    public CashingTypeResponseDTO updateCashingType(Long id, CashingTypeRequestDTO cashingTypeRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            CashingType cashingType1 = cashingTypeRepository.findByName(cashingTypeRequestDTO.getName());
            // Check if the cashing type already exists in the database and throw an exception if it does.
            if (cashingType1 != null) {
                throw new CashingTypeException("Cashing type already exists");
            } else {
                cashingType.setName(cashingTypeRequestDTO.getName());
                cashingTypeRepository.save(cashingType);
                return cashingTypeMapper.toDto(cashingType);
            }
        } catch (Exception e) {
            throw new CashingTypeException("Cashing type not updated: " + e.getMessage());
        }
    }

    /**
     * Deletes a cashing type.
     *
     * @param id                   The ID of the cashing type to be deleted.
     * @param httpServletRequest     The HTTP servlet request object.
     * @return The DTO representing the deleted cashing type.
     * @throws CashingTypeException If the cashing type is not found.
     */
    @Override
    public CashingTypeResponseDTO deleteCashingType(Long id, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            cashingTypeRepository.delete(cashingType);
            return cashingTypeMapper.toDto(cashingType);
        } catch (Exception e) {
            throw new CashingTypeException("Cashing type not deleted: " + e.getMessage());
        }
    }

    /**
     * Retrieves all cashing types.
     *
     * @param httpServletRequest The HTTP servlet request object.
     * @return The list of DTOs representing the retrieved cashing types.
     * @throws CashingTypeException If the cashing types are not found.
     */
    @Override
    public List<CashingTypeResponseDTO> getAllCashingType(HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            return cashingTypeMapper.toDto(cashingTypeRepository.findAll());
        } catch (Exception e) {
            throw new CashingTypeException("Cashing types not found: " + e.getMessage());
        }
    }
}
