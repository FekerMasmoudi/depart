package tn.soretras.depart.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BordereauMapperTest {

    private BordereauMapper bordereauMapper;

    @BeforeEach
    public void setUp() {
        bordereauMapper = new BordereauMapperImpl();
    }
}
