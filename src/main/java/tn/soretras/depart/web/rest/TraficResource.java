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
import tn.soretras.depart.repository.TraficRepository;
import tn.soretras.depart.service.TraficService;
import tn.soretras.depart.service.dto.TraficDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Trafic}.
 */
@RestController
@RequestMapping("/api")
public class TraficResource {

    private final Logger log = LoggerFactory.getLogger(TraficResource.class);

    private static final String ENTITY_NAME = "trafic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TraficService traficService;

    private final TraficRepository traficRepository;

    public TraficResource(TraficService traficService, TraficRepository traficRepository) {
        this.traficService = traficService;
        this.traficRepository = traficRepository;
    }

    /**
     * {@code POST  /trafics} : Create a new trafic.
     *
     * @param traficDTO the traficDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new traficDTO, or with status {@code 400 (Bad Request)} if the trafic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trafics")
    public ResponseEntity<TraficDTO> createTrafic(@Valid @RequestBody TraficDTO traficDTO) throws URISyntaxException {
        log.debug("REST request to save Trafic : {}", traficDTO);
        if (traficDTO.getId() != null) {
            throw new BadRequestAlertException("A new trafic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraficDTO result = traficService.save(traficDTO);
        return ResponseEntity
            .created(new URI("/api/trafics/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /trafics/:id} : Updates an existing trafic.
     *
     * @param id the id of the traficDTO to save.
     * @param traficDTO the traficDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated traficDTO,
     * or with status {@code 400 (Bad Request)} if the traficDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the traficDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trafics/{id}")
    public ResponseEntity<TraficDTO> updateTrafic(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody TraficDTO traficDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Trafic : {}, {}", id, traficDTO);
        if (traficDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, traficDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!traficRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TraficDTO result = traficService.update(traficDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, traficDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /trafics/:id} : Partial updates given fields of an existing trafic, field will ignore if it is null
     *
     * @param id the id of the traficDTO to save.
     * @param traficDTO the traficDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated traficDTO,
     * or with status {@code 400 (Bad Request)} if the traficDTO is not valid,
     * or with status {@code 404 (Not Found)} if the traficDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the traficDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trafics/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TraficDTO> partialUpdateTrafic(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody TraficDTO traficDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Trafic partially : {}, {}", id, traficDTO);
        if (traficDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, traficDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!traficRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TraficDTO> result = traficService.partialUpdate(traficDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, traficDTO.getId())
        );
    }

    /**
     * {@code GET  /trafics} : get all the trafics.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trafics in body.
     */
    @GetMapping("/trafics")
    public ResponseEntity<List<TraficDTO>> getAllTrafics(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Trafics");
        Page<TraficDTO> page = traficService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trafics/:id} : get the "id" trafic.
     *
     * @param id the id of the traficDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the traficDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trafics/{id}")
    public ResponseEntity<TraficDTO> getTrafic(@PathVariable String id) {
        log.debug("REST request to get Trafic : {}", id);
        Optional<TraficDTO> traficDTO = traficService.findOne(id);
        return ResponseUtil.wrapOrNotFound(traficDTO);
    }

    /**
     * {@code DELETE  /trafics/:id} : delete the "id" trafic.
     *
     * @param id the id of the traficDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trafics/{id}")
    public ResponseEntity<Void> deleteTrafic(@PathVariable String id) {
        log.debug("REST request to delete Trafic : {}", id);
        traficService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
