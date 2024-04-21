package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.CardType;

@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long>{

}
