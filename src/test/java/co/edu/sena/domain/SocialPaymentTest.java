package co.edu.sena.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SocialPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialPayment.class);
        SocialPayment socialPayment1 = new SocialPayment();
        socialPayment1.setId(1L);
        SocialPayment socialPayment2 = new SocialPayment();
        socialPayment2.setId(socialPayment1.getId());
        assertThat(socialPayment1).isEqualTo(socialPayment2);
        socialPayment2.setId(2L);
        assertThat(socialPayment1).isNotEqualTo(socialPayment2);
        socialPayment1.setId(null);
        assertThat(socialPayment1).isNotEqualTo(socialPayment2);
    }
}
