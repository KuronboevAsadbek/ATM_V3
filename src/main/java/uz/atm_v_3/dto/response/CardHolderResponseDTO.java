package uz.atm_v_3.dto.response;

import lombok.*;

/**
 * This class is responsible for creating a response to the card holder.
 * It contains the following fields:
 * - id
 * - name
 * - surname
 * - phoneNumber
 * - email
 * - address
 * - passportSeries
 * - passportNumber
 * - pinFL
 * - birthDate
 */
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
