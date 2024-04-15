package uz.atm_v_3.exception;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.atm_v_3.utils.ResponseCode;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ErrorResponse_2 {
        private String message;
    }


    @ExceptionHandler(CardHolderException.class)
    @ResponseBody
    public ResponseEntity<Object> handleCardHolderException(CardHolderException cardHolderException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(cardHolderException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CardException.class)
    @ResponseBody
    public ResponseEntity<Object> handleCardException(CardException cardException) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(cardException.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> on(MethodArgumentNotValidException ex) {
        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.REQUIRED_DATA_MISSING, ex.getBindingResult()
                .getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

}
