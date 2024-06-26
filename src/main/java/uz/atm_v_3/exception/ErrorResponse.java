package uz.atm_v_3.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.utils.ResponseCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;

    public static ErrorResponse of(ResponseCode responseCode, String message) {
        return new ErrorResponse(responseCode.getCode(), message);
    }
}
