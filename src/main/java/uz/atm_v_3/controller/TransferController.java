package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.TransferRequestDTO;
import uz.atm_v_3.dto.response.TransferResponseDTO;
import uz.atm_v_3.service.TransferMoneyService;

import static uz.atm_v_3.utils.Endpoint.CARD;
import static uz.atm_v_3.utils.Endpoint.TRANSFER;
/**
 * This class is a controller class. This class is responsible for handling requests related to transferring money.
 * This class contains the following methods:
 * - transferMoneyFromCardToCard
 */
@RestController
@RequestMapping(TRANSFER)
@RequiredArgsConstructor
public class TransferController {

    private final TransferMoneyService transferMoneyService;

    @PostMapping(CARD)
    public ResponseEntity<TransferResponseDTO> transferMoneyFromCardToCard(@RequestBody TransferRequestDTO transferRequestDTO,
                                                                           HttpServletRequest request) {
        return ResponseEntity.ok(transferMoneyService.transferMoney(transferRequestDTO, request));
    }
}
