package uz.atm_v_3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is responsible for creating a response to the card type.
 * It contains the following fields:
 * - id
 * - name
 * - description
 * - number
 * - expirationYear
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardTypeResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String number;

    private String expirationYear;
}
