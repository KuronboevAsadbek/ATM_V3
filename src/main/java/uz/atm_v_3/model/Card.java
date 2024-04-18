package uz.atm_v_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "card_expire_date")
    private String cardExpireDate;

    @Column(name = "card_cvc")
    private String cardCVC;

    @Column(name = "card_pin")
    private String cardPin;

    @Column(name = "balance")
    private String balance;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "card_holder_id")
    private CardHolder cardHolder;

    @ManyToOne
    @JoinColumn(name = "card_type_id")
    private CardType cardType;


}
