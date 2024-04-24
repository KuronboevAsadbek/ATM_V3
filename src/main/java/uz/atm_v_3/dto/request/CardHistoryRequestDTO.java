package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/**
 * This class is responsible for creating a request to get the history of the card.
 * It contains the following fields:
 * - cardNumber
 * - senderCardNumber
 * - pin
 */
public class CardHistoryRequestDTO {



    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("sender_card_number")
    private String senderCardNumber;
    private String pin;

}
