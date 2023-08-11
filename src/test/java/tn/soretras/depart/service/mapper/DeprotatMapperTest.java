package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeprotatMapperTest {

    private DeprotatMapper deprotatMapper;

    @BeforeEach
    public void setUp() {
        deprotatMapper = new DeprotatMapperImpl();
    }
}
