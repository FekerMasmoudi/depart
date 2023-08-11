package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DrtypabDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DrtypabDTO.class);
        DrtypabDTO drtypabDTO1 = new DrtypabDTO();
        drtypabDTO1.setId("id1");
        DrtypabDTO drtypabDTO2 = new DrtypabDTO();
        assertThat(drtypabDTO1).isNotEqualTo(drtypabDTO2);
        drtypabDTO2.setId(drtypabDTO1.getId());
        assertThat(drtypabDTO1).isEqualTo(drtypabDTO2);
        drtypabDTO2.setId("id2");
        assertThat(drtypabDTO1).isNotEqualTo(drtypabDTO2);
        drtypabDTO1.setId(null);
        assertThat(drtypabDTO1).isNotEqualTo(drtypabDTO2);
    }
}
