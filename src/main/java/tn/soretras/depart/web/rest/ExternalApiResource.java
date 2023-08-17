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
import tn.soretras.depart.repository.ExternalApiRepository;
import tn.soretras.depart.service.ExternalApiService;
import tn.soretras.depart.service.dto.ExternalApiDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.ExternalApi}.
 */
@RestController
@RequestMapping("/api")
public class ExternalApiResource {

    private final Logger log = LoggerFactory.getLogger(ExternalApiResource.class);

    private static final String ENTITY_NAME = "externalApi";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExternalApiService externalApiService;

    private final ExternalApiRepository externalApiRepository;

    public ExternalApiResource(ExternalApiService externalApiService, ExternalApiRepository externalApiRepository) {
        this.externalApiService = externalApiService;
        this.externalApiRepository = externalApiRepository;
    }

    /**
     * {@code POST  /external-apis} : Create a new externalApi.
     *
     * @param externalApiDTO the externalApiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new externalApiDTO, or with status {@code 400 (Bad Request)} if the externalApi has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/external-apis")
    public ResponseEntity<ExternalApiDTO> createExternalApi(@RequestBody ExternalApiDTO externalApiDTO) throws URISyntaxException {
        log.debug("REST request to save ExternalApi : {}", externalApiDTO);
        if (externalApiDTO.getId() != null) {
            throw new BadRequestAlertException("A new externalApi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExternalApiDTO result = externalApiService.save(externalApiDTO);
        return ResponseEntity
            .created(new URI("/api/external-apis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /external-apis/:id} : Updates an existing externalApi.
     *
     * @param id the id of the externalApiDTO to save.
     * @param externalApiDTO the externalApiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated externalApiDTO,
     * or with status {@code 400 (Bad Request)} if the externalApiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the externalApiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/external-apis/{id}")
    public ResponseEntity<ExternalApiDTO> updateExternalApi(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ExternalApiDTO externalApiDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ExternalApi : {}, {}", id, externalApiDTO);
        if (externalApiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, externalApiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!externalApiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ExternalApiDTO result = externalApiService.update(externalApiDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, externalApiDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /external-apis/:id} : Partial updates given fields of an existing externalApi, field will ignore if it is null
     *
     * @param id the id of the externalApiDTO to save.
     * @param externalApiDTO the externalApiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated externalApiDTO,
     * or with status {@code 400 (Bad Request)} if the externalApiDTO is not valid,
     * or with status {@code 404 (Not Found)} if the externalApiDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the externalApiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/external-apis/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExternalApiDTO> partialUpdateExternalApi(
        @PathVariable(value = "id", required = false) final String id,
        @RequestBody ExternalApiDTO externalApiDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExternalApi partially : {}, {}", id, externalApiDTO);
        if (externalApiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, externalApiDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!externalApiRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExternalApiDTO> result = externalApiService.partialUpdate(externalApiDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, externalApiDTO.getId())
        );
    }

    /**
     * {@code GET  /external-apis} : get all the externalApis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of externalApis in body.
     */
    @GetMapping("/external-apis")
    public ResponseEntity<List<ExternalApiDTO>> getAllExternalApis(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ExternalApis");
        Page<ExternalApiDTO> page = externalApiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /external-apis/:id} : get the "id" externalApi.
     *
     * @param id the id of the externalApiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the externalApiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/external-apis/{id}")
    public ResponseEntity<ExternalApiDTO> getExternalApi(@PathVariable String id) {
        log.debug("REST request to get ExternalApi : {}", id);
        Optional<ExternalApiDTO> externalApiDTO = externalApiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(externalApiDTO);
    }

    /**
     * {@code DELETE  /external-apis/:id} : delete the "id" externalApi.
     *
     * @param id the id of the externalApiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/external-apis/{id}")
    public ResponseEntity<Void> deleteExternalApi(@PathVariable String id) {
        log.debug("REST request to delete ExternalApi : {}", id);
        externalApiService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
