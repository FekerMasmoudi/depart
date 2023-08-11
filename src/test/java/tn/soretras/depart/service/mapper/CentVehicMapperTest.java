package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CentVehicMapperTest {

    private CentVehicMapper centVehicMapper;

    @BeforeEach
    public void setUp() {
        centVehicMapper = new CentVehicMapperImpl();
    }
}
