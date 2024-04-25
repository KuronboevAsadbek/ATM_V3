package uz.atm_v_3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * This class is responsible for creating a response to cashing.
 * It contains the following fields:
 * - message
 * - commission
 * - balance
 * - cashedAmount
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashingResponseDTO {

    private String message;
    private String commission;
    private String balance;
    private String cashedAmount;
}
