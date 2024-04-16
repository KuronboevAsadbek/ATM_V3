package uz.atm_v_3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.utils.Banknotes;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankNoteRequestDTO {

    private String banknoteType;
    private Banknotes banknotes;
}
