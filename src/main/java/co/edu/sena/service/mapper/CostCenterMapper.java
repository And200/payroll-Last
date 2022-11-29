package co.edu.sena.service.mapper;

import co.edu.sena.domain.CostCenter;
import co.edu.sena.service.dto.CostCenterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CostCenter} and its DTO {@link CostCenterDTO}.
 */
@Mapper(componentModel = "spring")
public interface CostCenterMapper extends EntityMapper<CostCenterDTO, CostCenter> {}
