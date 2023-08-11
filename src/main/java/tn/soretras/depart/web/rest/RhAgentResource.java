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
import tn.soretras.depart.repository.RhAgentRepository;
import tn.soretras.depart.service.RhAgentService;
import tn.soretras.depart.service.dto.RhAgentDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.RhAgent}.
 */
@RestController
@RequestMapping("/api")
public class RhAgentResource {

    private final Logger log = LoggerFactory.getLogger(RhAgentResource.class);

    private static final String ENTITY_NAME = "rhAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RhAgentService rhAgentService;

    private final RhAgentRepository rhAgentRepository;

    public RhAgentResource(RhAgentService rhAgentService, RhAgentRepository rhAgentRepository) {
        this.rhAgentService = rhAgentService;
        this.rhAgentRepository = rhAgentRepository;
    }

    /**
     * {@code POST  /rh-agents} : Create a new rhAgent.
     *
     * @param rhAgentDTO the rhAgentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rhAgentDTO, or with status {@code 400 (Bad Request)} if the rhAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rh-agents")
    public ResponseEntity<RhAgentDTO> createRhAgent(@RequestBody RhAgentDTO rhAgentDTO) throws URISyntaxException {
        log.debug("REST request to save RhAgent : {}", rhAgentDTO);
        if (rhAgentDTO.getId() != null) {
            throw new BadRequestAlertException("A new rhAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RhAgentDTO result = rhAgentService.save(rhAgentDTO);
        return ResponseEntity
            .created(new URI("/api/rh-agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /rh-agents/:id} : Updates an existing rhAgent.
     *
     * @param id the id of the rhAgentDTO to save.
     * @param rhAgentDTO the rhAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rhAgentDTO,
     * or with status {@code 400 (Bad Request)} if the rhAgentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rhAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rh-agents/{id}")
    public ResponseEntity<RhAgentDTO> updateRhAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RhAgentDTO rhAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RhAgent : {}, {}", id, rhAgentDTO);
        if (rhAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rhAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rhAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RhAgentDTO result = rhAgentService.update(rhAgentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rhAgentDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /rh-agents/:id} : Partial updates given fields of an existing rhAgent, field will ignore if it is null
     *
     * @param id the id of the rhAgentDTO to save.
     * @param rhAgentDTO the rhAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rhAgentDTO,
     * or with status {@code 400 (Bad Request)} if the rhAgentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rhAgentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rhAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rh-agents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RhAgentDTO> partialUpdateRhAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody RhAgentDTO rhAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RhAgent partially : {}, {}", id, rhAgentDTO);
        if (rhAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rhAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rhAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RhAgentDTO> result = rhAgentService.partialUpdate(rhAgentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rhAgentDTO.getId())
        );
    }

    /**
     * {@code GET  /rh-agents} : get all the rhAgents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rhAgents in body.
     */
    @GetMapping("/rh-agents")
    public ResponseEntity<List<RhAgentDTO>> getAllRhAgents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RhAgents");
        Page<RhAgentDTO> page = rhAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /rh-agents/:id} : get the "id" rhAgent.
     *
     * @param id the id of the rhAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rhAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rh-agents/{id}")
    public ResponseEntity<RhAgentDTO> getRhAgent(@PathVariable String id) {
        log.debug("REST request to get RhAgent : {}", id);
        Optional<RhAgentDTO> rhAgentDTO = rhAgentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rhAgentDTO);
    }

    /**
     * {@code DELETE  /rh-agents/:id} : delete the "id" rhAgent.
     *
     * @param id the id of the rhAgentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rh-agents/{id}")
    public ResponseEntity<Void> deleteRhAgent(@PathVariable String id) {
        log.debug("REST request to delete RhAgent : {}", id);
        rhAgentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
