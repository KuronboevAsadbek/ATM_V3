package uz.atm_v_3.service;


import jakarta.servlet.http.HttpServletRequest;
import uz.atm_v_3.dto.request.BanknoteTypeRequestDTO;
import uz.atm_v_3.dto.response.BanknoteTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;

public interface BanknoteTypeService {

    BanknoteTypeResponseDTO createBanknote(BanknoteTypeRequestDTO banknoteTypeRequestDTO,
                                           HttpServletRequest request);

    BanknoteTypeResponseDTO updateBanknote(Long id, BanknoteTypeRequestDTO banknoteTypeRequestDTO,
                                           HttpServletRequest request);

    ResponseDTO deleteBanknote(Long id, HttpServletRequest request);
}
