package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.FoncAgent;

/**
 * Spring Data MongoDB repository for the FoncAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FoncAgentRepository extends MongoRepository<FoncAgent, String> {}
