package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.BonTvx;
import tn.soretras.depart.repository.BonTvxRepository;
import tn.soretras.depart.service.dto.BonTvxDTO;
import tn.soretras.depart.service.mapper.BonTvxMapper;

/**
 * Service Implementation for managing {@link BonTvx}.
 */
@Service
public class BonTvxService {

    private final Logger log = LoggerFactory.getLogger(BonTvxService.class);

    private final BonTvxRepository bonTvxRepository;

    private final BonTvxMapper bonTvxMapper;

    public BonTvxService(BonTvxRepository bonTvxRepository, BonTvxMapper bonTvxMapper) {
        this.bonTvxRepository = bonTvxRepository;
        this.bonTvxMapper = bonTvxMapper;
    }

    /**
     * Save a bonTvx.
     *
     * @param bonTvxDTO the entity to save.
     * @return the persisted entity.
     */
    public BonTvxDTO save(BonTvxDTO bonTvxDTO) {
        log.debug("Request to save BonTvx : {}", bonTvxDTO);
        BonTvx bonTvx = bonTvxMapper.toEntity(bonTvxDTO);
        bonTvx = bonTvxRepository.save(bonTvx);
        return bonTvxMapper.toDto(bonTvx);
    }

    /**
     * Update a bonTvx.
     *
     * @param bonTvxDTO the entity to save.
     * @return the persisted entity.
     */
    public BonTvxDTO update(BonTvxDTO bonTvxDTO) {
        log.debug("Request to update BonTvx : {}", bonTvxDTO);
        BonTvx bonTvx = bonTvxMapper.toEntity(bonTvxDTO);
        bonTvx = bonTvxRepository.save(bonTvx);
        return bonTvxMapper.toDto(bonTvx);
    }

    /**
     * Partially update a bonTvx.
     *
     * @param bonTvxDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BonTvxDTO> partialUpdate(BonTvxDTO bonTvxDTO) {
        log.debug("Request to partially update BonTvx : {}", bonTvxDTO);

        return bonTvxRepository
            .findById(bonTvxDTO.getId())
            .map(existingBonTvx -> {
                bonTvxMapper.partialUpdate(existingBonTvx, bonTvxDTO);

                return existingBonTvx;
            })
            .map(bonTvxRepository::save)
            .map(bonTvxMapper::toDto);
    }

    /**
     * Get all the bonTvxes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<BonTvxDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BonTvxes");
        return bonTvxRepository.findAll(pageable).map(bonTvxMapper::toDto);
    }

    /**
     * Get one bonTvx by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<BonTvxDTO> findOne(String id) {
        log.debug("Request to get BonTvx : {}", id);
        return bonTvxRepository.findById(id).map(bonTvxMapper::toDto);
    }

    /**
     * Delete the bonTvx by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete BonTvx : {}", id);
        bonTvxRepository.deleteById(id);
    }
}
