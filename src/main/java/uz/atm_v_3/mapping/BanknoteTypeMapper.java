package uz.atm_v_3.mapping;

import org.mapstruct.Mapper;
import uz.atm_v_3.dto.request.BanknoteTypeRequestDTO;
import uz.atm_v_3.dto.response.BanknoteTypeResponseDTO;
import uz.atm_v_3.model.BanknoteType;

@Mapper(componentModel = "spring")
public interface BanknoteTypeMapper extends EntityMapping<BanknoteType, BanknoteTypeRequestDTO, BanknoteTypeResponseDTO>{
}
