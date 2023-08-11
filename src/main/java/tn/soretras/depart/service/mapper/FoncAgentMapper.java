package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.FoncAgent;
import tn.soretras.depart.service.dto.FoncAgentDTO;

/**
 * Mapper for the entity {@link FoncAgent} and its DTO {@link FoncAgentDTO}.
 */
@Mapper(componentModel = "spring")
public interface FoncAgentMapper extends EntityMapper<FoncAgentDTO, FoncAgent> {}
