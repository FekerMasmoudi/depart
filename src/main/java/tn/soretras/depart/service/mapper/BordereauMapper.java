package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Bordereau;
import tn.soretras.depart.service.dto.BordereauDTO;

/**
 * Mapper for the entity {@link Bordereau} and its DTO {@link BordereauDTO}.
 */
@Mapper(componentModel = "spring")
public interface BordereauMapper extends EntityMapper<BordereauDTO, Bordereau> {}
