package co.edu.sena.service;

import co.edu.sena.service.dto.DeductionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.Deduction}.
 */
public interface DeductionService {
    /**
     * Save a deduction.
     *
     * @param deductionDTO the entity to save.
     * @return the persisted entity.
     */
    DeductionDTO save(DeductionDTO deductionDTO);

    /**
     * Updates a deduction.
     *
     * @param deductionDTO the entity to update.
     * @return the persisted entity.
     */
    DeductionDTO update(DeductionDTO deductionDTO);

    /**
     * Partially updates a deduction.
     *
     * @param deductionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DeductionDTO> partialUpdate(DeductionDTO deductionDTO);

    /**
     * Get all the deductions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DeductionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" deduction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeductionDTO> findOne(Long id);

    /**
     * Delete the "id" deduction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
