package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Drtypab;
import tn.soretras.depart.repository.DrtypabRepository;
import tn.soretras.depart.service.dto.DrtypabDTO;
import tn.soretras.depart.service.mapper.DrtypabMapper;

/**
 * Service Implementation for managing {@link Drtypab}.
 */
@Service
public class DrtypabService {

    private final Logger log = LoggerFactory.getLogger(DrtypabService.class);

    private final DrtypabRepository drtypabRepository;

    private final DrtypabMapper drtypabMapper;

    public DrtypabService(DrtypabRepository drtypabRepository, DrtypabMapper drtypabMapper) {
        this.drtypabRepository = drtypabRepository;
        this.drtypabMapper = drtypabMapper;
    }

    /**
     * Save a drtypab.
     *
     * @param drtypabDTO the entity to save.
     * @return the persisted entity.
     */
    public DrtypabDTO save(DrtypabDTO drtypabDTO) {
        log.debug("Request to save Drtypab : {}", drtypabDTO);
        Drtypab drtypab = drtypabMapper.toEntity(drtypabDTO);
        drtypab = drtypabRepository.save(drtypab);
        return drtypabMapper.toDto(drtypab);
    }

    /**
     * Update a drtypab.
     *
     * @param drtypabDTO the entity to save.
     * @return the persisted entity.
     */
    public DrtypabDTO update(DrtypabDTO drtypabDTO) {
        log.debug("Request to update Drtypab : {}", drtypabDTO);
        Drtypab drtypab = drtypabMapper.toEntity(drtypabDTO);
        drtypab = drtypabRepository.save(drtypab);
        return drtypabMapper.toDto(drtypab);
    }

    /**
     * Partially update a drtypab.
     *
     * @param drtypabDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DrtypabDTO> partialUpdate(DrtypabDTO drtypabDTO) {
        log.debug("Request to partially update Drtypab : {}", drtypabDTO);

        return drtypabRepository
            .findById(drtypabDTO.getId())
            .map(existingDrtypab -> {
                drtypabMapper.partialUpdate(existingDrtypab, drtypabDTO);

                return existingDrtypab;
            })
            .map(drtypabRepository::save)
            .map(drtypabMapper::toDto);
    }

    /**
     * Get all the drtypabs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DrtypabDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Drtypabs");
        return drtypabRepository.findAll(pageable).map(drtypabMapper::toDto);
    }

    /**
     * Get one drtypab by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DrtypabDTO> findOne(String id) {
        log.debug("Request to get Drtypab : {}", id);
        return drtypabRepository.findById(id).map(drtypabMapper::toDto);
    }

    /**
     * Delete the drtypab by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Drtypab : {}", id);
        drtypabRepository.deleteById(id);
    }
}
