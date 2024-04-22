package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.CashingTypeRequestDTO;
import uz.atm_v_3.dto.response.CashingTypeResponseDTO;
import uz.atm_v_3.model.CashingType;

@Mapper(componentModel = "spring")
public interface CashingTypeMapper extends EntityMapping<CashingType, CashingTypeRequestDTO, CashingTypeResponseDTO>{
}
