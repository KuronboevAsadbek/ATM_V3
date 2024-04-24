package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.CardHistory;

import java.util.List;

@Repository
public interface CardHistoryRepository extends JpaRepository<CardHistory, Long>{


    @Query(value = "select * from card_history where card_history.to_card_id =:id or card_history.from_card_id = :id",
            nativeQuery = true)
    List<CardHistory> findAllByCardId(Long id);


    @Query(value = "SELECT * FROM card_history " +
            "WHERE card_history.date BETWEEN CAST(:startDate AS TIMESTAMP) AND CAST(:endDate AS TIMESTAMP) " +
            "AND (card_history.from_card_id = :id OR card_history.to_card_id = :id)" +
            "ORDER BY card_history.date DESC ", nativeQuery = true)
    List<CardHistory> findAllByDateAndCardId(@Param("startDate") String startDate,
                                             @Param("endDate") String endDate,
                                             @Param("id") Long id);


}
