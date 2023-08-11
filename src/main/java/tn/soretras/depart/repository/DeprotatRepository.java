package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Deprotat;

/**
 * Spring Data MongoDB repository for the Deprotat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeprotatRepository extends MongoRepository<Deprotat, String> {}
