package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Periode;

/**
 * Spring Data MongoDB repository for the Periode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodeRepository extends MongoRepository<Periode, String> {}
