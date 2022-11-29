package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PositionArlDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PositionArlDTO.class);
        PositionArlDTO positionArlDTO1 = new PositionArlDTO();
        positionArlDTO1.setId(1L);
        PositionArlDTO positionArlDTO2 = new PositionArlDTO();
        assertThat(positionArlDTO1).isNotEqualTo(positionArlDTO2);
        positionArlDTO2.setId(positionArlDTO1.getId());
        assertThat(positionArlDTO1).isEqualTo(positionArlDTO2);
        positionArlDTO2.setId(2L);
        assertThat(positionArlDTO1).isNotEqualTo(positionArlDTO2);
        positionArlDTO1.setId(null);
        assertThat(positionArlDTO1).isNotEqualTo(positionArlDTO2);
    }
}
