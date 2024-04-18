package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;

public interface CashingService {

    CashingResponseDTO cashingCardBalanceWithBanknote(CashingRequestDTO cashingOrFillOutRequestDTO
            , HttpServletRequest request);


}
