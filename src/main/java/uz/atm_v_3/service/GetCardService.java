package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.response.CardResponseDTO;

import java.util.List;

public interface GetCardService {

    CardResponseDTO getCard(Long id, HttpServletRequest request);

    List<CardResponseDTO> getAllCards(HttpServletRequest request);

    List<CardResponseDTO> getAllCardsByCardHolderPINFL(String pinFL, HttpServletRequest request);

    List<CardResponseDTO> getAllCardsByCardHolderPassportSeriesAndNumber(String passportSeries, String passportNumber,
                                                                         HttpServletRequest request);
}
