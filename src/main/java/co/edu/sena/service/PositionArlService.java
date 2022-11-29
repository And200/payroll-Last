package co.edu.sena.service;

import co.edu.sena.service.dto.PositionArlDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.PositionArl}.
 */
public interface PositionArlService {
    /**
     * Save a positionArl.
     *
     * @param positionArlDTO the entity to save.
     * @return the persisted entity.
     */
    PositionArlDTO save(PositionArlDTO positionArlDTO);

    /**
     * Updates a positionArl.
     *
     * @param positionArlDTO the entity to update.
     * @return the persisted entity.
     */
    PositionArlDTO update(PositionArlDTO positionArlDTO);

    /**
     * Partially updates a positionArl.
     *
     * @param positionArlDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PositionArlDTO> partialUpdate(PositionArlDTO positionArlDTO);

    /**
     * Get all the positionArls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PositionArlDTO> findAll(Pageable pageable);

    /**
     * Get the "id" positionArl.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PositionArlDTO> findOne(Long id);

    /**
     * Delete the "id" positionArl.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
