package uz.atm_v_3.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.BanknoteTypeRequestDTO;
import uz.atm_v_3.dto.response.BanknoteTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.exception.CashingTypeException;
import uz.atm_v_3.exception.CurrencyTypeException;
import uz.atm_v_3.mapping.BanknoteTypeMapper;
import uz.atm_v_3.model.BanknoteType;
import uz.atm_v_3.model.CashingType;
import uz.atm_v_3.model.CurrencyType;
import uz.atm_v_3.repository.BanknoteTypeRepository;
import uz.atm_v_3.repository.CashingTypeRepository;
import uz.atm_v_3.repository.CurrencyTypeRepository;
import uz.atm_v_3.service.BanknoteTypeService;

/**
 * The BanknoteServiceImpl class encapsulates methods for creating, updating, and deleting banknote types.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class BanknoteServiceImpl implements BanknoteTypeService {

    private final BanknoteTypeRepository banknoteTypeRepository;
    private final BanknoteTypeMapper banknoteTypeMapper;
    private final CashingTypeRepository cashingTypeRepository;
    private final CurrencyTypeRepository currencyTypeRepository;

    /**
     * Creates a new banknote type.
     *
     * @param banknoteTypeRequestDTO The DTO containing information about the banknote type to be created.
     * @param request                The HTTP servlet request object.
     * @return The DTO representing the created banknote type.
     * @throws CashingTypeException If the cashing type is not found.
     */
    @Override
    public BanknoteTypeResponseDTO createBanknote(BanknoteTypeRequestDTO banknoteTypeRequestDTO, HttpServletRequest request) {
        try {
            BanknoteType banknoteType = banknoteTypeMapper.toEntity(banknoteTypeRequestDTO);
            BanknoteType banknoteType1 = banknoteTypeRepository.findByName(banknoteType.getName());
            CashingType cashingType = cashingTypeRepository.findById(banknoteTypeRequestDTO.getCashingTypeId())
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            CurrencyType currencyType = currencyTypeRepository.findById(banknoteTypeRequestDTO.getCurrencyTypeId())
                    .orElseThrow(() -> new CurrencyTypeException("Currency type not found"));

            // Check if the banknote type already exists in the database and update the quantity if it does exist.
            if (banknoteType1 != null) {
                Integer quantity = banknoteType.getQuantity();
                banknoteType1.setQuantity(banknoteType1.getQuantity() + quantity);
                banknoteTypeRepository.save(banknoteType1);
                log.info("Banknote type with name: {} successfully updated", banknoteType1.getName());
                return BanknoteTypeResponseDTO.builder()
                        .cashingTypeName(banknoteType1.getCashingType().getName())
                        .currencyTypeName(banknoteType1.getCurrencyType().getName())
                        .name(banknoteType1.getName())
                        .quantity(banknoteType1.getQuantity())
                        .build();
                // If the banknote type does not exist in the database, create a new banknote type.
            } else {
                banknoteType.setCashingType(cashingType);
                banknoteType.setCurrencyType(currencyType);
                banknoteType = banknoteTypeRepository.save(banknoteType);
                log.info("Banknote type with name: {} successfully created", banknoteType.getName());
                return BanknoteTypeResponseDTO.builder()
                        .id(banknoteType.getId())
                        .cashingTypeName(banknoteType.getCashingType().getName())
                        .currencyTypeName(banknoteType.getCurrencyType().getName())
                        .name(banknoteType.getName())
                        .nominal(banknoteType.getNominal())
                        .quantity(banknoteType.getQuantity())
                        .build();
            }
        } catch (Exception e) {
            throw new CashingTypeException("Cashing type not found");
        }
    }


    /**
     * Retrieves a banknote type by ID.
     *
     * @param id      The ID of the banknote type to retrieve.
     * @param request The HTTP servlet request object.
     * @return The DTO representing the retrieved banknote type.
     * @throws CashingTypeException If the banknote type is not found.
     */

    @Override
    public BanknoteTypeResponseDTO updateBanknote(Long id, BanknoteTypeRequestDTO banknoteTypeRequestDTO, HttpServletRequest request) {

        try {
            BanknoteType banknoteType = banknoteTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Banknote type not found"));
            CashingType cashingType = cashingTypeRepository.findById(banknoteTypeRequestDTO.getCashingTypeId())
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            CurrencyType currencyType = currencyTypeRepository.findById(banknoteTypeRequestDTO.getCurrencyTypeId())
                    .orElseThrow(() -> new CurrencyTypeException("Currency type not found"));

            banknoteType.setCashingType(cashingType);
            banknoteType.setCurrencyType(currencyType);
            banknoteType.setName(banknoteTypeRequestDTO.getName());
            banknoteType.setNominal(banknoteTypeRequestDTO.getNominal());
            banknoteType.setQuantity(banknoteTypeRequestDTO.getQuantity());
            banknoteType = banknoteTypeRepository.save(banknoteType);
            log.info("Banknote type with name: {} successfully updated", banknoteType.getName());
            return BanknoteTypeResponseDTO.builder()
                    .id(banknoteType.getId())
                    .cashingTypeName(banknoteType.getCashingType().getName())
                    .currencyTypeName(banknoteType.getCurrencyType().getName())
                    .name(banknoteType.getName())
                    .nominal(banknoteType.getNominal())
                    .quantity(banknoteType.getQuantity())
                    .build();
        } catch (Exception e) {

            throw new CashingTypeException("Banknote type not found");
        }
    }

    /**
     * Retrieves a banknote type by ID.
     *
     * @param id The ID of the banknote type to retrieve.
     * @param request The HTTP servlet request object.
     * @return The DTO representing the retrieved banknote type.
     * @throws CashingTypeException If the banknote type is not found.
     */
    @Override
    public ResponseDTO deleteBanknote(Long id, HttpServletRequest request) {
        try {
            BanknoteType banknoteType = banknoteTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Banknote type not found"));
            banknoteTypeRepository.delete(banknoteType);
            log.info("Banknote type with name: {} successfully deleted", banknoteType.getName());
            return ResponseDTO.builder()
                    .message("Banknote type successfully deleted")
                    .build();
        } catch (Exception e) {

            throw new CashingTypeException("Banknote type not found: " + e.getMessage());
        }
    }
}
