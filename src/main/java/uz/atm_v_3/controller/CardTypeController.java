package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CardTypeRequestDTO;
import uz.atm_v_3.dto.response.CardTypeResponseDTO;
import uz.atm_v_3.service.CardTypeService;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to card types.
 * This class contains the following methods:
 * - createCardType
 * - updateCardType
 * - deleteCardType
 * - getAllCardTypes
 */
@RestController
@RequestMapping(CARD_TYPE)
@RequiredArgsConstructor
public class CardTypeController {

    private final CardTypeService cardTypeService;

    @PostMapping(CREATE)
    public ResponseEntity<CardTypeResponseDTO> createCardType(@RequestBody CardTypeRequestDTO cardTypeRequestDTO,
                                                              HttpServletRequest request) {
        return ResponseEntity.ok(cardTypeService.createCardType(cardTypeRequestDTO, request));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CardTypeResponseDTO> updateCardType(@RequestBody CardTypeRequestDTO cardTypeRequestDTO,
                                                              @RequestParam Long id,
                                                              HttpServletRequest request) {
        return ResponseEntity.ok(cardTypeService.updateCardType(cardTypeRequestDTO, id, request));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<?> deleteCardType(@RequestParam Long id, HttpServletRequest request) {
        cardTypeService.deleteCardType(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<?> getAllCardTypes(HttpServletRequest request) {
        return ResponseEntity.ok(cardTypeService.getAllCardTypes(request));
    }
}
