package co.edu.sena.service.impl;

import co.edu.sena.domain.Payroll;
import co.edu.sena.repository.PayrollRepository;
import co.edu.sena.service.PayrollService;
import co.edu.sena.service.dto.PayrollDTO;
import co.edu.sena.service.mapper.PayrollMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Payroll}.
 */
@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    private final Logger log = LoggerFactory.getLogger(PayrollServiceImpl.class);

    private final PayrollRepository payrollRepository;

    private final PayrollMapper payrollMapper;

    public PayrollServiceImpl(PayrollRepository payrollRepository, PayrollMapper payrollMapper) {
        this.payrollRepository = payrollRepository;
        this.payrollMapper = payrollMapper;
    }

    @Override
    public PayrollDTO save(PayrollDTO payrollDTO) {
        log.debug("Request to save Payroll : {}", payrollDTO);
        Payroll payroll = payrollMapper.toEntity(payrollDTO);
        payroll = payrollRepository.save(payroll);
        return payrollMapper.toDto(payroll);
    }

    @Override
    public PayrollDTO update(PayrollDTO payrollDTO) {
        log.debug("Request to save Payroll : {}", payrollDTO);
        Payroll payroll = payrollMapper.toEntity(payrollDTO);
        payroll = payrollRepository.save(payroll);
        return payrollMapper.toDto(payroll);
    }

    @Override
    public Optional<PayrollDTO> partialUpdate(PayrollDTO payrollDTO) {
        log.debug("Request to partially update Payroll : {}", payrollDTO);

        return payrollRepository
            .findById(payrollDTO.getId())
            .map(existingPayroll -> {
                payrollMapper.partialUpdate(existingPayroll, payrollDTO);

                return existingPayroll;
            })
            .map(payrollRepository::save)
            .map(payrollMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PayrollDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Payrolls");
        return payrollRepository.findAll(pageable).map(payrollMapper::toDto);
    }

    public Page<PayrollDTO> findAllWithEagerRelationships(Pageable pageable) {
        return payrollRepository.findAllWithEagerRelationships(pageable).map(payrollMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayrollDTO> findOne(Long id) {
        log.debug("Request to get Payroll : {}", id);
        return payrollRepository.findOneWithEagerRelationships(id).map(payrollMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Payroll : {}", id);
        payrollRepository.deleteById(id);
    }
}
