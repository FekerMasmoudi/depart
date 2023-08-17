package tn.soretras.depart.service.mapper;

import org.mapstruct.*;
import tn.soretras.depart.domain.ExternalApi;
import tn.soretras.depart.service.dto.ExternalApiDTO;

/**
 * Mapper for the entity {@link ExternalApi} and its DTO {@link ExternalApiDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExternalApiMapper extends EntityMapper<ExternalApiDTO, ExternalApi> {}
