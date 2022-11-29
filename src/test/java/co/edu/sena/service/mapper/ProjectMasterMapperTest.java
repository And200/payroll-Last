package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectMasterMapperTest {

    private ProjectMasterMapper projectMasterMapper;

    @BeforeEach
    public void setUp() {
        projectMasterMapper = new ProjectMasterMapperImpl();
    }
}
