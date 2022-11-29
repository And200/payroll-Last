package co.edu.sena.service.impl;

import co.edu.sena.domain.Deduction;
import co.edu.sena.repository.DeductionRepository;
import co.edu.sena.service.DeductionService;
import co.edu.sena.service.dto.DeductionDTO;
import co.edu.sena.service.mapper.DeductionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Deduction}.
 */
@Service
@Transactional
public class DeductionServiceImpl implements DeductionService {

    private final Logger log = LoggerFactory.getLogger(DeductionServiceImpl.class);

    private final DeductionRepository deductionRepository;

    private final DeductionMapper deductionMapper;

    public DeductionServiceImpl(DeductionRepository deductionRepository, DeductionMapper deductionMapper) {
        this.deductionRepository = deductionRepository;
        this.deductionMapper = deductionMapper;
    }

    @Override
    public DeductionDTO save(DeductionDTO deductionDTO) {
        log.debug("Request to save Deduction : {}", deductionDTO);
        Deduction deduction = deductionMapper.toEntity(deductionDTO);
        deduction = deductionRepository.save(deduction);
        return deductionMapper.toDto(deduction);
    }

    @Override
    public DeductionDTO update(DeductionDTO deductionDTO) {
        log.debug("Request to save Deduction : {}", deductionDTO);
        Deduction deduction = deductionMapper.toEntity(deductionDTO);
        deduction = deductionRepository.save(deduction);
        return deductionMapper.toDto(deduction);
    }

    @Override
    public Optional<DeductionDTO> partialUpdate(DeductionDTO deductionDTO) {
        log.debug("Request to partially update Deduction : {}", deductionDTO);

        return deductionRepository
            .findById(deductionDTO.getId())
            .map(existingDeduction -> {
                deductionMapper.partialUpdate(existingDeduction, deductionDTO);

                return existingDeduction;
            })
            .map(deductionRepository::save)
            .map(deductionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DeductionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Deductions");
        return deductionRepository.findAll(pageable).map(deductionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeductionDTO> findOne(Long id) {
        log.debug("Request to get Deduction : {}", id);
        return deductionRepository.findById(id).map(deductionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Deduction : {}", id);
        deductionRepository.deleteById(id);
    }
}
