package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Periode;
import tn.soretras.depart.service.dto.PeriodeDTO;

/**
 * Mapper for the entity {@link Periode} and its DTO {@link PeriodeDTO}.
 */
@Mapper(componentModel = "spring")
public interface PeriodeMapper extends EntityMapper<PeriodeDTO, Periode> {}
