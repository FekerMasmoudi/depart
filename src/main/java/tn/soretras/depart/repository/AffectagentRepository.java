package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Affectagent;

/**
 * Spring Data MongoDB repository for the Affectagent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AffectagentRepository extends MongoRepository<Affectagent, String> {}
