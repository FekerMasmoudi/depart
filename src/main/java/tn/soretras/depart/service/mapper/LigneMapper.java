package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Ligne;
import tn.soretras.depart.service.dto.LigneDTO;

/**
 * Mapper for the entity {@link Ligne} and its DTO {@link LigneDTO}.
 */
@Mapper(componentModel = "spring")
public interface LigneMapper extends EntityMapper<LigneDTO, Ligne> {}
