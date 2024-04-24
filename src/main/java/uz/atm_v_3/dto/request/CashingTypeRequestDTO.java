package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * This class is responsible for creating a request to cash out money.
 * It contains the following fields:
 * - cardNumber
 * - cardPin
 * - amount
 * - cashingBanknoteType
 * - chequeRequest
 */
public class CashingTypeRequestDTO {

    @JsonProperty(value = "name")
    private String name;
}
