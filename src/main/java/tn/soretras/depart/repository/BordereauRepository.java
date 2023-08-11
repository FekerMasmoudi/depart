package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Bordereau;

/**
 * Spring Data MongoDB repository for the Bordereau entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BordereauRepository extends MongoRepository<Bordereau, String> {}
