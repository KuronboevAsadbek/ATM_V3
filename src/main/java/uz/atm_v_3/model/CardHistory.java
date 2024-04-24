package uz.atm_v_3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_history")

/**
 * This class is responsible for storing the history of the card.
 * It contains the following fields:
 * - id
 * - amount
 * - commission
 * - date
 * - fromCard
 * - toCard
 */
public class CardHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "commission")
    private String commission;

    @Column(name = "date", nullable = false, updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @CurrentTimestamp
    private Instant date;

    @JoinColumn(name = "from_card_id")
    @ManyToOne
    private Card fromCard;

    @JoinColumn(name = "to_card_id")
    @ManyToOne
    private Card toCard;
}
