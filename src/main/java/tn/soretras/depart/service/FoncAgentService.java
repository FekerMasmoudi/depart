package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.FoncAgent;
import tn.soretras.depart.repository.FoncAgentRepository;
import tn.soretras.depart.service.dto.FoncAgentDTO;
import tn.soretras.depart.service.mapper.FoncAgentMapper;

/**
 * Service Implementation for managing {@link FoncAgent}.
 */
@Service
public class FoncAgentService {

    private final Logger log = LoggerFactory.getLogger(FoncAgentService.class);

    private final FoncAgentRepository foncAgentRepository;

    private final FoncAgentMapper foncAgentMapper;

    public FoncAgentService(FoncAgentRepository foncAgentRepository, FoncAgentMapper foncAgentMapper) {
        this.foncAgentRepository = foncAgentRepository;
        this.foncAgentMapper = foncAgentMapper;
    }

    /**
     * Save a foncAgent.
     *
     * @param foncAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public FoncAgentDTO save(FoncAgentDTO foncAgentDTO) {
        log.debug("Request to save FoncAgent : {}", foncAgentDTO);
        FoncAgent foncAgent = foncAgentMapper.toEntity(foncAgentDTO);
        foncAgent = foncAgentRepository.save(foncAgent);
        return foncAgentMapper.toDto(foncAgent);
    }

    /**
     * Update a foncAgent.
     *
     * @param foncAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public FoncAgentDTO update(FoncAgentDTO foncAgentDTO) {
        log.debug("Request to update FoncAgent : {}", foncAgentDTO);
        FoncAgent foncAgent = foncAgentMapper.toEntity(foncAgentDTO);
        foncAgent = foncAgentRepository.save(foncAgent);
        return foncAgentMapper.toDto(foncAgent);
    }

    /**
     * Partially update a foncAgent.
     *
     * @param foncAgentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FoncAgentDTO> partialUpdate(FoncAgentDTO foncAgentDTO) {
        log.debug("Request to partially update FoncAgent : {}", foncAgentDTO);

        return foncAgentRepository
            .findById(foncAgentDTO.getId())
            .map(existingFoncAgent -> {
                foncAgentMapper.partialUpdate(existingFoncAgent, foncAgentDTO);

                return existingFoncAgent;
            })
            .map(foncAgentRepository::save)
            .map(foncAgentMapper::toDto);
    }

    /**
     * Get all the foncAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<FoncAgentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FoncAgents");
        return foncAgentRepository.findAll(pageable).map(foncAgentMapper::toDto);
    }

    /**
     * Get one foncAgent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<FoncAgentDTO> findOne(String id) {
        log.debug("Request to get FoncAgent : {}", id);
        return foncAgentRepository.findById(id).map(foncAgentMapper::toDto);
    }

    /**
     * Delete the foncAgent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete FoncAgent : {}", id);
        foncAgentRepository.deleteById(id);
    }
}
