package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Route;

/**
 * Spring Data MongoDB repository for the Route entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RouteRepository extends MongoRepository<Route, String> {}
