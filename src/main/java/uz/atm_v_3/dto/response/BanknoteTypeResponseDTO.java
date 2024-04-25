package uz.atm_v_3.dto.response;

import lombok.*;

/**
 * This class is responsible for creating a response to the banknote type.
 * It contains the following fields:
 * - id
 * - name
 * - quantity
 * - nominal
 * - cashingTypeName
 * - currencyTypeName
 */
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
