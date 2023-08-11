package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MotifaMapperTest {

    private MotifaMapper motifaMapper;

    @BeforeEach
    public void setUp() {
        motifaMapper = new MotifaMapperImpl();
    }
}
