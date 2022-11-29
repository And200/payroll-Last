package co.edu.sena.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DetailEmployeeSocialPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetailEmployeeSocialPayment.class);
        DetailEmployeeSocialPayment detailEmployeeSocialPayment1 = new DetailEmployeeSocialPayment();
        detailEmployeeSocialPayment1.setId(1L);
        DetailEmployeeSocialPayment detailEmployeeSocialPayment2 = new DetailEmployeeSocialPayment();
        detailEmployeeSocialPayment2.setId(detailEmployeeSocialPayment1.getId());
        assertThat(detailEmployeeSocialPayment1).isEqualTo(detailEmployeeSocialPayment2);
        detailEmployeeSocialPayment2.setId(2L);
        assertThat(detailEmployeeSocialPayment1).isNotEqualTo(detailEmployeeSocialPayment2);
        detailEmployeeSocialPayment1.setId(null);
        assertThat(detailEmployeeSocialPayment1).isNotEqualTo(detailEmployeeSocialPayment2);
    }
}
