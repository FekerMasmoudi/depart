package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Modif;
import tn.soretras.depart.service.dto.ModifDTO;

/**
 * Mapper for the entity {@link Modif} and its DTO {@link ModifDTO}.
 */
@Mapper(componentModel = "spring")
public interface ModifMapper extends EntityMapper<ModifDTO, Modif> {}
