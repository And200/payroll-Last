package co.edu.sena.repository;

import co.edu.sena.domain.Payroll;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Payroll entity.
 */
@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    default Optional<Payroll> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Payroll> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Payroll> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct payroll from Payroll payroll left join fetch payroll.income left join fetch payroll.deduction left join fetch payroll.positionArl left join fetch payroll.employee",
        countQuery = "select count(distinct payroll) from Payroll payroll"
    )
    Page<Payroll> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct payroll from Payroll payroll left join fetch payroll.income left join fetch payroll.deduction left join fetch payroll.positionArl left join fetch payroll.employee"
    )
    List<Payroll> findAllWithToOneRelationships();

    @Query(
        "select payroll from Payroll payroll left join fetch payroll.income left join fetch payroll.deduction left join fetch payroll.positionArl left join fetch payroll.employee where payroll.id =:id"
    )
    Optional<Payroll> findOneWithToOneRelationships(@Param("id") Long id);
}
