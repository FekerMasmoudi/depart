package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Motifchserv;

/**
 * Spring Data MongoDB repository for the Motifchserv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotifchservRepository extends MongoRepository<Motifchserv, String> {}
