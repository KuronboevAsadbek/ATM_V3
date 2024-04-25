package uz.atm_v_3.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * This class is responsible for creating a response to card history.
 * It contains the following fields:
 * - id
 * - amount
 * - commission
 * - date
 * - receiverCardNumber
 * - receiverCardHolderName
 * - senderCardNumber
 * - senderCardHolderName
 * - operationType
 */
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tashkent")
    private Instant date;

    private String receiverCardNumber;
    private String receiverCardHolderName;

    private String senderCardNumber;
    private String senderCardHolderName;

    private String operationType;

}
