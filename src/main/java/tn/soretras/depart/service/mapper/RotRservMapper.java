package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.RotRserv;
import tn.soretras.depart.service.dto.RotRservDTO;

/**
 * Mapper for the entity {@link RotRserv} and its DTO {@link RotRservDTO}.
 */
@Mapper(componentModel = "spring")
public interface RotRservMapper extends EntityMapper<RotRservDTO, RotRserv> {}
