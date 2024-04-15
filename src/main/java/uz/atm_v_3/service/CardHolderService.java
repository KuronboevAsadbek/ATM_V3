package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardHolderRequestDTO;
import uz.atm_v_3.dto.response.CardHolderResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

import java.util.List;

public interface CardHolderService {

    CardHolderResponseDTO createCardHolder(CardHolderRequestDTO cardHolderRequestDTO, HttpServletRequest request);
    CardHolderResponseDTO getCardHolder(Long id, HttpServletRequest request);
    List<CardHolderResponseDTO> getAllCardHolders(HttpServletRequest request);
    ResponseDTO updateCardHolder(Long id, CardHolderRequestDTO cardHolderRequestDTO, HttpServletRequest request);

    void deleteCardHolder(Long id, HttpServletRequest request);
}

