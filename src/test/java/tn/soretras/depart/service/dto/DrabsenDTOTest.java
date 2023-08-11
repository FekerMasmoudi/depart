package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DrabsenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DrabsenDTO.class);
        DrabsenDTO drabsenDTO1 = new DrabsenDTO();
        drabsenDTO1.setId("id1");
        DrabsenDTO drabsenDTO2 = new DrabsenDTO();
        assertThat(drabsenDTO1).isNotEqualTo(drabsenDTO2);
        drabsenDTO2.setId(drabsenDTO1.getId());
        assertThat(drabsenDTO1).isEqualTo(drabsenDTO2);
        drabsenDTO2.setId("id2");
        assertThat(drabsenDTO1).isNotEqualTo(drabsenDTO2);
        drabsenDTO1.setId(null);
        assertThat(drabsenDTO1).isNotEqualTo(drabsenDTO2);
    }
}
