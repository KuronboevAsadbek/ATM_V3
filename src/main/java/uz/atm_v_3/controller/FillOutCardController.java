package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.FillOutRequestDTO;
import uz.atm_v_3.dto.response.FillOutResponseDTO;
import uz.atm_v_3.service.FillOutCardService;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to filling out cards.
 * This class contains the following methods:
 * - fillOutCard
 */
@RequiredArgsConstructor
@RequestMapping(FILL_OUT_CARD)
@RestController
public class FillOutCardController {

    private final FillOutCardService fillOutCardService;

    @PostMapping(OUT)
    public ResponseEntity<FillOutResponseDTO> fillOutCard(@RequestBody FillOutRequestDTO fillOutRequestDTO,
                                                          HttpServletRequest request) {
        return ResponseEntity.ok(fillOutCardService.fillOutCardBalance(fillOutRequestDTO, request));
    }

}
