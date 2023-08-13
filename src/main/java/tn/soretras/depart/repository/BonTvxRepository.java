package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.BonTvx;

/**
 * Spring Data MongoDB repository for the BonTvx entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BonTvxRepository extends MongoRepository<BonTvx, String> {}
