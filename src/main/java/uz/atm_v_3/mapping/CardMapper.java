package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.CardRequestDTO;
import uz.atm_v_3.dto.response.CardResponseDTO;
import uz.atm_v_3.model.Card;

@Mapper(componentModel = "spring")
public interface CardMapper extends EntityMapping<Card, CardRequestDTO, CardResponseDTO>{

}
