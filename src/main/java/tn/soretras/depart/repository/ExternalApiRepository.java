package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.ExternalApi;

/**
 * Spring Data MongoDB repository for the ExternalApi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExternalApiRepository extends MongoRepository<ExternalApi, String> {}
