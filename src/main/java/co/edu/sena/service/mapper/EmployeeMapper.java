package co.edu.sena.service.mapper;

import co.edu.sena.domain.Contract;
import co.edu.sena.domain.DocumentType;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.OperatorMatriz;
import co.edu.sena.domain.OperatorType;
import co.edu.sena.domain.Period;
import co.edu.sena.domain.SocialSecurity;
import co.edu.sena.domain.User;
import co.edu.sena.service.dto.ContractDTO;
import co.edu.sena.service.dto.DocumentTypeDTO;
import co.edu.sena.service.dto.EmployeeDTO;
import co.edu.sena.service.dto.OperatorMatrizDTO;
import co.edu.sena.service.dto.OperatorTypeDTO;
import co.edu.sena.service.dto.PeriodDTO;
import co.edu.sena.service.dto.SocialSecurityDTO;
import co.edu.sena.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userLogin")
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractSalary")
    @Mapping(target = "period", source = "period", qualifiedByName = "periodDescription")
    @Mapping(target = "operatorType", source = "operatorType", qualifiedByName = "operatorTypeDescription")
    @Mapping(target = "socialSecurity", source = "socialSecurity", qualifiedByName = "socialSecurityEps")
    @Mapping(target = "operatorMatriz", source = "operatorMatriz", qualifiedByName = "operatorMatrizName")
    @Mapping(target = "documentType", source = "documentType", qualifiedByName = "documentTypeDocumentName")
    EmployeeDTO toDto(Employee s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("contractSalary")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "salary", source = "salary")
    ContractDTO toDtoContractSalary(Contract contract);

    @Named("periodDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    PeriodDTO toDtoPeriodDescription(Period period);

    @Named("operatorTypeDescription")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    OperatorTypeDTO toDtoOperatorTypeDescription(OperatorType operatorType);

    @Named("socialSecurityEps")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "eps", source = "eps")
    SocialSecurityDTO toDtoSocialSecurityEps(SocialSecurity socialSecurity);

    @Named("operatorMatrizName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    OperatorMatrizDTO toDtoOperatorMatrizName(OperatorMatriz operatorMatriz);

    @Named("documentTypeDocumentName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "documentName", source = "documentName")
    DocumentTypeDTO toDtoDocumentTypeDocumentName(DocumentType documentType);
}
