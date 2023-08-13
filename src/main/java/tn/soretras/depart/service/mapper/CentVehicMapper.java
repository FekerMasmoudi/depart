package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.CentVehic;
import tn.soretras.depart.service.dto.CentVehicDTO;

/**
 * Mapper for the entity {@link CentVehic} and its DTO {@link CentVehicDTO}.
 */
@Mapper(componentModel = "spring")
public interface CentVehicMapper extends EntityMapper<CentVehicDTO, CentVehic> {}
