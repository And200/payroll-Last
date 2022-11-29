package co.edu.sena.web.rest;

import co.edu.sena.repository.PositionArlRepository;
import co.edu.sena.service.PositionArlService;
import co.edu.sena.service.dto.PositionArlDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.PositionArl}.
 */
@RestController
@RequestMapping("/api")
public class PositionArlResource {

    private final Logger log = LoggerFactory.getLogger(PositionArlResource.class);

    private static final String ENTITY_NAME = "positionArl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PositionArlService positionArlService;

    private final PositionArlRepository positionArlRepository;

    public PositionArlResource(PositionArlService positionArlService, PositionArlRepository positionArlRepository) {
        this.positionArlService = positionArlService;
        this.positionArlRepository = positionArlRepository;
    }

    /**
     * {@code POST  /position-arls} : Create a new positionArl.
     *
     * @param positionArlDTO the positionArlDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new positionArlDTO, or with status {@code 400 (Bad Request)} if the positionArl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/position-arls")
    public ResponseEntity<PositionArlDTO> createPositionArl(@Valid @RequestBody PositionArlDTO positionArlDTO) throws URISyntaxException {
        log.debug("REST request to save PositionArl : {}", positionArlDTO);
        if (positionArlDTO.getId() != null) {
            throw new BadRequestAlertException("A new positionArl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PositionArlDTO result = positionArlService.save(positionArlDTO);
        return ResponseEntity
            .created(new URI("/api/position-arls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /position-arls/:id} : Updates an existing positionArl.
     *
     * @param id the id of the positionArlDTO to save.
     * @param positionArlDTO the positionArlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated positionArlDTO,
     * or with status {@code 400 (Bad Request)} if the positionArlDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the positionArlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/position-arls/{id}")
    public ResponseEntity<PositionArlDTO> updatePositionArl(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PositionArlDTO positionArlDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PositionArl : {}, {}", id, positionArlDTO);
        if (positionArlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, positionArlDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!positionArlRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PositionArlDTO result = positionArlService.update(positionArlDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, positionArlDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /position-arls/:id} : Partial updates given fields of an existing positionArl, field will ignore if it is null
     *
     * @param id the id of the positionArlDTO to save.
     * @param positionArlDTO the positionArlDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated positionArlDTO,
     * or with status {@code 400 (Bad Request)} if the positionArlDTO is not valid,
     * or with status {@code 404 (Not Found)} if the positionArlDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the positionArlDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/position-arls/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PositionArlDTO> partialUpdatePositionArl(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PositionArlDTO positionArlDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PositionArl partially : {}, {}", id, positionArlDTO);
        if (positionArlDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, positionArlDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!positionArlRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PositionArlDTO> result = positionArlService.partialUpdate(positionArlDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, positionArlDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /position-arls} : get all the positionArls.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of positionArls in body.
     */
    @GetMapping("/position-arls")
    public ResponseEntity<List<PositionArlDTO>> getAllPositionArls(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PositionArls");
        Page<PositionArlDTO> page = positionArlService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /position-arls/:id} : get the "id" positionArl.
     *
     * @param id the id of the positionArlDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the positionArlDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/position-arls/{id}")
    public ResponseEntity<PositionArlDTO> getPositionArl(@PathVariable Long id) {
        log.debug("REST request to get PositionArl : {}", id);
        Optional<PositionArlDTO> positionArlDTO = positionArlService.findOne(id);
        return ResponseUtil.wrapOrNotFound(positionArlDTO);
    }

    /**
     * {@code DELETE  /position-arls/:id} : delete the "id" positionArl.
     *
     * @param id the id of the positionArlDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/position-arls/{id}")
    public ResponseEntity<Void> deletePositionArl(@PathVariable Long id) {
        log.debug("REST request to delete PositionArl : {}", id);
        positionArlService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
