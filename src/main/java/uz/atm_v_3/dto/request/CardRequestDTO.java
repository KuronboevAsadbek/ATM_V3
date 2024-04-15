package uz.atm_v_3.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.atm_v_3.model.CardHolder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardRequestDTO {


    @NotNull
    @JsonProperty("card_type")
    private String cardType;

    @NotNull
    @JsonProperty("card_pin")
    @Size(min = 4, max = 4, message = "Card pin must be 4 characters")
    private String cardPin;


}
