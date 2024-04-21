package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CashingResponseDTO {

    private String message;
    private String commission;
    private String balance;
    private String cashedAmount;
}