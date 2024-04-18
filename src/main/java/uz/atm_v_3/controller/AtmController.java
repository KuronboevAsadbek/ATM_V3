package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.AtmRequestDTO;
import uz.atm_v_3.dto.response.AtmResponseDTO;
import uz.atm_v_3.service.AtmService;

import static uz.atm_v_3.utils.Endpoint.ADD;
import static uz.atm_v_3.utils.Endpoint.ATM;

@RequiredArgsConstructor
@RestController
@RequestMapping(ATM)
public class AtmController {

    private final AtmService atmService;

    @PostMapping(ADD)
    public ResponseEntity<AtmResponseDTO> addBanknotes(@RequestBody AtmRequestDTO atmRequestDTO,
                                                       HttpServletRequest request) {
        return ResponseEntity.ok(atmService.addBanknotes(atmRequestDTO, request));
    }

}
