package co.edu.sena.service.impl;

import co.edu.sena.domain.OperatorType;
import co.edu.sena.repository.OperatorTypeRepository;
import co.edu.sena.service.OperatorTypeService;
import co.edu.sena.service.dto.OperatorTypeDTO;
import co.edu.sena.service.mapper.OperatorTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OperatorType}.
 */
@Service
@Transactional
public class OperatorTypeServiceImpl implements OperatorTypeService {

    private final Logger log = LoggerFactory.getLogger(OperatorTypeServiceImpl.class);

    private final OperatorTypeRepository operatorTypeRepository;

    private final OperatorTypeMapper operatorTypeMapper;

    public OperatorTypeServiceImpl(OperatorTypeRepository operatorTypeRepository, OperatorTypeMapper operatorTypeMapper) {
        this.operatorTypeRepository = operatorTypeRepository;
        this.operatorTypeMapper = operatorTypeMapper;
    }

    @Override
    public OperatorTypeDTO save(OperatorTypeDTO operatorTypeDTO) {
        log.debug("Request to save OperatorType : {}", operatorTypeDTO);
        OperatorType operatorType = operatorTypeMapper.toEntity(operatorTypeDTO);
        operatorType = operatorTypeRepository.save(operatorType);
        return operatorTypeMapper.toDto(operatorType);
    }

    @Override
    public OperatorTypeDTO update(OperatorTypeDTO operatorTypeDTO) {
        log.debug("Request to save OperatorType : {}", operatorTypeDTO);
        OperatorType operatorType = operatorTypeMapper.toEntity(operatorTypeDTO);
        operatorType = operatorTypeRepository.save(operatorType);
        return operatorTypeMapper.toDto(operatorType);
    }

    @Override
    public Optional<OperatorTypeDTO> partialUpdate(OperatorTypeDTO operatorTypeDTO) {
        log.debug("Request to partially update OperatorType : {}", operatorTypeDTO);

        return operatorTypeRepository
            .findById(operatorTypeDTO.getId())
            .map(existingOperatorType -> {
                operatorTypeMapper.partialUpdate(existingOperatorType, operatorTypeDTO);

                return existingOperatorType;
            })
            .map(operatorTypeRepository::save)
            .map(operatorTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperatorTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperatorTypes");
        return operatorTypeRepository.findAll(pageable).map(operatorTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperatorTypeDTO> findOne(Long id) {
        log.debug("Request to get OperatorType : {}", id);
        return operatorTypeRepository.findById(id).map(operatorTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperatorType : {}", id);
        operatorTypeRepository.deleteById(id);
    }
}
