package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.CardHolderRequestDTO;
import uz.atm_v_3.dto.response.CardHolderResponseDTO;
import uz.atm_v_3.model.CardHolder;

@Mapper(componentModel = "spring")
public interface CardHolderMapper extends EntityMapping<CardHolder, CardHolderRequestDTO, CardHolderResponseDTO> {

}
