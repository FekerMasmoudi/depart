package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Center;

/**
 * Spring Data MongoDB repository for the Center entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CenterRepository extends MongoRepository<Center, String> {}
