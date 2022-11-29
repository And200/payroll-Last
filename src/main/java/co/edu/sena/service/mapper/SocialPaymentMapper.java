package co.edu.sena.service.mapper;

import co.edu.sena.domain.SocialPayment;
import co.edu.sena.service.dto.SocialPaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SocialPayment} and its DTO {@link SocialPaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface SocialPaymentMapper extends EntityMapper<SocialPaymentDTO, SocialPayment> {}
