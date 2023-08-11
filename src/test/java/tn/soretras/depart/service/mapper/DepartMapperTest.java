package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepartMapperTest {

    private DepartMapper departMapper;

    @BeforeEach
    public void setUp() {
        departMapper = new DepartMapperImpl();
    }
}
