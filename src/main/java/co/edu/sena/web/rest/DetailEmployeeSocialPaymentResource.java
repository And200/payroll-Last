package co.edu.sena.web.rest;

import co.edu.sena.repository.DetailEmployeeSocialPaymentRepository;
import co.edu.sena.service.DetailEmployeeSocialPaymentService;
import co.edu.sena.service.dto.DetailEmployeeSocialPaymentDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.DetailEmployeeSocialPayment}.
 */
@RestController
@RequestMapping("/api")
public class DetailEmployeeSocialPaymentResource {

    private final Logger log = LoggerFactory.getLogger(DetailEmployeeSocialPaymentResource.class);

    private static final String ENTITY_NAME = "detailEmployeeSocialPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailEmployeeSocialPaymentService detailEmployeeSocialPaymentService;

    private final DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepository;

    public DetailEmployeeSocialPaymentResource(
        DetailEmployeeSocialPaymentService detailEmployeeSocialPaymentService,
        DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepository
    ) {
        this.detailEmployeeSocialPaymentService = detailEmployeeSocialPaymentService;
        this.detailEmployeeSocialPaymentRepository = detailEmployeeSocialPaymentRepository;
    }

    /**
     * {@code POST  /detail-employee-social-payments} : Create a new detailEmployeeSocialPayment.
     *
     * @param detailEmployeeSocialPaymentDTO the detailEmployeeSocialPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detailEmployeeSocialPaymentDTO, or with status {@code 400 (Bad Request)} if the detailEmployeeSocialPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detail-employee-social-payments")
    public ResponseEntity<DetailEmployeeSocialPaymentDTO> createDetailEmployeeSocialPayment(
        @Valid @RequestBody DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save DetailEmployeeSocialPayment : {}", detailEmployeeSocialPaymentDTO);
        if (detailEmployeeSocialPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new detailEmployeeSocialPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailEmployeeSocialPaymentDTO result = detailEmployeeSocialPaymentService.save(detailEmployeeSocialPaymentDTO);
        return ResponseEntity
            .created(new URI("/api/detail-employee-social-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detail-employee-social-payments/:id} : Updates an existing detailEmployeeSocialPayment.
     *
     * @param id the id of the detailEmployeeSocialPaymentDTO to save.
     * @param detailEmployeeSocialPaymentDTO the detailEmployeeSocialPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailEmployeeSocialPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the detailEmployeeSocialPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detailEmployeeSocialPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detail-employee-social-payments/{id}")
    public ResponseEntity<DetailEmployeeSocialPaymentDTO> updateDetailEmployeeSocialPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DetailEmployeeSocialPayment : {}, {}", id, detailEmployeeSocialPaymentDTO);
        if (detailEmployeeSocialPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailEmployeeSocialPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailEmployeeSocialPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DetailEmployeeSocialPaymentDTO result = detailEmployeeSocialPaymentService.update(detailEmployeeSocialPaymentDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailEmployeeSocialPaymentDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /detail-employee-social-payments/:id} : Partial updates given fields of an existing detailEmployeeSocialPayment, field will ignore if it is null
     *
     * @param id the id of the detailEmployeeSocialPaymentDTO to save.
     * @param detailEmployeeSocialPaymentDTO the detailEmployeeSocialPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detailEmployeeSocialPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the detailEmployeeSocialPaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the detailEmployeeSocialPaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the detailEmployeeSocialPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/detail-employee-social-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DetailEmployeeSocialPaymentDTO> partialUpdateDetailEmployeeSocialPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DetailEmployeeSocialPayment partially : {}, {}", id, detailEmployeeSocialPaymentDTO);
        if (detailEmployeeSocialPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, detailEmployeeSocialPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!detailEmployeeSocialPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DetailEmployeeSocialPaymentDTO> result = detailEmployeeSocialPaymentService.partialUpdate(detailEmployeeSocialPaymentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailEmployeeSocialPaymentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /detail-employee-social-payments} : get all the detailEmployeeSocialPayments.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detailEmployeeSocialPayments in body.
     */
    @GetMapping("/detail-employee-social-payments")
    public ResponseEntity<List<DetailEmployeeSocialPaymentDTO>> getAllDetailEmployeeSocialPayments(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of DetailEmployeeSocialPayments");
        Page<DetailEmployeeSocialPaymentDTO> page;
        if (eagerload) {
            page = detailEmployeeSocialPaymentService.findAllWithEagerRelationships(pageable);
        } else {
            page = detailEmployeeSocialPaymentService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /detail-employee-social-payments/:id} : get the "id" detailEmployeeSocialPayment.
     *
     * @param id the id of the detailEmployeeSocialPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detailEmployeeSocialPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detail-employee-social-payments/{id}")
    public ResponseEntity<DetailEmployeeSocialPaymentDTO> getDetailEmployeeSocialPayment(@PathVariable Long id) {
        log.debug("REST request to get DetailEmployeeSocialPayment : {}", id);
        Optional<DetailEmployeeSocialPaymentDTO> detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailEmployeeSocialPaymentDTO);
    }

    /**
     * {@code DELETE  /detail-employee-social-payments/:id} : delete the "id" detailEmployeeSocialPayment.
     *
     * @param id the id of the detailEmployeeSocialPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detail-employee-social-payments/{id}")
    public ResponseEntity<Void> deleteDetailEmployeeSocialPayment(@PathVariable Long id) {
        log.debug("REST request to delete DetailEmployeeSocialPayment : {}", id);
        detailEmployeeSocialPaymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
