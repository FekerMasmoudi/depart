package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModifMapperTest {

    private ModifMapper modifMapper;

    @BeforeEach
    public void setUp() {
        modifMapper = new ModifMapperImpl();
    }
}
