package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.model.CurrencyType;

/**
 * This class is responsible for creating a request to add a card type.
 * It contains the following fields:
 * - name
 * - description
 * - number
 * - expirationYear
 * - currencyType
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CardTypeRequestDTO {

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotEmpty
    @Size(min = 8, max = 8, message = "Card number must be 8 characters")
    @JsonProperty("number")
    private String number;

    @JsonProperty("expiration_year")
    private int expirationYear;

    @JsonProperty("currency_type")
    private CurrencyType currencyType;
}


