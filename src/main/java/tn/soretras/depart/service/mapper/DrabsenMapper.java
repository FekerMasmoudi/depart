package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Drabsen;
import tn.soretras.depart.service.dto.DrabsenDTO;

/**
 * Mapper for the entity {@link Drabsen} and its DTO {@link DrabsenDTO}.
 */
@Mapper(componentModel = "spring")
public interface DrabsenMapper extends EntityMapper<DrabsenDTO, Drabsen> {}
