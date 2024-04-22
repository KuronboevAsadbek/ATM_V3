package uz.atm_v_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "banknote_type")
public class BanknoteType {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "nominal")
    private Integer nominal;

    @JoinColumn(name = "cashing_type_id")
    @ManyToOne
    private CashingType cashingType;

    @JoinColumn(name = "currency_type_id")
    @ManyToOne
    private CurrencyType currencyType;

}
