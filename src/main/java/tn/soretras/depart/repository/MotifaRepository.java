package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Motifa;

/**
 * Spring Data MongoDB repository for the Motifa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotifaRepository extends MongoRepository<Motifa, String> {}
