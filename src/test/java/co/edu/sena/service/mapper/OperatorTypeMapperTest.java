package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OperatorTypeMapperTest {

    private OperatorTypeMapper operatorTypeMapper;

    @BeforeEach
    public void setUp() {
        operatorTypeMapper = new OperatorTypeMapperImpl();
    }
}
