package co.edu.sena.repository;

import co.edu.sena.domain.DetailEmployeeSocialPayment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the DetailEmployeeSocialPayment entity.
 */
@Repository
public interface DetailEmployeeSocialPaymentRepository extends JpaRepository<DetailEmployeeSocialPayment, Long> {
    default Optional<DetailEmployeeSocialPayment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<DetailEmployeeSocialPayment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<DetailEmployeeSocialPayment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct detailEmployeeSocialPayment from DetailEmployeeSocialPayment detailEmployeeSocialPayment left join fetch detailEmployeeSocialPayment.employee left join fetch detailEmployeeSocialPayment.socialPayment",
        countQuery = "select count(distinct detailEmployeeSocialPayment) from DetailEmployeeSocialPayment detailEmployeeSocialPayment"
    )
    Page<DetailEmployeeSocialPayment> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct detailEmployeeSocialPayment from DetailEmployeeSocialPayment detailEmployeeSocialPayment left join fetch detailEmployeeSocialPayment.employee left join fetch detailEmployeeSocialPayment.socialPayment"
    )
    List<DetailEmployeeSocialPayment> findAllWithToOneRelationships();

    @Query(
        "select detailEmployeeSocialPayment from DetailEmployeeSocialPayment detailEmployeeSocialPayment left join fetch detailEmployeeSocialPayment.employee left join fetch detailEmployeeSocialPayment.socialPayment where detailEmployeeSocialPayment.id =:id"
    )
    Optional<DetailEmployeeSocialPayment> findOneWithToOneRelationships(@Param("id") Long id);
}
