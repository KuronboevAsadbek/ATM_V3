package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.service.CardService;

import java.util.List;

import static uz.atm_v_3.utils.Endpoint.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CARD)
public class CardController {

    private final CardService cardService;

    @PostMapping(CREATE)
    public ResponseEntity<CardResponseDTO> createCard(@RequestBody @Valid CardRequestDTO cardRequestDTO,
                                                      HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.createCard(cardRequestDTO, httpServletRequest));
    }

    @GetMapping(GET_CARD_BY_ID)
    public ResponseEntity<CardResponseDTO> getCard(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.getCard(id, httpServletRequest));
    }

    @GetMapping(GET_ALL_CARDS)
    public ResponseEntity<List<CardResponseDTO>> getAllCards(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.getAllCards(httpServletRequest));
    }

    @GetMapping(GET_ALL_CARDS_BY_CARD_HOLDER_PINFL)
    public ResponseEntity<List<CardResponseDTO>> getAllCardsByCardHolderPINFL(@RequestParam("pinfl") String pinFL,
                                                                             HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.getAllCardsByCardHolderPINFL(pinFL, httpServletRequest));
    }

    @PostMapping(FILL_OUT_BALANCE)
    public ResponseEntity<ResponseDTO> fillOutBalance(@RequestParam("cardnumber") String cardNumber,
                                                      @RequestParam("amount") String amount,
                                                      HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.fillCardBalance(cardNumber, amount, httpServletRequest));
    }
}
