package co.edu.sena.service.mapper;

import co.edu.sena.domain.DetailEmployeeSocialPayment;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.SocialPayment;
import co.edu.sena.service.dto.DetailEmployeeSocialPaymentDTO;
import co.edu.sena.service.dto.EmployeeDTO;
import co.edu.sena.service.dto.SocialPaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetailEmployeeSocialPayment} and its DTO {@link DetailEmployeeSocialPaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DetailEmployeeSocialPaymentMapper extends EntityMapper<DetailEmployeeSocialPaymentDTO, DetailEmployeeSocialPayment> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeCompleteName")
    @Mapping(target = "socialPayment", source = "socialPayment", qualifiedByName = "socialPaymentSocialPaymentName")
    DetailEmployeeSocialPaymentDTO toDto(DetailEmployeeSocialPayment s);

    @Named("employeeCompleteName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "completeName", source = "completeName")
    EmployeeDTO toDtoEmployeeCompleteName(Employee employee);

    @Named("socialPaymentSocialPaymentName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "socialPaymentName", source = "socialPaymentName")
    SocialPaymentDTO toDtoSocialPaymentSocialPaymentName(SocialPayment socialPayment);
}
