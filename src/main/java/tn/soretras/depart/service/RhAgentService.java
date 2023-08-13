package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.RhAgent;
import tn.soretras.depart.repository.RhAgentRepository;
import tn.soretras.depart.service.dto.RhAgentDTO;
import tn.soretras.depart.service.mapper.RhAgentMapper;

/**
 * Service Implementation for managing {@link RhAgent}.
 */
@Service
public class RhAgentService {

    private final Logger log = LoggerFactory.getLogger(RhAgentService.class);

    private final RhAgentRepository rhAgentRepository;

    private final RhAgentMapper rhAgentMapper;

    public RhAgentService(RhAgentRepository rhAgentRepository, RhAgentMapper rhAgentMapper) {
        this.rhAgentRepository = rhAgentRepository;
        this.rhAgentMapper = rhAgentMapper;
    }

    /**
     * Save a rhAgent.
     *
     * @param rhAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public RhAgentDTO save(RhAgentDTO rhAgentDTO) {
        log.debug("Request to save RhAgent : {}", rhAgentDTO);
        RhAgent rhAgent = rhAgentMapper.toEntity(rhAgentDTO);
        rhAgent = rhAgentRepository.save(rhAgent);
        return rhAgentMapper.toDto(rhAgent);
    }

    /**
     * Update a rhAgent.
     *
     * @param rhAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public RhAgentDTO update(RhAgentDTO rhAgentDTO) {
        log.debug("Request to update RhAgent : {}", rhAgentDTO);
        RhAgent rhAgent = rhAgentMapper.toEntity(rhAgentDTO);
        rhAgent = rhAgentRepository.save(rhAgent);
        return rhAgentMapper.toDto(rhAgent);
    }

    /**
     * Partially update a rhAgent.
     *
     * @param rhAgentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RhAgentDTO> partialUpdate(RhAgentDTO rhAgentDTO) {
        log.debug("Request to partially update RhAgent : {}", rhAgentDTO);

        return rhAgentRepository
            .findById(rhAgentDTO.getId())
            .map(existingRhAgent -> {
                rhAgentMapper.partialUpdate(existingRhAgent, rhAgentDTO);

                return existingRhAgent;
            })
            .map(rhAgentRepository::save)
            .map(rhAgentMapper::toDto);
    }

    /**
     * Get all the rhAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RhAgentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RhAgents");
        return rhAgentRepository.findAll(pageable).map(rhAgentMapper::toDto);
    }

    /**
     * Get one rhAgent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RhAgentDTO> findOne(String id) {
        log.debug("Request to get RhAgent : {}", id);
        return rhAgentRepository.findById(id).map(rhAgentMapper::toDto);
    }

    /**
     * Delete the rhAgent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RhAgent : {}", id);
        rhAgentRepository.deleteById(id);
    }
}
