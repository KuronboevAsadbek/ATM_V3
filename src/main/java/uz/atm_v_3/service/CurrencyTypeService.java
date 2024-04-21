package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CurrencyTypeRequestDTO;
import uz.atm_v_3.dto.response.CurrencyTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

import java.util.List;

public interface CurrencyTypeService {

    CurrencyTypeResponseDTO creteCurrencyType(CurrencyTypeRequestDTO currencyTypeRequestDTO, HttpServletRequest request);
    CurrencyTypeResponseDTO updateCurrencyType(CurrencyTypeRequestDTO currencyTypeRequestDTO, Long id, HttpServletRequest request);
    ResponseDTO deleteCurrencyType(Long id, HttpServletRequest request);
    List<CurrencyTypeResponseDTO> getAllCurrencyTypes(HttpServletRequest request);
}
