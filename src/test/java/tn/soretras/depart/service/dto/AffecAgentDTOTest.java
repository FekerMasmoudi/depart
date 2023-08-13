package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class AffecAgentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffecAgentDTO.class);
        AffecAgentDTO affecAgentDTO1 = new AffecAgentDTO();
        affecAgentDTO1.setId("id1");
        AffecAgentDTO affecAgentDTO2 = new AffecAgentDTO();
        assertThat(affecAgentDTO1).isNotEqualTo(affecAgentDTO2);
        affecAgentDTO2.setId(affecAgentDTO1.getId());
        assertThat(affecAgentDTO1).isEqualTo(affecAgentDTO2);
        affecAgentDTO2.setId("id2");
        assertThat(affecAgentDTO1).isNotEqualTo(affecAgentDTO2);
        affecAgentDTO1.setId(null);
        assertThat(affecAgentDTO1).isNotEqualTo(affecAgentDTO2);
    }
}
