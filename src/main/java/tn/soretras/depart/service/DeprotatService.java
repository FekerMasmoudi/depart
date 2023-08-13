package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Deprotat;
import tn.soretras.depart.repository.DeprotatRepository;
import tn.soretras.depart.service.dto.DeprotatDTO;
import tn.soretras.depart.service.mapper.DeprotatMapper;

/**
 * Service Implementation for managing {@link Deprotat}.
 */
@Service
public class DeprotatService {

    private final Logger log = LoggerFactory.getLogger(DeprotatService.class);

    private final DeprotatRepository deprotatRepository;

    private final DeprotatMapper deprotatMapper;

    public DeprotatService(DeprotatRepository deprotatRepository, DeprotatMapper deprotatMapper) {
        this.deprotatRepository = deprotatRepository;
        this.deprotatMapper = deprotatMapper;
    }

    /**
     * Save a deprotat.
     *
     * @param deprotatDTO the entity to save.
     * @return the persisted entity.
     */
    public DeprotatDTO save(DeprotatDTO deprotatDTO) {
        log.debug("Request to save Deprotat : {}", deprotatDTO);
        Deprotat deprotat = deprotatMapper.toEntity(deprotatDTO);
        deprotat = deprotatRepository.save(deprotat);
        return deprotatMapper.toDto(deprotat);
    }

    /**
     * Update a deprotat.
     *
     * @param deprotatDTO the entity to save.
     * @return the persisted entity.
     */
    public DeprotatDTO update(DeprotatDTO deprotatDTO) {
        log.debug("Request to update Deprotat : {}", deprotatDTO);
        Deprotat deprotat = deprotatMapper.toEntity(deprotatDTO);
        deprotat = deprotatRepository.save(deprotat);
        return deprotatMapper.toDto(deprotat);
    }

    /**
     * Partially update a deprotat.
     *
     * @param deprotatDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DeprotatDTO> partialUpdate(DeprotatDTO deprotatDTO) {
        log.debug("Request to partially update Deprotat : {}", deprotatDTO);

        return deprotatRepository
            .findById(deprotatDTO.getId())
            .map(existingDeprotat -> {
                deprotatMapper.partialUpdate(existingDeprotat, deprotatDTO);

                return existingDeprotat;
            })
            .map(deprotatRepository::save)
            .map(deprotatMapper::toDto);
    }

    /**
     * Get all the deprotats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DeprotatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Deprotats");
        return deprotatRepository.findAll(pageable).map(deprotatMapper::toDto);
    }

    /**
     * Get one deprotat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DeprotatDTO> findOne(String id) {
        log.debug("Request to get Deprotat : {}", id);
        return deprotatRepository.findById(id).map(deprotatMapper::toDto);
    }

    /**
     * Delete the deprotat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Deprotat : {}", id);
        deprotatRepository.deleteById(id);
    }
}
