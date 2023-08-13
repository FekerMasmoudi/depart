package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Drtypab;
import tn.soretras.depart.service.dto.DrtypabDTO;

/**
 * Mapper for the entity {@link Drtypab} and its DTO {@link DrtypabDTO}.
 */
@Mapper(componentModel = "spring")
public interface DrtypabMapper extends EntityMapper<DrtypabDTO, Drtypab> {}
