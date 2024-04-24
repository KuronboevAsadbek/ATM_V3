package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.model.CashingType;
import uz.atm_v_3.model.CurrencyType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * This class is responsible for creating a request to add a banknote type.
 * It contains the following fields:
 * - name
 * - quantity
 * - nominal
 * - cashingTypeId
 * - currencyTypeId
 */
public class BanknoteTypeRequestDTO {

    private String name;

    private Integer quantity;

    private Integer nominal;

        @JsonProperty("cashing_type_id")
    private Long cashingTypeId;

    @JsonProperty("currency_type_id")
    private Long currencyTypeId;


}
