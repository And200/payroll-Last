package co.edu.sena.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PositionArlMapperTest {

    private PositionArlMapper positionArlMapper;

    @BeforeEach
    public void setUp() {
        positionArlMapper = new PositionArlMapperImpl();
    }
}
