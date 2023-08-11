package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TraficMapperTest {

    private TraficMapper traficMapper;

    @BeforeEach
    public void setUp() {
        traficMapper = new TraficMapperImpl();
    }
}
