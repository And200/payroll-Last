package co.edu.sena.service.impl;

import co.edu.sena.domain.Income;
import co.edu.sena.repository.IncomeRepository;
import co.edu.sena.service.IncomeService;
import co.edu.sena.service.dto.IncomeDTO;
import co.edu.sena.service.mapper.IncomeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Income}.
 */
@Service
@Transactional
public class IncomeServiceImpl implements IncomeService {

    private final Logger log = LoggerFactory.getLogger(IncomeServiceImpl.class);

    private final IncomeRepository incomeRepository;

    private final IncomeMapper incomeMapper;

    public IncomeServiceImpl(IncomeRepository incomeRepository, IncomeMapper incomeMapper) {
        this.incomeRepository = incomeRepository;
        this.incomeMapper = incomeMapper;
    }

    @Override
    public IncomeDTO save(IncomeDTO incomeDTO) {
        log.debug("Request to save Income : {}", incomeDTO);
        Income income = incomeMapper.toEntity(incomeDTO);
        income = incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    @Override
    public IncomeDTO update(IncomeDTO incomeDTO) {
        log.debug("Request to save Income : {}", incomeDTO);
        Income income = incomeMapper.toEntity(incomeDTO);
        income = incomeRepository.save(income);
        return incomeMapper.toDto(income);
    }

    @Override
    public Optional<IncomeDTO> partialUpdate(IncomeDTO incomeDTO) {
        log.debug("Request to partially update Income : {}", incomeDTO);

        return incomeRepository
            .findById(incomeDTO.getId())
            .map(existingIncome -> {
                incomeMapper.partialUpdate(existingIncome, incomeDTO);

                return existingIncome;
            })
            .map(incomeRepository::save)
            .map(incomeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncomeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Incomes");
        return incomeRepository.findAll(pageable).map(incomeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncomeDTO> findOne(Long id) {
        log.debug("Request to get Income : {}", id);
        return incomeRepository.findById(id).map(incomeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Income : {}", id);
        incomeRepository.deleteById(id);
    }
}
