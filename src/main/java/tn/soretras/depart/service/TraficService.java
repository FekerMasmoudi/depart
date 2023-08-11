package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Trafic;
import tn.soretras.depart.repository.TraficRepository;
import tn.soretras.depart.service.dto.TraficDTO;
import tn.soretras.depart.service.mapper.TraficMapper;

/**
 * Service Implementation for managing {@link Trafic}.
 */
@Service
public class TraficService {

    private final Logger log = LoggerFactory.getLogger(TraficService.class);

    private final TraficRepository traficRepository;

    private final TraficMapper traficMapper;

    public TraficService(TraficRepository traficRepository, TraficMapper traficMapper) {
        this.traficRepository = traficRepository;
        this.traficMapper = traficMapper;
    }

    /**
     * Save a trafic.
     *
     * @param traficDTO the entity to save.
     * @return the persisted entity.
     */
    public TraficDTO save(TraficDTO traficDTO) {
        log.debug("Request to save Trafic : {}", traficDTO);
        Trafic trafic = traficMapper.toEntity(traficDTO);
        trafic = traficRepository.save(trafic);
        return traficMapper.toDto(trafic);
    }

    /**
     * Update a trafic.
     *
     * @param traficDTO the entity to save.
     * @return the persisted entity.
     */
    public TraficDTO update(TraficDTO traficDTO) {
        log.debug("Request to update Trafic : {}", traficDTO);
        Trafic trafic = traficMapper.toEntity(traficDTO);
        trafic = traficRepository.save(trafic);
        return traficMapper.toDto(trafic);
    }

    /**
     * Partially update a trafic.
     *
     * @param traficDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TraficDTO> partialUpdate(TraficDTO traficDTO) {
        log.debug("Request to partially update Trafic : {}", traficDTO);

        return traficRepository
            .findById(traficDTO.getId())
            .map(existingTrafic -> {
                traficMapper.partialUpdate(existingTrafic, traficDTO);

                return existingTrafic;
            })
            .map(traficRepository::save)
            .map(traficMapper::toDto);
    }

    /**
     * Get all the trafics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<TraficDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trafics");
        return traficRepository.findAll(pageable).map(traficMapper::toDto);
    }

    /**
     * Get one trafic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<TraficDTO> findOne(String id) {
        log.debug("Request to get Trafic : {}", id);
        return traficRepository.findById(id).map(traficMapper::toDto);
    }

    /**
     * Delete the trafic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Trafic : {}", id);
        traficRepository.deleteById(id);
    }
}
