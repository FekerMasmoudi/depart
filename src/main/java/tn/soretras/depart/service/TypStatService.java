package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.TypStat;
import tn.soretras.depart.repository.TypStatRepository;
import tn.soretras.depart.service.dto.TypStatDTO;
import tn.soretras.depart.service.mapper.TypStatMapper;

/**
 * Service Implementation for managing {@link TypStat}.
 */
@Service
public class TypStatService {

    private final Logger log = LoggerFactory.getLogger(TypStatService.class);

    private final TypStatRepository typStatRepository;

    private final TypStatMapper typStatMapper;

    public TypStatService(TypStatRepository typStatRepository, TypStatMapper typStatMapper) {
        this.typStatRepository = typStatRepository;
        this.typStatMapper = typStatMapper;
    }

    /**
     * Save a typStat.
     *
     * @param typStatDTO the entity to save.
     * @return the persisted entity.
     */
    public TypStatDTO save(TypStatDTO typStatDTO) {
        log.debug("Request to save TypStat : {}", typStatDTO);
        TypStat typStat = typStatMapper.toEntity(typStatDTO);
        typStat = typStatRepository.save(typStat);
        return typStatMapper.toDto(typStat);
    }

    /**
     * Update a typStat.
     *
     * @param typStatDTO the entity to save.
     * @return the persisted entity.
     */
    public TypStatDTO update(TypStatDTO typStatDTO) {
        log.debug("Request to update TypStat : {}", typStatDTO);
        TypStat typStat = typStatMapper.toEntity(typStatDTO);
        typStat = typStatRepository.save(typStat);
        return typStatMapper.toDto(typStat);
    }

    /**
     * Partially update a typStat.
     *
     * @param typStatDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TypStatDTO> partialUpdate(TypStatDTO typStatDTO) {
        log.debug("Request to partially update TypStat : {}", typStatDTO);

        return typStatRepository
            .findById(typStatDTO.getId())
            .map(existingTypStat -> {
                typStatMapper.partialUpdate(existingTypStat, typStatDTO);

                return existingTypStat;
            })
            .map(typStatRepository::save)
            .map(typStatMapper::toDto);
    }

    /**
     * Get all the typStats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<TypStatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TypStats");
        return typStatRepository.findAll(pageable).map(typStatMapper::toDto);
    }

    /**
     * Get one typStat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<TypStatDTO> findOne(String id) {
        log.debug("Request to get TypStat : {}", id);
        return typStatRepository.findById(id).map(typStatMapper::toDto);
    }

    /**
     * Delete the typStat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete TypStat : {}", id);
        typStatRepository.deleteById(id);
    }
}
