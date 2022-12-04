package co.edu.sena.web.rest;

import co.edu.sena.repository.SocialSecurityRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.SocialSecurityService;
import co.edu.sena.service.dto.SocialSecurityDTO;
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
 * REST controller for managing {@link co.edu.sena.domain.SocialSecurity}.
 */
@RestController
@RequestMapping("/api")
public class SocialSecurityResource {

    private final Logger log = LoggerFactory.getLogger(SocialSecurityResource.class);

    private static final String ENTITY_NAME = "socialSecurity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SocialSecurityService socialSecurityService;

    private final SocialSecurityRepository socialSecurityRepository;

    public SocialSecurityResource(SocialSecurityService socialSecurityService, SocialSecurityRepository socialSecurityRepository) {
        this.socialSecurityService = socialSecurityService;
        this.socialSecurityRepository = socialSecurityRepository;
    }

    /**
     * {@code POST  /social-securities} : Create a new socialSecurity.
     *
     * @param socialSecurityDTO the socialSecurityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new socialSecurityDTO, or with status {@code 400 (Bad Request)} if the socialSecurity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/social-securities")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialSecurityDTO> createSocialSecurity(@Valid @RequestBody SocialSecurityDTO socialSecurityDTO)
        throws URISyntaxException {
        log.debug("REST request to save SocialSecurity : {}", socialSecurityDTO);
        if (socialSecurityDTO.getId() != null) {
            throw new BadRequestAlertException("A new socialSecurity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SocialSecurityDTO result = socialSecurityService.save(socialSecurityDTO);
        return ResponseEntity
            .created(new URI("/api/social-securities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /social-securities/:id} : Updates an existing socialSecurity.
     *
     * @param id the id of the socialSecurityDTO to save.
     * @param socialSecurityDTO the socialSecurityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialSecurityDTO,
     * or with status {@code 400 (Bad Request)} if the socialSecurityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the socialSecurityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/social-securities/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialSecurityDTO> updateSocialSecurity(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SocialSecurityDTO socialSecurityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SocialSecurity : {}, {}", id, socialSecurityDTO);
        if (socialSecurityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, socialSecurityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!socialSecurityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SocialSecurityDTO result = socialSecurityService.update(socialSecurityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialSecurityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /social-securities/:id} : Partial updates given fields of an existing socialSecurity, field will ignore if it is null
     *
     * @param id the id of the socialSecurityDTO to save.
     * @param socialSecurityDTO the socialSecurityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated socialSecurityDTO,
     * or with status {@code 400 (Bad Request)} if the socialSecurityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the socialSecurityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the socialSecurityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/social-securities/{id}", consumes = { "application/json", "application/merge-patch+json" })
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialSecurityDTO> partialUpdateSocialSecurity(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SocialSecurityDTO socialSecurityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SocialSecurity partially : {}, {}", id, socialSecurityDTO);
        if (socialSecurityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, socialSecurityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!socialSecurityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SocialSecurityDTO> result = socialSecurityService.partialUpdate(socialSecurityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, socialSecurityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /social-securities} : get all the socialSecurities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of socialSecurities in body.
     */
    @GetMapping("/social-securities")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<List<SocialSecurityDTO>> getAllSocialSecurities(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SocialSecurities");
        Page<SocialSecurityDTO> page = socialSecurityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /social-securities/:id} : get the "id" socialSecurity.
     *
     * @param id the id of the socialSecurityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the socialSecurityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/social-securities/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<SocialSecurityDTO> getSocialSecurity(@PathVariable Long id) {
        log.debug("REST request to get SocialSecurity : {}", id);
        Optional<SocialSecurityDTO> socialSecurityDTO = socialSecurityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(socialSecurityDTO);
    }

    /**
     * {@code DELETE  /social-securities/:id} : delete the "id" socialSecurity.
     *
     * @param id the id of the socialSecurityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/social-securities/{id}")
    @PreAuthorize("hasAuthority('" + AuthoritiesConstants.ADMIN + "')or hasAuthority('" + AuthoritiesConstants.MANAGER + "')")
    public ResponseEntity<Void> deleteSocialSecurity(@PathVariable Long id) {
        log.debug("REST request to delete SocialSecurity : {}", id);
        socialSecurityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
