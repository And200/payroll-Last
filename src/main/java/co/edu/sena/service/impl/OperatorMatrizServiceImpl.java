package co.edu.sena.service.impl;

import co.edu.sena.domain.OperatorMatriz;
import co.edu.sena.repository.OperatorMatrizRepository;
import co.edu.sena.service.OperatorMatrizService;
import co.edu.sena.service.dto.OperatorMatrizDTO;
import co.edu.sena.service.mapper.OperatorMatrizMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OperatorMatriz}.
 */
@Service
@Transactional
public class OperatorMatrizServiceImpl implements OperatorMatrizService {

    private final Logger log = LoggerFactory.getLogger(OperatorMatrizServiceImpl.class);

    private final OperatorMatrizRepository operatorMatrizRepository;

    private final OperatorMatrizMapper operatorMatrizMapper;

    public OperatorMatrizServiceImpl(OperatorMatrizRepository operatorMatrizRepository, OperatorMatrizMapper operatorMatrizMapper) {
        this.operatorMatrizRepository = operatorMatrizRepository;
        this.operatorMatrizMapper = operatorMatrizMapper;
    }

    @Override
    public OperatorMatrizDTO save(OperatorMatrizDTO operatorMatrizDTO) {
        log.debug("Request to save OperatorMatriz : {}", operatorMatrizDTO);
        OperatorMatriz operatorMatriz = operatorMatrizMapper.toEntity(operatorMatrizDTO);
        operatorMatriz = operatorMatrizRepository.save(operatorMatriz);
        return operatorMatrizMapper.toDto(operatorMatriz);
    }

    @Override
    public OperatorMatrizDTO update(OperatorMatrizDTO operatorMatrizDTO) {
        log.debug("Request to save OperatorMatriz : {}", operatorMatrizDTO);
        OperatorMatriz operatorMatriz = operatorMatrizMapper.toEntity(operatorMatrizDTO);
        operatorMatriz = operatorMatrizRepository.save(operatorMatriz);
        return operatorMatrizMapper.toDto(operatorMatriz);
    }

    @Override
    public Optional<OperatorMatrizDTO> partialUpdate(OperatorMatrizDTO operatorMatrizDTO) {
        log.debug("Request to partially update OperatorMatriz : {}", operatorMatrizDTO);

        return operatorMatrizRepository
            .findById(operatorMatrizDTO.getId())
            .map(existingOperatorMatriz -> {
                operatorMatrizMapper.partialUpdate(existingOperatorMatriz, operatorMatrizDTO);

                return existingOperatorMatriz;
            })
            .map(operatorMatrizRepository::save)
            .map(operatorMatrizMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperatorMatrizDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperatorMatrizs");
        return operatorMatrizRepository.findAll(pageable).map(operatorMatrizMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperatorMatrizDTO> findOne(Long id) {
        log.debug("Request to get OperatorMatriz : {}", id);
        return operatorMatrizRepository.findById(id).map(operatorMatrizMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperatorMatriz : {}", id);
        operatorMatrizRepository.deleteById(id);
    }
}
