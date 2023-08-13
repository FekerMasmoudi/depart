package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Drabsen;

/**
 * Spring Data MongoDB repository for the Drabsen entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DrabsenRepository extends MongoRepository<Drabsen, String> {}
