package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

import java.util.List;

public interface CardService {

    CardResponseDTO createCard(CardRequestDTO cardRequestDTO, HttpServletRequest request);
    CardResponseDTO getCard(Long id, HttpServletRequest request);
    List<CardResponseDTO> getAllCards(HttpServletRequest request);
    List<CardResponseDTO> getAllCardsByCardHolderPINFL(String pinFL, HttpServletRequest request);
    List<CardResponseDTO> getAllCardsByCardHolderPassportSeriesAndNumber(String passportSeries, String passportNumber,
                                                                         HttpServletRequest request);
    ResponseDTO updateCard(Long id, CardRequestDTO cardRequestDTO, HttpServletRequest request);

}
