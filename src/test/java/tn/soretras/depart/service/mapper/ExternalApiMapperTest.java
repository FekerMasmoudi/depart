package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExternalApiMapperTest {

    private ExternalApiMapper externalApiMapper;

    @BeforeEach
    public void setUp() {
        externalApiMapper = new ExternalApiMapperImpl();
    }
}
