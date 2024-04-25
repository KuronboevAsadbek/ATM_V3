package uz.atm_v_3.dto.response;


import jakarta.persistence.Id;
import lombok.*;
import uz.atm_v_3.model.CardType;

/**
 * This class is responsible for creating a response to the card.
 * It contains the following fields:
 * - id
 * - cardBalance
 * - cardNumber
 * - cardExpireDate
 * - cardCVC
 * - cardPin
 * - cardTypeName
 * - cardStatus
 * - cardHolderName
 * - cardHolderAddress
 * - cardHolderPhoneNumber
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardResponseDTO {

    @Id
    private Long id;
    private String cardBalance;
    private String cardNumber;
    private String cardExpireDate;
    private String cardCVC;
    private String cardPin;
    private String cardTypeName;
    private Boolean cardStatus;
    private String cardHolderName;
    private String cardHolderAddress;
    private String cardHolderPhoneNumber;
}
