package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Drabsen;
import tn.soretras.depart.repository.DrabsenRepository;
import tn.soretras.depart.service.dto.DrabsenDTO;
import tn.soretras.depart.service.mapper.DrabsenMapper;

/**
 * Service Implementation for managing {@link Drabsen}.
 */
@Service
public class DrabsenService {

    private final Logger log = LoggerFactory.getLogger(DrabsenService.class);

    private final DrabsenRepository drabsenRepository;

    private final DrabsenMapper drabsenMapper;

    public DrabsenService(DrabsenRepository drabsenRepository, DrabsenMapper drabsenMapper) {
        this.drabsenRepository = drabsenRepository;
        this.drabsenMapper = drabsenMapper;
    }

    /**
     * Save a drabsen.
     *
     * @param drabsenDTO the entity to save.
     * @return the persisted entity.
     */
    public DrabsenDTO save(DrabsenDTO drabsenDTO) {
        log.debug("Request to save Drabsen : {}", drabsenDTO);
        Drabsen drabsen = drabsenMapper.toEntity(drabsenDTO);
        drabsen = drabsenRepository.save(drabsen);
        return drabsenMapper.toDto(drabsen);
    }

    /**
     * Update a drabsen.
     *
     * @param drabsenDTO the entity to save.
     * @return the persisted entity.
     */
    public DrabsenDTO update(DrabsenDTO drabsenDTO) {
        log.debug("Request to update Drabsen : {}", drabsenDTO);
        Drabsen drabsen = drabsenMapper.toEntity(drabsenDTO);
        drabsen = drabsenRepository.save(drabsen);
        return drabsenMapper.toDto(drabsen);
    }

    /**
     * Partially update a drabsen.
     *
     * @param drabsenDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DrabsenDTO> partialUpdate(DrabsenDTO drabsenDTO) {
        log.debug("Request to partially update Drabsen : {}", drabsenDTO);

        return drabsenRepository
            .findById(drabsenDTO.getId())
            .map(existingDrabsen -> {
                drabsenMapper.partialUpdate(existingDrabsen, drabsenDTO);

                return existingDrabsen;
            })
            .map(drabsenRepository::save)
            .map(drabsenMapper::toDto);
    }

    /**
     * Get all the drabsens.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<DrabsenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Drabsens");
        return drabsenRepository.findAll(pageable).map(drabsenMapper::toDto);
    }

    /**
     * Get one drabsen by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<DrabsenDTO> findOne(String id) {
        log.debug("Request to get Drabsen : {}", id);
        return drabsenRepository.findById(id).map(drabsenMapper::toDto);
    }

    /**
     * Delete the drabsen by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Drabsen : {}", id);
        drabsenRepository.deleteById(id);
    }
}
