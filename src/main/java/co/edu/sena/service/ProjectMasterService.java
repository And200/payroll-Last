package co.edu.sena.service;

import co.edu.sena.service.dto.ProjectMasterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.edu.sena.domain.ProjectMaster}.
 */
public interface ProjectMasterService {
    /**
     * Save a projectMaster.
     *
     * @param projectMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ProjectMasterDTO save(ProjectMasterDTO projectMasterDTO);

    /**
     * Updates a projectMaster.
     *
     * @param projectMasterDTO the entity to update.
     * @return the persisted entity.
     */
    ProjectMasterDTO update(ProjectMasterDTO projectMasterDTO);

    /**
     * Partially updates a projectMaster.
     *
     * @param projectMasterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectMasterDTO> partialUpdate(ProjectMasterDTO projectMasterDTO);

    /**
     * Get all the projectMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectMasterDTO> findAll(Pageable pageable);

    /**
     * Get all the projectMasters with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectMasterDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" projectMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectMasterDTO> findOne(Long id);

    /**
     * Delete the "id" projectMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
