package co.edu.sena.service.impl;

import co.edu.sena.domain.CostCenter;
import co.edu.sena.repository.CostCenterRepository;
import co.edu.sena.service.CostCenterService;
import co.edu.sena.service.dto.CostCenterDTO;
import co.edu.sena.service.mapper.CostCenterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CostCenter}.
 */
@Service
@Transactional
public class CostCenterServiceImpl implements CostCenterService {

    private final Logger log = LoggerFactory.getLogger(CostCenterServiceImpl.class);

    private final CostCenterRepository costCenterRepository;

    private final CostCenterMapper costCenterMapper;

    public CostCenterServiceImpl(CostCenterRepository costCenterRepository, CostCenterMapper costCenterMapper) {
        this.costCenterRepository = costCenterRepository;
        this.costCenterMapper = costCenterMapper;
    }

    @Override
    public CostCenterDTO save(CostCenterDTO costCenterDTO) {
        log.debug("Request to save CostCenter : {}", costCenterDTO);
        CostCenter costCenter = costCenterMapper.toEntity(costCenterDTO);
        costCenter = costCenterRepository.save(costCenter);
        return costCenterMapper.toDto(costCenter);
    }

    @Override
    public CostCenterDTO update(CostCenterDTO costCenterDTO) {
        log.debug("Request to save CostCenter : {}", costCenterDTO);
        CostCenter costCenter = costCenterMapper.toEntity(costCenterDTO);
        costCenter = costCenterRepository.save(costCenter);
        return costCenterMapper.toDto(costCenter);
    }

    @Override
    public Optional<CostCenterDTO> partialUpdate(CostCenterDTO costCenterDTO) {
        log.debug("Request to partially update CostCenter : {}", costCenterDTO);

        return costCenterRepository
            .findById(costCenterDTO.getId())
            .map(existingCostCenter -> {
                costCenterMapper.partialUpdate(existingCostCenter, costCenterDTO);

                return existingCostCenter;
            })
            .map(costCenterRepository::save)
            .map(costCenterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CostCenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CostCenters");
        return costCenterRepository.findAll(pageable).map(costCenterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CostCenterDTO> findOne(Long id) {
        log.debug("Request to get CostCenter : {}", id);
        return costCenterRepository.findById(id).map(costCenterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CostCenter : {}", id);
        costCenterRepository.deleteById(id);
    }
}
