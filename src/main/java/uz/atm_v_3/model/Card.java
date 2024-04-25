package uz.atm_v_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
/**
 * This class is responsible for creating a table in the database that will store information about cards.
 * The table will contain the following columns:
 * id - the primary key of the table.
 * cardNumber - the number of the card.
 * cardExpireDate - the expiration date of the card.
 * cardCVC - the CVC code of the card.
 * cardPin - the PIN code of the card.
 * balance - the balance of the card.
 * isActive - the status of the card.
 * cardHolder - the card holder.
 * cardType - the type of the card.
 * checkCardQuantity - the quantity of the card.
 */

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

    @Column(name = "check_card_quantity")
    private Integer checkCardQuantity;


}
