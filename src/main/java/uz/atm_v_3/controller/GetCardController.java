package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.service.GetCardService;

import java.util.List;


import static uz.atm_v_3.utils.Endpoint.*;

@RestController
@RequestMapping(GET)
@RequiredArgsConstructor
public class GetCardController {

    private final GetCardService getCardService;

    @GetMapping(GET_ALL_CARDS)
    public ResponseEntity<List<CardResponseDTO>> getAllCards(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(getCardService.getAllCards(httpServletRequest));
    }

    @GetMapping(GET_ALL_CARDS_BY_CARD_HOLDER_PINFL)
    public ResponseEntity<List<CardResponseDTO>> getAllCardsByHolderPINFL(@RequestParam("pinfl") String pinfl,
                                                                          HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(getCardService.getAllCardsByCardHolderPINFL(pinfl, httpServletRequest));
    }

    @GetMapping(GET_CARD_BY_ID)
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(getCardService.getCard(id, httpServletRequest));
    }

}
