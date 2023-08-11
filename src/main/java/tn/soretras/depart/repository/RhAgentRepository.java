package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.RhAgent;

/**
 * Spring Data MongoDB repository for the RhAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RhAgentRepository extends MongoRepository<RhAgent, String> {}
