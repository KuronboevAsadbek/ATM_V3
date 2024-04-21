package uz.atm_v_3.service.impl;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uz.atm_v_3.dto.request.AtmRequestDTO;
import uz.atm_v_3.dto.response.AtmResponseDTO;
import uz.atm_v_3.mapping.AtmMapper;
import uz.atm_v_3.model.Atm;
import uz.atm_v_3.model.CurrencyType;
import uz.atm_v_3.repository.AtmRepository;
import uz.atm_v_3.repository.CurrencyTypeRepository;
import uz.atm_v_3.service.AtmService;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmServiceImpl implements AtmService {

    private final static Logger LOG = LoggerFactory.getLogger(CardHolderServiceImpl.class);
    private final Gson gson;
    private final ClientInfoService clientInfoService;
    private final AtmRepository atmRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final AtmMapper atmMapper;

    @Override
    public AtmResponseDTO addBanknotes(AtmRequestDTO atmRequestDTO, HttpServletRequest request) {
        Atm atm = atmMapper.toEntity(atmRequestDTO);
        CurrencyType currencyType = currencyTypeRepository.findById(atmRequestDTO.getCurrencyTypeId())
                .orElseThrow(() -> new RuntimeException("Currency type not found"));
        Atm atm1 = atmRepository.findBanknoteByType(atmRequestDTO.getBanknoteType());
        clientInfoService.getLogger(request);
        if (atm1 != null) {
            Integer banknoteCount = atm1.getBanknoteCount() + atmRequestDTO.getBanknoteCount();
            atm1.setBanknoteCount(banknoteCount);
            LOG.info("Banknotes updated: {}", gson.toJson(atm));
            atmRepository.save(atm1);
            return AtmResponseDTO.builder()
                    .banknoteType(atmRequestDTO.getBanknoteType())
                    .message("Banknotes updated")
                    .banknoteCount(banknoteCount)
                    .build();
        } else {
            atm.setCurrencyType(currencyType);
            atmRepository.save(atm);
            LOG.info("Banknotes added: {}", gson.toJson(atm));
            return AtmResponseDTO.builder()
                    .banknoteType(atmRequestDTO.getBanknoteType())
                    .message("Banknotes added")
                    .banknoteCount(atmRequestDTO.getBanknoteCount())
                    .build();
        }
    }
}
