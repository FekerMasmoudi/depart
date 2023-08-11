package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Ligne;
import tn.soretras.depart.repository.LigneRepository;
import tn.soretras.depart.service.dto.LigneDTO;
import tn.soretras.depart.service.mapper.LigneMapper;

/**
 * Service Implementation for managing {@link Ligne}.
 */
@Service
public class LigneService {

    private final Logger log = LoggerFactory.getLogger(LigneService.class);

    private final LigneRepository ligneRepository;

    private final LigneMapper ligneMapper;

    public LigneService(LigneRepository ligneRepository, LigneMapper ligneMapper) {
        this.ligneRepository = ligneRepository;
        this.ligneMapper = ligneMapper;
    }

    /**
     * Save a ligne.
     *
     * @param ligneDTO the entity to save.
     * @return the persisted entity.
     */
    public LigneDTO save(LigneDTO ligneDTO) {
        log.debug("Request to save Ligne : {}", ligneDTO);
        Ligne ligne = ligneMapper.toEntity(ligneDTO);
        ligne = ligneRepository.save(ligne);
        return ligneMapper.toDto(ligne);
    }

    /**
     * Update a ligne.
     *
     * @param ligneDTO the entity to save.
     * @return the persisted entity.
     */
    public LigneDTO update(LigneDTO ligneDTO) {
        log.debug("Request to update Ligne : {}", ligneDTO);
        Ligne ligne = ligneMapper.toEntity(ligneDTO);
        ligne = ligneRepository.save(ligne);
        return ligneMapper.toDto(ligne);
    }

    /**
     * Partially update a ligne.
     *
     * @param ligneDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<LigneDTO> partialUpdate(LigneDTO ligneDTO) {
        log.debug("Request to partially update Ligne : {}", ligneDTO);

        return ligneRepository
            .findById(ligneDTO.getId())
            .map(existingLigne -> {
                ligneMapper.partialUpdate(existingLigne, ligneDTO);

                return existingLigne;
            })
            .map(ligneRepository::save)
            .map(ligneMapper::toDto);
    }

    /**
     * Get all the lignes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<LigneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lignes");
        return ligneRepository.findAll(pageable).map(ligneMapper::toDto);
    }

    /**
     * Get one ligne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<LigneDTO> findOne(String id) {
        log.debug("Request to get Ligne : {}", id);
        return ligneRepository.findById(id).map(ligneMapper::toDto);
    }

    /**
     * Delete the ligne by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Ligne : {}", id);
        ligneRepository.deleteById(id);
    }
}
