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
import tn.soretras.depart.repository.BonTvxRepository;
import tn.soretras.depart.service.BonTvxService;
import tn.soretras.depart.service.dto.BonTvxDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.BonTvx}.
 */
@RestController
@RequestMapping("/api")
public class BonTvxResource {

    private final Logger log = LoggerFactory.getLogger(BonTvxResource.class);

    private static final String ENTITY_NAME = "bonTvx";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BonTvxService bonTvxService;

    private final BonTvxRepository bonTvxRepository;

    public BonTvxResource(BonTvxService bonTvxService, BonTvxRepository bonTvxRepository) {
        this.bonTvxService = bonTvxService;
        this.bonTvxRepository = bonTvxRepository;
    }

    /**
     * {@code POST  /bon-tvxes} : Create a new bonTvx.
     *
     * @param bonTvxDTO the bonTvxDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bonTvxDTO, or with status {@code 400 (Bad Request)} if the bonTvx has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bon-tvxes")
    public ResponseEntity<BonTvxDTO> createBonTvx(@RequestBody BonTvxDTO bonTvxDTO) throws URISyntaxException {
        log.debug("REST request to save BonTvx : {}", bonTvxDTO);
        if (bonTvxDTO.getId() != null) {
            throw new BadRequestAlertException("A new bonTvx cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BonTvxDTO result = bonTvxService.save(bonTvxDTO);
        return ResponseEntity
            .created(new URI("/api/bon-tvxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bon-tvxes/:id} : Updates an existing bonTvx.
     *
     * @param id the id of the bonTvxDTO to save.
     * @param bonTvxDTO the bonTvxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bonTvxDTO,
     * or with status {@code 400 (Bad Request)} if the bonTvxDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bonTvxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bon-tvxes/{id}")
    public ResponseEntity<BonTvxDTO> updateBonTvx(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody BonTvxDTO bonTvxDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BonTvx : {}, {}", id, bonTvxDTO);
        if (bonTvxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bonTvxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bonTvxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BonTvxDTO result = bonTvxService.update(bonTvxDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bonTvxDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bon-tvxes/:id} : Partial updates given fields of an existing bonTvx, field will ignore if it is null
     *
     * @param id the id of the bonTvxDTO to save.
     * @param bonTvxDTO the bonTvxDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bonTvxDTO,
     * or with status {@code 400 (Bad Request)} if the bonTvxDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bonTvxDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bonTvxDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bon-tvxes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BonTvxDTO> partialUpdateBonTvx(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody BonTvxDTO bonTvxDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BonTvx partially : {}, {}", id, bonTvxDTO);
        if (bonTvxDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bonTvxDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bonTvxRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BonTvxDTO> result = bonTvxService.partialUpdate(bonTvxDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bonTvxDTO.getId())
        );
    }

    /**
     * {@code GET  /bon-tvxes} : get all the bonTvxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bonTvxes in body.
     */
    @GetMapping("/bon-tvxes")
    public ResponseEntity<List<BonTvxDTO>> getAllBonTvxes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of BonTvxes");
        Page<BonTvxDTO> page = bonTvxService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bon-tvxes/:id} : get the "id" bonTvx.
     *
     * @param id the id of the bonTvxDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bonTvxDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bon-tvxes/{id}")
    public ResponseEntity<BonTvxDTO> getBonTvx(@PathVariable String id) {
        log.debug("REST request to get BonTvx : {}", id);
        Optional<BonTvxDTO> bonTvxDTO = bonTvxService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bonTvxDTO);
    }

    /**
     * {@code DELETE  /bon-tvxes/:id} : delete the "id" bonTvx.
     *
     * @param id the id of the bonTvxDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bon-tvxes/{id}")
    public ResponseEntity<Void> deleteBonTvx(@PathVariable String id) {
        log.debug("REST request to delete BonTvx : {}", id);
        bonTvxService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
