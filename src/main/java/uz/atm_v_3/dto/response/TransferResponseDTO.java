package uz.atm_v_3.dto.response;

import lombok.*;

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
