package co.edu.sena.service.mapper;

import co.edu.sena.domain.SocialSecurity;
import co.edu.sena.service.dto.SocialSecurityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SocialSecurity} and its DTO {@link SocialSecurityDTO}.
 */
@Mapper(componentModel = "spring")
public interface SocialSecurityMapper extends EntityMapper<SocialSecurityDTO, SocialSecurity> {}
