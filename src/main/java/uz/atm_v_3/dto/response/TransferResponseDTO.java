package uz.atm_v_3.dto.response;

import lombok.*;

/**
 * This class is responsible for creating a response to the transfer.
 * It contains the following fields:
 * - fromCardNumber
 * - toCardNumber
 * - amount
 * - message
 * - status
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferResponseDTO {

    private String fromCardNumber;
    private String toCardNumber;
    private String amount;
    private String message;
    private Boolean status;
}
