package co.edu.sena.web.rest;

import co.edu.sena.repository.AccountPlanRepository;
import co.edu.sena.service.AccountPlanService;
import co.edu.sena.service.dto.AccountPlanDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.AccountPlan}.
 */
@RestController
@RequestMapping("/api")
public class AccountPlanResource {

    private final Logger log = LoggerFactory.getLogger(AccountPlanResource.class);

    private static final String ENTITY_NAME = "accountPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountPlanService accountPlanService;

    private final AccountPlanRepository accountPlanRepository;

    public AccountPlanResource(AccountPlanService accountPlanService, AccountPlanRepository accountPlanRepository) {
        this.accountPlanService = accountPlanService;
        this.accountPlanRepository = accountPlanRepository;
    }

    /**
     * {@code POST  /account-plans} : Create a new accountPlan.
     *
     * @param accountPlanDTO the accountPlanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountPlanDTO, or with status {@code 400 (Bad Request)} if the accountPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-plans")
    public ResponseEntity<AccountPlanDTO> createAccountPlan(@Valid @RequestBody AccountPlanDTO accountPlanDTO) throws URISyntaxException {
        log.debug("REST request to save AccountPlan : {}", accountPlanDTO);
        if (accountPlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountPlanDTO result = accountPlanService.save(accountPlanDTO);
        return ResponseEntity
            .created(new URI("/api/account-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-plans/:id} : Updates an existing accountPlan.
     *
     * @param id the id of the accountPlanDTO to save.
     * @param accountPlanDTO the accountPlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountPlanDTO,
     * or with status {@code 400 (Bad Request)} if the accountPlanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountPlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-plans/{id}")
    public ResponseEntity<AccountPlanDTO> updateAccountPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AccountPlanDTO accountPlanDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AccountPlan : {}, {}", id, accountPlanDTO);
        if (accountPlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountPlanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accountPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AccountPlanDTO result = accountPlanService.update(accountPlanDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountPlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /account-plans/:id} : Partial updates given fields of an existing accountPlan, field will ignore if it is null
     *
     * @param id the id of the accountPlanDTO to save.
     * @param accountPlanDTO the accountPlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountPlanDTO,
     * or with status {@code 400 (Bad Request)} if the accountPlanDTO is not valid,
     * or with status {@code 404 (Not Found)} if the accountPlanDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the accountPlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/account-plans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AccountPlanDTO> partialUpdateAccountPlan(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AccountPlanDTO accountPlanDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AccountPlan partially : {}, {}", id, accountPlanDTO);
        if (accountPlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountPlanDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accountPlanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AccountPlanDTO> result = accountPlanService.partialUpdate(accountPlanDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, accountPlanDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /account-plans} : get all the accountPlans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountPlans in body.
     */
    @GetMapping("/account-plans")
    public ResponseEntity<List<AccountPlanDTO>> getAllAccountPlans(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AccountPlans");
        Page<AccountPlanDTO> page = accountPlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /account-plans/:id} : get the "id" accountPlan.
     *
     * @param id the id of the accountPlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountPlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-plans/{id}")
    public ResponseEntity<AccountPlanDTO> getAccountPlan(@PathVariable Long id) {
        log.debug("REST request to get AccountPlan : {}", id);
        Optional<AccountPlanDTO> accountPlanDTO = accountPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountPlanDTO);
    }

    /**
     * {@code DELETE  /account-plans/:id} : delete the "id" accountPlan.
     *
     * @param id the id of the accountPlanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-plans/{id}")
    public ResponseEntity<Void> deleteAccountPlan(@PathVariable Long id) {
        log.debug("REST request to delete AccountPlan : {}", id);
        accountPlanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
