package uz.atm_v_3.dto.response;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {

    private Long id;
    private String cardNumber;
    private String cardType;
    private String cardHolderName;
    private String cardExpireDate;
    private String cardStatus;
    private String cardCVC;
    private String cardPin;
    private String cardBalance;
    private String cardHolderPhoneNumber;
    private String cardHolderAddress;
}
