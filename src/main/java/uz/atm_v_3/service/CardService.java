package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

public interface CardService {

    CardResponseDTO createCard(CardRequestDTO cardRequestDTO, HttpServletRequest request);
    ResponseDTO updateCard(Long id, CardRequestDTO cardRequestDTO, HttpServletRequest request);

    CardResponseDTO creatNewCard(CardRequestDTO cardRequestDTO, HttpServletRequest request);

}
