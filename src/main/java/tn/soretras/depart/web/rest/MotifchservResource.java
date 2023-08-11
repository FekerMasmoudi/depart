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
import tn.soretras.depart.repository.MotifchservRepository;
import tn.soretras.depart.service.MotifchservService;
import tn.soretras.depart.service.dto.MotifchservDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Motifchserv}.
 */
@RestController
@RequestMapping("/api")
public class MotifchservResource {

    private final Logger log = LoggerFactory.getLogger(MotifchservResource.class);

    private static final String ENTITY_NAME = "motifchserv";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MotifchservService motifchservService;

    private final MotifchservRepository motifchservRepository;

    public MotifchservResource(MotifchservService motifchservService, MotifchservRepository motifchservRepository) {
        this.motifchservService = motifchservService;
        this.motifchservRepository = motifchservRepository;
    }

    /**
     * {@code POST  /motifchservs} : Create a new motifchserv.
     *
     * @param motifchservDTO the motifchservDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new motifchservDTO, or with status {@code 400 (Bad Request)} if the motifchserv has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/motifchservs")
    public ResponseEntity<MotifchservDTO> createMotifchserv(@Valid @RequestBody MotifchservDTO motifchservDTO) throws URISyntaxException {
        log.debug("REST request to save Motifchserv : {}", motifchservDTO);
        if (motifchservDTO.getId() != null) {
            throw new BadRequestAlertException("A new motifchserv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MotifchservDTO result = motifchservService.save(motifchservDTO);
        return ResponseEntity
            .created(new URI("/api/motifchservs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /motifchservs/:id} : Updates an existing motifchserv.
     *
     * @param id the id of the motifchservDTO to save.
     * @param motifchservDTO the motifchservDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motifchservDTO,
     * or with status {@code 400 (Bad Request)} if the motifchservDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the motifchservDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/motifchservs/{id}")
    public ResponseEntity<MotifchservDTO> updateMotifchserv(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody MotifchservDTO motifchservDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Motifchserv : {}, {}", id, motifchservDTO);
        if (motifchservDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, motifchservDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!motifchservRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MotifchservDTO result = motifchservService.update(motifchservDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, motifchservDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /motifchservs/:id} : Partial updates given fields of an existing motifchserv, field will ignore if it is null
     *
     * @param id the id of the motifchservDTO to save.
     * @param motifchservDTO the motifchservDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motifchservDTO,
     * or with status {@code 400 (Bad Request)} if the motifchservDTO is not valid,
     * or with status {@code 404 (Not Found)} if the motifchservDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the motifchservDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/motifchservs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MotifchservDTO> partialUpdateMotifchserv(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody MotifchservDTO motifchservDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Motifchserv partially : {}, {}", id, motifchservDTO);
        if (motifchservDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, motifchservDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!motifchservRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MotifchservDTO> result = motifchservService.partialUpdate(motifchservDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, motifchservDTO.getId())
        );
    }

    /**
     * {@code GET  /motifchservs} : get all the motifchservs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of motifchservs in body.
     */
    @GetMapping("/motifchservs")
    public ResponseEntity<List<MotifchservDTO>> getAllMotifchservs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Motifchservs");
        Page<MotifchservDTO> page = motifchservService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /motifchservs/:id} : get the "id" motifchserv.
     *
     * @param id the id of the motifchservDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the motifchservDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/motifchservs/{id}")
    public ResponseEntity<MotifchservDTO> getMotifchserv(@PathVariable String id) {
        log.debug("REST request to get Motifchserv : {}", id);
        Optional<MotifchservDTO> motifchservDTO = motifchservService.findOne(id);
        return ResponseUtil.wrapOrNotFound(motifchservDTO);
    }

    /**
     * {@code DELETE  /motifchservs/:id} : delete the "id" motifchserv.
     *
     * @param id the id of the motifchservDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/motifchservs/{id}")
    public ResponseEntity<Void> deleteMotifchserv(@PathVariable String id) {
        log.debug("REST request to delete Motifchserv : {}", id);
        motifchservService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
