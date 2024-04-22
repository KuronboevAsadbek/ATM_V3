package uz.atm_v_3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card_history")
public class CardHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private String amount;

    @Column(name = "commission")
    private String commission;

    @Column(name = "date")
    @JsonFormat(pattern = "dd.MM.yyy:dd.MM.yyyy:HH:mm:ss")
    @DateTimeFormat(pattern = "dd.MM.yyy:dd.MM.yyyy:HH:mm:ss")
    private String date;

    @JoinColumn(name = "from_card_id")
    @ManyToOne
    private Card fromCard;

    @JoinColumn(name = "to_card_id")
    @ManyToOne
    private Card toCard;
}
