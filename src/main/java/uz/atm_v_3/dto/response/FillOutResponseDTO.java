package uz.atm_v_3.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * This class is responsible for creating a response to fill out.
 * It contains the following fields:
 * - message
 * - balance
 * - commission
 * - filledAmount
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FillOutResponseDTO {

    private String message;
    private String balance;
    private String commission;
    private String filledAmount;
}
