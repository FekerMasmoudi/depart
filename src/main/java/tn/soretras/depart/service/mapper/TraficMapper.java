package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Trafic;
import tn.soretras.depart.service.dto.TraficDTO;

/**
 * Mapper for the entity {@link Trafic} and its DTO {@link TraficDTO}.
 */
@Mapper(componentModel = "spring")
public interface TraficMapper extends EntityMapper<TraficDTO, Trafic> {}
