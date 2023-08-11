package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class RhAgentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RhAgentDTO.class);
        RhAgentDTO rhAgentDTO1 = new RhAgentDTO();
        rhAgentDTO1.setId("id1");
        RhAgentDTO rhAgentDTO2 = new RhAgentDTO();
        assertThat(rhAgentDTO1).isNotEqualTo(rhAgentDTO2);
        rhAgentDTO2.setId(rhAgentDTO1.getId());
        assertThat(rhAgentDTO1).isEqualTo(rhAgentDTO2);
        rhAgentDTO2.setId("id2");
        assertThat(rhAgentDTO1).isNotEqualTo(rhAgentDTO2);
        rhAgentDTO1.setId(null);
        assertThat(rhAgentDTO1).isNotEqualTo(rhAgentDTO2);
    }
}
