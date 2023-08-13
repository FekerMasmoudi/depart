package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypStatMapperTest {

    private TypStatMapper typStatMapper;

    @BeforeEach
    public void setUp() {
        typStatMapper = new TypStatMapperImpl();
    }
}
