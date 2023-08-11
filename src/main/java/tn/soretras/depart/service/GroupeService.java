package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Groupe;
import tn.soretras.depart.repository.GroupeRepository;
import tn.soretras.depart.service.dto.GroupeDTO;
import tn.soretras.depart.service.mapper.GroupeMapper;

/**
 * Service Implementation for managing {@link Groupe}.
 */
@Service
public class GroupeService {

    private final Logger log = LoggerFactory.getLogger(GroupeService.class);

    private final GroupeRepository groupeRepository;

    private final GroupeMapper groupeMapper;

    public GroupeService(GroupeRepository groupeRepository, GroupeMapper groupeMapper) {
        this.groupeRepository = groupeRepository;
        this.groupeMapper = groupeMapper;
    }

    /**
     * Save a groupe.
     *
     * @param groupeDTO the entity to save.
     * @return the persisted entity.
     */
    public GroupeDTO save(GroupeDTO groupeDTO) {
        log.debug("Request to save Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    /**
     * Update a groupe.
     *
     * @param groupeDTO the entity to save.
     * @return the persisted entity.
     */
    public GroupeDTO update(GroupeDTO groupeDTO) {
        log.debug("Request to update Groupe : {}", groupeDTO);
        Groupe groupe = groupeMapper.toEntity(groupeDTO);
        groupe = groupeRepository.save(groupe);
        return groupeMapper.toDto(groupe);
    }

    /**
     * Partially update a groupe.
     *
     * @param groupeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GroupeDTO> partialUpdate(GroupeDTO groupeDTO) {
        log.debug("Request to partially update Groupe : {}", groupeDTO);

        return groupeRepository
            .findById(groupeDTO.getId())
            .map(existingGroupe -> {
                groupeMapper.partialUpdate(existingGroupe, groupeDTO);

                return existingGroupe;
            })
            .map(groupeRepository::save)
            .map(groupeMapper::toDto);
    }

    /**
     * Get all the groupes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GroupeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Groupes");
        return groupeRepository.findAll(pageable).map(groupeMapper::toDto);
    }

    /**
     * Get one groupe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GroupeDTO> findOne(String id) {
        log.debug("Request to get Groupe : {}", id);
        return groupeRepository.findById(id).map(groupeMapper::toDto);
    }

    /**
     * Delete the groupe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Groupe : {}", id);
        groupeRepository.deleteById(id);
    }
}
