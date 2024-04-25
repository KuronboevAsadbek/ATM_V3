package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.CurrencyTypeRequestDTO;
import uz.atm_v_3.dto.response.CurrencyTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.service.CurrencyTypeService;

import static uz.atm_v_3.utils.Endpoint.*;
/**
 * This class is a controller class. This class is responsible for handling requests related to currency types.
 * This class contains the following methods:
 * - createCurrency
 * - updateCurrency
 * - deleteCurrency
 * - getAllCurrencies
 */
@RestController
@RequestMapping(CURRENCY_TYPE)
@RequiredArgsConstructor
public class CurrencyTypeController {

    private final CurrencyTypeService currencyTypeService;

    @PostMapping(CREATE)
    public ResponseEntity<CurrencyTypeResponseDTO> createCurrency(
            @RequestBody
            @Valid CurrencyTypeRequestDTO currencyTypeRequestDTO,
            HttpServletRequest request){
        return ResponseEntity.ok(currencyTypeService.creteCurrencyType(currencyTypeRequestDTO, request));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<CurrencyTypeResponseDTO> updateCurrency(
            @RequestParam("currencyId") Long id,
            @RequestBody
            @Valid CurrencyTypeRequestDTO currencyTypeRequestDTO,
            HttpServletRequest request){
        return ResponseEntity.ok(currencyTypeService.updateCurrencyType(currencyTypeRequestDTO, id, request));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<ResponseDTO> deleteCurrency(@RequestParam("currencyId") Long id, HttpServletRequest request){
       return ResponseEntity.ok(currencyTypeService.deleteCurrencyType(id, request));

    }

    @GetMapping(GET_ALL)
    public ResponseEntity<?> getAllCurrencies(HttpServletRequest request){
        return ResponseEntity.ok(currencyTypeService.getAllCurrencyTypes(request));
    }

}
