package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.CardHistory;

@Repository
public interface CardHistoryRepository extends JpaRepository<CardHistory, Long>{

}
