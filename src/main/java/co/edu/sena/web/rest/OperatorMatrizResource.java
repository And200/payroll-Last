package co.edu.sena.web.rest;

import co.edu.sena.repository.OperatorMatrizRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.OperatorMatrizService;
import co.edu.sena.service.dto.OperatorMatrizDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link co.edu.sena.domain.OperatorMatriz}.
 */
@RestController
@RequestMapping("/api")
public class OperatorMatrizResource {

    private final Logger log = LoggerFactory.getLogger(OperatorMatrizResource.class);

    private static final String ENTITY_NAME = "operatorMatriz";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperatorMatrizService operatorMatrizService;

    private final OperatorMatrizRepository operatorMatrizRepository;

    public OperatorMatrizResource(OperatorMatrizService operatorMatrizService, OperatorMatrizRepository operatorMatrizRepository) {
        this.operatorMatrizService = operatorMatrizService;
        this.operatorMatrizRepository = operatorMatrizRepository;
    }

    /**
     * {@code POST  /operator-matrizs} : Create a new operatorMatriz.
     *
     * @param operatorMatrizDTO the operatorMatrizDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operatorMatrizDTO, or with status {@code 400 (Bad Request)} if the operatorMatriz has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operator-matrizs")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<OperatorMatrizDTO> createOperatorMatriz(@Valid @RequestBody OperatorMatrizDTO operatorMatrizDTO)
        throws URISyntaxException {
        log.debug("REST request to save OperatorMatriz : {}", operatorMatrizDTO);
        if (operatorMatrizDTO.getId() != null) {
            throw new BadRequestAlertException("A new operatorMatriz cannot already have an ID", ENTITY_NAME, "idexists");
        } else if (operatorMatrizRepository.findByName(operatorMatrizDTO.getName()).isPresent()) {
            throw new BadRequestAlertException("A new OperatorMatriz cannot already have an existing Name", ENTITY_NAME, "nameExist");
        }
        OperatorMatrizDTO result = operatorMatrizService.save(operatorMatrizDTO);
        return ResponseEntity
            .created(new URI("/api/operator-matrizs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operator-matrizs/:id} : Updates an existing operatorMatriz.
     *
     * @param id the id of the operatorMatrizDTO to save.
     * @param operatorMatrizDTO the operatorMatrizDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorMatrizDTO,
     * or with status {@code 400 (Bad Request)} if the operatorMatrizDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operatorMatrizDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operator-matrizs/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<OperatorMatrizDTO> updateOperatorMatriz(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OperatorMatrizDTO operatorMatrizDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OperatorMatriz : {}, {}", id, operatorMatrizDTO);
        if (operatorMatrizDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operatorMatrizDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operatorMatrizRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperatorMatrizDTO result = operatorMatrizService.update(operatorMatrizDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorMatrizDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /operator-matrizs/:id} : Partial updates given fields of an existing operatorMatriz, field will ignore if it is null
     *
     * @param id the id of the operatorMatrizDTO to save.
     * @param operatorMatrizDTO the operatorMatrizDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operatorMatrizDTO,
     * or with status {@code 400 (Bad Request)} if the operatorMatrizDTO is not valid,
     * or with status {@code 404 (Not Found)} if the operatorMatrizDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the operatorMatrizDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/operator-matrizs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<OperatorMatrizDTO> partialUpdateOperatorMatriz(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OperatorMatrizDTO operatorMatrizDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperatorMatriz partially : {}, {}", id, operatorMatrizDTO);
        if (operatorMatrizDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operatorMatrizDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operatorMatrizRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperatorMatrizDTO> result = operatorMatrizService.partialUpdate(operatorMatrizDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operatorMatrizDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /operator-matrizs} : get all the operatorMatrizs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operatorMatrizs in body.
     */
    @GetMapping("/operator-matrizs")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<List<OperatorMatrizDTO>> getAllOperatorMatrizs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of OperatorMatrizs");
        Page<OperatorMatrizDTO> page = operatorMatrizService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operator-matrizs/:id} : get the "id" operatorMatriz.
     *
     * @param id the id of the operatorMatrizDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operatorMatrizDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operator-matrizs/{id}")
    public ResponseEntity<OperatorMatrizDTO> getOperatorMatriz(@PathVariable Long id) {
        log.debug("REST request to get OperatorMatriz : {}", id);
        Optional<OperatorMatrizDTO> operatorMatrizDTO = operatorMatrizService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operatorMatrizDTO);
    }

    /**
     * {@code DELETE  /operator-matrizs/:id} : delete the "id" operatorMatriz.
     *
     * @param id the id of the operatorMatrizDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operator-matrizs/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<Void> deleteOperatorMatriz(@PathVariable Long id) {
        log.debug("REST request to delete OperatorMatriz : {}", id);
        operatorMatrizService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
