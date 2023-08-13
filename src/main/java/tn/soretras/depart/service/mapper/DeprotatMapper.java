package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Deprotat;
import tn.soretras.depart.service.dto.DeprotatDTO;

/**
 * Mapper for the entity {@link Deprotat} and its DTO {@link DeprotatDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeprotatMapper extends EntityMapper<DeprotatDTO, Deprotat> {}
