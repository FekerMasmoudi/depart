package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Groupe;
import tn.soretras.depart.service.dto.GroupeDTO;

/**
 * Mapper for the entity {@link Groupe} and its DTO {@link GroupeDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupeMapper extends EntityMapper<GroupeDTO, Groupe> {}
