package co.edu.sena.service;

import co.edu.sena.service.dto.CostCenterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.CostCenter}.
 */
public interface CostCenterService {
    /**
     * Save a costCenter.
     *
     * @param costCenterDTO the entity to save.
     * @return the persisted entity.
     */
    CostCenterDTO save(CostCenterDTO costCenterDTO);

    /**
     * Updates a costCenter.
     *
     * @param costCenterDTO the entity to update.
     * @return the persisted entity.
     */
    CostCenterDTO update(CostCenterDTO costCenterDTO);

    /**
     * Partially updates a costCenter.
     *
     * @param costCenterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CostCenterDTO> partialUpdate(CostCenterDTO costCenterDTO);

    /**
     * Get all the costCenters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CostCenterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" costCenter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CostCenterDTO> findOne(Long id);

    /**
     * Delete the "id" costCenter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
