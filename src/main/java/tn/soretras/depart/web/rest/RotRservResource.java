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
import tn.soretras.depart.repository.RotRservRepository;
import tn.soretras.depart.service.RotRservService;
import tn.soretras.depart.service.dto.RotRservDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.RotRserv}.
 */
@RestController
@RequestMapping("/api")
public class RotRservResource {

    private final Logger log = LoggerFactory.getLogger(RotRservResource.class);

    private static final String ENTITY_NAME = "rotRserv";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RotRservService rotRservService;

    private final RotRservRepository rotRservRepository;

    public RotRservResource(RotRservService rotRservService, RotRservRepository rotRservRepository) {
        this.rotRservService = rotRservService;
        this.rotRservRepository = rotRservRepository;
    }

    /**
     * {@code POST  /rot-rservs} : Create a new rotRserv.
     *
     * @param rotRservDTO the rotRservDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rotRservDTO, or with status {@code 400 (Bad Request)} if the rotRserv has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rot-rservs")
    public ResponseEntity<RotRservDTO> createRotRserv(@Valid @RequestBody RotRservDTO rotRservDTO) throws URISyntaxException {
        log.debug("REST request to save RotRserv : {}", rotRservDTO);
        if (rotRservDTO.getId() != null) {
            throw new BadRequestAlertException("A new rotRserv cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RotRservDTO result = rotRservService.save(rotRservDTO);
        return ResponseEntity
            .created(new URI("/api/rot-rservs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /rot-rservs/:id} : Updates an existing rotRserv.
     *
     * @param id the id of the rotRservDTO to save.
     * @param rotRservDTO the rotRservDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rotRservDTO,
     * or with status {@code 400 (Bad Request)} if the rotRservDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rotRservDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rot-rservs/{id}")
    public ResponseEntity<RotRservDTO> updateRotRserv(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody RotRservDTO rotRservDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RotRserv : {}, {}", id, rotRservDTO);
        if (rotRservDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rotRservDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rotRservRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RotRservDTO result = rotRservService.update(rotRservDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rotRservDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /rot-rservs/:id} : Partial updates given fields of an existing rotRserv, field will ignore if it is null
     *
     * @param id the id of the rotRservDTO to save.
     * @param rotRservDTO the rotRservDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rotRservDTO,
     * or with status {@code 400 (Bad Request)} if the rotRservDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rotRservDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rotRservDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rot-rservs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RotRservDTO> partialUpdateRotRserv(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody RotRservDTO rotRservDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RotRserv partially : {}, {}", id, rotRservDTO);
        if (rotRservDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rotRservDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rotRservRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RotRservDTO> result = rotRservService.partialUpdate(rotRservDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rotRservDTO.getId())
        );
    }

    /**
     * {@code GET  /rot-rservs} : get all the rotRservs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rotRservs in body.
     */
    @GetMapping("/rot-rservs")
    public ResponseEntity<List<RotRservDTO>> getAllRotRservs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RotRservs");
        Page<RotRservDTO> page = rotRservService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rot-rservs/:id} : get the "id" rotRserv.
     *
     * @param id the id of the rotRservDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rotRservDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rot-rservs/{id}")
    public ResponseEntity<RotRservDTO> getRotRserv(@PathVariable String id) {
        log.debug("REST request to get RotRserv : {}", id);
        Optional<RotRservDTO> rotRservDTO = rotRservService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rotRservDTO);
    }

    /**
     * {@code DELETE  /rot-rservs/:id} : delete the "id" rotRserv.
     *
     * @param id the id of the rotRservDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rot-rservs/{id}")
    public ResponseEntity<Void> deleteRotRserv(@PathVariable String id) {
        log.debug("REST request to delete RotRserv : {}", id);
        rotRservService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
