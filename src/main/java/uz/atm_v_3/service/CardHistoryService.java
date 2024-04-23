package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CardHistoryRequestDTO;
import uz.atm_v_3.dto.response.CardHistoryResponseDTO;
import uz.atm_v_3.model.CardHistory;

import java.util.List;

public interface CardHistoryService {

    List<CardHistoryResponseDTO> getCardHistoryByCardNumber(CardHistoryRequestDTO cardHistoryRequestDTO,
                                                            HttpServletRequest httpServletRequest);

    CardHistoryResponseDTO getCardHistoryBySenderCardNumber(CardHistoryRequestDTO cardHistoryRequestDTO,
                                              HttpServletRequest httpServletRequest);

    CardHistoryResponseDTO getCardHistoryByPeriod(CardHistoryRequestDTO cardHistoryRequestDTO,
                                                  String startDate,
                                                  String endDate,
                                              HttpServletRequest httpServletRequest);
    CardHistoryResponseDTO getReceiverCardHistory(CardHistoryRequestDTO cardHistoryRequestDTO,
                                              HttpServletRequest httpServletRequest);
    CardHistoryResponseDTO getSendCardHistory(CardHistoryRequestDTO cardHistoryRequestDTO,
                                                HttpServletRequest httpServletRequest);
}
