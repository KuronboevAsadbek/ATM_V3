package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.CardTypeRequestDTO;
import uz.atm_v_3.dto.response.CardTypeResponseDTO;
import uz.atm_v_3.model.CardType;

@Mapper(componentModel = "spring")
public interface CardTypeMapper extends EntityMapping<CardType, CardTypeRequestDTO, CardTypeResponseDTO>{
}
