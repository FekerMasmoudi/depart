package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.RhAgent;
import tn.soretras.depart.service.dto.RhAgentDTO;

/**
 * Mapper for the entity {@link RhAgent} and its DTO {@link RhAgentDTO}.
 */
@Mapper(componentModel = "spring")
public interface RhAgentMapper extends EntityMapper<RhAgentDTO, RhAgent> {}
