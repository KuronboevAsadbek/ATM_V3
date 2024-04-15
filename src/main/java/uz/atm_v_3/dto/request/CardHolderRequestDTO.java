package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class CardHolderRequestDTO {

    @JsonProperty("name")
    @NotEmpty(message = "Name must be filled")
    private String name;

    @JsonProperty("surname")
    @NotEmpty(message = "Surname must be filled")
    private String surname;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    @NotEmpty(message = "Address must be filled")
    private String address;

    @JsonProperty("passport_series")
    @NotEmpty(message = "Passport series must be filled")
    private String passportSeries;

    @JsonProperty("passport_number")
    @NotEmpty(message = "Passport number must be filled")
    private String passportNumber;


    @NotEmpty(message = "PinFL must be filled")
    @Size(min = 14, max = 14, message = "PinFL must be 14 characters")
    @JsonProperty("pin_FL")
    private String pinFL;

    @JsonProperty("birth_date")
    @NotEmpty(message = "Birth date must be filled")
    private String birthDate;
}
