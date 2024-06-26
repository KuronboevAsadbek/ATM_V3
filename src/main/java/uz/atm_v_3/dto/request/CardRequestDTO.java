package uz.atm_v_3.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * This class is responsible for creating a request to add a card.
 * It contains the following fields:
 * - cardPin
 * - cardHolderId
 * - cardTypeId
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated

public class CardRequestDTO {

    @NotEmpty
    @JsonProperty("card_pin")
    @Size(min = 4, max = 4, message = "Card pin must be 4 characters")
    private String cardPin;

    @NotNull
    @JsonProperty("card_holder_id")
    private Long cardHolderId;


    @NotNull
    @JsonProperty("card_type_id")
    private Long cardTypeId;


}
