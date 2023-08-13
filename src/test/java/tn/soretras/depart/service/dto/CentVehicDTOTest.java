package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class CentVehicDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentVehicDTO.class);
        CentVehicDTO centVehicDTO1 = new CentVehicDTO();
        centVehicDTO1.setId("id1");
        CentVehicDTO centVehicDTO2 = new CentVehicDTO();
        assertThat(centVehicDTO1).isNotEqualTo(centVehicDTO2);
        centVehicDTO2.setId(centVehicDTO1.getId());
        assertThat(centVehicDTO1).isEqualTo(centVehicDTO2);
        centVehicDTO2.setId("id2");
        assertThat(centVehicDTO1).isNotEqualTo(centVehicDTO2);
        centVehicDTO1.setId(null);
        assertThat(centVehicDTO1).isNotEqualTo(centVehicDTO2);
    }
}
