package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.AffecAgent;
import tn.soretras.depart.repository.AffecAgentRepository;
import tn.soretras.depart.service.dto.AffecAgentDTO;
import tn.soretras.depart.service.mapper.AffecAgentMapper;

/**
 * Service Implementation for managing {@link AffecAgent}.
 */
@Service
public class AffecAgentService {

    private final Logger log = LoggerFactory.getLogger(AffecAgentService.class);

    private final AffecAgentRepository affecAgentRepository;

    private final AffecAgentMapper affecAgentMapper;

    public AffecAgentService(AffecAgentRepository affecAgentRepository, AffecAgentMapper affecAgentMapper) {
        this.affecAgentRepository = affecAgentRepository;
        this.affecAgentMapper = affecAgentMapper;
    }

    /**
     * Save a affecAgent.
     *
     * @param affecAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public AffecAgentDTO save(AffecAgentDTO affecAgentDTO) {
        log.debug("Request to save AffecAgent : {}", affecAgentDTO);
        AffecAgent affecAgent = affecAgentMapper.toEntity(affecAgentDTO);
        affecAgent = affecAgentRepository.save(affecAgent);
        return affecAgentMapper.toDto(affecAgent);
    }

    /**
     * Update a affecAgent.
     *
     * @param affecAgentDTO the entity to save.
     * @return the persisted entity.
     */
    public AffecAgentDTO update(AffecAgentDTO affecAgentDTO) {
        log.debug("Request to update AffecAgent : {}", affecAgentDTO);
        AffecAgent affecAgent = affecAgentMapper.toEntity(affecAgentDTO);
        affecAgent = affecAgentRepository.save(affecAgent);
        return affecAgentMapper.toDto(affecAgent);
    }

    /**
     * Partially update a affecAgent.
     *
     * @param affecAgentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AffecAgentDTO> partialUpdate(AffecAgentDTO affecAgentDTO) {
        log.debug("Request to partially update AffecAgent : {}", affecAgentDTO);

        return affecAgentRepository
            .findById(affecAgentDTO.getId())
            .map(existingAffecAgent -> {
                affecAgentMapper.partialUpdate(existingAffecAgent, affecAgentDTO);

                return existingAffecAgent;
            })
            .map(affecAgentRepository::save)
            .map(affecAgentMapper::toDto);
    }

    /**
     * Get all the affecAgents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<AffecAgentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AffecAgents");
        return affecAgentRepository.findAll(pageable).map(affecAgentMapper::toDto);
    }

    /**
     * Get one affecAgent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<AffecAgentDTO> findOne(String id) {
        log.debug("Request to get AffecAgent : {}", id);
        return affecAgentRepository.findById(id).map(affecAgentMapper::toDto);
    }

    /**
     * Delete the affecAgent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete AffecAgent : {}", id);
        affecAgentRepository.deleteById(id);
    }
}
