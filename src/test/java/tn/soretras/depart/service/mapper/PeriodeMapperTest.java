package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PeriodeMapperTest {

    private PeriodeMapper periodeMapper;

    @BeforeEach
    public void setUp() {
        periodeMapper = new PeriodeMapperImpl();
    }
}
