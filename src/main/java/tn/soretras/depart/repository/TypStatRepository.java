package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.TypStat;

/**
 * Spring Data MongoDB repository for the TypStat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypStatRepository extends MongoRepository<TypStat, String> {}
