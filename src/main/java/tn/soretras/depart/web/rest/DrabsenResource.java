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
import tn.soretras.depart.repository.DrabsenRepository;
import tn.soretras.depart.service.DrabsenService;
import tn.soretras.depart.service.dto.DrabsenDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Drabsen}.
 */
@RestController
@RequestMapping("/api")
public class DrabsenResource {

    private final Logger log = LoggerFactory.getLogger(DrabsenResource.class);

    private static final String ENTITY_NAME = "drabsen";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DrabsenService drabsenService;

    private final DrabsenRepository drabsenRepository;

    public DrabsenResource(DrabsenService drabsenService, DrabsenRepository drabsenRepository) {
        this.drabsenService = drabsenService;
        this.drabsenRepository = drabsenRepository;
    }

    /**
     * {@code POST  /drabsens} : Create a new drabsen.
     *
     * @param drabsenDTO the drabsenDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new drabsenDTO, or with status {@code 400 (Bad Request)} if the drabsen has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/drabsens")
    public ResponseEntity<DrabsenDTO> createDrabsen(@RequestBody DrabsenDTO drabsenDTO) throws URISyntaxException {
        log.debug("REST request to save Drabsen : {}", drabsenDTO);
        if (drabsenDTO.getId() != null) {
            throw new BadRequestAlertException("A new drabsen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DrabsenDTO result = drabsenService.save(drabsenDTO);
        return ResponseEntity
            .created(new URI("/api/drabsens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /drabsens/:id} : Updates an existing drabsen.
     *
     * @param id the id of the drabsenDTO to save.
     * @param drabsenDTO the drabsenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drabsenDTO,
     * or with status {@code 400 (Bad Request)} if the drabsenDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the drabsenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/drabsens/{id}")
    public ResponseEntity<DrabsenDTO> updateDrabsen(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DrabsenDTO drabsenDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Drabsen : {}, {}", id, drabsenDTO);
        if (drabsenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, drabsenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!drabsenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DrabsenDTO result = drabsenService.update(drabsenDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drabsenDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /drabsens/:id} : Partial updates given fields of an existing drabsen, field will ignore if it is null
     *
     * @param id the id of the drabsenDTO to save.
     * @param drabsenDTO the drabsenDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated drabsenDTO,
     * or with status {@code 400 (Bad Request)} if the drabsenDTO is not valid,
     * or with status {@code 404 (Not Found)} if the drabsenDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the drabsenDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/drabsens/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DrabsenDTO> partialUpdateDrabsen(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DrabsenDTO drabsenDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Drabsen partially : {}, {}", id, drabsenDTO);
        if (drabsenDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, drabsenDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!drabsenRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DrabsenDTO> result = drabsenService.partialUpdate(drabsenDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, drabsenDTO.getId())
        );
    }

    /**
     * {@code GET  /drabsens} : get all the drabsens.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of drabsens in body.
     */
    @GetMapping("/drabsens")
    public ResponseEntity<List<DrabsenDTO>> getAllDrabsens(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Drabsens");
        Page<DrabsenDTO> page = drabsenService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /drabsens/:id} : get the "id" drabsen.
     *
     * @param id the id of the drabsenDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the drabsenDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/drabsens/{id}")
    public ResponseEntity<DrabsenDTO> getDrabsen(@PathVariable String id) {
        log.debug("REST request to get Drabsen : {}", id);
        Optional<DrabsenDTO> drabsenDTO = drabsenService.findOne(id);
        return ResponseUtil.wrapOrNotFound(drabsenDTO);
    }

    /**
     * {@code DELETE  /drabsens/:id} : delete the "id" drabsen.
     *
     * @param id the id of the drabsenDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/drabsens/{id}")
    public ResponseEntity<Void> deleteDrabsen(@PathVariable String id) {
        log.debug("REST request to delete Drabsen : {}", id);
        drabsenService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
