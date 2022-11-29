package co.edu.sena.service;

import co.edu.sena.service.dto.OperatorTypeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.OperatorType}.
 */
public interface OperatorTypeService {
    /**
     * Save a operatorType.
     *
     * @param operatorTypeDTO the entity to save.
     * @return the persisted entity.
     */
    OperatorTypeDTO save(OperatorTypeDTO operatorTypeDTO);

    /**
     * Updates a operatorType.
     *
     * @param operatorTypeDTO the entity to update.
     * @return the persisted entity.
     */
    OperatorTypeDTO update(OperatorTypeDTO operatorTypeDTO);

    /**
     * Partially updates a operatorType.
     *
     * @param operatorTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OperatorTypeDTO> partialUpdate(OperatorTypeDTO operatorTypeDTO);

    /**
     * Get all the operatorTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperatorTypeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" operatorType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperatorTypeDTO> findOne(Long id);

    /**
     * Delete the "id" operatorType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
