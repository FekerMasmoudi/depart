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
import tn.soretras.depart.repository.ItineraireRepository;
import tn.soretras.depart.service.ItineraireService;
import tn.soretras.depart.service.dto.ItineraireDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Itineraire}.
 */
@RestController
@RequestMapping("/api")
public class ItineraireResource {

    private final Logger log = LoggerFactory.getLogger(ItineraireResource.class);

    private static final String ENTITY_NAME = "itineraire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ItineraireService itineraireService;

    private final ItineraireRepository itineraireRepository;

    public ItineraireResource(ItineraireService itineraireService, ItineraireRepository itineraireRepository) {
        this.itineraireService = itineraireService;
        this.itineraireRepository = itineraireRepository;
    }

    /**
     * {@code POST  /itineraires} : Create a new itineraire.
     *
     * @param itineraireDTO the itineraireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new itineraireDTO, or with status {@code 400 (Bad Request)} if the itineraire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/itineraires")
    public ResponseEntity<ItineraireDTO> createItineraire(@Valid @RequestBody ItineraireDTO itineraireDTO) throws URISyntaxException {
        log.debug("REST request to save Itineraire : {}", itineraireDTO);
        if (itineraireDTO.getId() != null) {
            throw new BadRequestAlertException("A new itineraire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ItineraireDTO result = itineraireService.save(itineraireDTO);
        return ResponseEntity
            .created(new URI("/api/itineraires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /itineraires/:id} : Updates an existing itineraire.
     *
     * @param id the id of the itineraireDTO to save.
     * @param itineraireDTO the itineraireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itineraireDTO,
     * or with status {@code 400 (Bad Request)} if the itineraireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the itineraireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/itineraires/{id}")
    public ResponseEntity<ItineraireDTO> updateItineraire(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody ItineraireDTO itineraireDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Itineraire : {}, {}", id, itineraireDTO);
        if (itineraireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itineraireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itineraireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ItineraireDTO result = itineraireService.update(itineraireDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itineraireDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /itineraires/:id} : Partial updates given fields of an existing itineraire, field will ignore if it is null
     *
     * @param id the id of the itineraireDTO to save.
     * @param itineraireDTO the itineraireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated itineraireDTO,
     * or with status {@code 400 (Bad Request)} if the itineraireDTO is not valid,
     * or with status {@code 404 (Not Found)} if the itineraireDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the itineraireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/itineraires/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ItineraireDTO> partialUpdateItineraire(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody ItineraireDTO itineraireDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Itineraire partially : {}, {}", id, itineraireDTO);
        if (itineraireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, itineraireDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!itineraireRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ItineraireDTO> result = itineraireService.partialUpdate(itineraireDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, itineraireDTO.getId())
        );
    }

    /**
     * {@code GET  /itineraires} : get all the itineraires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of itineraires in body.
     */
    @GetMapping("/itineraires")
    public ResponseEntity<List<ItineraireDTO>> getAllItineraires(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Itineraires");
        Page<ItineraireDTO> page = itineraireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /itineraires/:id} : get the "id" itineraire.
     *
     * @param id the id of the itineraireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the itineraireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/itineraires/{id}")
    public ResponseEntity<ItineraireDTO> getItineraire(@PathVariable String id) {
        log.debug("REST request to get Itineraire : {}", id);
        Optional<ItineraireDTO> itineraireDTO = itineraireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(itineraireDTO);
    }

    /**
     * {@code DELETE  /itineraires/:id} : delete the "id" itineraire.
     *
     * @param id the id of the itineraireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/itineraires/{id}")
    public ResponseEntity<Void> deleteItineraire(@PathVariable String id) {
        log.debug("REST request to delete Itineraire : {}", id);
        itineraireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
