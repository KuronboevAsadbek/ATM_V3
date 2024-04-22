package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.CashingType;

@Repository
public interface CashingTypeRepository extends JpaRepository<CashingType, Long>{

    CashingType findByName(String name);
}
