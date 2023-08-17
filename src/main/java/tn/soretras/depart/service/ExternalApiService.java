package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.ExternalApi;
import tn.soretras.depart.repository.ExternalApiRepository;
import tn.soretras.depart.service.dto.ExternalApiDTO;
import tn.soretras.depart.service.mapper.ExternalApiMapper;

/**
 * Service Implementation for managing {@link ExternalApi}.
 */
@Service
public class ExternalApiService {

    private final Logger log = LoggerFactory.getLogger(ExternalApiService.class);

    private final ExternalApiRepository externalApiRepository;

    private final ExternalApiMapper externalApiMapper;

    public ExternalApiService(ExternalApiRepository externalApiRepository, ExternalApiMapper externalApiMapper) {
        this.externalApiRepository = externalApiRepository;
        this.externalApiMapper = externalApiMapper;
    }

    /**
     * Save a externalApi.
     *
     * @param externalApiDTO the entity to save.
     * @return the persisted entity.
     */
    public ExternalApiDTO save(ExternalApiDTO externalApiDTO) {
        log.debug("Request to save ExternalApi : {}", externalApiDTO);
        ExternalApi externalApi = externalApiMapper.toEntity(externalApiDTO);
        externalApi = externalApiRepository.save(externalApi);
        return externalApiMapper.toDto(externalApi);
    }

    /**
     * Update a externalApi.
     *
     * @param externalApiDTO the entity to save.
     * @return the persisted entity.
     */
    public ExternalApiDTO update(ExternalApiDTO externalApiDTO) {
        log.debug("Request to update ExternalApi : {}", externalApiDTO);
        ExternalApi externalApi = externalApiMapper.toEntity(externalApiDTO);
        externalApi = externalApiRepository.save(externalApi);
        return externalApiMapper.toDto(externalApi);
    }

    /**
     * Partially update a externalApi.
     *
     * @param externalApiDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ExternalApiDTO> partialUpdate(ExternalApiDTO externalApiDTO) {
        log.debug("Request to partially update ExternalApi : {}", externalApiDTO);

        return externalApiRepository
            .findById(externalApiDTO.getId())
            .map(existingExternalApi -> {
                externalApiMapper.partialUpdate(existingExternalApi, externalApiDTO);

                return existingExternalApi;
            })
            .map(externalApiRepository::save)
            .map(externalApiMapper::toDto);
    }

    /**
     * Get all the externalApis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ExternalApiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExternalApis");
        return externalApiRepository.findAll(pageable).map(externalApiMapper::toDto);
    }

    /**
     * Get one externalApi by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ExternalApiDTO> findOne(String id) {
        log.debug("Request to get ExternalApi : {}", id);
        return externalApiRepository.findById(id).map(externalApiMapper::toDto);
    }

    /**
     * Delete the externalApi by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ExternalApi : {}", id);
        externalApiRepository.deleteById(id);
    }
}
