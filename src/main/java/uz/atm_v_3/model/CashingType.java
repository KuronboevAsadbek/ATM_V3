package uz.atm_v_3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class is responsible for creating a table in the database that will store information about cashing types.
 * The table will contain the following columns:
 * id - the primary key of the table.
 * name - the name of the cashing type.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cashing_type")

public class CashingType {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true )
    private String name;

}
