package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DetailEmployeeSocialPaymentMapperTest {

    private DetailEmployeeSocialPaymentMapper detailEmployeeSocialPaymentMapper;

    @BeforeEach
    public void setUp() {
        detailEmployeeSocialPaymentMapper = new DetailEmployeeSocialPaymentMapperImpl();
    }
}
