package co.edu.sena.repository;

import co.edu.sena.domain.CostCenter;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CostCenter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    Optional<CostCenter> findByCostCenterName(String name);
}
