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
import tn.soretras.depart.repository.AffecAgentRepository;
import tn.soretras.depart.service.AffecAgentService;
import tn.soretras.depart.service.dto.AffecAgentDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.AffecAgent}.
 */
@RestController
@RequestMapping("/api")
public class AffecAgentResource {

    private final Logger log = LoggerFactory.getLogger(AffecAgentResource.class);

    private static final String ENTITY_NAME = "affecAgent";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AffecAgentService affecAgentService;

    private final AffecAgentRepository affecAgentRepository;

    public AffecAgentResource(AffecAgentService affecAgentService, AffecAgentRepository affecAgentRepository) {
        this.affecAgentService = affecAgentService;
        this.affecAgentRepository = affecAgentRepository;
    }

    /**
     * {@code POST  /affec-agents} : Create a new affecAgent.
     *
     * @param affecAgentDTO the affecAgentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new affecAgentDTO, or with status {@code 400 (Bad Request)} if the affecAgent has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/affec-agents")
    public ResponseEntity<AffecAgentDTO> createAffecAgent(@RequestBody AffecAgentDTO affecAgentDTO) throws URISyntaxException {
        log.debug("REST request to save AffecAgent : {}", affecAgentDTO);
        if (affecAgentDTO.getId() != null) {
            throw new BadRequestAlertException("A new affecAgent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AffecAgentDTO result = affecAgentService.save(affecAgentDTO);
        return ResponseEntity
            .created(new URI("/api/affec-agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /affec-agents/:id} : Updates an existing affecAgent.
     *
     * @param id the id of the affecAgentDTO to save.
     * @param affecAgentDTO the affecAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affecAgentDTO,
     * or with status {@code 400 (Bad Request)} if the affecAgentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the affecAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/affec-agents/{id}")
    public ResponseEntity<AffecAgentDTO> updateAffecAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AffecAgentDTO affecAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AffecAgent : {}, {}", id, affecAgentDTO);
        if (affecAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affecAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affecAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AffecAgentDTO result = affecAgentService.update(affecAgentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affecAgentDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /affec-agents/:id} : Partial updates given fields of an existing affecAgent, field will ignore if it is null
     *
     * @param id the id of the affecAgentDTO to save.
     * @param affecAgentDTO the affecAgentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated affecAgentDTO,
     * or with status {@code 400 (Bad Request)} if the affecAgentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the affecAgentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the affecAgentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/affec-agents/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AffecAgentDTO> partialUpdateAffecAgent(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody AffecAgentDTO affecAgentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AffecAgent partially : {}, {}", id, affecAgentDTO);
        if (affecAgentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, affecAgentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!affecAgentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AffecAgentDTO> result = affecAgentService.partialUpdate(affecAgentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, affecAgentDTO.getId())
        );
    }

    /**
     * {@code GET  /affec-agents} : get all the affecAgents.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of affecAgents in body.
     */
    @GetMapping("/affec-agents")
    public ResponseEntity<List<AffecAgentDTO>> getAllAffecAgents(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of AffecAgents");
        Page<AffecAgentDTO> page = affecAgentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /affec-agents/:id} : get the "id" affecAgent.
     *
     * @param id the id of the affecAgentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the affecAgentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/affec-agents/{id}")
    public ResponseEntity<AffecAgentDTO> getAffecAgent(@PathVariable String id) {
        log.debug("REST request to get AffecAgent : {}", id);
        Optional<AffecAgentDTO> affecAgentDTO = affecAgentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(affecAgentDTO);
    }

    /**
     * {@code DELETE  /affec-agents/:id} : delete the "id" affecAgent.
     *
     * @param id the id of the affecAgentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/affec-agents/{id}")
    public ResponseEntity<Void> deleteAffecAgent(@PathVariable String id) {
        log.debug("REST request to delete AffecAgent : {}", id);
        affecAgentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
