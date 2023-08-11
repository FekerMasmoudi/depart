package tn.soretras.depart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import tn.soretras.depart.domain.Machine;

/**
 * Spring Data MongoDB repository for the Machine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MachineRepository extends MongoRepository<Machine, String> {}
