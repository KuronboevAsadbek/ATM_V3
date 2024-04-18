package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.AtmRequestDTO;
import uz.atm_v_3.dto.response.AtmResponseDTO;

public interface AtmService {

    AtmResponseDTO addBanknotes(AtmRequestDTO atmRequestDTO, HttpServletRequest request);

}
