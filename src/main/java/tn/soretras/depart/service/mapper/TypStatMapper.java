package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.TypStat;
import tn.soretras.depart.service.dto.TypStatDTO;

/**
 * Mapper for the entity {@link TypStat} and its DTO {@link TypStatDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypStatMapper extends EntityMapper<TypStatDTO, TypStat> {}
