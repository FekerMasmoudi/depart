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
import tn.soretras.depart.repository.BordereauRepository;
import tn.soretras.depart.service.BordereauService;
import tn.soretras.depart.service.dto.BordereauDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Bordereau}.
 */
@RestController
@RequestMapping("/api")
public class BordereauResource {

    private final Logger log = LoggerFactory.getLogger(BordereauResource.class);

    private static final String ENTITY_NAME = "bordereau";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BordereauService bordereauService;

    private final BordereauRepository bordereauRepository;

    public BordereauResource(BordereauService bordereauService, BordereauRepository bordereauRepository) {
        this.bordereauService = bordereauService;
        this.bordereauRepository = bordereauRepository;
    }

    /**
     * {@code POST  /bordereaus} : Create a new bordereau.
     *
     * @param bordereauDTO the bordereauDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bordereauDTO, or with status {@code 400 (Bad Request)} if the bordereau has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bordereaus")
    public ResponseEntity<BordereauDTO> createBordereau(@Valid @RequestBody BordereauDTO bordereauDTO) throws URISyntaxException {
        log.debug("REST request to save Bordereau : {}", bordereauDTO);
        if (bordereauDTO.getId() != null) {
            throw new BadRequestAlertException("A new bordereau cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BordereauDTO result = bordereauService.save(bordereauDTO);
        return ResponseEntity
            .created(new URI("/api/bordereaus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /bordereaus/:id} : Updates an existing bordereau.
     *
     * @param id the id of the bordereauDTO to save.
     * @param bordereauDTO the bordereauDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bordereauDTO,
     * or with status {@code 400 (Bad Request)} if the bordereauDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bordereauDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bordereaus/{id}")
    public ResponseEntity<BordereauDTO> updateBordereau(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody BordereauDTO bordereauDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Bordereau : {}, {}", id, bordereauDTO);
        if (bordereauDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bordereauDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bordereauRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BordereauDTO result = bordereauService.update(bordereauDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bordereauDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /bordereaus/:id} : Partial updates given fields of an existing bordereau, field will ignore if it is null
     *
     * @param id the id of the bordereauDTO to save.
     * @param bordereauDTO the bordereauDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bordereauDTO,
     * or with status {@code 400 (Bad Request)} if the bordereauDTO is not valid,
     * or with status {@code 404 (Not Found)} if the bordereauDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the bordereauDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/bordereaus/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BordereauDTO> partialUpdateBordereau(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody BordereauDTO bordereauDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Bordereau partially : {}, {}", id, bordereauDTO);
        if (bordereauDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bordereauDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bordereauRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BordereauDTO> result = bordereauService.partialUpdate(bordereauDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bordereauDTO.getId())
        );
    }

    /**
     * {@code GET  /bordereaus} : get all the bordereaus.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bordereaus in body.
     */
    @GetMapping("/bordereaus")
    public ResponseEntity<List<BordereauDTO>> getAllBordereaus(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Bordereaus");
        Page<BordereauDTO> page = bordereauService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bordereaus/:id} : get the "id" bordereau.
     *
     * @param id the id of the bordereauDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bordereauDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bordereaus/{id}")
    public ResponseEntity<BordereauDTO> getBordereau(@PathVariable String id) {
        log.debug("REST request to get Bordereau : {}", id);
        Optional<BordereauDTO> bordereauDTO = bordereauService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bordereauDTO);
    }

    /**
     * {@code DELETE  /bordereaus/:id} : delete the "id" bordereau.
     *
     * @param id the id of the bordereauDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bordereaus/{id}")
    public ResponseEntity<Void> deleteBordereau(@PathVariable String id) {
        log.debug("REST request to delete Bordereau : {}", id);
        bordereauService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
