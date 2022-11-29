package co.edu.sena.service.mapper;

import co.edu.sena.domain.AccountPlan;
import co.edu.sena.domain.Income;
import co.edu.sena.service.dto.AccountPlanDTO;
import co.edu.sena.service.dto.IncomeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Income} and its DTO {@link IncomeDTO}.
 */
@Mapper(componentModel = "spring")
public interface IncomeMapper extends EntityMapper<IncomeDTO, Income> {
    @Mapping(target = "accountPlan", source = "accountPlan", qualifiedByName = "accountPlanId")
    IncomeDTO toDto(Income s);

    @Named("accountPlanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountPlanDTO toDtoAccountPlanId(AccountPlan accountPlan);
}
