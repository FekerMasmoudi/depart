package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.AffecAgent;
import tn.soretras.depart.service.dto.AffecAgentDTO;

/**
 * Mapper for the entity {@link AffecAgent} and its DTO {@link AffecAgentDTO}.
 */
@Mapper(componentModel = "spring")
public interface AffecAgentMapper extends EntityMapper<AffecAgentDTO, AffecAgent> {}
