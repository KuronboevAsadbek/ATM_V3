package uz.atm_v_3.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.atm_v_3.dto.request.BanknoteTypeRequestDTO;
import uz.atm_v_3.dto.response.BanknoteTypeResponseDTO;
import uz.atm_v_3.dto.response.ResponseDTO;
import uz.atm_v_3.service.BanknoteTypeService;

import static uz.atm_v_3.utils.Endpoint.*;

/**
* This class is a controller class. This class is responsible for handling requests related to banknote types.
* This class contains the following methods:
* - createBanknoteType
* - deleteBanknoteType
* - updateBanknoteType
*
 */
@RestController
@RequestMapping(BANKNOTE_TYPE)
@RequiredArgsConstructor
public class BanknoteTypeController {

    private final BanknoteTypeService banknoteTypeService;

    @PostMapping(CREATE)
    public ResponseEntity<BanknoteTypeResponseDTO> createBanknoteType(@RequestBody BanknoteTypeRequestDTO banknoteTypeRequestDTO,
                                                                      HttpServletRequest request) {
        return ResponseEntity.ok(banknoteTypeService.createBanknote(banknoteTypeRequestDTO, request));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<ResponseDTO> deleteBanknoteType(@RequestParam Long id, HttpServletRequest request) {
        return ResponseEntity.ok(banknoteTypeService.deleteBanknote(id, request));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<BanknoteTypeResponseDTO> updateBanknoteType(@RequestParam Long id,
                                                                      @RequestBody BanknoteTypeRequestDTO banknoteTypeRequestDTO,
                                                                      HttpServletRequest request) {
        return ResponseEntity.ok(banknoteTypeService.updateBanknote(id, banknoteTypeRequestDTO, request));
    }
}
