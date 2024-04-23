package uz.atm_v_3.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardHistoryResponseDTO {

    private Long id;
    private String amount;
    private String commission;
    private String date;

    private String receiverCardNumber;
    private String receiverCardHolderName;

    private String senderCardNumber;
    private String senderCardHolderName;

    private String operationType;

}
