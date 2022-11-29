package co.edu.sena.repository;

import co.edu.sena.domain.Deduction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Deduction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeductionRepository extends JpaRepository<Deduction, Long> {}
