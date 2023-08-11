package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Periode;
import tn.soretras.depart.repository.PeriodeRepository;
import tn.soretras.depart.service.dto.PeriodeDTO;
import tn.soretras.depart.service.mapper.PeriodeMapper;

/**
 * Service Implementation for managing {@link Periode}.
 */
@Service
public class PeriodeService {

    private final Logger log = LoggerFactory.getLogger(PeriodeService.class);

    private final PeriodeRepository periodeRepository;

    private final PeriodeMapper periodeMapper;

    public PeriodeService(PeriodeRepository periodeRepository, PeriodeMapper periodeMapper) {
        this.periodeRepository = periodeRepository;
        this.periodeMapper = periodeMapper;
    }

    /**
     * Save a periode.
     *
     * @param periodeDTO the entity to save.
     * @return the persisted entity.
     */
    public PeriodeDTO save(PeriodeDTO periodeDTO) {
        log.debug("Request to save Periode : {}", periodeDTO);
        Periode periode = periodeMapper.toEntity(periodeDTO);
        periode = periodeRepository.save(periode);
        return periodeMapper.toDto(periode);
    }

    /**
     * Update a periode.
     *
     * @param periodeDTO the entity to save.
     * @return the persisted entity.
     */
    public PeriodeDTO update(PeriodeDTO periodeDTO) {
        log.debug("Request to update Periode : {}", periodeDTO);
        Periode periode = periodeMapper.toEntity(periodeDTO);
        periode = periodeRepository.save(periode);
        return periodeMapper.toDto(periode);
    }

    /**
     * Partially update a periode.
     *
     * @param periodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PeriodeDTO> partialUpdate(PeriodeDTO periodeDTO) {
        log.debug("Request to partially update Periode : {}", periodeDTO);

        return periodeRepository
            .findById(periodeDTO.getId())
            .map(existingPeriode -> {
                periodeMapper.partialUpdate(existingPeriode, periodeDTO);

                return existingPeriode;
            })
            .map(periodeRepository::save)
            .map(periodeMapper::toDto);
    }

    /**
     * Get all the periodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<PeriodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Periodes");
        return periodeRepository.findAll(pageable).map(periodeMapper::toDto);
    }

    /**
     * Get one periode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<PeriodeDTO> findOne(String id) {
        log.debug("Request to get Periode : {}", id);
        return periodeRepository.findById(id).map(periodeMapper::toDto);
    }

    /**
     * Delete the periode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Periode : {}", id);
        periodeRepository.deleteById(id);
    }
}
