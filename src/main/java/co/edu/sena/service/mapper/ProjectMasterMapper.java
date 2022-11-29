package co.edu.sena.service.mapper;

import co.edu.sena.domain.CostCenter;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.ProjectMaster;
import co.edu.sena.service.dto.CostCenterDTO;
import co.edu.sena.service.dto.EmployeeDTO;
import co.edu.sena.service.dto.ProjectMasterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProjectMaster} and its DTO {@link ProjectMasterDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMasterMapper extends EntityMapper<ProjectMasterDTO, ProjectMaster> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeCompleteName")
    @Mapping(target = "costCenter", source = "costCenter", qualifiedByName = "costCenterCostCenterName")
    ProjectMasterDTO toDto(ProjectMaster s);

    @Named("employeeCompleteName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "completeName", source = "completeName")
    EmployeeDTO toDtoEmployeeCompleteName(Employee employee);

    @Named("costCenterCostCenterName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "costCenterName", source = "costCenterName")
    CostCenterDTO toDtoCostCenterCostCenterName(CostCenter costCenter);
}
