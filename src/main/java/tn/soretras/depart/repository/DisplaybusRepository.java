package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Displaybus;

/**
 * Spring Data MongoDB repository for the Displaybus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DisplaybusRepository extends MongoRepository<Displaybus, String> {}
