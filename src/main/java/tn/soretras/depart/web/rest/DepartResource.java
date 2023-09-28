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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.soretras.depart.domain.Depart;
import tn.soretras.depart.repository.DepartRepository;
import tn.soretras.depart.service.DepartService;
import tn.soretras.depart.service.dto.DepartDTO;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Depart}.
 */
@RestController
@RequestMapping("/api")
public class DepartResource {

    private final Logger log = LoggerFactory.getLogger(DepartResource.class);

    private static final String ENTITY_NAME = "depart";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartRepository departRepository;

    private final DepartService departService;

    public DepartResource(DepartRepository departRepository, DepartService departService) {
        this.departRepository = departRepository;
        this.departService = departService;
    }

    /**
     * {@code POST  /departs} : Create a new depart.
     *
     * @param depart the depart to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new depart, or with status {@code 400 (Bad Request)} if the depart has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departs")
    public ResponseEntity<Depart> createDepart(@Valid @RequestBody Depart depart) throws URISyntaxException {
        log.debug("REST request to save Depart : {}", depart);
        if (depart.getId() != null) {
            throw new BadRequestAlertException("A new depart cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Depart result = departRepository.save(depart);
        return ResponseEntity
            .created(new URI("/api/departs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /departs/:id} : Updates an existing depart.
     *
     * @param id the id of the depart to save.
     * @param depart the depart to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depart,
     * or with status {@code 400 (Bad Request)} if the depart is not valid,
     * or with status {@code 500 (Internal Server Error)} if the depart couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departs/{id}")
    public ResponseEntity<Depart> updateDepart(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Depart depart
    ) throws URISyntaxException {
        log.debug("REST request to update Depart : {}, {}", id, depart);
        if (depart.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, depart.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Depart result = departRepository.save(depart);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depart.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /departs/:id} : Partial updates given fields of an existing depart, field will ignore if it is null
     *
     * @param id the id of the depart to save.
     * @param depart the depart to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated depart,
     * or with status {@code 400 (Bad Request)} if the depart is not valid,
     * or with status {@code 404 (Not Found)} if the depart is not found,
     * or with status {@code 500 (Internal Server Error)} if the depart couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/departs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Depart> partialUpdateDepart(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Depart depart
    ) throws URISyntaxException {
        log.debug("REST request to partial update Depart partially : {}, {}", id, depart);
        if (depart.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, depart.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Depart> result = departRepository
            .findById(depart.getId())
            .map(existingDepart -> {
                if (depart.getDeccent() != null) {
                    existingDepart.setDeccent(depart.getDeccent());
                }
                if (depart.getDecagenc() != null) {
                    existingDepart.setDecagenc(depart.getDecagenc());
                }
                if (depart.getDecserv() != null) {
                    existingDepart.setDecserv(depart.getDecserv());
                }
                if (depart.getDecoper() != null) {
                    existingDepart.setDecoper(depart.getDecoper());
                }
                if (depart.getDecsean() != null) {
                    existingDepart.setDecsean(depart.getDecsean());
                }
                if (depart.getDedated() != null) {
                    existingDepart.setDedated(depart.getDedated());
                }
                if (depart.getDenumdp() != null) {
                    existingDepart.setDenumdp(depart.getDenumdp());
                }
                if (depart.getMatric() != null) {
                    existingDepart.setMatric(depart.getMatric());
                }
                if (depart.getMatric1() != null) {
                    existingDepart.setMatric1(depart.getMatric1());
                }
                if (depart.getCdmac() != null) {
                    existingDepart.setCdmac(depart.getCdmac());
                }
                if (depart.getDeheups() != null) {
                    existingDepart.setDeheups(depart.getDeheups());
                }
                if (depart.getDeheufs() != null) {
                    existingDepart.setDeheufs(depart.getDeheufs());
                }
                if (depart.getDenbrro() != null) {
                    existingDepart.setDenbrro(depart.getDenbrro());
                }
                if (depart.getDeheuaa() != null) {
                    existingDepart.setDeheuaa(depart.getDeheuaa());
                }
                if (depart.getDeheudr() != null) {
                    existingDepart.setDeheudr(depart.getDeheudr());
                }
                if (depart.getDeheupd() != null) {
                    existingDepart.setDeheupd(depart.getDeheupd());
                }
                if (depart.getDeampli() != null) {
                    existingDepart.setDeampli(depart.getDeampli());
                }
                if (depart.getObs_ind() != null) {
                    existingDepart.setObs_ind(depart.getObs_ind());
                }
                if (depart.getVld_roul() != null) {
                    existingDepart.setVld_roul(depart.getVld_roul());
                }
                if (depart.getDeetat() != null) {
                    existingDepart.setDeetat(depart.getDeetat());
                }
                if (depart.getDeannul() != null) {
                    existingDepart.setDeannul(depart.getDeannul());
                }
                if (depart.getDecclot() != null) {
                    existingDepart.setDecclot(depart.getDecclot());
                }
                if (depart.getExecute() != null) {
                    existingDepart.setExecute(depart.getExecute());
                }
                if (depart.getMotif_a() != null) {
                    existingDepart.setMotif_a(depart.getMotif_a());
                }
                if (depart.getObserv() != null) {
                    existingDepart.setObserv(depart.getObserv());
                }
                if (depart.getRecettes() != null) {
                    existingDepart.setRecettes(depart.getRecettes());
                }
                if (depart.getNbre_voy() != null) {
                    existingDepart.setNbre_voy(depart.getNbre_voy());
                }
                if (depart.getDecmotifch() != null) {
                    existingDepart.setDecmotifch(depart.getDecmotifch());
                }
                if (depart.getDecmotifre() != null) {
                    existingDepart.setDecmotifre(depart.getDecmotifre());
                }
                if (depart.getCd1() != null) {
                    existingDepart.setCd1(depart.getCd1());
                }
                if (depart.getCd2() != null) {
                    existingDepart.setCd2(depart.getCd2());
                }
                if (depart.getCd3() != null) {
                    existingDepart.setCd3(depart.getCd3());
                }
                if (depart.getDecmotifcha() != null) {
                    existingDepart.setDecmotifcha(depart.getDecmotifcha());
                }
                if (depart.getDecmotifrea() != null) {
                    existingDepart.setDecmotifrea(depart.getDecmotifrea());
                }

                return existingDepart;
            })
            .map(departRepository::save);

        return ResponseUtil.wrapOrNotFound(result, HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, depart.getId()));
    }

    /**
     * {@code GET  /departs} : get all the departs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departs in body.
     */
    /*  @GetMapping("/departs")
    public List<Depart> getAllDeparts() {
        log.debug("REST request to get all Departs");
        return departRepository.findAll();
    }*/

    @GetMapping("/departs")
    public ResponseEntity<List<DepartDTO>> getAllDeparts(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Departs");
        Page<DepartDTO> page = departService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /departs/:id} : get the "id" depart.
     *
     * @param id the id of the depart to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the depart, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departs/{id}")
    public ResponseEntity<Depart> getDepart(@PathVariable String id) {
        log.debug("REST request to get Depart : {}", id);
        Optional<Depart> depart = departRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(depart);
    }

    /**
     * {@code DELETE  /departs/:id} : delete the "id" depart.
     *
     * @param id the id of the depart to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departs/{id}")
    public ResponseEntity<Void> deleteDepart(@PathVariable String id) {
        log.debug("REST request to delete Depart : {}", id);
        departRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
