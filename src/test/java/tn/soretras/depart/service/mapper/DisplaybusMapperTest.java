package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisplaybusMapperTest {

    private DisplaybusMapper displaybusMapper;

    @BeforeEach
    public void setUp() {
        displaybusMapper = new DisplaybusMapperImpl();
    }
}
