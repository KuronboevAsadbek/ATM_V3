package uz.atm_v_3.exception;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.atm_v_3.utils.ResponseCode;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);


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
        LOG.error(cardHolderException.getMessage());
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
        LOG.error(cardException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(cardException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CurrencyTypeException.class)
    @ResponseBody
    public ResponseEntity<Object> handleCurrencyTypeException(CurrencyTypeException currencyTypeException) {
        LOG.error(currencyTypeException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(currencyTypeException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CheckPinException.class)
    @ResponseBody
    public ResponseEntity<Object> handleCheckPinException(CheckPinException checkPinException) {
        LOG.error(checkPinException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(checkPinException.getMessage())
                        .build()
                );
    }



    @ExceptionHandler(CashingTypeException.class)
    @ResponseBody
    ResponseEntity<Object> handleCashingTypeException(CashingTypeException cashingTypeException) {
        LOG.error(cashingTypeException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(cashingTypeException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(BankNoteException.class)
    @ResponseBody
    ResponseEntity<Object> handleBankNoteException(BankNoteException bankNoteException) {
        LOG.error(bankNoteException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(bankNoteException.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(CardHistoryException.class)
    @ResponseBody
    ResponseEntity<Object> handleCardHistoryException(CardHistoryException cardHistoryException) {
        LOG.error(cardHistoryException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse_2
                        .builder()
                        .message(cardHistoryException.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> on(MethodArgumentNotValidException ex) {
        LOG.error(ex.getMessage());
        ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.REQUIRED_DATA_MISSING, ex.getBindingResult()
                .getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

}
