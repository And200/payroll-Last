package co.edu.sena.service.impl;

import co.edu.sena.domain.SocialSecurity;
import co.edu.sena.repository.SocialSecurityRepository;
import co.edu.sena.service.SocialSecurityService;
import co.edu.sena.service.dto.SocialSecurityDTO;
import co.edu.sena.service.mapper.SocialSecurityMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SocialSecurity}.
 */
@Service
@Transactional
public class SocialSecurityServiceImpl implements SocialSecurityService {

    private final Logger log = LoggerFactory.getLogger(SocialSecurityServiceImpl.class);

    private final SocialSecurityRepository socialSecurityRepository;

    private final SocialSecurityMapper socialSecurityMapper;

    public SocialSecurityServiceImpl(SocialSecurityRepository socialSecurityRepository, SocialSecurityMapper socialSecurityMapper) {
        this.socialSecurityRepository = socialSecurityRepository;
        this.socialSecurityMapper = socialSecurityMapper;
    }

    @Override
    public SocialSecurityDTO save(SocialSecurityDTO socialSecurityDTO) {
        log.debug("Request to save SocialSecurity : {}", socialSecurityDTO);
        SocialSecurity socialSecurity = socialSecurityMapper.toEntity(socialSecurityDTO);
        socialSecurity = socialSecurityRepository.save(socialSecurity);
        return socialSecurityMapper.toDto(socialSecurity);
    }

    @Override
    public SocialSecurityDTO update(SocialSecurityDTO socialSecurityDTO) {
        log.debug("Request to save SocialSecurity : {}", socialSecurityDTO);
        SocialSecurity socialSecurity = socialSecurityMapper.toEntity(socialSecurityDTO);
        socialSecurity = socialSecurityRepository.save(socialSecurity);
        return socialSecurityMapper.toDto(socialSecurity);
    }

    @Override
    public Optional<SocialSecurityDTO> partialUpdate(SocialSecurityDTO socialSecurityDTO) {
        log.debug("Request to partially update SocialSecurity : {}", socialSecurityDTO);

        return socialSecurityRepository
            .findById(socialSecurityDTO.getId())
            .map(existingSocialSecurity -> {
                socialSecurityMapper.partialUpdate(existingSocialSecurity, socialSecurityDTO);

                return existingSocialSecurity;
            })
            .map(socialSecurityRepository::save)
            .map(socialSecurityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SocialSecurityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocialSecurities");
        return socialSecurityRepository.findAll(pageable).map(socialSecurityMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SocialSecurityDTO> findOne(Long id) {
        log.debug("Request to get SocialSecurity : {}", id);
        return socialSecurityRepository.findById(id).map(socialSecurityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocialSecurity : {}", id);
        socialSecurityRepository.deleteById(id);
    }
}
