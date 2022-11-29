package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperatorMatrizDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperatorMatrizDTO.class);
        OperatorMatrizDTO operatorMatrizDTO1 = new OperatorMatrizDTO();
        operatorMatrizDTO1.setId(1L);
        OperatorMatrizDTO operatorMatrizDTO2 = new OperatorMatrizDTO();
        assertThat(operatorMatrizDTO1).isNotEqualTo(operatorMatrizDTO2);
        operatorMatrizDTO2.setId(operatorMatrizDTO1.getId());
        assertThat(operatorMatrizDTO1).isEqualTo(operatorMatrizDTO2);
        operatorMatrizDTO2.setId(2L);
        assertThat(operatorMatrizDTO1).isNotEqualTo(operatorMatrizDTO2);
        operatorMatrizDTO1.setId(null);
        assertThat(operatorMatrizDTO1).isNotEqualTo(operatorMatrizDTO2);
    }
}
