package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SocialPaymentMapperTest {

    private SocialPaymentMapper socialPaymentMapper;

    @BeforeEach
    public void setUp() {
        socialPaymentMapper = new SocialPaymentMapperImpl();
    }
}
