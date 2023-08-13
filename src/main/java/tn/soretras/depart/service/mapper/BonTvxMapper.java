package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.BonTvx;
import tn.soretras.depart.service.dto.BonTvxDTO;

/**
 * Mapper for the entity {@link BonTvx} and its DTO {@link BonTvxDTO}.
 */
@Mapper(componentModel = "spring")
public interface BonTvxMapper extends EntityMapper<BonTvxDTO, BonTvx> {}
