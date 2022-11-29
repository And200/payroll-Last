package co.edu.sena.service.mapper;

import co.edu.sena.domain.Period;
import co.edu.sena.service.dto.PeriodDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Period} and its DTO {@link PeriodDTO}.
 */
@Mapper(componentModel = "spring")
public interface PeriodMapper extends EntityMapper<PeriodDTO, Period> {}
