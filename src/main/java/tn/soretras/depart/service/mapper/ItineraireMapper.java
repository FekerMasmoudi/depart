package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Itineraire;
import tn.soretras.depart.service.dto.ItineraireDTO;

/**
 * Mapper for the entity {@link Itineraire} and its DTO {@link ItineraireDTO}.
 */
@Mapper(componentModel = "spring")
public interface ItineraireMapper extends EntityMapper<ItineraireDTO, Itineraire> {}
