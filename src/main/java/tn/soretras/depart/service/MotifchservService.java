package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Motifchserv;
import tn.soretras.depart.repository.MotifchservRepository;
import tn.soretras.depart.service.dto.MotifchservDTO;
import tn.soretras.depart.service.mapper.MotifchservMapper;

/**
 * Service Implementation for managing {@link Motifchserv}.
 */
@Service
public class MotifchservService {

    private final Logger log = LoggerFactory.getLogger(MotifchservService.class);

    private final MotifchservRepository motifchservRepository;

    private final MotifchservMapper motifchservMapper;

    public MotifchservService(MotifchservRepository motifchservRepository, MotifchservMapper motifchservMapper) {
        this.motifchservRepository = motifchservRepository;
        this.motifchservMapper = motifchservMapper;
    }

    /**
     * Save a motifchserv.
     *
     * @param motifchservDTO the entity to save.
     * @return the persisted entity.
     */
    public MotifchservDTO save(MotifchservDTO motifchservDTO) {
        log.debug("Request to save Motifchserv : {}", motifchservDTO);
        Motifchserv motifchserv = motifchservMapper.toEntity(motifchservDTO);
        motifchserv = motifchservRepository.save(motifchserv);
        return motifchservMapper.toDto(motifchserv);
    }

    /**
     * Update a motifchserv.
     *
     * @param motifchservDTO the entity to save.
     * @return the persisted entity.
     */
    public MotifchservDTO update(MotifchservDTO motifchservDTO) {
        log.debug("Request to update Motifchserv : {}", motifchservDTO);
        Motifchserv motifchserv = motifchservMapper.toEntity(motifchservDTO);
        motifchserv = motifchservRepository.save(motifchserv);
        return motifchservMapper.toDto(motifchserv);
    }

    /**
     * Partially update a motifchserv.
     *
     * @param motifchservDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MotifchservDTO> partialUpdate(MotifchservDTO motifchservDTO) {
        log.debug("Request to partially update Motifchserv : {}", motifchservDTO);

        return motifchservRepository
            .findById(motifchservDTO.getId())
            .map(existingMotifchserv -> {
                motifchservMapper.partialUpdate(existingMotifchserv, motifchservDTO);

                return existingMotifchserv;
            })
            .map(motifchservRepository::save)
            .map(motifchservMapper::toDto);
    }

    /**
     * Get all the motifchservs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<MotifchservDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Motifchservs");
        return motifchservRepository.findAll(pageable).map(motifchservMapper::toDto);
    }

    /**
     * Get one motifchserv by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<MotifchservDTO> findOne(String id) {
        log.debug("Request to get Motifchserv : {}", id);
        return motifchservRepository.findById(id).map(motifchservMapper::toDto);
    }

    /**
     * Delete the motifchserv by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Motifchserv : {}", id);
        motifchservRepository.deleteById(id);
    }
}
