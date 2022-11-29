package co.edu.sena.service;

import co.edu.sena.service.dto.PayrollDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.Payroll}.
 */
public interface PayrollService {
    /**
     * Save a payroll.
     *
     * @param payrollDTO the entity to save.
     * @return the persisted entity.
     */
    PayrollDTO save(PayrollDTO payrollDTO);

    /**
     * Updates a payroll.
     *
     * @param payrollDTO the entity to update.
     * @return the persisted entity.
     */
    PayrollDTO update(PayrollDTO payrollDTO);

    /**
     * Partially updates a payroll.
     *
     * @param payrollDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PayrollDTO> partialUpdate(PayrollDTO payrollDTO);

    /**
     * Get all the payrolls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayrollDTO> findAll(Pageable pageable);

    /**
     * Get all the payrolls with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayrollDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" payroll.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayrollDTO> findOne(Long id);

    /**
     * Delete the "id" payroll.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
