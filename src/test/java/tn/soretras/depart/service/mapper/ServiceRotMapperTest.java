package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceRotMapperTest {

    private ServiceRotMapper serviceRotMapper;

    @BeforeEach
    public void setUp() {
        serviceRotMapper = new ServiceRotMapperImpl();
    }
}
