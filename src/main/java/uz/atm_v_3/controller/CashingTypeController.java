package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CashingTypeRequestDTO;
import uz.atm_v_3.dto.response.CashingResponseDTO;
import uz.atm_v_3.dto.response.CashingTypeResponseDTO;
import uz.atm_v_3.service.CashingTypeService;

import java.util.List;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to cashing types.
 * This class contains the following methods:
 * - createCashingType
 * - updateCashingType
 * - deleteCashingType
 * - getAllCashingType
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(CASHING_TYPE)
public class CashingTypeController {

    private final CashingTypeService cashingTypeService;

    @PostMapping(CREATE)
    public ResponseEntity<CashingTypeResponseDTO> createCashingType(@RequestBody CashingTypeRequestDTO cashingTypeRequestDTO,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(cashingTypeService.createCashingType(cashingTypeRequestDTO, request));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CashingTypeResponseDTO> updateCashingType(@RequestParam Long id,
                                                                    @RequestBody CashingTypeRequestDTO cashingTypeRequestDTO,
                                                                    HttpServletRequest request) {
        return ResponseEntity.ok(cashingTypeService.updateCashingType(id, cashingTypeRequestDTO, request));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<CashingTypeResponseDTO> deleteCashingType(@RequestParam Long id, HttpServletRequest request) {
        return ResponseEntity.ok(cashingTypeService.deleteCashingType(id, request));
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<CashingTypeResponseDTO>> getAllCashingType(HttpServletRequest request) {
        return ResponseEntity.ok(cashingTypeService.getAllCashingType(request));
    }
}
