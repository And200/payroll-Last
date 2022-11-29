package co.edu.sena.service.mapper;

import co.edu.sena.domain.AccountPlan;
import co.edu.sena.domain.Deduction;
import co.edu.sena.service.dto.AccountPlanDTO;
import co.edu.sena.service.dto.DeductionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Deduction} and its DTO {@link DeductionDTO}.
 */
@Mapper(componentModel = "spring")
public interface DeductionMapper extends EntityMapper<DeductionDTO, Deduction> {
    @Mapping(target = "accountPlan", source = "accountPlan", qualifiedByName = "accountPlanId")
    DeductionDTO toDto(Deduction s);

    @Named("accountPlanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountPlanDTO toDtoAccountPlanId(AccountPlan accountPlan);
}
