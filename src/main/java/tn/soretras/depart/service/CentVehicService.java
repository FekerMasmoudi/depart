package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.CentVehic;
import tn.soretras.depart.repository.CentVehicRepository;
import tn.soretras.depart.service.dto.CentVehicDTO;
import tn.soretras.depart.service.mapper.CentVehicMapper;

/**
 * Service Implementation for managing {@link CentVehic}.
 */
@Service
public class CentVehicService {

    private final Logger log = LoggerFactory.getLogger(CentVehicService.class);

    private final CentVehicRepository centVehicRepository;

    private final CentVehicMapper centVehicMapper;

    public CentVehicService(CentVehicRepository centVehicRepository, CentVehicMapper centVehicMapper) {
        this.centVehicRepository = centVehicRepository;
        this.centVehicMapper = centVehicMapper;
    }

    /**
     * Save a centVehic.
     *
     * @param centVehicDTO the entity to save.
     * @return the persisted entity.
     */
    public CentVehicDTO save(CentVehicDTO centVehicDTO) {
        log.debug("Request to save CentVehic : {}", centVehicDTO);
        CentVehic centVehic = centVehicMapper.toEntity(centVehicDTO);
        centVehic = centVehicRepository.save(centVehic);
        return centVehicMapper.toDto(centVehic);
    }

    /**
     * Update a centVehic.
     *
     * @param centVehicDTO the entity to save.
     * @return the persisted entity.
     */
    public CentVehicDTO update(CentVehicDTO centVehicDTO) {
        log.debug("Request to update CentVehic : {}", centVehicDTO);
        CentVehic centVehic = centVehicMapper.toEntity(centVehicDTO);
        centVehic = centVehicRepository.save(centVehic);
        return centVehicMapper.toDto(centVehic);
    }

    /**
     * Partially update a centVehic.
     *
     * @param centVehicDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CentVehicDTO> partialUpdate(CentVehicDTO centVehicDTO) {
        log.debug("Request to partially update CentVehic : {}", centVehicDTO);

        return centVehicRepository
            .findById(centVehicDTO.getId())
            .map(existingCentVehic -> {
                centVehicMapper.partialUpdate(existingCentVehic, centVehicDTO);

                return existingCentVehic;
            })
            .map(centVehicRepository::save)
            .map(centVehicMapper::toDto);
    }

    /**
     * Get all the centVehics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<CentVehicDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CentVehics");
        return centVehicRepository.findAll(pageable).map(centVehicMapper::toDto);
    }

    /**
     * Get one centVehic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<CentVehicDTO> findOne(String id) {
        log.debug("Request to get CentVehic : {}", id);
        return centVehicRepository.findById(id).map(centVehicMapper::toDto);
    }

    /**
     * Delete the centVehic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete CentVehic : {}", id);
        centVehicRepository.deleteById(id);
    }
}
