package uz.atm_v_3.dto.response;

import lombok.*;

/**
 * This class is responsible for creating a response.
 * It contains the following fields:
 * - message
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {

    private String message;
}
