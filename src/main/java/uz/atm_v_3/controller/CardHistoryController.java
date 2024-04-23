package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.atm_v_3.dto.request.CardHistoryRequestDTO;
import uz.atm_v_3.dto.response.CardHistoryResponseDTO;
import uz.atm_v_3.service.CardHistoryService;

import java.util.List;

import static uz.atm_v_3.utils.Endpoint.CARD_HISTORY;
import static uz.atm_v_3.utils.Endpoint.GET_ALL;

@RestController
@RequiredArgsConstructor
@RequestMapping(CARD_HISTORY)
public class CardHistoryController {

    private final CardHistoryService cardHistoryService;

    @GetMapping(GET_ALL)
    public ResponseEntity<List<CardHistoryResponseDTO>> getAllCardHistories(@RequestBody CardHistoryRequestDTO cardHistoryRequestDTO,
                                                                            HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardHistoryService.getCardHistoryByCardNumber(cardHistoryRequestDTO, httpServletRequest));
    }
}
