package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectMasterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectMasterDTO.class);
        ProjectMasterDTO projectMasterDTO1 = new ProjectMasterDTO();
        projectMasterDTO1.setId(1L);
        ProjectMasterDTO projectMasterDTO2 = new ProjectMasterDTO();
        assertThat(projectMasterDTO1).isNotEqualTo(projectMasterDTO2);
        projectMasterDTO2.setId(projectMasterDTO1.getId());
        assertThat(projectMasterDTO1).isEqualTo(projectMasterDTO2);
        projectMasterDTO2.setId(2L);
        assertThat(projectMasterDTO1).isNotEqualTo(projectMasterDTO2);
        projectMasterDTO1.setId(null);
        assertThat(projectMasterDTO1).isNotEqualTo(projectMasterDTO2);
    }
}
