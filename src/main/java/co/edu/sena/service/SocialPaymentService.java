package co.edu.sena.service;

import co.edu.sena.service.dto.SocialPaymentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.SocialPayment}.
 */
public interface SocialPaymentService {
    /**
     * Save a socialPayment.
     *
     * @param socialPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    SocialPaymentDTO save(SocialPaymentDTO socialPaymentDTO);

    /**
     * Updates a socialPayment.
     *
     * @param socialPaymentDTO the entity to update.
     * @return the persisted entity.
     */
    SocialPaymentDTO update(SocialPaymentDTO socialPaymentDTO);

    /**
     * Partially updates a socialPayment.
     *
     * @param socialPaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SocialPaymentDTO> partialUpdate(SocialPaymentDTO socialPaymentDTO);

    /**
     * Get all the socialPayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SocialPaymentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" socialPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SocialPaymentDTO> findOne(Long id);

    /**
     * Delete the "id" socialPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
