package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FillOutResponseDTO {

    private String message;
    private String balance;
    private String commission;
    private String filledAmount;
}
