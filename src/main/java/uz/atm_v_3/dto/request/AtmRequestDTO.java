package uz.atm_v_3.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AtmRequestDTO {

    @JsonProperty("banknote_type")
    private String banknoteType;

    @JsonProperty("banknote_count")
    private Integer banknoteCount;

    @JsonProperty("currency_type_id")
    private Long currencyTypeId;
}
