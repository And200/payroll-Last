package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperatorTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorTypeDTO.class);
        OperatorTypeDTO operatorTypeDTO1 = new OperatorTypeDTO();
        operatorTypeDTO1.setId(1L);
        OperatorTypeDTO operatorTypeDTO2 = new OperatorTypeDTO();
        assertThat(operatorTypeDTO1).isNotEqualTo(operatorTypeDTO2);
        operatorTypeDTO2.setId(operatorTypeDTO1.getId());
        assertThat(operatorTypeDTO1).isEqualTo(operatorTypeDTO2);
        operatorTypeDTO2.setId(2L);
        assertThat(operatorTypeDTO1).isNotEqualTo(operatorTypeDTO2);
        operatorTypeDTO1.setId(null);
        assertThat(operatorTypeDTO1).isNotEqualTo(operatorTypeDTO2);
    }
}
