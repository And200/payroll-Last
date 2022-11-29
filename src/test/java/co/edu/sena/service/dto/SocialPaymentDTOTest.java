package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SocialPaymentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialPaymentDTO.class);
        SocialPaymentDTO socialPaymentDTO1 = new SocialPaymentDTO();
        socialPaymentDTO1.setId(1L);
        SocialPaymentDTO socialPaymentDTO2 = new SocialPaymentDTO();
        assertThat(socialPaymentDTO1).isNotEqualTo(socialPaymentDTO2);
        socialPaymentDTO2.setId(socialPaymentDTO1.getId());
        assertThat(socialPaymentDTO1).isEqualTo(socialPaymentDTO2);
        socialPaymentDTO2.setId(2L);
        assertThat(socialPaymentDTO1).isNotEqualTo(socialPaymentDTO2);
        socialPaymentDTO1.setId(null);
        assertThat(socialPaymentDTO1).isNotEqualTo(socialPaymentDTO2);
    }
}
