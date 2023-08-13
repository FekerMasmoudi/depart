package tn.soretras.depart.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.soretras.depart.domain.Route;
import tn.soretras.depart.repository.RouteRepository;
import tn.soretras.depart.service.dto.RouteDTO;
import tn.soretras.depart.service.mapper.RouteMapper;

/**
 * Service Implementation for managing {@link Route}.
 */
@Service
public class RouteService {

    private final Logger log = LoggerFactory.getLogger(RouteService.class);

    private final RouteRepository routeRepository;

    private final RouteMapper routeMapper;

    public RouteService(RouteRepository routeRepository, RouteMapper routeMapper) {
        this.routeRepository = routeRepository;
        this.routeMapper = routeMapper;
    }

    /**
     * Save a route.
     *
     * @param routeDTO the entity to save.
     * @return the persisted entity.
     */
    public RouteDTO save(RouteDTO routeDTO) {
        log.debug("Request to save Route : {}", routeDTO);
        Route route = routeMapper.toEntity(routeDTO);
        route = routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    /**
     * Update a route.
     *
     * @param routeDTO the entity to save.
     * @return the persisted entity.
     */
    public RouteDTO update(RouteDTO routeDTO) {
        log.debug("Request to update Route : {}", routeDTO);
        Route route = routeMapper.toEntity(routeDTO);
        route = routeRepository.save(route);
        return routeMapper.toDto(route);
    }

    /**
     * Partially update a route.
     *
     * @param routeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RouteDTO> partialUpdate(RouteDTO routeDTO) {
        log.debug("Request to partially update Route : {}", routeDTO);

        return routeRepository
            .findById(routeDTO.getId())
            .map(existingRoute -> {
                routeMapper.partialUpdate(existingRoute, routeDTO);

                return existingRoute;
            })
            .map(routeRepository::save)
            .map(routeMapper::toDto);
    }

    /**
     * Get all the routes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<RouteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Routes");
        return routeRepository.findAll(pageable).map(routeMapper::toDto);
    }

    /**
     * Get one route by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<RouteDTO> findOne(String id) {
        log.debug("Request to get Route : {}", id);
        return routeRepository.findById(id).map(routeMapper::toDto);
    }

    /**
     * Delete the route by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Route : {}", id);
        routeRepository.deleteById(id);
    }
}
