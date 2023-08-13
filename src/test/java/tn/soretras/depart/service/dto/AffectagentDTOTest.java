package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class AffectagentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffectagentDTO.class);
        AffectagentDTO affectagentDTO1 = new AffectagentDTO();
        affectagentDTO1.setId("id1");
        AffectagentDTO affectagentDTO2 = new AffectagentDTO();
        assertThat(affectagentDTO1).isNotEqualTo(affectagentDTO2);
        affectagentDTO2.setId(affectagentDTO1.getId());
        assertThat(affectagentDTO1).isEqualTo(affectagentDTO2);
        affectagentDTO2.setId("id2");
        assertThat(affectagentDTO1).isNotEqualTo(affectagentDTO2);
        affectagentDTO1.setId(null);
        assertThat(affectagentDTO1).isNotEqualTo(affectagentDTO2);
    }
}
