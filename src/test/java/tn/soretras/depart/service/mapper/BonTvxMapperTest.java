package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BonTvxMapperTest {

    private BonTvxMapper bonTvxMapper;

    @BeforeEach
    public void setUp() {
        bonTvxMapper = new BonTvxMapperImpl();
    }
}
