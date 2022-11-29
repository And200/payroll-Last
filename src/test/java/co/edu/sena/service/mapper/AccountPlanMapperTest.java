package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountPlanMapperTest {

    private AccountPlanMapper accountPlanMapper;

    @BeforeEach
    public void setUp() {
        accountPlanMapper = new AccountPlanMapperImpl();
    }
}
