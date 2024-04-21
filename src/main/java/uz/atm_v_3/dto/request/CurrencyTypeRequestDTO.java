package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CurrencyTypeRequestDTO {


    @NotEmpty(message = "Currency name must not be empty")
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters")
    @JsonProperty("currency_name")
    private String name;
}
