package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Displaybus;
import tn.soretras.depart.service.dto.DisplaybusDTO;

/**
 * Mapper for the entity {@link Displaybus} and its DTO {@link DisplaybusDTO}.
 */
@Mapper(componentModel = "spring")
public interface DisplaybusMapper extends EntityMapper<DisplaybusDTO, Displaybus> {}
