package co.edu.sena.service.mapper;

import co.edu.sena.domain.OperatorType;
import co.edu.sena.service.dto.OperatorTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperatorType} and its DTO {@link OperatorTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperatorTypeMapper extends EntityMapper<OperatorTypeDTO, OperatorType> {}
