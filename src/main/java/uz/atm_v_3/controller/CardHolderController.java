package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CardHolderRequestDTO;
import uz.atm_v_3.dto.response.CardHolderResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.service.CardHolderService;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to card holders.
 * This class contains the following methods:
 * - createCardHolder
 * - updateCardHolder
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(CARD_HOLDER)
public class CardHolderController {

    private final CardHolderService cardHolderService;

    @PostMapping(CREATE)
    public ResponseEntity<CardHolderResponseDTO> createCardHolder(@RequestBody @Valid CardHolderRequestDTO cardHolderRequestDTO,
                                                                  HttpServletRequest request) {
        return ResponseEntity.ok(cardHolderService.createCardHolder(cardHolderRequestDTO, request));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ResponseDTO> updateCardHolder(@RequestParam("cardholderid") Long id,
                                                        @RequestBody @Valid CardHolderRequestDTO cardHolderRequestDTO,
                                                        HttpServletRequest request) {
        return ResponseEntity.ok(cardHolderService.updateCardHolder(id, cardHolderRequestDTO, request));
    }
}
