package uz.atm_v_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.atm_v_3.model.BanknoteType;

import java.util.List;

@Repository
public interface BanknoteTypeRepository extends JpaRepository<BanknoteType, Long> {

    BanknoteType findByName(String name);

    List<BanknoteType> getAllByCurrencyType_Id(Long currencyTypeId);

    List<BanknoteType> getAllByCashingTypeName(String cashingTypeName);
}
