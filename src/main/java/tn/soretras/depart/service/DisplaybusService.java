package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Displaybus;
import tn.soretras.depart.repository.DisplaybusRepository;
import tn.soretras.depart.service.dto.DisplaybusDTO;
import tn.soretras.depart.service.mapper.DisplaybusMapper;

/**
 * Service Implementation for managing {@link Displaybus}.
 */
@Service
public class DisplaybusService {

    private final Logger log = LoggerFactory.getLogger(DisplaybusService.class);

    private final DisplaybusRepository displaybusRepository;

    private final DisplaybusMapper displaybusMapper;

    public DisplaybusService(DisplaybusRepository displaybusRepository, DisplaybusMapper displaybusMapper) {
        this.displaybusRepository = displaybusRepository;
        this.displaybusMapper = displaybusMapper;
    }

    /**
     * Save a displaybus.
     *
     * @param displaybusDTO the entity to save.
     * @return the persisted entity.
     */
    public DisplaybusDTO save(DisplaybusDTO displaybusDTO) {
        log.debug("Request to save Displaybus : {}", displaybusDTO);
        Displaybus displaybus = displaybusMapper.toEntity(displaybusDTO);
        displaybus = displaybusRepository.save(displaybus);
        return displaybusMapper.toDto(displaybus);
    }

    /**
     * Update a displaybus.
     *
     * @param displaybusDTO the entity to save.
     * @return the persisted entity.
     */
    public DisplaybusDTO update(DisplaybusDTO displaybusDTO) {
        log.debug("Request to update Displaybus : {}", displaybusDTO);
        Displaybus displaybus = displaybusMapper.toEntity(displaybusDTO);
        displaybus = displaybusRepository.save(displaybus);
        return displaybusMapper.toDto(displaybus);
    }

    /**
     * Partially update a displaybus.
     *
     * @param displaybusDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DisplaybusDTO> partialUpdate(DisplaybusDTO displaybusDTO) {
        log.debug("Request to partially update Displaybus : {}", displaybusDTO);

        return displaybusRepository
            .findById(displaybusDTO.getId())
            .map(existingDisplaybus -> {
                displaybusMapper.partialUpdate(existingDisplaybus, displaybusDTO);

                return existingDisplaybus;
            })
            .map(displaybusRepository::save)
            .map(displaybusMapper::toDto);
    }

    /**
     * Get all the displaybuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DisplaybusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Displaybuses");
        return displaybusRepository.findAll(pageable).map(displaybusMapper::toDto);
    }

    /**
     * Get one displaybus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DisplaybusDTO> findOne(String id) {
        log.debug("Request to get Displaybus : {}", id);
        return displaybusRepository.findById(id).map(displaybusMapper::toDto);
    }

    /**
     * Delete the displaybus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Displaybus : {}", id);
        displaybusRepository.deleteById(id);
    }
}
