package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Modif;

/**
 * Spring Data MongoDB repository for the Modif entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModifRepository extends MongoRepository<Modif, String> {}
