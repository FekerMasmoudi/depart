package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Affectagent;
import tn.soretras.depart.repository.AffectagentRepository;
import tn.soretras.depart.service.dto.AffectagentDTO;
import tn.soretras.depart.service.mapper.AffectagentMapper;

/**
 * Service Implementation for managing {@link Affectagent}.
 */
@Service
public class AffectagentService {

    private final Logger log = LoggerFactory.getLogger(AffectagentService.class);

    private final AffectagentRepository affectagentRepository;

    private final AffectagentMapper affectagentMapper;

    public AffectagentService(AffectagentRepository affectagentRepository, AffectagentMapper affectagentMapper) {
        this.affectagentRepository = affectagentRepository;
        this.affectagentMapper = affectagentMapper;
    }

    /**
     * Save a affectagent.
     *
     * @param affectagentDTO the entity to save.
     * @return the persisted entity.
     */
    public AffectagentDTO save(AffectagentDTO affectagentDTO) {
        log.debug("Request to save Affectagent : {}", affectagentDTO);
        Affectagent affectagent = affectagentMapper.toEntity(affectagentDTO);
        affectagent = affectagentRepository.save(affectagent);
        return affectagentMapper.toDto(affectagent);
    }

    /**
     * Update a affectagent.
     *
     * @param affectagentDTO the entity to save.
     * @return the persisted entity.
     */
    public AffectagentDTO update(AffectagentDTO affectagentDTO) {
        log.debug("Request to update Affectagent : {}", affectagentDTO);
        Affectagent affectagent = affectagentMapper.toEntity(affectagentDTO);
        affectagent = affectagentRepository.save(affectagent);
        return affectagentMapper.toDto(affectagent);
    }

    /**
     * Partially update a affectagent.
     *
     * @param affectagentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AffectagentDTO> partialUpdate(AffectagentDTO affectagentDTO) {
        log.debug("Request to partially update Affectagent : {}", affectagentDTO);

        return affectagentRepository
            .findById(affectagentDTO.getId())
            .map(existingAffectagent -> {
                affectagentMapper.partialUpdate(existingAffectagent, affectagentDTO);

                return existingAffectagent;
            })
            .map(affectagentRepository::save)
            .map(affectagentMapper::toDto);
    }

    /**
     * Get all the affectagents.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<AffectagentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Affectagents");
        return affectagentRepository.findAll(pageable).map(affectagentMapper::toDto);
    }

    /**
     * Get one affectagent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<AffectagentDTO> findOne(String id) {
        log.debug("Request to get Affectagent : {}", id);
        return affectagentRepository.findById(id).map(affectagentMapper::toDto);
    }

    /**
     * Delete the affectagent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Affectagent : {}", id);
        affectagentRepository.deleteById(id);
    }
}
