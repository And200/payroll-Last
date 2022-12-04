package co.edu.sena.web.rest;

import co.edu.sena.repository.PayrollRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.PayrollService;
import co.edu.sena.service.dto.PayrollDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.Payroll}.
 */
@RestController
@RequestMapping("/api")
public class PayrollResource {

    private final Logger log = LoggerFactory.getLogger(PayrollResource.class);

    private static final String ENTITY_NAME = "payroll";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayrollService payrollService;

    private final PayrollRepository payrollRepository;

    public PayrollResource(PayrollService payrollService, PayrollRepository payrollRepository) {
        this.payrollService = payrollService;
        this.payrollRepository = payrollRepository;
    }

    /**
     * {@code POST  /payrolls} : Create a new payroll.
     *
     * @param payrollDTO the payrollDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payrollDTO, or with status {@code 400 (Bad Request)} if the payroll has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payrolls")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<PayrollDTO> createPayroll(@Valid @RequestBody PayrollDTO payrollDTO) throws URISyntaxException {
        log.debug("REST request to save Payroll : {}", payrollDTO);
        if (payrollDTO.getId() != null) {
            throw new BadRequestAlertException("A new payroll cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayrollDTO result = payrollService.save(payrollDTO);
        return ResponseEntity
            .created(new URI("/api/payrolls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payrolls/:id} : Updates an existing payroll.
     *
     * @param id the id of the payrollDTO to save.
     * @param payrollDTO the payrollDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payrollDTO,
     * or with status {@code 400 (Bad Request)} if the payrollDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payrollDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payrolls/{id}")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<PayrollDTO> updatePayroll(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PayrollDTO payrollDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Payroll : {}, {}", id, payrollDTO);
        if (payrollDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payrollDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payrollRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PayrollDTO result = payrollService.update(payrollDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payrollDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /payrolls/:id} : Partial updates given fields of an existing payroll, field will ignore if it is null
     *
     * @param id the id of the payrollDTO to save.
     * @param payrollDTO the payrollDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payrollDTO,
     * or with status {@code 400 (Bad Request)} if the payrollDTO is not valid,
     * or with status {@code 404 (Not Found)} if the payrollDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the payrollDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payrolls/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<PayrollDTO> partialUpdatePayroll(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PayrollDTO payrollDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Payroll partially : {}, {}", id, payrollDTO);
        if (payrollDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, payrollDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!payrollRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PayrollDTO> result = payrollService.partialUpdate(payrollDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payrollDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payrolls} : get all the payrolls.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payrolls in body.
     */
    @GetMapping("/payrolls")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<List<PayrollDTO>> getAllPayrolls(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable,
        @RequestParam(required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Payrolls");
        Page<PayrollDTO> page;
        if (eagerload) {
            page = payrollService.findAllWithEagerRelationships(pageable);
        } else {
            page = payrollService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payrolls/:id} : get the "id" payroll.
     *
     * @param id the id of the payrollDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payrollDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payrolls/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')")
    public ResponseEntity<PayrollDTO> getPayroll(@PathVariable Long id) {
        log.debug("REST request to get Payroll : {}", id);
        Optional<PayrollDTO> payrollDTO = payrollService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payrollDTO);
    }

    /**
     * {@code DELETE  /payrolls/:id} : delete the "id" payroll.
     *
     * @param id the id of the payrollDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payrolls/{id}")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        log.debug("REST request to delete Payroll : {}", id);
        payrollService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
