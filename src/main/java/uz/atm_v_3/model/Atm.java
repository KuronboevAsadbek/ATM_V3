package uz.atm_v_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.atm_v_3.utils.BanknoteType;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Atm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "banknote_type")
    private String banknoteType;

    @Column(name = "banknote_count")
    private Integer banknoteCount;

    @JoinColumn(name = "currency_type_id")
    @ManyToOne
    private CurrencyType currencyType;


}
