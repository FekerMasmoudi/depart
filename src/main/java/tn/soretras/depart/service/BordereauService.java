package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Bordereau;
import tn.soretras.depart.repository.BordereauRepository;
import tn.soretras.depart.service.dto.BordereauDTO;
import tn.soretras.depart.service.mapper.BordereauMapper;

/**
 * Service Implementation for managing {@link Bordereau}.
 */
@Service
public class BordereauService {

    private final Logger log = LoggerFactory.getLogger(BordereauService.class);

    private final BordereauRepository bordereauRepository;

    private final BordereauMapper bordereauMapper;

    public BordereauService(BordereauRepository bordereauRepository, BordereauMapper bordereauMapper) {
        this.bordereauRepository = bordereauRepository;
        this.bordereauMapper = bordereauMapper;
    }

    /**
     * Save a bordereau.
     *
     * @param bordereauDTO the entity to save.
     * @return the persisted entity.
     */
    public BordereauDTO save(BordereauDTO bordereauDTO) {
        log.debug("Request to save Bordereau : {}", bordereauDTO);
        Bordereau bordereau = bordereauMapper.toEntity(bordereauDTO);
        bordereau = bordereauRepository.save(bordereau);
        return bordereauMapper.toDto(bordereau);
    }

    /**
     * Update a bordereau.
     *
     * @param bordereauDTO the entity to save.
     * @return the persisted entity.
     */
    public BordereauDTO update(BordereauDTO bordereauDTO) {
        log.debug("Request to update Bordereau : {}", bordereauDTO);
        Bordereau bordereau = bordereauMapper.toEntity(bordereauDTO);
        bordereau = bordereauRepository.save(bordereau);
        return bordereauMapper.toDto(bordereau);
    }

    /**
     * Partially update a bordereau.
     *
     * @param bordereauDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BordereauDTO> partialUpdate(BordereauDTO bordereauDTO) {
        log.debug("Request to partially update Bordereau : {}", bordereauDTO);

        return bordereauRepository
            .findById(bordereauDTO.getId())
            .map(existingBordereau -> {
                bordereauMapper.partialUpdate(existingBordereau, bordereauDTO);

                return existingBordereau;
            })
            .map(bordereauRepository::save)
            .map(bordereauMapper::toDto);
    }

    /**
     * Get all the bordereaus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<BordereauDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bordereaus");
        return bordereauRepository.findAll(pageable).map(bordereauMapper::toDto);
    }

    /**
     * Get one bordereau by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<BordereauDTO> findOne(String id) {
        log.debug("Request to get Bordereau : {}", id);
        return bordereauRepository.findById(id).map(bordereauMapper::toDto);
    }

    /**
     * Delete the bordereau by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Bordereau : {}", id);
        bordereauRepository.deleteById(id);
    }
}
