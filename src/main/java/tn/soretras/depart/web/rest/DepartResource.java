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
import tn.soretras.depart.repository.DepartRepository;
import tn.soretras.depart.service.DepartService;
import tn.soretras.depart.service.dto.DepartDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Depart}.
 */
@RestController
@RequestMapping("/api")
public class DepartResource {

    private final Logger log = LoggerFactory.getLogger(DepartResource.class);

    private static final String ENTITY_NAME = "depart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartService departService;

    private final DepartRepository departRepository;

    public DepartResource(DepartService departService, DepartRepository departRepository) {
        this.departService = departService;
        this.departRepository = departRepository;
    }

    /**
     * {@code POST  /departs} : Create a new depart.
     *
     * @param departDTO the departDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new departDTO, or with status {@code 400 (Bad Request)} if the depart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departs")
    public ResponseEntity<DepartDTO> createDepart(@Valid @RequestBody DepartDTO departDTO) throws URISyntaxException {
        log.debug("REST request to save Depart : {}", departDTO);
        if (departDTO.getId() != null) {
            throw new BadRequestAlertException("A new depart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DepartDTO result = departService.save(departDTO);
        return ResponseEntity
            .created(new URI("/api/departs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /departs/:id} : Updates an existing depart.
     *
     * @param id the id of the departDTO to save.
     * @param departDTO the departDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departDTO,
     * or with status {@code 400 (Bad Request)} if the departDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the departDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departs/{id}")
    public ResponseEntity<DepartDTO> updateDepart(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DepartDTO departDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Depart : {}, {}", id, departDTO);
        if (departDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DepartDTO result = departService.update(departDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, departDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /departs/:id} : Partial updates given fields of an existing depart, field will ignore if it is null
     *
     * @param id the id of the departDTO to save.
     * @param departDTO the departDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departDTO,
     * or with status {@code 400 (Bad Request)} if the departDTO is not valid,
     * or with status {@code 404 (Not Found)} if the departDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the departDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/departs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DepartDTO> partialUpdateDepart(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DepartDTO departDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Depart partially : {}, {}", id, departDTO);
        if (departDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DepartDTO> result = departService.partialUpdate(departDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, departDTO.getId())
        );
    }

    /**
     * {@code GET  /departs} : get all the departs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departs in body.
     */
    @GetMapping("/departs")
    public ResponseEntity<List<DepartDTO>> getAllDeparts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Departs");
        Page<DepartDTO> page = departService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /departs/:id} : get the "id" depart.
     *
     * @param id the id of the departDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the departDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departs/{id}")
    public ResponseEntity<DepartDTO> getDepart(@PathVariable String id) {
        log.debug("REST request to get Depart : {}", id);
        Optional<DepartDTO> departDTO = departService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departDTO);
    }

    /**
     * {@code DELETE  /departs/:id} : delete the "id" depart.
     *
     * @param id the id of the departDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departs/{id}")
    public ResponseEntity<Void> deleteDepart(@PathVariable String id) {
        log.debug("REST request to delete Depart : {}", id);
        departService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
