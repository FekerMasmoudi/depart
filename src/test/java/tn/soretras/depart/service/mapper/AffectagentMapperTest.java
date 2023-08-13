package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AffectagentMapperTest {

    private AffectagentMapper affectagentMapper;

    @BeforeEach
    public void setUp() {
        affectagentMapper = new AffectagentMapperImpl();
    }
}
