package co.edu.sena.service;

import co.edu.sena.service.dto.SocialSecurityDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.SocialSecurity}.
 */
public interface SocialSecurityService {
    /**
     * Save a socialSecurity.
     *
     * @param socialSecurityDTO the entity to save.
     * @return the persisted entity.
     */
    SocialSecurityDTO save(SocialSecurityDTO socialSecurityDTO);

    /**
     * Updates a socialSecurity.
     *
     * @param socialSecurityDTO the entity to update.
     * @return the persisted entity.
     */
    SocialSecurityDTO update(SocialSecurityDTO socialSecurityDTO);

    /**
     * Partially updates a socialSecurity.
     *
     * @param socialSecurityDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SocialSecurityDTO> partialUpdate(SocialSecurityDTO socialSecurityDTO);

    /**
     * Get all the socialSecurities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SocialSecurityDTO> findAll(Pageable pageable);

    /**
     * Get the "id" socialSecurity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SocialSecurityDTO> findOne(Long id);

    /**
     * Delete the "id" socialSecurity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
