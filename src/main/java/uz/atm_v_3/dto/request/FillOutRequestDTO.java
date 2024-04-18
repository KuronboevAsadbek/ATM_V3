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
public class FillOutRequestDTO {

    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("card_pin")
    private String cardPin;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("cashing_banknote_type")
    private String cashingBanknoteType;

}
