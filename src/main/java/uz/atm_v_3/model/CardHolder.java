package uz.atm_v_3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This class is responsible for creating a table in the database that will store information about card holders.
 * The table will contain the following columns:
 * id - the primary key of the table.
 * name - the name of the card holder.
 * surname - the surname of the card holder.
 * phoneNumber - the phone number of the card holder.
 * email - the email of the card holder.
 * address - the address of the card holder.
 * passportSeries - the series of the passport of the card holder.
 * passportNumber - the number of the passport of the card holder.
 * pinFL - the pin of the card holder.
 * birthDate - the birth date of the card holder.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CardHolder  implements Serializable{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "passport_series")
    private String passportSeries;

    @Column(name = "passport_number",unique = true)
    private String passportNumber;

    @Column(name = "pin_FL", unique = true)
    private String pinFL;

    @Column(name = "birth_date")
    private String birthDate;

}
