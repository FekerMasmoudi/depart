package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Motifchserv;
import tn.soretras.depart.service.dto.MotifchservDTO;

/**
 * Mapper for the entity {@link Motifchserv} and its DTO {@link MotifchservDTO}.
 */
@Mapper(componentModel = "spring")
public interface MotifchservMapper extends EntityMapper<MotifchservDTO, Motifchserv> {}
