package co.edu.sena.service.mapper;

import co.edu.sena.domain.OperatorMatriz;
import co.edu.sena.service.dto.OperatorMatrizDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperatorMatriz} and its DTO {@link OperatorMatrizDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperatorMatrizMapper extends EntityMapper<OperatorMatrizDTO, OperatorMatriz> {}
