package co.edu.sena.service;

import co.edu.sena.service.dto.DetailEmployeeSocialPaymentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.DetailEmployeeSocialPayment}.
 */
public interface DetailEmployeeSocialPaymentService {
    /**
     * Save a detailEmployeeSocialPayment.
     *
     * @param detailEmployeeSocialPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    DetailEmployeeSocialPaymentDTO save(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO);

    /**
     * Updates a detailEmployeeSocialPayment.
     *
     * @param detailEmployeeSocialPaymentDTO the entity to update.
     * @return the persisted entity.
     */
    DetailEmployeeSocialPaymentDTO update(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO);

    /**
     * Partially updates a detailEmployeeSocialPayment.
     *
     * @param detailEmployeeSocialPaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DetailEmployeeSocialPaymentDTO> partialUpdate(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO);

    /**
     * Get all the detailEmployeeSocialPayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailEmployeeSocialPaymentDTO> findAll(Pageable pageable);

    /**
     * Get all the detailEmployeeSocialPayments with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailEmployeeSocialPaymentDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" detailEmployeeSocialPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailEmployeeSocialPaymentDTO> findOne(Long id);

    /**
     * Delete the "id" detailEmployeeSocialPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
