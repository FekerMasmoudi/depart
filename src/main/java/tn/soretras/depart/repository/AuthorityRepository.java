package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.soretras.depart.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {}
