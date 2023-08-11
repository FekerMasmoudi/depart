package tn.soretras.depart.web.rest;

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
import tn.soretras.depart.repository.AffectagentRepository;
import tn.soretras.depart.service.AffectagentService;
import tn.soretras.depart.service.dto.AffectagentDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Affectagent}.
 */
@RestController
@RequestMapping("/api")
public class AffectagentResource {

    private final Logger log = LoggerFactory.getLogger(AffectagentResource.class);

    private static final String ENTITY_NAME = "affectagent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AffectagentService affectagentService;

    private final AffectagentRepository affectagentRepository;

    public AffectagentResource(AffectagentService affectagentService, AffectagentRepository affectagentRepository) {
        this.affectagentService = affectagentService;
        this.affectagentRepository = affectagentRepository;
    }

    /**
     * {@code POST  /affectagents} : Create a new affectagent.
     *
     * @param affectagentDTO the affectagentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new affectagentDTO, or with status {@code 400 (Bad Request)} if the affectagent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/affectagents")
    public ResponseEntity<AffectagentDTO> createAffectagent(@Valid @RequestBody AffectagentDTO affectagentDTO) throws URISyntaxException {
        log.debug("REST request to save Affectagent : {}", affectagentDTO);
        if (affectagentDTO.getId() != null) {
            throw new BadRequestAlertException("A new affectagent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AffectagentDTO result = affectagentService.save(affectagentDTO);
        return ResponseEntity
            .created(new URI("/api/affectagents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /affectagents/:id} : Updates an existing affectagent.
     *
     * @param id the id of the affectagentDTO to save.
     * @param affectagentDTO the affectagentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affectagentDTO,
     * or with status {@code 400 (Bad Request)} if the affectagentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the affectagentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/affectagents/{id}")
    public ResponseEntity<AffectagentDTO> updateAffectagent(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody AffectagentDTO affectagentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Affectagent : {}, {}", id, affectagentDTO);
        if (affectagentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affectagentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affectagentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AffectagentDTO result = affectagentService.update(affectagentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affectagentDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /affectagents/:id} : Partial updates given fields of an existing affectagent, field will ignore if it is null
     *
     * @param id the id of the affectagentDTO to save.
     * @param affectagentDTO the affectagentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affectagentDTO,
     * or with status {@code 400 (Bad Request)} if the affectagentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the affectagentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the affectagentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/affectagents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AffectagentDTO> partialUpdateAffectagent(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody AffectagentDTO affectagentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Affectagent partially : {}, {}", id, affectagentDTO);
        if (affectagentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affectagentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affectagentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AffectagentDTO> result = affectagentService.partialUpdate(affectagentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affectagentDTO.getId())
        );
    }

    /**
     * {@code GET  /affectagents} : get all the affectagents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of affectagents in body.
     */
    @GetMapping("/affectagents")
    public ResponseEntity<List<AffectagentDTO>> getAllAffectagents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Affectagents");
        Page<AffectagentDTO> page = affectagentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /affectagents/:id} : get the "id" affectagent.
     *
     * @param id the id of the affectagentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the affectagentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/affectagents/{id}")
    public ResponseEntity<AffectagentDTO> getAffectagent(@PathVariable String id) {
        log.debug("REST request to get Affectagent : {}", id);
        Optional<AffectagentDTO> affectagentDTO = affectagentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(affectagentDTO);
    }

    /**
     * {@code DELETE  /affectagents/:id} : delete the "id" affectagent.
     *
     * @param id the id of the affectagentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/affectagents/{id}")
    public ResponseEntity<Void> deleteAffectagent(@PathVariable String id) {
        log.debug("REST request to delete Affectagent : {}", id);
        affectagentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
