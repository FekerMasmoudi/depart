package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RhAgentMapperTest {

    private RhAgentMapper rhAgentMapper;

    @BeforeEach
    public void setUp() {
        rhAgentMapper = new RhAgentMapperImpl();
    }
}
