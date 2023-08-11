package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class TraficDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraficDTO.class);
        TraficDTO traficDTO1 = new TraficDTO();
        traficDTO1.setId("id1");
        TraficDTO traficDTO2 = new TraficDTO();
        assertThat(traficDTO1).isNotEqualTo(traficDTO2);
        traficDTO2.setId(traficDTO1.getId());
        assertThat(traficDTO1).isEqualTo(traficDTO2);
        traficDTO2.setId("id2");
        assertThat(traficDTO1).isNotEqualTo(traficDTO2);
        traficDTO1.setId(null);
        assertThat(traficDTO1).isNotEqualTo(traficDTO2);
    }
}
