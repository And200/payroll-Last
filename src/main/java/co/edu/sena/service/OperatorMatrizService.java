package co.edu.sena.service;

import co.edu.sena.service.dto.OperatorMatrizDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.OperatorMatriz}.
 */
public interface OperatorMatrizService {
    /**
     * Save a operatorMatriz.
     *
     * @param operatorMatrizDTO the entity to save.
     * @return the persisted entity.
     */
    OperatorMatrizDTO save(OperatorMatrizDTO operatorMatrizDTO);

    /**
     * Updates a operatorMatriz.
     *
     * @param operatorMatrizDTO the entity to update.
     * @return the persisted entity.
     */
    OperatorMatrizDTO update(OperatorMatrizDTO operatorMatrizDTO);

    /**
     * Partially updates a operatorMatriz.
     *
     * @param operatorMatrizDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OperatorMatrizDTO> partialUpdate(OperatorMatrizDTO operatorMatrizDTO);

    /**
     * Get all the operatorMatrizs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperatorMatrizDTO> findAll(Pageable pageable);

    /**
     * Get the "id" operatorMatriz.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperatorMatrizDTO> findOne(Long id);

    /**
     * Delete the "id" operatorMatriz.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
