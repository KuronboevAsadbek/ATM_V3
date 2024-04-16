package uz.atm_v_3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.model.BankNote;
import uz.atm_v_3.utils.Banknotes;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CashingResponseDTO {

    private String totalAmount;
    List<Banknotes> banknotes;
}
