package co.edu.sena.web.rest;

import co.edu.sena.repository.IncomeRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.IncomeService;
import co.edu.sena.service.dto.IncomeDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.Income}.
 */
@RestController
@RequestMapping("/api")
public class IncomeResource {

    private final Logger log = LoggerFactory.getLogger(IncomeResource.class);

    private static final String ENTITY_NAME = "income";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncomeService incomeService;

    private final IncomeRepository incomeRepository;

    public IncomeResource(IncomeService incomeService, IncomeRepository incomeRepository) {
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
    }

    /**
     * {@code POST  /incomes} : Create a new income.
     *
     * @param incomeDTO the incomeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incomeDTO, or with status {@code 400 (Bad Request)} if the income has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incomes")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<IncomeDTO> createIncome(@Valid @RequestBody IncomeDTO incomeDTO) throws URISyntaxException {
        log.debug("REST request to save Income : {}", incomeDTO);
        if (incomeDTO.getId() != null) {
            throw new BadRequestAlertException("A new income cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncomeDTO result = incomeService.save(incomeDTO);
        return ResponseEntity
            .created(new URI("/api/incomes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /incomes/:id} : Updates an existing income.
     *
     * @param id the id of the incomeDTO to save.
     * @param incomeDTO the incomeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomeDTO,
     * or with status {@code 400 (Bad Request)} if the incomeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incomeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incomes/{id}")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<IncomeDTO> updateIncome(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody IncomeDTO incomeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Income : {}, {}", id, incomeDTO);
        if (incomeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incomeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        IncomeDTO result = incomeService.update(incomeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incomeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /incomes/:id} : Partial updates given fields of an existing income, field will ignore if it is null
     *
     * @param id the id of the incomeDTO to save.
     * @param incomeDTO the incomeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomeDTO,
     * or with status {@code 400 (Bad Request)} if the incomeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the incomeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the incomeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/incomes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<IncomeDTO> partialUpdateIncome(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody IncomeDTO incomeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Income partially : {}, {}", id, incomeDTO);
        if (incomeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incomeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IncomeDTO> result = incomeService.partialUpdate(incomeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incomeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /incomes} : get all the incomes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incomes in body.
     */
    @GetMapping("/incomes")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<List<IncomeDTO>> getAllIncomes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Incomes");
        Page<IncomeDTO> page = incomeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /incomes/:id} : get the "id" income.
     *
     * @param id the id of the incomeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incomeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incomes/{id}")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<IncomeDTO> getIncome(@PathVariable Long id) {
        log.debug("REST request to get Income : {}", id);
        Optional<IncomeDTO> incomeDTO = incomeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incomeDTO);
    }

    /**
     * {@code DELETE  /incomes/:id} : delete the "id" income.
     *
     * @param id the id of the incomeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incomes/{id}")
    @PreAuthorize(
        "hasAuthority('" +
        AuthoritiesConstants.ADMIN +
        "')or hasAuthority('" +
        AuthoritiesConstants.MANAGER +
        "')or hasAuthority('" +
        AuthoritiesConstants.ASSISTANT +
        "')"
    )
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        log.debug("REST request to delete Income : {}", id);
        incomeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
