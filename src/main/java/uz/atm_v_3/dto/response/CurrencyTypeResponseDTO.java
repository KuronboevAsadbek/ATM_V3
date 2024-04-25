package uz.atm_v_3.dto.response;

import lombok.*;

/**
 * This class is responsible for creating a response to the currency type.
 * It contains the following fields:
 * - id
 * - name
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyTypeResponseDTO {

    private Long id;
    private String name;
}
