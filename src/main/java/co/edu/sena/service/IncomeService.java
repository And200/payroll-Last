package co.edu.sena.service;

import co.edu.sena.service.dto.IncomeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.Income}.
 */
public interface IncomeService {
    /**
     * Save a income.
     *
     * @param incomeDTO the entity to save.
     * @return the persisted entity.
     */
    IncomeDTO save(IncomeDTO incomeDTO);

    /**
     * Updates a income.
     *
     * @param incomeDTO the entity to update.
     * @return the persisted entity.
     */
    IncomeDTO update(IncomeDTO incomeDTO);

    /**
     * Partially updates a income.
     *
     * @param incomeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IncomeDTO> partialUpdate(IncomeDTO incomeDTO);

    /**
     * Get all the incomes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncomeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" income.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncomeDTO> findOne(Long id);

    /**
     * Delete the "id" income.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
