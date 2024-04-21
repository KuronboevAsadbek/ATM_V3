package uz.atm_v_3.dto.response;


import jakarta.persistence.Id;
import lombok.*;
import uz.atm_v_3.model.CardType;

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
