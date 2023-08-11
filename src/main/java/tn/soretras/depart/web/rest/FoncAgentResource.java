package tn.soretras.depart.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import tn.soretras.depart.repository.FoncAgentRepository;
import tn.soretras.depart.service.FoncAgentService;
import tn.soretras.depart.service.dto.FoncAgentDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.FoncAgent}.
 */
@RestController
@RequestMapping("/api")
public class FoncAgentResource {

    private final Logger log = LoggerFactory.getLogger(FoncAgentResource.class);

    private static final String ENTITY_NAME = "foncAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FoncAgentService foncAgentService;

    private final FoncAgentRepository foncAgentRepository;

    public FoncAgentResource(FoncAgentService foncAgentService, FoncAgentRepository foncAgentRepository) {
        this.foncAgentService = foncAgentService;
        this.foncAgentRepository = foncAgentRepository;
    }

    /**
     * {@code POST  /fonc-agents} : Create a new foncAgent.
     *
     * @param foncAgentDTO the foncAgentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new foncAgentDTO, or with status {@code 400 (Bad Request)} if the foncAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fonc-agents")
    public ResponseEntity<FoncAgentDTO> createFoncAgent(@RequestBody FoncAgentDTO foncAgentDTO) throws URISyntaxException {
        log.debug("REST request to save FoncAgent : {}", foncAgentDTO);
        if (foncAgentDTO.getId() != null) {
            throw new BadRequestAlertException("A new foncAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoncAgentDTO result = foncAgentService.save(foncAgentDTO);
        return ResponseEntity
            .created(new URI("/api/fonc-agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /fonc-agents/:id} : Updates an existing foncAgent.
     *
     * @param id the id of the foncAgentDTO to save.
     * @param foncAgentDTO the foncAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foncAgentDTO,
     * or with status {@code 400 (Bad Request)} if the foncAgentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the foncAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fonc-agents/{id}")
    public ResponseEntity<FoncAgentDTO> updateFoncAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FoncAgentDTO foncAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FoncAgent : {}, {}", id, foncAgentDTO);
        if (foncAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foncAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foncAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FoncAgentDTO result = foncAgentService.update(foncAgentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foncAgentDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /fonc-agents/:id} : Partial updates given fields of an existing foncAgent, field will ignore if it is null
     *
     * @param id the id of the foncAgentDTO to save.
     * @param foncAgentDTO the foncAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated foncAgentDTO,
     * or with status {@code 400 (Bad Request)} if the foncAgentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the foncAgentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the foncAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/fonc-agents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FoncAgentDTO> partialUpdateFoncAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody FoncAgentDTO foncAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FoncAgent partially : {}, {}", id, foncAgentDTO);
        if (foncAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, foncAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!foncAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FoncAgentDTO> result = foncAgentService.partialUpdate(foncAgentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, foncAgentDTO.getId())
        );
    }

    /**
     * {@code GET  /fonc-agents} : get all the foncAgents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of foncAgents in body.
     */
    @GetMapping("/fonc-agents")
    public ResponseEntity<List<FoncAgentDTO>> getAllFoncAgents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of FoncAgents");
        Page<FoncAgentDTO> page = foncAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fonc-agents/:id} : get the "id" foncAgent.
     *
     * @param id the id of the foncAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the foncAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fonc-agents/{id}")
    public ResponseEntity<FoncAgentDTO> getFoncAgent(@PathVariable String id) {
        log.debug("REST request to get FoncAgent : {}", id);
        Optional<FoncAgentDTO> foncAgentDTO = foncAgentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foncAgentDTO);
    }

    /**
     * {@code DELETE  /fonc-agents/:id} : delete the "id" foncAgent.
     *
     * @param id the id of the foncAgentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fonc-agents/{id}")
    public ResponseEntity<Void> deleteFoncAgent(@PathVariable String id) {
        log.debug("REST request to delete FoncAgent : {}", id);
        foncAgentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
