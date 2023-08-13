package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RotRservMapperTest {

    private RotRservMapper rotRservMapper;

    @BeforeEach
    public void setUp() {
        rotRservMapper = new RotRservMapperImpl();
    }
}
