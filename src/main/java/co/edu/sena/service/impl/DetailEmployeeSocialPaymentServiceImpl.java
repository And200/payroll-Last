package co.edu.sena.service.impl;

import co.edu.sena.domain.DetailEmployeeSocialPayment;
import co.edu.sena.repository.DetailEmployeeSocialPaymentRepository;
import co.edu.sena.service.DetailEmployeeSocialPaymentService;
import co.edu.sena.service.dto.DetailEmployeeSocialPaymentDTO;
import co.edu.sena.service.mapper.DetailEmployeeSocialPaymentMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DetailEmployeeSocialPayment}.
 */
@Service
@Transactional
public class DetailEmployeeSocialPaymentServiceImpl implements DetailEmployeeSocialPaymentService {

    private final Logger log = LoggerFactory.getLogger(DetailEmployeeSocialPaymentServiceImpl.class);

    private final DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepository;

    private final DetailEmployeeSocialPaymentMapper detailEmployeeSocialPaymentMapper;

    public DetailEmployeeSocialPaymentServiceImpl(
        DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepository,
        DetailEmployeeSocialPaymentMapper detailEmployeeSocialPaymentMapper
    ) {
        this.detailEmployeeSocialPaymentRepository = detailEmployeeSocialPaymentRepository;
        this.detailEmployeeSocialPaymentMapper = detailEmployeeSocialPaymentMapper;
    }

    @Override
    public DetailEmployeeSocialPaymentDTO save(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO) {
        log.debug("Request to save DetailEmployeeSocialPayment : {}", detailEmployeeSocialPaymentDTO);
        DetailEmployeeSocialPayment detailEmployeeSocialPayment = detailEmployeeSocialPaymentMapper.toEntity(
            detailEmployeeSocialPaymentDTO
        );
        detailEmployeeSocialPayment = detailEmployeeSocialPaymentRepository.save(detailEmployeeSocialPayment);
        return detailEmployeeSocialPaymentMapper.toDto(detailEmployeeSocialPayment);
    }

    @Override
    public DetailEmployeeSocialPaymentDTO update(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO) {
        log.debug("Request to save DetailEmployeeSocialPayment : {}", detailEmployeeSocialPaymentDTO);
        DetailEmployeeSocialPayment detailEmployeeSocialPayment = detailEmployeeSocialPaymentMapper.toEntity(
            detailEmployeeSocialPaymentDTO
        );
        detailEmployeeSocialPayment = detailEmployeeSocialPaymentRepository.save(detailEmployeeSocialPayment);
        return detailEmployeeSocialPaymentMapper.toDto(detailEmployeeSocialPayment);
    }

    @Override
    public Optional<DetailEmployeeSocialPaymentDTO> partialUpdate(DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO) {
        log.debug("Request to partially update DetailEmployeeSocialPayment : {}", detailEmployeeSocialPaymentDTO);

        return detailEmployeeSocialPaymentRepository
            .findById(detailEmployeeSocialPaymentDTO.getId())
            .map(existingDetailEmployeeSocialPayment -> {
                detailEmployeeSocialPaymentMapper.partialUpdate(existingDetailEmployeeSocialPayment, detailEmployeeSocialPaymentDTO);

                return existingDetailEmployeeSocialPayment;
            })
            .map(detailEmployeeSocialPaymentRepository::save)
            .map(detailEmployeeSocialPaymentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetailEmployeeSocialPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetailEmployeeSocialPayments");
        return detailEmployeeSocialPaymentRepository.findAll(pageable).map(detailEmployeeSocialPaymentMapper::toDto);
    }

    public Page<DetailEmployeeSocialPaymentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return detailEmployeeSocialPaymentRepository.findAllWithEagerRelationships(pageable).map(detailEmployeeSocialPaymentMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetailEmployeeSocialPaymentDTO> findOne(Long id) {
        log.debug("Request to get DetailEmployeeSocialPayment : {}", id);
        return detailEmployeeSocialPaymentRepository.findOneWithEagerRelationships(id).map(detailEmployeeSocialPaymentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailEmployeeSocialPayment : {}", id);
        detailEmployeeSocialPaymentRepository.deleteById(id);
    }
}
