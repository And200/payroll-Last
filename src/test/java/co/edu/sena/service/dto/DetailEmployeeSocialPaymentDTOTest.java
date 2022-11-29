package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailEmployeeSocialPaymentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailEmployeeSocialPaymentDTO.class);
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO1 = new DetailEmployeeSocialPaymentDTO();
        detailEmployeeSocialPaymentDTO1.setId(1L);
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO2 = new DetailEmployeeSocialPaymentDTO();
        assertThat(detailEmployeeSocialPaymentDTO1).isNotEqualTo(detailEmployeeSocialPaymentDTO2);
        detailEmployeeSocialPaymentDTO2.setId(detailEmployeeSocialPaymentDTO1.getId());
        assertThat(detailEmployeeSocialPaymentDTO1).isEqualTo(detailEmployeeSocialPaymentDTO2);
        detailEmployeeSocialPaymentDTO2.setId(2L);
        assertThat(detailEmployeeSocialPaymentDTO1).isNotEqualTo(detailEmployeeSocialPaymentDTO2);
        detailEmployeeSocialPaymentDTO1.setId(null);
        assertThat(detailEmployeeSocialPaymentDTO1).isNotEqualTo(detailEmployeeSocialPaymentDTO2);
    }
}
