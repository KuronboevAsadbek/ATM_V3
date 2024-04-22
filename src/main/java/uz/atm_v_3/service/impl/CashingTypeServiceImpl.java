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

@Service
@RequiredArgsConstructor
public class CashingTypeServiceImpl implements CashingTypeService {

    private final CashingTypeRepository cashingTypeRepository;
    private final CashingTypeMapper cashingTypeMapper;
    private final ClientInfoService clientInfoService;

    @Override
    public CashingTypeResponseDTO createCashingType(CashingTypeRequestDTO cashingTypeRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeMapper.toEntity(cashingTypeRequestDTO);
            CashingType cashingType1 = cashingTypeRepository.findByName(cashingTypeRequestDTO.getName());
            if (cashingType1 != null){
                throw new CashingTypeException("Cashing type already exists");
            }else {
                cashingTypeRepository.save(cashingType);
                return cashingTypeMapper.toDto(cashingType);
            }
        }catch (Exception e){
            throw new CashingTypeException(e.getMessage());
        }
    }

    @Override
    public CashingTypeResponseDTO updateCashingType(Long id, CashingTypeRequestDTO cashingTypeRequestDTO, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            CashingType cashingType1 = cashingTypeRepository.findByName(cashingTypeRequestDTO.getName());
            if (cashingType1 != null) {
                throw new CashingTypeException("Cashing type already exists");
            } else {
                cashingType.setName(cashingTypeRequestDTO.getName());
                cashingTypeRepository.save(cashingType);
                return cashingTypeMapper.toDto(cashingType);
            }
        } catch (Exception e) {
            throw new CashingTypeException(e.getMessage());
        }
    }

    @Override
    public CashingTypeResponseDTO deleteCashingType(Long id, HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            CashingType cashingType = cashingTypeRepository.findById(id)
                    .orElseThrow(() -> new CashingTypeException("Cashing type not found"));
            cashingTypeRepository.delete(cashingType);
            return cashingTypeMapper.toDto(cashingType);
        } catch (Exception e) {
            throw new CashingTypeException(e.getMessage());
        }
    }

    @Override
    public List<CashingTypeResponseDTO> getAllCashingType(HttpServletRequest httpServletRequest) {
        try {
            clientInfoService.getLogger(httpServletRequest);
            return cashingTypeMapper.toDto(cashingTypeRepository.findAll());
        } catch (Exception e) {
            throw new CashingTypeException(e.getMessage());
        }
    }
}
