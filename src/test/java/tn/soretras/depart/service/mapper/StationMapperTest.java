package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StationMapperTest {

    private StationMapper stationMapper;

    @BeforeEach
    public void setUp() {
        stationMapper = new StationMapperImpl();
    }
}
