package co.edu.sena.web.rest;

import co.edu.sena.repository.OperatorTypeRepository;
import co.edu.sena.service.OperatorTypeService;
import co.edu.sena.service.dto.OperatorTypeDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.OperatorType}.
 */
@RestController
@RequestMapping("/api")
public class OperatorTypeResource {

    private final Logger log = LoggerFactory.getLogger(OperatorTypeResource.class);

    private static final String ENTITY_NAME = "operatorType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperatorTypeService operatorTypeService;

    private final OperatorTypeRepository operatorTypeRepository;

    public OperatorTypeResource(OperatorTypeService operatorTypeService, OperatorTypeRepository operatorTypeRepository) {
        this.operatorTypeService = operatorTypeService;
        this.operatorTypeRepository = operatorTypeRepository;
    }

    /**
     * {@code POST  /operator-types} : Create a new operatorType.
     *
     * @param operatorTypeDTO the operatorTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operatorTypeDTO, or with status {@code 400 (Bad Request)} if the operatorType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operator-types")
    public ResponseEntity<OperatorTypeDTO> createOperatorType(@Valid @RequestBody OperatorTypeDTO operatorTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save OperatorType : {}", operatorTypeDTO);
        if (operatorTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new operatorType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperatorTypeDTO result = operatorTypeService.save(operatorTypeDTO);
        return ResponseEntity
            .created(new URI("/api/operator-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operator-types/:id} : Updates an existing operatorType.
     *
     * @param id the id of the operatorTypeDTO to save.
     * @param operatorTypeDTO the operatorTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorTypeDTO,
     * or with status {@code 400 (Bad Request)} if the operatorTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operatorTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operator-types/{id}")
    public ResponseEntity<OperatorTypeDTO> updateOperatorType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OperatorTypeDTO operatorTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OperatorType : {}, {}", id, operatorTypeDTO);
        if (operatorTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operatorTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operatorTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperatorTypeDTO result = operatorTypeService.update(operatorTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /operator-types/:id} : Partial updates given fields of an existing operatorType, field will ignore if it is null
     *
     * @param id the id of the operatorTypeDTO to save.
     * @param operatorTypeDTO the operatorTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorTypeDTO,
     * or with status {@code 400 (Bad Request)} if the operatorTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the operatorTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the operatorTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/operator-types/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OperatorTypeDTO> partialUpdateOperatorType(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OperatorTypeDTO operatorTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperatorType partially : {}, {}", id, operatorTypeDTO);
        if (operatorTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operatorTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operatorTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperatorTypeDTO> result = operatorTypeService.partialUpdate(operatorTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /operator-types} : get all the operatorTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operatorTypes in body.
     */
    @GetMapping("/operator-types")
    public ResponseEntity<List<OperatorTypeDTO>> getAllOperatorTypes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of OperatorTypes");
        Page<OperatorTypeDTO> page = operatorTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operator-types/:id} : get the "id" operatorType.
     *
     * @param id the id of the operatorTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operatorTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operator-types/{id}")
    public ResponseEntity<OperatorTypeDTO> getOperatorType(@PathVariable Long id) {
        log.debug("REST request to get OperatorType : {}", id);
        Optional<OperatorTypeDTO> operatorTypeDTO = operatorTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operatorTypeDTO);
    }

    /**
     * {@code DELETE  /operator-types/:id} : delete the "id" operatorType.
     *
     * @param id the id of the operatorTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operator-types/{id}")
    public ResponseEntity<Void> deleteOperatorType(@PathVariable Long id) {
        log.debug("REST request to delete OperatorType : {}", id);
        operatorTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
