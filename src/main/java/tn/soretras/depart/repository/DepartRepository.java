package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Depart;

/**
 * Spring Data MongoDB repository for the Depart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartRepository extends MongoRepository<Depart, String> {}
