package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DisplaybusDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisplaybusDTO.class);
        DisplaybusDTO displaybusDTO1 = new DisplaybusDTO();
        displaybusDTO1.setId("id1");
        DisplaybusDTO displaybusDTO2 = new DisplaybusDTO();
        assertThat(displaybusDTO1).isNotEqualTo(displaybusDTO2);
        displaybusDTO2.setId(displaybusDTO1.getId());
        assertThat(displaybusDTO1).isEqualTo(displaybusDTO2);
        displaybusDTO2.setId("id2");
        assertThat(displaybusDTO1).isNotEqualTo(displaybusDTO2);
        displaybusDTO1.setId(null);
        assertThat(displaybusDTO1).isNotEqualTo(displaybusDTO2);
    }
}
