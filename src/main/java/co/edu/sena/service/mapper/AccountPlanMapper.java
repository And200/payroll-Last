package co.edu.sena.service.mapper;

import co.edu.sena.domain.AccountPlan;
import co.edu.sena.service.dto.AccountPlanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AccountPlan} and its DTO {@link AccountPlanDTO}.
 */
@Mapper(componentModel = "spring")
public interface AccountPlanMapper extends EntityMapper<AccountPlanDTO, AccountPlan> {}
