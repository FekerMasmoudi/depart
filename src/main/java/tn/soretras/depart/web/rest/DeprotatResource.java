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
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.service.DeprotatService;
import tn.soretras.depart.service.dto.DeprotatDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Deprotat}.
 */
@RestController
@RequestMapping("/api")
public class DeprotatResource {

    private final Logger log = LoggerFactory.getLogger(DeprotatResource.class);

    private static final String ENTITY_NAME = "deprotat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeprotatService deprotatService;

    private final DeprotatRepository deprotatRepository;

    public DeprotatResource(DeprotatService deprotatService, DeprotatRepository deprotatRepository) {
        this.deprotatService = deprotatService;
        this.deprotatRepository = deprotatRepository;
    }

    /**
     * {@code POST  /deprotats} : Create a new deprotat.
     *
     * @param deprotatDTO the deprotatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deprotatDTO, or with status {@code 400 (Bad Request)} if the deprotat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deprotats")
    public ResponseEntity<DeprotatDTO> createDeprotat(@Valid @RequestBody DeprotatDTO deprotatDTO) throws URISyntaxException {
        log.debug("REST request to save Deprotat : {}", deprotatDTO);
        if (deprotatDTO.getId() != null) {
            throw new BadRequestAlertException("A new deprotat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeprotatDTO result = deprotatService.save(deprotatDTO);
        return ResponseEntity
            .created(new URI("/api/deprotats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /deprotats/:id} : Updates an existing deprotat.
     *
     * @param id the id of the deprotatDTO to save.
     * @param deprotatDTO the deprotatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deprotatDTO,
     * or with status {@code 400 (Bad Request)} if the deprotatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deprotatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deprotats/{id}")
    public ResponseEntity<DeprotatDTO> updateDeprotat(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DeprotatDTO deprotatDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Deprotat : {}, {}", id, deprotatDTO);
        if (deprotatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deprotatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deprotatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeprotatDTO result = deprotatService.update(deprotatDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deprotatDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /deprotats/:id} : Partial updates given fields of an existing deprotat, field will ignore if it is null
     *
     * @param id the id of the deprotatDTO to save.
     * @param deprotatDTO the deprotatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deprotatDTO,
     * or with status {@code 400 (Bad Request)} if the deprotatDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deprotatDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deprotatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/deprotats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeprotatDTO> partialUpdateDeprotat(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DeprotatDTO deprotatDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Deprotat partially : {}, {}", id, deprotatDTO);
        if (deprotatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deprotatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deprotatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeprotatDTO> result = deprotatService.partialUpdate(deprotatDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deprotatDTO.getId())
        );
    }

    /**
     * {@code GET  /deprotats} : get all the deprotats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deprotats in body.
     */
    @GetMapping("/deprotats")
    public ResponseEntity<List<DeprotatDTO>> getAllDeprotats(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Deprotats");
        Page<DeprotatDTO> page = deprotatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /deprotats/:id} : get the "id" deprotat.
     *
     * @param id the id of the deprotatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deprotatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deprotats/{id}")
    public ResponseEntity<DeprotatDTO> getDeprotat(@PathVariable String id) {
        log.debug("REST request to get Deprotat : {}", id);
        Optional<DeprotatDTO> deprotatDTO = deprotatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deprotatDTO);
    }

    /**
     * {@code DELETE  /deprotats/:id} : delete the "id" deprotat.
     *
     * @param id the id of the deprotatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deprotats/{id}")
    public ResponseEntity<Void> deleteDeprotat(@PathVariable String id) {
        log.debug("REST request to delete Deprotat : {}", id);
        deprotatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
