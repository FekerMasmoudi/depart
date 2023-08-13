package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Ligne;

/**
 * Spring Data MongoDB repository for the Ligne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneRepository extends MongoRepository<Ligne, String> {}
