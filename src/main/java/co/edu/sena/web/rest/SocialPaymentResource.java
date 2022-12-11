package co.edu.sena.web.rest;

import co.edu.sena.repository.SocialPaymentRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.SocialPaymentService;
import co.edu.sena.service.dto.SocialPaymentDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.SocialPayment}.
 */
@RestController
@RequestMapping("/api")
public class SocialPaymentResource {

    private final Logger log = LoggerFactory.getLogger(SocialPaymentResource.class);

    private static final String ENTITY_NAME = "socialPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocialPaymentService socialPaymentService;

    private final SocialPaymentRepository socialPaymentRepository;

    public SocialPaymentResource(SocialPaymentService socialPaymentService, SocialPaymentRepository socialPaymentRepository) {
        this.socialPaymentService = socialPaymentService;
        this.socialPaymentRepository = socialPaymentRepository;
    }

    /**
     * {@code POST  /social-payments} : Create a new socialPayment.
     *
     * @param socialPaymentDTO the socialPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new socialPaymentDTO, or with status {@code 400 (Bad Request)} if the socialPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/social-payments")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialPaymentDTO> createSocialPayment(@Valid @RequestBody SocialPaymentDTO socialPaymentDTO)
        throws URISyntaxException {
        log.debug("REST request to save SocialPayment : {}", socialPaymentDTO);
        if (socialPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new socialPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocialPaymentDTO result = socialPaymentService.save(socialPaymentDTO);
        return ResponseEntity
            .created(new URI("/api/social-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /social-payments/:id} : Updates an existing socialPayment.
     *
     * @param id the id of the socialPaymentDTO to save.
     * @param socialPaymentDTO the socialPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the socialPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the socialPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/social-payments/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialPaymentDTO> updateSocialPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SocialPaymentDTO socialPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SocialPayment : {}, {}", id, socialPaymentDTO);
        if (socialPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, socialPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!socialPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SocialPaymentDTO result = socialPaymentService.update(socialPaymentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialPaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /social-payments/:id} : Partial updates given fields of an existing socialPayment, field will ignore if it is null
     *
     * @param id the id of the socialPaymentDTO to save.
     * @param socialPaymentDTO the socialPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the socialPaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the socialPaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the socialPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/social-payments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialPaymentDTO> partialUpdateSocialPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SocialPaymentDTO socialPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SocialPayment partially : {}, {}", id, socialPaymentDTO);
        if (socialPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, socialPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!socialPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SocialPaymentDTO> result = socialPaymentService.partialUpdate(socialPaymentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialPaymentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /social-payments} : get all the socialPayments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of socialPayments in body.
     */
    @GetMapping("/social-payments")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<List<SocialPaymentDTO>> getAllSocialPayments(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SocialPayments");
        Page<SocialPaymentDTO> page = socialPaymentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /social-payments/:id} : get the "id" socialPayment.
     *
     * @param id the id of the socialPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the socialPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/social-payments/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialPaymentDTO> getSocialPayment(@PathVariable Long id) {
        log.debug("REST request to get SocialPayment : {}", id);
        Optional<SocialPaymentDTO> socialPaymentDTO = socialPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(socialPaymentDTO);
    }

    /**
     * {@code DELETE  /social-payments/:id} : delete the "id" socialPayment.
     *
     * @param id the id of the socialPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/social-payments/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<Void> deleteSocialPayment(@PathVariable Long id) {
        log.debug("REST request to delete SocialPayment : {}", id);
        socialPaymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
