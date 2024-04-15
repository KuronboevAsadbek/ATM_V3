package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.service.CardService;

import static uz.atm_v_3.utils.Endpoint.CARD;
import static uz.atm_v_3.utils.Endpoint.CREATE;

@RestController
@RequiredArgsConstructor
@RequestMapping(CARD)
public class CardController {

    private final CardService cardService;

    @PostMapping(CREATE)
    public ResponseEntity<CardResponseDTO> createCard(@RequestBody CardRequestDTO cardRequestDTO,
                                                      HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.createCard(cardRequestDTO, httpServletRequest));
    }
}
