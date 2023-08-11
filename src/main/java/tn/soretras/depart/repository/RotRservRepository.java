package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.RotRserv;

/**
 * Spring Data MongoDB repository for the RotRserv entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RotRservRepository extends MongoRepository<RotRserv, String> {}
