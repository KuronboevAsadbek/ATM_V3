package uz.atm_v_3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.utils.BanknoteType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashingRequestDTO {

        private String cardNumber;
        private BanknoteType banknoteType;
        private String cardPin;
        private String cashAmount;

}
