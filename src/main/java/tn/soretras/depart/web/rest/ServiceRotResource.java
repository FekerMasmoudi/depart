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
import tn.soretras.depart.repository.ServiceRotRepository;
import tn.soretras.depart.service.ServiceRotService;
import tn.soretras.depart.service.dto.ServiceRotDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.ServiceRot}.
 */
@RestController
@RequestMapping("/api")
public class ServiceRotResource {

    private final Logger log = LoggerFactory.getLogger(ServiceRotResource.class);

    private static final String ENTITY_NAME = "serviceRot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceRotService serviceRotService;

    private final ServiceRotRepository serviceRotRepository;

    public ServiceRotResource(ServiceRotService serviceRotService, ServiceRotRepository serviceRotRepository) {
        this.serviceRotService = serviceRotService;
        this.serviceRotRepository = serviceRotRepository;
    }

    /**
     * {@code POST  /service-rots} : Create a new serviceRot.
     *
     * @param serviceRotDTO the serviceRotDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceRotDTO, or with status {@code 400 (Bad Request)} if the serviceRot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-rots")
    public ResponseEntity<ServiceRotDTO> createServiceRot(@Valid @RequestBody ServiceRotDTO serviceRotDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceRot : {}", serviceRotDTO);
        if (serviceRotDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceRot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceRotDTO result = serviceRotService.save(serviceRotDTO);
        return ResponseEntity
            .created(new URI("/api/service-rots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /service-rots/:id} : Updates an existing serviceRot.
     *
     * @param id the id of the serviceRotDTO to save.
     * @param serviceRotDTO the serviceRotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceRotDTO,
     * or with status {@code 400 (Bad Request)} if the serviceRotDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceRotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-rots/{id}")
    public ResponseEntity<ServiceRotDTO> updateServiceRot(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ServiceRotDTO serviceRotDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ServiceRot : {}, {}", id, serviceRotDTO);
        if (serviceRotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceRotDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceRotRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServiceRotDTO result = serviceRotService.update(serviceRotDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceRotDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /service-rots/:id} : Partial updates given fields of an existing serviceRot, field will ignore if it is null
     *
     * @param id the id of the serviceRotDTO to save.
     * @param serviceRotDTO the serviceRotDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceRotDTO,
     * or with status {@code 400 (Bad Request)} if the serviceRotDTO is not valid,
     * or with status {@code 404 (Not Found)} if the serviceRotDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceRotDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/service-rots/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServiceRotDTO> partialUpdateServiceRot(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody ServiceRotDTO serviceRotDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ServiceRot partially : {}, {}", id, serviceRotDTO);
        if (serviceRotDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceRotDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceRotRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceRotDTO> result = serviceRotService.partialUpdate(serviceRotDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceRotDTO.getId())
        );
    }

    /**
     * {@code GET  /service-rots} : get all the serviceRots.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceRots in body.
     */
    @GetMapping("/service-rots")
    public ResponseEntity<List<ServiceRotDTO>> getAllServiceRots(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ServiceRots");
        Page<ServiceRotDTO> page = serviceRotService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /service-rots/:id} : get the "id" serviceRot.
     *
     * @param id the id of the serviceRotDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceRotDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-rots/{id}")
    public ResponseEntity<ServiceRotDTO> getServiceRot(@PathVariable String id) {
        log.debug("REST request to get ServiceRot : {}", id);
        Optional<ServiceRotDTO> serviceRotDTO = serviceRotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceRotDTO);
    }

    /**
     * {@code DELETE  /service-rots/:id} : delete the "id" serviceRot.
     *
     * @param id the id of the serviceRotDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-rots/{id}")
    public ResponseEntity<Void> deleteServiceRot(@PathVariable String id) {
        log.debug("REST request to delete ServiceRot : {}", id);
        serviceRotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
