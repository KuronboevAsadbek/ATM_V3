package uz.atm_v_3.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardHolderResponseDTO {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String passportSeries;
    private String passportNumber;
    private String pinFL;
    private String birthDate;
}
