package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is responsible for creating a request to cash out money.
 * It contains the following fields:
 * - cardNumber
 * - cardPin
 * - amount
 * - cashingBanknoteType
 * - chequeRequest
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashingRequestDTO {

    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("card_pin")
    private String cardPin;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("cashing_banknote_type")
    private String cashingBanknoteType;
    @JsonProperty("cheque_request")
    private boolean chequeRequest;
}
