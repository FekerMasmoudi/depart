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
import tn.soretras.depart.repository.TypStatRepository;
import tn.soretras.depart.service.TypStatService;
import tn.soretras.depart.service.dto.TypStatDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.TypStat}.
 */
@RestController
@RequestMapping("/api")
public class TypStatResource {

    private final Logger log = LoggerFactory.getLogger(TypStatResource.class);

    private static final String ENTITY_NAME = "typStat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypStatService typStatService;

    private final TypStatRepository typStatRepository;

    public TypStatResource(TypStatService typStatService, TypStatRepository typStatRepository) {
        this.typStatService = typStatService;
        this.typStatRepository = typStatRepository;
    }

    /**
     * {@code POST  /typ-stats} : Create a new typStat.
     *
     * @param typStatDTO the typStatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typStatDTO, or with status {@code 400 (Bad Request)} if the typStat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/typ-stats")
    public ResponseEntity<TypStatDTO> createTypStat(@Valid @RequestBody TypStatDTO typStatDTO) throws URISyntaxException {
        log.debug("REST request to save TypStat : {}", typStatDTO);
        if (typStatDTO.getId() != null) {
            throw new BadRequestAlertException("A new typStat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypStatDTO result = typStatService.save(typStatDTO);
        return ResponseEntity
            .created(new URI("/api/typ-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /typ-stats/:id} : Updates an existing typStat.
     *
     * @param id the id of the typStatDTO to save.
     * @param typStatDTO the typStatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typStatDTO,
     * or with status {@code 400 (Bad Request)} if the typStatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typStatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/typ-stats/{id}")
    public ResponseEntity<TypStatDTO> updateTypStat(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody TypStatDTO typStatDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TypStat : {}, {}", id, typStatDTO);
        if (typStatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typStatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typStatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TypStatDTO result = typStatService.update(typStatDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typStatDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /typ-stats/:id} : Partial updates given fields of an existing typStat, field will ignore if it is null
     *
     * @param id the id of the typStatDTO to save.
     * @param typStatDTO the typStatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typStatDTO,
     * or with status {@code 400 (Bad Request)} if the typStatDTO is not valid,
     * or with status {@code 404 (Not Found)} if the typStatDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the typStatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/typ-stats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypStatDTO> partialUpdateTypStat(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody TypStatDTO typStatDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypStat partially : {}, {}", id, typStatDTO);
        if (typStatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typStatDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typStatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypStatDTO> result = typStatService.partialUpdate(typStatDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typStatDTO.getId())
        );
    }

    /**
     * {@code GET  /typ-stats} : get all the typStats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typStats in body.
     */
    @GetMapping("/typ-stats")
    public ResponseEntity<List<TypStatDTO>> getAllTypStats(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of TypStats");
        Page<TypStatDTO> page = typStatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /typ-stats/:id} : get the "id" typStat.
     *
     * @param id the id of the typStatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typStatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/typ-stats/{id}")
    public ResponseEntity<TypStatDTO> getTypStat(@PathVariable String id) {
        log.debug("REST request to get TypStat : {}", id);
        Optional<TypStatDTO> typStatDTO = typStatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typStatDTO);
    }

    /**
     * {@code DELETE  /typ-stats/:id} : delete the "id" typStat.
     *
     * @param id the id of the typStatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/typ-stats/{id}")
    public ResponseEntity<Void> deleteTypStat(@PathVariable String id) {
        log.debug("REST request to delete TypStat : {}", id);
        typStatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
