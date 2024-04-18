package uz.atm_v_3.service;

import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.TransferRequestDTO;
import uz.atm_v_3.dto.response.TransferResponseDTO;

public interface TransferMoneyService {

    TransferResponseDTO transferMoney(TransferRequestDTO transferRequestDTO, HttpServletRequest request);

}
