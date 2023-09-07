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
import tn.soretras.depart.repository.DisplaybusRepository;
import tn.soretras.depart.service.DisplaybusService;
import tn.soretras.depart.service.dto.DisplaybusDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Displaybus}.
 */
@RestController
@RequestMapping("/api")
public class DisplaybusResource {

    private final Logger log = LoggerFactory.getLogger(DisplaybusResource.class);

    private static final String ENTITY_NAME = "displaybus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisplaybusService displaybusService;

    private final DisplaybusRepository displaybusRepository;

    public DisplaybusResource(DisplaybusService displaybusService, DisplaybusRepository displaybusRepository) {
        this.displaybusService = displaybusService;
        this.displaybusRepository = displaybusRepository;
    }

    /**
     * {@code POST  /displaybuses} : Create a new displaybus.
     *
     * @param displaybusDTO the displaybusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new displaybusDTO, or with status {@code 400 (Bad Request)} if the displaybus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/displaybuses")
    public ResponseEntity<DisplaybusDTO> createDisplaybus(@RequestBody DisplaybusDTO displaybusDTO) throws URISyntaxException {
        log.debug("REST request to save Displaybus : {}", displaybusDTO);
        if (displaybusDTO.getId() != null) {
            throw new BadRequestAlertException("A new displaybus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisplaybusDTO result = displaybusService.save(displaybusDTO);
        return ResponseEntity
            .created(new URI("/api/displaybuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /displaybuses/:id} : Updates an existing displaybus.
     *
     * @param id the id of the displaybusDTO to save.
     * @param displaybusDTO the displaybusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated displaybusDTO,
     * or with status {@code 400 (Bad Request)} if the displaybusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the displaybusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/displaybuses/{id}")
    public ResponseEntity<DisplaybusDTO> updateDisplaybus(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DisplaybusDTO displaybusDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Displaybus : {}, {}", id, displaybusDTO);
        if (displaybusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, displaybusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!displaybusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DisplaybusDTO result = displaybusService.update(displaybusDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, displaybusDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /displaybuses/:id} : Partial updates given fields of an existing displaybus, field will ignore if it is null
     *
     * @param id the id of the displaybusDTO to save.
     * @param displaybusDTO the displaybusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated displaybusDTO,
     * or with status {@code 400 (Bad Request)} if the displaybusDTO is not valid,
     * or with status {@code 404 (Not Found)} if the displaybusDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the displaybusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/displaybuses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DisplaybusDTO> partialUpdateDisplaybus(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody DisplaybusDTO displaybusDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Displaybus partially : {}, {}", id, displaybusDTO);
        if (displaybusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, displaybusDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!displaybusRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DisplaybusDTO> result = displaybusService.partialUpdate(displaybusDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, displaybusDTO.getId())
        );
    }

    /**
     * {@code GET  /displaybuses} : get all the displaybuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of displaybuses in body.
     */
    @GetMapping("/displaybuses")
    public ResponseEntity<List<DisplaybusDTO>> getAllDisplaybuses(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Displaybuses");
        Page<DisplaybusDTO> page = displaybusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /displaybuses/:id} : get the "id" displaybus.
     *
     * @param id the id of the displaybusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the displaybusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/displaybuses/{id}")
    public ResponseEntity<DisplaybusDTO> getDisplaybus(@PathVariable String id) {
        log.debug("REST request to get Displaybus : {}", id);
        Optional<DisplaybusDTO> displaybusDTO = displaybusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(displaybusDTO);
    }

    /**
     * {@code DELETE  /displaybuses/:id} : delete the "id" displaybus.
     *
     * @param id the id of the displaybusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/displaybuses/{id}")
    public ResponseEntity<Void> deleteDisplaybus(@PathVariable String id) {
        log.debug("REST request to delete Displaybus : {}", id);
        displaybusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
