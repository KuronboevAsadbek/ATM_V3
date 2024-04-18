package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtmResponseDTO {

    private String message;
    private String banknoteType;
    private Integer banknoteCount;
}
