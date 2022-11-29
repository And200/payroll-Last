package co.edu.sena.service.mapper;

import co.edu.sena.domain.PositionArl;
import co.edu.sena.service.dto.PositionArlDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PositionArl} and its DTO {@link PositionArlDTO}.
 */
@Mapper(componentModel = "spring")
public interface PositionArlMapper extends EntityMapper<PositionArlDTO, PositionArl> {}
