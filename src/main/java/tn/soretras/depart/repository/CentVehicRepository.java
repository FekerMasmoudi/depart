package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.CentVehic;

/**
 * Spring Data MongoDB repository for the CentVehic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CentVehicRepository extends MongoRepository<CentVehic, String> {}
