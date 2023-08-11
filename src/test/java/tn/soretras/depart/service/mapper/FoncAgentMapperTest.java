package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FoncAgentMapperTest {

    private FoncAgentMapper foncAgentMapper;

    @BeforeEach
    public void setUp() {
        foncAgentMapper = new FoncAgentMapperImpl();
    }
}
