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
public class TransferRequestDTO {

    @JsonProperty("from_card_number")
    private String fromCardNumber;

    @JsonProperty("to_card_number")
    private String toCardNumber;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("pin")
    private String pin;
}
