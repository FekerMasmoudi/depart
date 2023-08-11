package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Drtypab;

/**
 * Spring Data MongoDB repository for the Drtypab entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DrtypabRepository extends MongoRepository<Drtypab, String> {}
