package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Affectagent;
import tn.soretras.depart.service.dto.AffectagentDTO;

/**
 * Mapper for the entity {@link Affectagent} and its DTO {@link AffectagentDTO}.
 */
@Mapper(componentModel = "spring")
public interface AffectagentMapper extends EntityMapper<AffectagentDTO, Affectagent> {}
