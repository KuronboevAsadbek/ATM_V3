package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.Card;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "SELECT c.*, ch.pin_fl\n" +
            "FROM Card c\n" +
            "         JOIN card_holder ch ON c.card_holder_id = ch.id\n" +
            "WHERE ch.pin_fl =:pinfl\n", nativeQuery = true)
    List<Card> findAllByCardHolderPINFL(String pinfl);

    Card findCardByCardNumber(String cardNumber);
}
