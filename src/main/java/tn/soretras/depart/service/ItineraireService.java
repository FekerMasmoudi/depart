package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Itineraire;
import tn.soretras.depart.repository.ItineraireRepository;
import tn.soretras.depart.service.dto.ItineraireDTO;
import tn.soretras.depart.service.mapper.ItineraireMapper;

/**
 * Service Implementation for managing {@link Itineraire}.
 */
@Service
public class ItineraireService {

    private final Logger log = LoggerFactory.getLogger(ItineraireService.class);

    private final ItineraireRepository itineraireRepository;

    private final ItineraireMapper itineraireMapper;

    public ItineraireService(ItineraireRepository itineraireRepository, ItineraireMapper itineraireMapper) {
        this.itineraireRepository = itineraireRepository;
        this.itineraireMapper = itineraireMapper;
    }

    /**
     * Save a itineraire.
     *
     * @param itineraireDTO the entity to save.
     * @return the persisted entity.
     */
    public ItineraireDTO save(ItineraireDTO itineraireDTO) {
        log.debug("Request to save Itineraire : {}", itineraireDTO);
        Itineraire itineraire = itineraireMapper.toEntity(itineraireDTO);
        itineraire = itineraireRepository.save(itineraire);
        return itineraireMapper.toDto(itineraire);
    }

    /**
     * Update a itineraire.
     *
     * @param itineraireDTO the entity to save.
     * @return the persisted entity.
     */
    public ItineraireDTO update(ItineraireDTO itineraireDTO) {
        log.debug("Request to update Itineraire : {}", itineraireDTO);
        Itineraire itineraire = itineraireMapper.toEntity(itineraireDTO);
        itineraire = itineraireRepository.save(itineraire);
        return itineraireMapper.toDto(itineraire);
    }

    /**
     * Partially update a itineraire.
     *
     * @param itineraireDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ItineraireDTO> partialUpdate(ItineraireDTO itineraireDTO) {
        log.debug("Request to partially update Itineraire : {}", itineraireDTO);

        return itineraireRepository
            .findById(itineraireDTO.getId())
            .map(existingItineraire -> {
                itineraireMapper.partialUpdate(existingItineraire, itineraireDTO);

                return existingItineraire;
            })
            .map(itineraireRepository::save)
            .map(itineraireMapper::toDto);
    }

    /**
     * Get all the itineraires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ItineraireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Itineraires");
        return itineraireRepository.findAll(pageable).map(itineraireMapper::toDto);
    }

    /**
     * Get one itineraire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ItineraireDTO> findOne(String id) {
        log.debug("Request to get Itineraire : {}", id);
        return itineraireRepository.findById(id).map(itineraireMapper::toDto);
    }

    /**
     * Delete the itineraire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Itineraire : {}", id);
        itineraireRepository.deleteById(id);
    }
}
