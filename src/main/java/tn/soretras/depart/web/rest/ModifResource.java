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
import tn.soretras.depart.repository.ModifRepository;
import tn.soretras.depart.service.ModifService;
import tn.soretras.depart.service.dto.ModifDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Modif}.
 */
@RestController
@RequestMapping("/api")
public class ModifResource {

    private final Logger log = LoggerFactory.getLogger(ModifResource.class);

    private static final String ENTITY_NAME = "modif";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModifService modifService;

    private final ModifRepository modifRepository;

    public ModifResource(ModifService modifService, ModifRepository modifRepository) {
        this.modifService = modifService;
        this.modifRepository = modifRepository;
    }

    /**
     * {@code POST  /modifs} : Create a new modif.
     *
     * @param modifDTO the modifDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modifDTO, or with status {@code 400 (Bad Request)} if the modif has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modifs")
    public ResponseEntity<ModifDTO> createModif(@RequestBody ModifDTO modifDTO) throws URISyntaxException {
        log.debug("REST request to save Modif : {}", modifDTO);
        if (modifDTO.getId() != null) {
            throw new BadRequestAlertException("A new modif cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModifDTO result = modifService.save(modifDTO);
        return ResponseEntity
            .created(new URI("/api/modifs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /modifs/:id} : Updates an existing modif.
     *
     * @param id the id of the modifDTO to save.
     * @param modifDTO the modifDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modifDTO,
     * or with status {@code 400 (Bad Request)} if the modifDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modifDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modifs/{id}")
    public ResponseEntity<ModifDTO> updateModif(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ModifDTO modifDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Modif : {}, {}", id, modifDTO);
        if (modifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modifDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modifRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ModifDTO result = modifService.update(modifDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modifDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /modifs/:id} : Partial updates given fields of an existing modif, field will ignore if it is null
     *
     * @param id the id of the modifDTO to save.
     * @param modifDTO the modifDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modifDTO,
     * or with status {@code 400 (Bad Request)} if the modifDTO is not valid,
     * or with status {@code 404 (Not Found)} if the modifDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the modifDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/modifs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ModifDTO> partialUpdateModif(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ModifDTO modifDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Modif partially : {}, {}", id, modifDTO);
        if (modifDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, modifDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!modifRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ModifDTO> result = modifService.partialUpdate(modifDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modifDTO.getId())
        );
    }

    /**
     * {@code GET  /modifs} : get all the modifs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modifs in body.
     */
    @GetMapping("/modifs")
    public ResponseEntity<List<ModifDTO>> getAllModifs(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Modifs");
        Page<ModifDTO> page = modifService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modifs/:id} : get the "id" modif.
     *
     * @param id the id of the modifDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modifDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modifs/{id}")
    public ResponseEntity<ModifDTO> getModif(@PathVariable String id) {
        log.debug("REST request to get Modif : {}", id);
        Optional<ModifDTO> modifDTO = modifService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modifDTO);
    }

    /**
     * {@code DELETE  /modifs/:id} : delete the "id" modif.
     *
     * @param id the id of the modifDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modifs/{id}")
    public ResponseEntity<Void> deleteModif(@PathVariable String id) {
        log.debug("REST request to delete Modif : {}", id);
        modifService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
