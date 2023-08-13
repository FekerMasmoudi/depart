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
import tn.soretras.depart.repository.MotifaRepository;
import tn.soretras.depart.service.MotifaService;
import tn.soretras.depart.service.dto.MotifaDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Motifa}.
 */
@RestController
@RequestMapping("/api")
public class MotifaResource {

    private final Logger log = LoggerFactory.getLogger(MotifaResource.class);

    private static final String ENTITY_NAME = "motifa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MotifaService motifaService;

    private final MotifaRepository motifaRepository;

    public MotifaResource(MotifaService motifaService, MotifaRepository motifaRepository) {
        this.motifaService = motifaService;
        this.motifaRepository = motifaRepository;
    }

    /**
     * {@code POST  /motifas} : Create a new motifa.
     *
     * @param motifaDTO the motifaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new motifaDTO, or with status {@code 400 (Bad Request)} if the motifa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/motifas")
    public ResponseEntity<MotifaDTO> createMotifa(@Valid @RequestBody MotifaDTO motifaDTO) throws URISyntaxException {
        log.debug("REST request to save Motifa : {}", motifaDTO);
        if (motifaDTO.getId() != null) {
            throw new BadRequestAlertException("A new motifa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MotifaDTO result = motifaService.save(motifaDTO);
        return ResponseEntity
            .created(new URI("/api/motifas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /motifas/:id} : Updates an existing motifa.
     *
     * @param id the id of the motifaDTO to save.
     * @param motifaDTO the motifaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motifaDTO,
     * or with status {@code 400 (Bad Request)} if the motifaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the motifaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/motifas/{id}")
    public ResponseEntity<MotifaDTO> updateMotifa(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody MotifaDTO motifaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Motifa : {}, {}", id, motifaDTO);
        if (motifaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, motifaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!motifaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MotifaDTO result = motifaService.update(motifaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, motifaDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /motifas/:id} : Partial updates given fields of an existing motifa, field will ignore if it is null
     *
     * @param id the id of the motifaDTO to save.
     * @param motifaDTO the motifaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motifaDTO,
     * or with status {@code 400 (Bad Request)} if the motifaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the motifaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the motifaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/motifas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MotifaDTO> partialUpdateMotifa(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody MotifaDTO motifaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Motifa partially : {}, {}", id, motifaDTO);
        if (motifaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, motifaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!motifaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MotifaDTO> result = motifaService.partialUpdate(motifaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, motifaDTO.getId())
        );
    }

    /**
     * {@code GET  /motifas} : get all the motifas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of motifas in body.
     */
    @GetMapping("/motifas")
    public ResponseEntity<List<MotifaDTO>> getAllMotifas(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Motifas");
        Page<MotifaDTO> page = motifaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /motifas/:id} : get the "id" motifa.
     *
     * @param id the id of the motifaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the motifaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/motifas/{id}")
    public ResponseEntity<MotifaDTO> getMotifa(@PathVariable String id) {
        log.debug("REST request to get Motifa : {}", id);
        Optional<MotifaDTO> motifaDTO = motifaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(motifaDTO);
    }

    /**
     * {@code DELETE  /motifas/:id} : delete the "id" motifa.
     *
     * @param id the id of the motifaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/motifas/{id}")
    public ResponseEntity<Void> deleteMotifa(@PathVariable String id) {
        log.debug("REST request to delete Motifa : {}", id);
        motifaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
