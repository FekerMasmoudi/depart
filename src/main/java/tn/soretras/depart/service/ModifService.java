package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Modif;
import tn.soretras.depart.repository.ModifRepository;
import tn.soretras.depart.service.dto.ModifDTO;
import tn.soretras.depart.service.mapper.ModifMapper;

/**
 * Service Implementation for managing {@link Modif}.
 */
@Service
public class ModifService {

    private final Logger log = LoggerFactory.getLogger(ModifService.class);

    private final ModifRepository modifRepository;

    private final ModifMapper modifMapper;

    public ModifService(ModifRepository modifRepository, ModifMapper modifMapper) {
        this.modifRepository = modifRepository;
        this.modifMapper = modifMapper;
    }

    /**
     * Save a modif.
     *
     * @param modifDTO the entity to save.
     * @return the persisted entity.
     */
    public ModifDTO save(ModifDTO modifDTO) {
        log.debug("Request to save Modif : {}", modifDTO);
        Modif modif = modifMapper.toEntity(modifDTO);
        modif = modifRepository.save(modif);
        return modifMapper.toDto(modif);
    }

    /**
     * Update a modif.
     *
     * @param modifDTO the entity to save.
     * @return the persisted entity.
     */
    public ModifDTO update(ModifDTO modifDTO) {
        log.debug("Request to update Modif : {}", modifDTO);
        Modif modif = modifMapper.toEntity(modifDTO);
        modif = modifRepository.save(modif);
        return modifMapper.toDto(modif);
    }

    /**
     * Partially update a modif.
     *
     * @param modifDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ModifDTO> partialUpdate(ModifDTO modifDTO) {
        log.debug("Request to partially update Modif : {}", modifDTO);

        return modifRepository
            .findById(modifDTO.getId())
            .map(existingModif -> {
                modifMapper.partialUpdate(existingModif, modifDTO);

                return existingModif;
            })
            .map(modifRepository::save)
            .map(modifMapper::toDto);
    }

    /**
     * Get all the modifs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ModifDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Modifs");
        return modifRepository.findAll(pageable).map(modifMapper::toDto);
    }

    /**
     * Get one modif by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ModifDTO> findOne(String id) {
        log.debug("Request to get Modif : {}", id);
        return modifRepository.findById(id).map(modifMapper::toDto);
    }

    /**
     * Delete the modif by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Modif : {}", id);
        modifRepository.deleteById(id);
    }
}
