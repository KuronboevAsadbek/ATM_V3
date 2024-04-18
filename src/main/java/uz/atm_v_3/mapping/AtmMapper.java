package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.AtmRequestDTO;
import uz.atm_v_3.dto.response.AtmResponseDTO;
import uz.atm_v_3.model.Atm;

@Mapper(componentModel = "spring")
public interface AtmMapper extends EntityMapping<Atm, AtmRequestDTO, AtmResponseDTO> {
}
