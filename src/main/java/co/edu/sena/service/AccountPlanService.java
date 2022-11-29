package co.edu.sena.service;

import co.edu.sena.service.dto.AccountPlanDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.AccountPlan}.
 */
public interface AccountPlanService {
    /**
     * Save a accountPlan.
     *
     * @param accountPlanDTO the entity to save.
     * @return the persisted entity.
     */
    AccountPlanDTO save(AccountPlanDTO accountPlanDTO);

    /**
     * Updates a accountPlan.
     *
     * @param accountPlanDTO the entity to update.
     * @return the persisted entity.
     */
    AccountPlanDTO update(AccountPlanDTO accountPlanDTO);

    /**
     * Partially updates a accountPlan.
     *
     * @param accountPlanDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AccountPlanDTO> partialUpdate(AccountPlanDTO accountPlanDTO);

    /**
     * Get all the accountPlans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountPlanDTO> findAll(Pageable pageable);

    /**
     * Get the "id" accountPlan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountPlanDTO> findOne(Long id);

    /**
     * Delete the "id" accountPlan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
