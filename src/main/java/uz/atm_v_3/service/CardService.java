package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.request.StatusRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.dto.response.StatusResponseDTO;

public interface CardService {

    ResponseDTO updateCard(Long id, CardRequestDTO cardRequestDTO, HttpServletRequest request);

    CardResponseDTO creatNewCard(CardRequestDTO cardRequestDTO, HttpServletRequest request);

    StatusResponseDTO blockCard(StatusRequestDTO statusRequestDTO, HttpServletRequest request);
}
