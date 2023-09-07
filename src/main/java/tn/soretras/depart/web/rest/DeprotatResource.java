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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.soretras.depart.domain.Deprotat;
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.soretras.depart.domain.Deprotat}.
 */
@RestController
@RequestMapping("/api")
public class DeprotatResource {

    private final Logger log = LoggerFactory.getLogger(DeprotatResource.class);

    private static final String ENTITY_NAME = "deprotat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeprotatRepository deprotatRepository;

    public DeprotatResource(DeprotatRepository deprotatRepository) {
        this.deprotatRepository = deprotatRepository;
    }

    /**
     * {@code POST  /deprotats} : Create a new deprotat.
     *
     * @param deprotat the deprotat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deprotat, or with status {@code 400 (Bad Request)} if the deprotat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/deprotats")
    public ResponseEntity<Deprotat> createDeprotat(@Valid @RequestBody Deprotat deprotat) throws URISyntaxException {
        log.debug("REST request to save Deprotat : {}", deprotat);
        if (deprotat.getId() != null) {
            throw new BadRequestAlertException("A new deprotat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Deprotat result = deprotatRepository.save(deprotat);
        return ResponseEntity
            .created(new URI("/api/deprotats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /deprotats/:id} : Updates an existing deprotat.
     *
     * @param id the id of the deprotat to save.
     * @param deprotat the deprotat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deprotat,
     * or with status {@code 400 (Bad Request)} if the deprotat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deprotat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/deprotats/{id}")
    public ResponseEntity<Deprotat> updateDeprotat(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Deprotat deprotat
    ) throws URISyntaxException {
        log.debug("REST request to update Deprotat : {}, {}", id, deprotat);
        if (deprotat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deprotat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deprotatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Deprotat result = deprotatRepository.save(deprotat);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deprotat.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /deprotats/:id} : Partial updates given fields of an existing deprotat, field will ignore if it is null
     *
     * @param id the id of the deprotat to save.
     * @param deprotat the deprotat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deprotat,
     * or with status {@code 400 (Bad Request)} if the deprotat is not valid,
     * or with status {@code 404 (Not Found)} if the deprotat is not found,
     * or with status {@code 500 (Internal Server Error)} if the deprotat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/deprotats/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Deprotat> partialUpdateDeprotat(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Deprotat deprotat
    ) throws URISyntaxException {
        log.debug("REST request to partial update Deprotat partially : {}, {}", id, deprotat);
        if (deprotat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deprotat.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deprotatRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Deprotat> result = deprotatRepository
            .findById(deprotat.getId())
            .map(existingDeprotat -> {
                if (deprotat.getDeccent() != null) {
                    existingDeprotat.setDeccent(deprotat.getDeccent());
                }
                if (deprotat.getDecagenc() != null) {
                    existingDeprotat.setDecagenc(deprotat.getDecagenc());
                }
                if (deprotat.getDedated() != null) {
                    existingDeprotat.setDedated(deprotat.getDedated());
                }
                if (deprotat.getDenumdp() != null) {
                    existingDeprotat.setDenumdp(deprotat.getDenumdp());
                }
                if (deprotat.getDecserv() != null) {
                    existingDeprotat.setDecserv(deprotat.getDecserv());
                }
                if (deprotat.getDecoper() != null) {
                    existingDeprotat.setDecoper(deprotat.getDecoper());
                }
                if (deprotat.getDecsean() != null) {
                    existingDeprotat.setDecsean(deprotat.getDecsean());
                }
                if (deprotat.getNumrotat() != null) {
                    existingDeprotat.setNumrotat(deprotat.getNumrotat());
                }
                if (deprotat.getLigdeccent() != null) {
                    existingDeprotat.setLigdeccent(deprotat.getLigdeccent());
                }
                if (deprotat.getLigdecagenc() != null) {
                    existingDeprotat.setLigdecagenc(deprotat.getLigdecagenc());
                }
                if (deprotat.getDenumli() != null) {
                    existingDeprotat.setDenumli(deprotat.getDenumli());
                }
                if (deprotat.getDecstat() != null) {
                    existingDeprotat.setDecstat(deprotat.getDecstat());
                }
                if (deprotat.getDecsta1() != null) {
                    existingDeprotat.setDecsta1(deprotat.getDecsta1());
                }
                if (deprotat.getMatric() != null) {
                    existingDeprotat.setMatric(deprotat.getMatric());
                }
                if (deprotat.getMatric1() != null) {
                    existingDeprotat.setMatric1(deprotat.getMatric1());
                }
                if (deprotat.getCdmac() != null) {
                    existingDeprotat.setCdmac(deprotat.getCdmac());
                }
                if (deprotat.getHdeparte() != null) {
                    existingDeprotat.setHdeparte(deprotat.getHdeparte());
                }
                if (deprotat.getHretoure() != null) {
                    existingDeprotat.setHretoure(deprotat.getHretoure());
                }
                if (deprotat.getHarralle() != null) {
                    existingDeprotat.setHarralle(deprotat.getHarralle());
                }
                if (deprotat.getHarrrete() != null) {
                    existingDeprotat.setHarrrete(deprotat.getHarrrete());
                }
                if (deprotat.getRannul() != null) {
                    existingDeprotat.setRannul(deprotat.getRannul());
                }
                if (deprotat.getKm() != null) {
                    existingDeprotat.setKm(deprotat.getKm());
                }
                if (deprotat.getMotifa() != null) {
                    existingDeprotat.setMotifa(deprotat.getMotifa());
                }
                if (deprotat.getObserv() != null) {
                    existingDeprotat.setObserv(deprotat.getObserv());
                }
                if (deprotat.getRecettesvoy() != null) {
                    existingDeprotat.setRecettesvoy(deprotat.getRecettesvoy());
                }
                if (deprotat.getNbrevoy() != null) {
                    existingDeprotat.setNbrevoy(deprotat.getNbrevoy());
                }
                if (deprotat.getPaye() != null) {
                    existingDeprotat.setPaye(deprotat.getPaye());
                }
                if (deprotat.getCd1() != null) {
                    existingDeprotat.setCd1(deprotat.getCd1());
                }
                if (deprotat.getCd2() != null) {
                    existingDeprotat.setCd2(deprotat.getCd2());
                }
                if (deprotat.getCd3() != null) {
                    existingDeprotat.setCd3(deprotat.getCd3());
                }
                if (deprotat.getDecmotifcha() != null) {
                    existingDeprotat.setDecmotifcha(deprotat.getDecmotifcha());
                }
                if (deprotat.getDecmotifrea() != null) {
                    existingDeprotat.setDecmotifrea(deprotat.getDecmotifrea());
                }
                if (deprotat.getIdapex() != null) {
                    existingDeprotat.setIdapex(deprotat.getIdapex());
                }
                if (deprotat.getPlusmoins() != null) {
                    existingDeprotat.setPlusmoins(deprotat.getPlusmoins());
                }
                if (deprotat.getA() != null) {
                    existingDeprotat.setA(deprotat.getA());
                }
                if (deprotat.getR() != null) {
                    existingDeprotat.setR(deprotat.getR());
                }

                return existingDeprotat;
            })
            .map(deprotatRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, deprotat.getId())
        );
    }

    /**
     * {@code GET  /deprotats} : get all the deprotats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of deprotats in body.
     */
    @GetMapping("/deprotats")
    public List<Deprotat> getAllDeprotats() {
        log.debug("REST request to get all Deprotats");
        return deprotatRepository.findAll();
    }

    /**
     * {@code GET  /deprotats/:id} : get the "id" deprotat.
     *
     * @param id the id of the deprotat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deprotat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/deprotats/{id}")
    public ResponseEntity<Deprotat> getDeprotat(@PathVariable String id) {
        log.debug("REST request to get Deprotat : {}", id);
        Optional<Deprotat> deprotat = deprotatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(deprotat);
    }

    /**
     * {@code DELETE  /deprotats/:id} : delete the "id" deprotat.
     *
     * @param id the id of the deprotat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/deprotats/{id}")
    public ResponseEntity<Void> deleteDeprotat(@PathVariable String id) {
        log.debug("REST request to delete Deprotat : {}", id);
        deprotatRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
