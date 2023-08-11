package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.RotRserv;
import tn.soretras.depart.repository.RotRservRepository;
import tn.soretras.depart.service.dto.RotRservDTO;
import tn.soretras.depart.service.mapper.RotRservMapper;

/**
 * Service Implementation for managing {@link RotRserv}.
 */
@Service
public class RotRservService {

    private final Logger log = LoggerFactory.getLogger(RotRservService.class);

    private final RotRservRepository rotRservRepository;

    private final RotRservMapper rotRservMapper;

    public RotRservService(RotRservRepository rotRservRepository, RotRservMapper rotRservMapper) {
        this.rotRservRepository = rotRservRepository;
        this.rotRservMapper = rotRservMapper;
    }

    /**
     * Save a rotRserv.
     *
     * @param rotRservDTO the entity to save.
     * @return the persisted entity.
     */
    public RotRservDTO save(RotRservDTO rotRservDTO) {
        log.debug("Request to save RotRserv : {}", rotRservDTO);
        RotRserv rotRserv = rotRservMapper.toEntity(rotRservDTO);
        rotRserv = rotRservRepository.save(rotRserv);
        return rotRservMapper.toDto(rotRserv);
    }

    /**
     * Update a rotRserv.
     *
     * @param rotRservDTO the entity to save.
     * @return the persisted entity.
     */
    public RotRservDTO update(RotRservDTO rotRservDTO) {
        log.debug("Request to update RotRserv : {}", rotRservDTO);
        RotRserv rotRserv = rotRservMapper.toEntity(rotRservDTO);
        //rotRserv.setIsPersisted();
        rotRserv = rotRservRepository.save(rotRserv);
        return rotRservMapper.toDto(rotRserv);
    }

    /**
     * Partially update a rotRserv.
     *
     * @param rotRservDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RotRservDTO> partialUpdate(RotRservDTO rotRservDTO) {
        log.debug("Request to partially update RotRserv : {}", rotRservDTO);

        return rotRservRepository
            .findById(rotRservDTO.getId())
            .map(existingRotRserv -> {
                rotRservMapper.partialUpdate(existingRotRserv, rotRservDTO);

                return existingRotRserv;
            })
            .map(rotRservRepository::save)
            .map(rotRservMapper::toDto);
    }

    /**
     * Get all the rotRservs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RotRservDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RotRservs");
        return rotRservRepository.findAll(pageable).map(rotRservMapper::toDto);
    }

    /**
     * Get one rotRserv by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RotRservDTO> findOne(String id) {
        log.debug("Request to get RotRserv : {}", id);
        return rotRservRepository.findById(id).map(rotRservMapper::toDto);
    }

    /**
     * Delete the rotRserv by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete RotRserv : {}", id);
        rotRservRepository.deleteById(id);
    }
}
