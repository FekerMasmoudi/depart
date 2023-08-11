package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LigneMapperTest {

    private LigneMapper ligneMapper;

    @BeforeEach
    public void setUp() {
        ligneMapper = new LigneMapperImpl();
    }
}
