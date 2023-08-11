package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.Motifa;
import tn.soretras.depart.service.dto.MotifaDTO;

/**
 * Mapper for the entity {@link Motifa} and its DTO {@link MotifaDTO}.
 */
@Mapper(componentModel = "spring")
public interface MotifaMapper extends EntityMapper<MotifaDTO, Motifa> {}
