package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.CurrencyTypeRequestDTO;
import uz.atm_v_3.dto.response.CurrencyTypeResponseDTO;
import uz.atm_v_3.model.CurrencyType;

@Mapper(componentModel = "spring")
public interface CurrencyTypeMapper extends EntityMapping<CurrencyType, CurrencyTypeRequestDTO, CurrencyTypeResponseDTO>{
}
