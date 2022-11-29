package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperatorMatrizMapperTest {

    private OperatorMatrizMapper operatorMatrizMapper;

    @BeforeEach
    public void setUp() {
        operatorMatrizMapper = new OperatorMatrizMapperImpl();
    }
}
