package co.edu.sena.service.impl;

import co.edu.sena.domain.AccountPlan;
import co.edu.sena.repository.AccountPlanRepository;
import co.edu.sena.service.AccountPlanService;
import co.edu.sena.service.dto.AccountPlanDTO;
import co.edu.sena.service.mapper.AccountPlanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AccountPlan}.
 */
@Service
@Transactional
public class AccountPlanServiceImpl implements AccountPlanService {

    private final Logger log = LoggerFactory.getLogger(AccountPlanServiceImpl.class);

    private final AccountPlanRepository accountPlanRepository;

    private final AccountPlanMapper accountPlanMapper;

    public AccountPlanServiceImpl(AccountPlanRepository accountPlanRepository, AccountPlanMapper accountPlanMapper) {
        this.accountPlanRepository = accountPlanRepository;
        this.accountPlanMapper = accountPlanMapper;
    }

    @Override
    public AccountPlanDTO save(AccountPlanDTO accountPlanDTO) {
        log.debug("Request to save AccountPlan : {}", accountPlanDTO);
        AccountPlan accountPlan = accountPlanMapper.toEntity(accountPlanDTO);
        accountPlan = accountPlanRepository.save(accountPlan);
        return accountPlanMapper.toDto(accountPlan);
    }

    @Override
    public AccountPlanDTO update(AccountPlanDTO accountPlanDTO) {
        log.debug("Request to save AccountPlan : {}", accountPlanDTO);
        AccountPlan accountPlan = accountPlanMapper.toEntity(accountPlanDTO);
        accountPlan = accountPlanRepository.save(accountPlan);
        return accountPlanMapper.toDto(accountPlan);
    }

    @Override
    public Optional<AccountPlanDTO> partialUpdate(AccountPlanDTO accountPlanDTO) {
        log.debug("Request to partially update AccountPlan : {}", accountPlanDTO);

        return accountPlanRepository
            .findById(accountPlanDTO.getId())
            .map(existingAccountPlan -> {
                accountPlanMapper.partialUpdate(existingAccountPlan, accountPlanDTO);

                return existingAccountPlan;
            })
            .map(accountPlanRepository::save)
            .map(accountPlanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AccountPlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountPlans");
        return accountPlanRepository.findAll(pageable).map(accountPlanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountPlanDTO> findOne(Long id) {
        log.debug("Request to get AccountPlan : {}", id);
        return accountPlanRepository.findById(id).map(accountPlanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountPlan : {}", id);
        accountPlanRepository.deleteById(id);
    }
}
