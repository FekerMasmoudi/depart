package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Center;
import tn.soretras.depart.repository.CenterRepository;
import tn.soretras.depart.service.dto.CenterDTO;
import tn.soretras.depart.service.mapper.CenterMapper;

/**
 * Service Implementation for managing {@link Center}.
 */
@Service
public class CenterService {

    private final Logger log = LoggerFactory.getLogger(CenterService.class);

    private final CenterRepository centerRepository;

    private final CenterMapper centerMapper;

    public CenterService(CenterRepository centerRepository, CenterMapper centerMapper) {
        this.centerRepository = centerRepository;
        this.centerMapper = centerMapper;
    }

    /**
     * Save a center.
     *
     * @param centerDTO the entity to save.
     * @return the persisted entity.
     */
    public CenterDTO save(CenterDTO centerDTO) {
        log.debug("Request to save Center : {}", centerDTO);
        Center center = centerMapper.toEntity(centerDTO);
        center = centerRepository.save(center);
        return centerMapper.toDto(center);
    }

    /**
     * Update a center.
     *
     * @param centerDTO the entity to save.
     * @return the persisted entity.
     */
    public CenterDTO update(CenterDTO centerDTO) {
        log.debug("Request to update Center : {}", centerDTO);
        Center center = centerMapper.toEntity(centerDTO);
        center = centerRepository.save(center);
        return centerMapper.toDto(center);
    }

    /**
     * Partially update a center.
     *
     * @param centerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CenterDTO> partialUpdate(CenterDTO centerDTO) {
        log.debug("Request to partially update Center : {}", centerDTO);

        return centerRepository
            .findById(centerDTO.getId())
            .map(existingCenter -> {
                centerMapper.partialUpdate(existingCenter, centerDTO);

                return existingCenter;
            })
            .map(centerRepository::save)
            .map(centerMapper::toDto);
    }

    /**
     * Get all the centers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Centers");
        return centerRepository.findAll(pageable).map(centerMapper::toDto);
    }

    /**
     * Get one center by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CenterDTO> findOne(String id) {
        log.debug("Request to get Center : {}", id);
        return centerRepository.findById(id).map(centerMapper::toDto);
    }

    /**
     * Delete the center by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Center : {}", id);
        centerRepository.deleteById(id);
    }
}
