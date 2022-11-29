package co.edu.sena.service.impl;

import co.edu.sena.domain.ProjectMaster;
import co.edu.sena.repository.ProjectMasterRepository;
import co.edu.sena.service.ProjectMasterService;
import co.edu.sena.service.dto.ProjectMasterDTO;
import co.edu.sena.service.mapper.ProjectMasterMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectMaster}.
 */
@Service
@Transactional
public class ProjectMasterServiceImpl implements ProjectMasterService {

    private final Logger log = LoggerFactory.getLogger(ProjectMasterServiceImpl.class);

    private final ProjectMasterRepository projectMasterRepository;

    private final ProjectMasterMapper projectMasterMapper;

    public ProjectMasterServiceImpl(ProjectMasterRepository projectMasterRepository, ProjectMasterMapper projectMasterMapper) {
        this.projectMasterRepository = projectMasterRepository;
        this.projectMasterMapper = projectMasterMapper;
    }

    @Override
    public ProjectMasterDTO save(ProjectMasterDTO projectMasterDTO) {
        log.debug("Request to save ProjectMaster : {}", projectMasterDTO);
        ProjectMaster projectMaster = projectMasterMapper.toEntity(projectMasterDTO);
        projectMaster = projectMasterRepository.save(projectMaster);
        return projectMasterMapper.toDto(projectMaster);
    }

    @Override
    public ProjectMasterDTO update(ProjectMasterDTO projectMasterDTO) {
        log.debug("Request to save ProjectMaster : {}", projectMasterDTO);
        ProjectMaster projectMaster = projectMasterMapper.toEntity(projectMasterDTO);
        projectMaster = projectMasterRepository.save(projectMaster);
        return projectMasterMapper.toDto(projectMaster);
    }

    @Override
    public Optional<ProjectMasterDTO> partialUpdate(ProjectMasterDTO projectMasterDTO) {
        log.debug("Request to partially update ProjectMaster : {}", projectMasterDTO);

        return projectMasterRepository
            .findById(projectMasterDTO.getId())
            .map(existingProjectMaster -> {
                projectMasterMapper.partialUpdate(existingProjectMaster, projectMasterDTO);

                return existingProjectMaster;
            })
            .map(projectMasterRepository::save)
            .map(projectMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectMasters");
        return projectMasterRepository.findAll(pageable).map(projectMasterMapper::toDto);
    }

    public Page<ProjectMasterDTO> findAllWithEagerRelationships(Pageable pageable) {
        return projectMasterRepository.findAllWithEagerRelationships(pageable).map(projectMasterMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectMasterDTO> findOne(Long id) {
        log.debug("Request to get ProjectMaster : {}", id);
        return projectMasterRepository.findOneWithEagerRelationships(id).map(projectMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectMaster : {}", id);
        projectMasterRepository.deleteById(id);
    }
}
