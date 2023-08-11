package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.ServiceRot;
import tn.soretras.depart.service.dto.ServiceRotDTO;

/**
 * Mapper for the entity {@link ServiceRot} and its DTO {@link ServiceRotDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServiceRotMapper extends EntityMapper<ServiceRotDTO, ServiceRot> {}
