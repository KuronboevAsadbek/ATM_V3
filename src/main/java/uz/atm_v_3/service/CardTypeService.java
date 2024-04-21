package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardTypeRequestDTO;
import uz.atm_v_3.dto.response.CardTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

import java.util.List;

public interface CardTypeService {

    CardTypeResponseDTO createCardType(CardTypeRequestDTO cardTypeRequestDTO, HttpServletRequest httpServletRequest);
    CardTypeResponseDTO updateCardType(CardTypeRequestDTO cardTypeRequestDTO, Long id, HttpServletRequest httpServletRequest);
    ResponseDTO deleteCardType(Long id, HttpServletRequest httpServletRequest);
    List<CardTypeResponseDTO> getAllCardTypes(HttpServletRequest httpServletRequest);
}
