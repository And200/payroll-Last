package co.edu.sena.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.sena.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SocialSecurityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SocialSecurityDTO.class);
        SocialSecurityDTO socialSecurityDTO1 = new SocialSecurityDTO();
        socialSecurityDTO1.setId(1L);
        SocialSecurityDTO socialSecurityDTO2 = new SocialSecurityDTO();
        assertThat(socialSecurityDTO1).isNotEqualTo(socialSecurityDTO2);
        socialSecurityDTO2.setId(socialSecurityDTO1.getId());
        assertThat(socialSecurityDTO1).isEqualTo(socialSecurityDTO2);
        socialSecurityDTO2.setId(2L);
        assertThat(socialSecurityDTO1).isNotEqualTo(socialSecurityDTO2);
        socialSecurityDTO1.setId(null);
        assertThat(socialSecurityDTO1).isNotEqualTo(socialSecurityDTO2);
    }
}
