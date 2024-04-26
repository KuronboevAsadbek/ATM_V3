package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.CashingRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;
import uz.atm_v_3.service.CashingService;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to cashing.
 * This class contains the following methods:
 * - cashingMoneyWithBanknote
 */
@RequestMapping(CASHING)
@RestController
@RequiredArgsConstructor
public class CashingController {

    private final CashingService cashingService;

    @PostMapping(IN)
    public ResponseEntity<CashingResponseDTO> cashingMoneyWithBanknote(@RequestBody CashingRequestDTO cashingRequestDTO,
                                                                       HttpServletRequest request) {
        return ResponseEntity.ok(cashingService.cashingCard(cashingRequestDTO, request));
    }


}
