package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CardHistoryRequestDTO;
import uz.atm_v_3.dto.response.CardHistoryResponseDTO;
import uz.atm_v_3.service.CardHistoryService;

import java.util.List;

import static uz.atm_v_3.utils.Endpoint.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CARD_HISTORY)
public class CardHistoryController {

    private final CardHistoryService cardHistoryService;

    @GetMapping(GET_ALL)
    public ResponseEntity<List<CardHistoryResponseDTO>> getAllCardHistories(@RequestBody CardHistoryRequestDTO
                                                                                        cardHistoryRequestDTO,
                                                                            HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardHistoryService.getCardHistoryByCardNumber(cardHistoryRequestDTO, httpServletRequest));
    }

    @GetMapping(BY_PERIOD)
    public ResponseEntity<List<CardHistoryResponseDTO>> getAllCardHistories(@RequestBody CardHistoryRequestDTO cardHistoryRequestDTO,
                                                                            @RequestParam("start") String startDate,
                                                                            @RequestParam("end") String endDate,
                                                                            HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(cardHistoryService.getCardHistoryByPeriod(cardHistoryRequestDTO, startDate,
                endDate, httpServletRequest));
    }
}
