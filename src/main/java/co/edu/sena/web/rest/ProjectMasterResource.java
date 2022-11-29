package co.edu.sena.web.rest;

import co.edu.sena.repository.ProjectMasterRepository;
import co.edu.sena.service.ProjectMasterService;
import co.edu.sena.service.dto.ProjectMasterDTO;
import co.edu.sena.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link co.edu.sena.domain.ProjectMaster}.
 */
@RestController
@RequestMapping("/api")
public class ProjectMasterResource {

    private final Logger log = LoggerFactory.getLogger(ProjectMasterResource.class);

    private static final String ENTITY_NAME = "projectMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectMasterService projectMasterService;

    private final ProjectMasterRepository projectMasterRepository;

    public ProjectMasterResource(ProjectMasterService projectMasterService, ProjectMasterRepository projectMasterRepository) {
        this.projectMasterService = projectMasterService;
        this.projectMasterRepository = projectMasterRepository;
    }

    /**
     * {@code POST  /project-masters} : Create a new projectMaster.
     *
     * @param projectMasterDTO the projectMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectMasterDTO, or with status {@code 400 (Bad Request)} if the projectMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-masters")
    public ResponseEntity<ProjectMasterDTO> createProjectMaster(@Valid @RequestBody ProjectMasterDTO projectMasterDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProjectMaster : {}", projectMasterDTO);
        if (projectMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectMasterDTO result = projectMasterService.save(projectMasterDTO);
        return ResponseEntity
            .created(new URI("/api/project-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-masters/:id} : Updates an existing projectMaster.
     *
     * @param id the id of the projectMasterDTO to save.
     * @param projectMasterDTO the projectMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMasterDTO,
     * or with status {@code 400 (Bad Request)} if the projectMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-masters/{id}")
    public ResponseEntity<ProjectMasterDTO> updateProjectMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectMasterDTO projectMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectMaster : {}, {}", id, projectMasterDTO);
        if (projectMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectMasterDTO result = projectMasterService.update(projectMasterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-masters/:id} : Partial updates given fields of an existing projectMaster, field will ignore if it is null
     *
     * @param id the id of the projectMasterDTO to save.
     * @param projectMasterDTO the projectMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMasterDTO,
     * or with status {@code 400 (Bad Request)} if the projectMasterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the projectMasterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-masters/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectMasterDTO> partialUpdateProjectMaster(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectMasterDTO projectMasterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectMaster partially : {}, {}", id, projectMasterDTO);
        if (projectMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectMasterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectMasterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectMasterDTO> result = projectMasterService.partialUpdate(projectMasterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectMasterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /project-masters} : get all the projectMasters.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectMasters in body.
     */
    @GetMapping("/project-masters")
    public ResponseEntity<List<ProjectMasterDTO>> getAllProjectMasters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ProjectMasters");
        Page<ProjectMasterDTO> page;
        if (eagerload) {
            page = projectMasterService.findAllWithEagerRelationships(pageable);
        } else {
            page = projectMasterService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /project-masters/:id} : get the "id" projectMaster.
     *
     * @param id the id of the projectMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-masters/{id}")
    public ResponseEntity<ProjectMasterDTO> getProjectMaster(@PathVariable Long id) {
        log.debug("REST request to get ProjectMaster : {}", id);
        Optional<ProjectMasterDTO> projectMasterDTO = projectMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectMasterDTO);
    }

    /**
     * {@code DELETE  /project-masters/:id} : delete the "id" projectMaster.
     *
     * @param id the id of the projectMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-masters/{id}")
    public ResponseEntity<Void> deleteProjectMaster(@PathVariable Long id) {
        log.debug("REST request to delete ProjectMaster : {}", id);
        projectMasterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
