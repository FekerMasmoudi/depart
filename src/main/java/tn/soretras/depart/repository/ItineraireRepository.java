package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Itineraire;

/**
 * Spring Data MongoDB repository for the Itineraire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItineraireRepository extends MongoRepository<Itineraire, String> {}
