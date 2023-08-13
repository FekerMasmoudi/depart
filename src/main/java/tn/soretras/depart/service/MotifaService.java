package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Motifa;
import tn.soretras.depart.repository.MotifaRepository;
import tn.soretras.depart.service.dto.MotifaDTO;
import tn.soretras.depart.service.mapper.MotifaMapper;

/**
 * Service Implementation for managing {@link Motifa}.
 */
@Service
public class MotifaService {

    private final Logger log = LoggerFactory.getLogger(MotifaService.class);

    private final MotifaRepository motifaRepository;

    private final MotifaMapper motifaMapper;

    public MotifaService(MotifaRepository motifaRepository, MotifaMapper motifaMapper) {
        this.motifaRepository = motifaRepository;
        this.motifaMapper = motifaMapper;
    }

    /**
     * Save a motifa.
     *
     * @param motifaDTO the entity to save.
     * @return the persisted entity.
     */
    public MotifaDTO save(MotifaDTO motifaDTO) {
        log.debug("Request to save Motifa : {}", motifaDTO);
        Motifa motifa = motifaMapper.toEntity(motifaDTO);
        motifa = motifaRepository.save(motifa);
        return motifaMapper.toDto(motifa);
    }

    /**
     * Update a motifa.
     *
     * @param motifaDTO the entity to save.
     * @return the persisted entity.
     */
    public MotifaDTO update(MotifaDTO motifaDTO) {
        log.debug("Request to update Motifa : {}", motifaDTO);
        Motifa motifa = motifaMapper.toEntity(motifaDTO);
        motifa = motifaRepository.save(motifa);
        return motifaMapper.toDto(motifa);
    }

    /**
     * Partially update a motifa.
     *
     * @param motifaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MotifaDTO> partialUpdate(MotifaDTO motifaDTO) {
        log.debug("Request to partially update Motifa : {}", motifaDTO);

        return motifaRepository
            .findById(motifaDTO.getId())
            .map(existingMotifa -> {
                motifaMapper.partialUpdate(existingMotifa, motifaDTO);

                return existingMotifa;
            })
            .map(motifaRepository::save)
            .map(motifaMapper::toDto);
    }

    /**
     * Get all the motifas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<MotifaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Motifas");
        return motifaRepository.findAll(pageable).map(motifaMapper::toDto);
    }

    /**
     * Get one motifa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<MotifaDTO> findOne(String id) {
        log.debug("Request to get Motifa : {}", id);
        return motifaRepository.findById(id).map(motifaMapper::toDto);
    }

    /**
     * Delete the motifa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Motifa : {}", id);
        motifaRepository.deleteById(id);
    }
}
