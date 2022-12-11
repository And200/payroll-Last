package co.edu.sena.service.mapper;

import co.edu.sena.domain.Deduction;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.Income;
import co.edu.sena.domain.Payroll;
import co.edu.sena.domain.PositionArl;
import co.edu.sena.service.dto.DeductionDTO;
import co.edu.sena.service.dto.EmployeeDTO;
import co.edu.sena.service.dto.IncomeDTO;
import co.edu.sena.service.dto.PayrollDTO;
import co.edu.sena.service.dto.PositionArlDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payroll} and its DTO {@link PayrollDTO}.
 */
@Mapper(componentModel = "spring")
public interface PayrollMapper extends EntityMapper<PayrollDTO, Payroll> {
    @Mapping(target = "income", source = "income", qualifiedByName = "incomeDescription")
    @Mapping(target = "deduction", source = "deduction", qualifiedByName = "deductionDescription")
    @Mapping(target = "positionArl", source = "positionArl", qualifiedByName = "positionArlRiskLevel")
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeCompleteName")
    PayrollDTO toDto(Payroll s);

    @Named("incomeDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    IncomeDTO toDtoIncomeDescription(Income income);

    @Named("deductionDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    DeductionDTO toDtoDeductionDescription(Deduction deduction);

    @Named("positionArlRiskLevel")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "riskLevel", source = "riskLevel")
    @Mapping(target = "position", source = "position")
    PositionArlDTO toDtoPositionArlRiskLevel(PositionArl positionArl);

    @Named("employeeCompleteName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "completeName", source = "completeName")
    EmployeeDTO toDtoEmployeeCompleteName(Employee employee);
}
