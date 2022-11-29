package co.edu.sena.service.impl;

import co.edu.sena.domain.PositionArl;
import co.edu.sena.repository.PositionArlRepository;
import co.edu.sena.service.PositionArlService;
import co.edu.sena.service.dto.PositionArlDTO;
import co.edu.sena.service.mapper.PositionArlMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PositionArl}.
 */
@Service
@Transactional
public class PositionArlServiceImpl implements PositionArlService {

    private final Logger log = LoggerFactory.getLogger(PositionArlServiceImpl.class);

    private final PositionArlRepository positionArlRepository;

    private final PositionArlMapper positionArlMapper;

    public PositionArlServiceImpl(PositionArlRepository positionArlRepository, PositionArlMapper positionArlMapper) {
        this.positionArlRepository = positionArlRepository;
        this.positionArlMapper = positionArlMapper;
    }

    @Override
    public PositionArlDTO save(PositionArlDTO positionArlDTO) {
        log.debug("Request to save PositionArl : {}", positionArlDTO);
        PositionArl positionArl = positionArlMapper.toEntity(positionArlDTO);
        positionArl = positionArlRepository.save(positionArl);
        return positionArlMapper.toDto(positionArl);
    }

    @Override
    public PositionArlDTO update(PositionArlDTO positionArlDTO) {
        log.debug("Request to save PositionArl : {}", positionArlDTO);
        PositionArl positionArl = positionArlMapper.toEntity(positionArlDTO);
        positionArl = positionArlRepository.save(positionArl);
        return positionArlMapper.toDto(positionArl);
    }

    @Override
    public Optional<PositionArlDTO> partialUpdate(PositionArlDTO positionArlDTO) {
        log.debug("Request to partially update PositionArl : {}", positionArlDTO);

        return positionArlRepository
            .findById(positionArlDTO.getId())
            .map(existingPositionArl -> {
                positionArlMapper.partialUpdate(existingPositionArl, positionArlDTO);

                return existingPositionArl;
            })
            .map(positionArlRepository::save)
            .map(positionArlMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PositionArlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PositionArls");
        return positionArlRepository.findAll(pageable).map(positionArlMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PositionArlDTO> findOne(Long id) {
        log.debug("Request to get PositionArl : {}", id);
        return positionArlRepository.findById(id).map(positionArlMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PositionArl : {}", id);
        positionArlRepository.deleteById(id);
    }
}
