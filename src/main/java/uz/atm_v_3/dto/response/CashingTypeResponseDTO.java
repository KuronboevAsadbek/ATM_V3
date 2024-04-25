package uz.atm_v_3.dto.response;


import lombok.*;

/**
 * This class is responsible for creating a response to the cashing type.
 * It contains the following fields:
 * - id
 * - name
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashingTypeResponseDTO {

    private Long id;

    private String name;
}
