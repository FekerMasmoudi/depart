package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.ServiceRot;
import tn.soretras.depart.repository.ServiceRotRepository;
import tn.soretras.depart.service.dto.ServiceRotDTO;
import tn.soretras.depart.service.mapper.ServiceRotMapper;

/**
 * Service Implementation for managing {@link ServiceRot}.
 */
@Service
public class ServiceRotService {

    private final Logger log = LoggerFactory.getLogger(ServiceRotService.class);

    private final ServiceRotRepository serviceRotRepository;

    private final ServiceRotMapper serviceRotMapper;

    public ServiceRotService(ServiceRotRepository serviceRotRepository, ServiceRotMapper serviceRotMapper) {
        this.serviceRotRepository = serviceRotRepository;
        this.serviceRotMapper = serviceRotMapper;
    }

    /**
     * Save a serviceRot.
     *
     * @param serviceRotDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceRotDTO save(ServiceRotDTO serviceRotDTO) {
        log.debug("Request to save ServiceRot : {}", serviceRotDTO);
        ServiceRot serviceRot = serviceRotMapper.toEntity(serviceRotDTO);
        serviceRot = serviceRotRepository.save(serviceRot);
        return serviceRotMapper.toDto(serviceRot);
    }

    /**
     * Update a serviceRot.
     *
     * @param serviceRotDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceRotDTO update(ServiceRotDTO serviceRotDTO) {
        log.debug("Request to update ServiceRot : {}", serviceRotDTO);
        ServiceRot serviceRot = serviceRotMapper.toEntity(serviceRotDTO);
        serviceRot = serviceRotRepository.save(serviceRot);
        return serviceRotMapper.toDto(serviceRot);
    }

    /**
     * Partially update a serviceRot.
     *
     * @param serviceRotDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ServiceRotDTO> partialUpdate(ServiceRotDTO serviceRotDTO) {
        log.debug("Request to partially update ServiceRot : {}", serviceRotDTO);

        return serviceRotRepository
            .findById(serviceRotDTO.getId())
            .map(existingServiceRot -> {
                serviceRotMapper.partialUpdate(existingServiceRot, serviceRotDTO);

                return existingServiceRot;
            })
            .map(serviceRotRepository::save)
            .map(serviceRotMapper::toDto);
    }

    /**
     * Get all the serviceRots.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<ServiceRotDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceRots");
        return serviceRotRepository.findAll(pageable).map(serviceRotMapper::toDto);
    }

    /**
     * Get one serviceRot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<ServiceRotDTO> findOne(String id) {
        log.debug("Request to get ServiceRot : {}", id);
        return serviceRotRepository.findById(id).map(serviceRotMapper::toDto);
    }

    /**
     * Delete the serviceRot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete ServiceRot : {}", id);
        serviceRotRepository.deleteById(id);
    }
}
