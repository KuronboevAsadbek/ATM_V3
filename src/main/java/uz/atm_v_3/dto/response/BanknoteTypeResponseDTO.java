package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BanknoteTypeResponseDTO {

    private Long id;

    private String name;

    private Integer quantity;

    private Integer nominal;

    private String cashingTypeName;

    private String currencyTypeName;
}
