package uz.atm_v_3.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;
import uz.atm_v_3.model.CardHolder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class CardRequestDTO {


    @NotEmpty
    @JsonProperty("card_type")
    private String cardType;

    @NotEmpty
    @JsonProperty("card_pin")
    @Size(min = 4, max = 4, message = "Card pin must be 4 characters")
    private String cardPin;

    @NotNull
    @JsonProperty("card_holder")
    private CardHolder cardHolder;

}
