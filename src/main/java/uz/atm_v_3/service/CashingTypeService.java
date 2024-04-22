package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CashingTypeRequestDTO;
import uz.atm_v_3.dto.response.CashingTypeResponseDTO;

import java.util.List;

public interface CashingTypeService {

    CashingTypeResponseDTO createCashingType(CashingTypeRequestDTO cashingTypeRequestDTO,
                                             HttpServletRequest httpServletRequest);
    CashingTypeResponseDTO updateCashingType(Long id, CashingTypeRequestDTO cashingTypeRequestDTO,
                                             HttpServletRequest httpServletRequest);
    CashingTypeResponseDTO deleteCashingType(Long id, HttpServletRequest httpServletRequest);

    List<CashingTypeResponseDTO> getAllCashingType(HttpServletRequest httpServletRequest);

}
