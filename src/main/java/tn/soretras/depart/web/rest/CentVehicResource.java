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
import tn.soretras.depart.repository.CentVehicRepository;
import tn.soretras.depart.service.CentVehicService;
import tn.soretras.depart.service.dto.CentVehicDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.CentVehic}.
 */
@RestController
@RequestMapping("/api")
public class CentVehicResource {

    private final Logger log = LoggerFactory.getLogger(CentVehicResource.class);

    private static final String ENTITY_NAME = "centVehic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CentVehicService centVehicService;

    private final CentVehicRepository centVehicRepository;

    public CentVehicResource(CentVehicService centVehicService, CentVehicRepository centVehicRepository) {
        this.centVehicService = centVehicService;
        this.centVehicRepository = centVehicRepository;
    }

    /**
     * {@code POST  /cent-vehics} : Create a new centVehic.
     *
     * @param centVehicDTO the centVehicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centVehicDTO, or with status {@code 400 (Bad Request)} if the centVehic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cent-vehics")
    public ResponseEntity<CentVehicDTO> createCentVehic(@RequestBody CentVehicDTO centVehicDTO) throws URISyntaxException {
        log.debug("REST request to save CentVehic : {}", centVehicDTO);
        if (centVehicDTO.getId() != null) {
            throw new BadRequestAlertException("A new centVehic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CentVehicDTO result = centVehicService.save(centVehicDTO);
        return ResponseEntity
            .created(new URI("/api/cent-vehics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /cent-vehics/:id} : Updates an existing centVehic.
     *
     * @param id the id of the centVehicDTO to save.
     * @param centVehicDTO the centVehicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centVehicDTO,
     * or with status {@code 400 (Bad Request)} if the centVehicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centVehicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cent-vehics/{id}")
    public ResponseEntity<CentVehicDTO> updateCentVehic(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CentVehicDTO centVehicDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CentVehic : {}, {}", id, centVehicDTO);
        if (centVehicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, centVehicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!centVehicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CentVehicDTO result = centVehicService.update(centVehicDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centVehicDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /cent-vehics/:id} : Partial updates given fields of an existing centVehic, field will ignore if it is null
     *
     * @param id the id of the centVehicDTO to save.
     * @param centVehicDTO the centVehicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centVehicDTO,
     * or with status {@code 400 (Bad Request)} if the centVehicDTO is not valid,
     * or with status {@code 404 (Not Found)} if the centVehicDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the centVehicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/cent-vehics/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CentVehicDTO> partialUpdateCentVehic(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody CentVehicDTO centVehicDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CentVehic partially : {}, {}", id, centVehicDTO);
        if (centVehicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, centVehicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!centVehicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CentVehicDTO> result = centVehicService.partialUpdate(centVehicDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, centVehicDTO.getId())
        );
    }

    /**
     * {@code GET  /cent-vehics} : get all the centVehics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centVehics in body.
     */
    @GetMapping("/cent-vehics")
    public ResponseEntity<List<CentVehicDTO>> getAllCentVehics(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of CentVehics");
        Page<CentVehicDTO> page = centVehicService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cent-vehics/:id} : get the "id" centVehic.
     *
     * @param id the id of the centVehicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centVehicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cent-vehics/{id}")
    public ResponseEntity<CentVehicDTO> getCentVehic(@PathVariable String id) {
        log.debug("REST request to get CentVehic : {}", id);
        Optional<CentVehicDTO> centVehicDTO = centVehicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centVehicDTO);
    }

    /**
     * {@code DELETE  /cent-vehics/:id} : delete the "id" centVehic.
     *
     * @param id the id of the centVehicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cent-vehics/{id}")
    public ResponseEntity<Void> deleteCentVehic(@PathVariable String id) {
        log.debug("REST request to delete CentVehic : {}", id);
        centVehicService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
