package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncomeMapperTest {

    private IncomeMapper incomeMapper;

    @BeforeEach
    public void setUp() {
        incomeMapper = new IncomeMapperImpl();
    }
}
