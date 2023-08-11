package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class FoncAgentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoncAgentDTO.class);
        FoncAgentDTO foncAgentDTO1 = new FoncAgentDTO();
        foncAgentDTO1.setId("id1");
        FoncAgentDTO foncAgentDTO2 = new FoncAgentDTO();
        assertThat(foncAgentDTO1).isNotEqualTo(foncAgentDTO2);
        foncAgentDTO2.setId(foncAgentDTO1.getId());
        assertThat(foncAgentDTO1).isEqualTo(foncAgentDTO2);
        foncAgentDTO2.setId("id2");
        assertThat(foncAgentDTO1).isNotEqualTo(foncAgentDTO2);
        foncAgentDTO1.setId(null);
        assertThat(foncAgentDTO1).isNotEqualTo(foncAgentDTO2);
    }
}
