package uz.atm_v_3.service.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import uz.atm_v_3.dto.request.CurrencyTypeRequestDTO;
import uz.atm_v_3.dto.response.CurrencyTypeResponseDTO;
import uz.atm_v_3.mapping.CurrencyTypeMapper;
import uz.atm_v_3.model.CurrencyType;
import uz.atm_v_3.repository.CurrencyTypeRepository;
import uz.atm_v_3.service.checkAndInfo.ClientInfoService;
import uz.atm_v_3.service.impl.CurrencyTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
 class CurrencyServiceTest {

    @Mock
    private CurrencyTypeRepository currencyTypeRepository;

    @Mock
    private CurrencyTypeMapper currencyTypeMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @InjectMocks
    private CurrencyTypeServiceImpl currencyTypeService;

//    @InjectMocks
//    private ClientInfoService clientInfoService;

    @Test
    public void shouldCreateCurrency(){

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        Mockito.when(clientInfoService.getLogger(request)).thenReturn("dummy log");
        CurrencyTypeRequestDTO currencyTypeRequestDTO = new CurrencyTypeRequestDTO("tanos");
        CurrencyType currencyType = new CurrencyType();
        CurrencyTypeResponseDTO expectedResponseDTO = new CurrencyTypeResponseDTO();

        Mockito.when(currencyTypeMapper.toEntity(currencyTypeRequestDTO)).thenReturn(currencyType);
        Mockito.when(currencyTypeRepository.save(currencyType)).thenReturn(currencyType);
        Mockito.when(currencyTypeMapper.toDto(currencyType)).thenReturn(expectedResponseDTO);

        CurrencyTypeResponseDTO responseDTO = currencyTypeService.creteCurrencyType(currencyTypeRequestDTO, httpServletRequest);


        Assertions.assertEquals(responseDTO.getName(), currencyType.getName());
        Mockito.verify(currencyTypeRepository, Mockito.times(1)).save(currencyType);
        Mockito.verify(currencyTypeMapper, Mockito.times(1)).toEntity(currencyTypeRequestDTO);
        Mockito.verify(currencyTypeMapper, Mockito.times(1)).toDto(currencyType);
//        Mockito.verify(clientInfoService, Mockito.times(1)).getLogger(httpServletRequest);

    }
}
