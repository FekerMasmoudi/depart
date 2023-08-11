package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DeprotatDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeprotatDTO.class);
        DeprotatDTO deprotatDTO1 = new DeprotatDTO();
        deprotatDTO1.setId("id1");
        DeprotatDTO deprotatDTO2 = new DeprotatDTO();
        assertThat(deprotatDTO1).isNotEqualTo(deprotatDTO2);
        deprotatDTO2.setId(deprotatDTO1.getId());
        assertThat(deprotatDTO1).isEqualTo(deprotatDTO2);
        deprotatDTO2.setId("id2");
        assertThat(deprotatDTO1).isNotEqualTo(deprotatDTO2);
        deprotatDTO1.setId(null);
        assertThat(deprotatDTO1).isNotEqualTo(deprotatDTO2);
    }
}
