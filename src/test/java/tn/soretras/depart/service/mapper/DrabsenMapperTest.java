package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrabsenMapperTest {

    private DrabsenMapper drabsenMapper;

    @BeforeEach
    public void setUp() {
        drabsenMapper = new DrabsenMapperImpl();
    }
}
