package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.Atm;

@Repository
public interface AtmRepository extends JpaRepository<Atm, Long> {


    @Query(value = "select * from  atm a where a.banknote_type = :banknoteType", nativeQuery = true)
    Atm findBanknoteByType(String banknoteType);
}
