package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Depart;
import tn.soretras.depart.service.dto.DepartDTO;

/**
 * Mapper for the entity {@link Depart} and its DTO {@link DepartDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartMapper extends EntityMapper<DepartDTO, Depart> {}
