package uz.atm_v_3.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BanknoteListResponseDTO {

    private String banknoteType;
    private String banknote1000;
    private String banknote5000;
    private String banknote10000;
    private String banknote20000;
    private String banknote50000;
    private String banknote100000;
    private String banknote200000;
}
