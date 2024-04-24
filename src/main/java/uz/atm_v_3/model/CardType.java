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
@Table(name = "card_type")
/**
 * This class is responsible for creating a table in the database that will store information about card types.
 * The table will contain the following columns:
 * id - the primary key of the table.
 * name - the name of the card type.
 * description - the description of the card type.
 * number - the number of the card type.
 * expirationYear - the expiration year of the card type.
 * currencyType - the type of currency.
 */
public class CardType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "number", unique = true)
    private String number;

    @Column(name = "expiration_year")
    private int expirationYear;


    @JoinColumn(name = "currency_type_id")
    @ManyToOne
    private CurrencyType currencyType;
}
