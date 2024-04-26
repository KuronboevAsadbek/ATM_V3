package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusResponseDTO {


    private String status;
    private String message;
}
