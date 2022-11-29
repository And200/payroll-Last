package co.edu.sena.repository;

import co.edu.sena.domain.SocialPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SocialPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialPaymentRepository extends JpaRepository<SocialPayment, Long> {}
