package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AffecAgentMapperTest {

    private AffecAgentMapper affecAgentMapper;

    @BeforeEach
    public void setUp() {
        affecAgentMapper = new AffecAgentMapperImpl();
    }
}
