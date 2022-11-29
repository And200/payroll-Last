package co.edu.sena.service.impl;

import co.edu.sena.domain.SocialPayment;
import co.edu.sena.repository.SocialPaymentRepository;
import co.edu.sena.service.SocialPaymentService;
import co.edu.sena.service.dto.SocialPaymentDTO;
import co.edu.sena.service.mapper.SocialPaymentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SocialPayment}.
 */
@Service
@Transactional
public class SocialPaymentServiceImpl implements SocialPaymentService {

    private final Logger log = LoggerFactory.getLogger(SocialPaymentServiceImpl.class);

    private final SocialPaymentRepository socialPaymentRepository;

    private final SocialPaymentMapper socialPaymentMapper;

    public SocialPaymentServiceImpl(SocialPaymentRepository socialPaymentRepository, SocialPaymentMapper socialPaymentMapper) {
        this.socialPaymentRepository = socialPaymentRepository;
        this.socialPaymentMapper = socialPaymentMapper;
    }

    @Override
    public SocialPaymentDTO save(SocialPaymentDTO socialPaymentDTO) {
        log.debug("Request to save SocialPayment : {}", socialPaymentDTO);
        SocialPayment socialPayment = socialPaymentMapper.toEntity(socialPaymentDTO);
        socialPayment = socialPaymentRepository.save(socialPayment);
        return socialPaymentMapper.toDto(socialPayment);
    }

    @Override
    public SocialPaymentDTO update(SocialPaymentDTO socialPaymentDTO) {
        log.debug("Request to save SocialPayment : {}", socialPaymentDTO);
        SocialPayment socialPayment = socialPaymentMapper.toEntity(socialPaymentDTO);
        socialPayment = socialPaymentRepository.save(socialPayment);
        return socialPaymentMapper.toDto(socialPayment);
    }

    @Override
    public Optional<SocialPaymentDTO> partialUpdate(SocialPaymentDTO socialPaymentDTO) {
        log.debug("Request to partially update SocialPayment : {}", socialPaymentDTO);

        return socialPaymentRepository
            .findById(socialPaymentDTO.getId())
            .map(existingSocialPayment -> {
                socialPaymentMapper.partialUpdate(existingSocialPayment, socialPaymentDTO);

                return existingSocialPayment;
            })
            .map(socialPaymentRepository::save)
            .map(socialPaymentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SocialPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocialPayments");
        return socialPaymentRepository.findAll(pageable).map(socialPaymentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SocialPaymentDTO> findOne(Long id) {
        log.debug("Request to get SocialPayment : {}", id);
        return socialPaymentRepository.findById(id).map(socialPaymentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocialPayment : {}", id);
        socialPaymentRepository.deleteById(id);
    }
}
