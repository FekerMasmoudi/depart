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
import tn.soretras.depart.repository.DrtypabRepository;
import tn.soretras.depart.service.DrtypabService;
import tn.soretras.depart.service.dto.DrtypabDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Drtypab}.
 */
@RestController
@RequestMapping("/api")
public class DrtypabResource {

    private final Logger log = LoggerFactory.getLogger(DrtypabResource.class);

    private static final String ENTITY_NAME = "drtypab";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DrtypabService drtypabService;

    private final DrtypabRepository drtypabRepository;

    public DrtypabResource(DrtypabService drtypabService, DrtypabRepository drtypabRepository) {
        this.drtypabService = drtypabService;
        this.drtypabRepository = drtypabRepository;
    }

    /**
     * {@code POST  /drtypabs} : Create a new drtypab.
     *
     * @param drtypabDTO the drtypabDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new drtypabDTO, or with status {@code 400 (Bad Request)} if the drtypab has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/drtypabs")
    public ResponseEntity<DrtypabDTO> createDrtypab(@RequestBody DrtypabDTO drtypabDTO) throws URISyntaxException {
        log.debug("REST request to save Drtypab : {}", drtypabDTO);
        if (drtypabDTO.getId() != null) {
            throw new BadRequestAlertException("A new drtypab cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DrtypabDTO result = drtypabService.save(drtypabDTO);
        return ResponseEntity
            .created(new URI("/api/drtypabs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /drtypabs/:id} : Updates an existing drtypab.
     *
     * @param id the id of the drtypabDTO to save.
     * @param drtypabDTO the drtypabDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drtypabDTO,
     * or with status {@code 400 (Bad Request)} if the drtypabDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the drtypabDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/drtypabs/{id}")
    public ResponseEntity<DrtypabDTO> updateDrtypab(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DrtypabDTO drtypabDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Drtypab : {}, {}", id, drtypabDTO);
        if (drtypabDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, drtypabDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!drtypabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DrtypabDTO result = drtypabService.update(drtypabDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drtypabDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /drtypabs/:id} : Partial updates given fields of an existing drtypab, field will ignore if it is null
     *
     * @param id the id of the drtypabDTO to save.
     * @param drtypabDTO the drtypabDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drtypabDTO,
     * or with status {@code 400 (Bad Request)} if the drtypabDTO is not valid,
     * or with status {@code 404 (Not Found)} if the drtypabDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the drtypabDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/drtypabs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DrtypabDTO> partialUpdateDrtypab(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DrtypabDTO drtypabDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Drtypab partially : {}, {}", id, drtypabDTO);
        if (drtypabDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, drtypabDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!drtypabRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DrtypabDTO> result = drtypabService.partialUpdate(drtypabDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drtypabDTO.getId())
        );
    }

    /**
     * {@code GET  /drtypabs} : get all the drtypabs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drtypabs in body.
     */
    @GetMapping("/drtypabs")
    public ResponseEntity<List<DrtypabDTO>> getAllDrtypabs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Drtypabs");
        Page<DrtypabDTO> page = drtypabService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /drtypabs/:id} : get the "id" drtypab.
     *
     * @param id the id of the drtypabDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the drtypabDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/drtypabs/{id}")
    public ResponseEntity<DrtypabDTO> getDrtypab(@PathVariable String id) {
        log.debug("REST request to get Drtypab : {}", id);
        Optional<DrtypabDTO> drtypabDTO = drtypabService.findOne(id);
        return ResponseUtil.wrapOrNotFound(drtypabDTO);
    }

    /**
     * {@code DELETE  /drtypabs/:id} : delete the "id" drtypab.
     *
     * @param id the id of the drtypabDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/drtypabs/{id}")
    public ResponseEntity<Void> deleteDrtypab(@PathVariable String id) {
        log.debug("REST request to delete Drtypab : {}", id);
        drtypabService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
