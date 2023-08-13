package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Station;

/**
 * Spring Data MongoDB repository for the Station entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StationRepository extends MongoRepository<Station, String> {}
