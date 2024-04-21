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

import static uz.atm_v_3.utils.Endpoint.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CARD)
public class CardController {

    private final CardService cardService;

//    @PostMapping(CREATE)
//    public ResponseEntity<CardResponseDTO> createCard(@RequestBody @Valid CardRequestDTO cardRequestDTO,
//                                                      HttpServletRequest httpServletRequest) {
//        return ResponseEntity.ok(cardService.createCard(cardRequestDTO, httpServletRequest));
//    }

    @PutMapping(UPDATE)
    public ResponseEntity<ResponseDTO> updateCard(@PathVariable Long id,
                                                  @RequestBody @Valid CardRequestDTO cardRequestDTO,
                                                  HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.updateCard(id, cardRequestDTO, httpServletRequest));
    }

    @PostMapping(CREATE)
    public ResponseEntity<CardResponseDTO> createCard(@RequestBody @Valid CardRequestDTO cardRequestDTO,
                                                      HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardService.creatNewCard(cardRequestDTO, httpServletRequest));
    }
}
