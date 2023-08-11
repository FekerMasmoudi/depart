package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DepartDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartDTO.class);
        DepartDTO departDTO1 = new DepartDTO();
        departDTO1.setId("id1");
        DepartDTO departDTO2 = new DepartDTO();
        assertThat(departDTO1).isNotEqualTo(departDTO2);
        departDTO2.setId(departDTO1.getId());
        assertThat(departDTO1).isEqualTo(departDTO2);
        departDTO2.setId("id2");
        assertThat(departDTO1).isNotEqualTo(departDTO2);
        departDTO1.setId(null);
        assertThat(departDTO1).isNotEqualTo(departDTO2);
    }
}
