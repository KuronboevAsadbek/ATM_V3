package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.response.FillOutResponseDTO;

public interface FillOutCardService {


    FillOutResponseDTO fillOutCardBalance(FillOutRequestDTO fillOutRequestDTO,
                                         HttpServletRequest request);
}
