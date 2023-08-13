package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class BordereauDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BordereauDTO.class);
        BordereauDTO bordereauDTO1 = new BordereauDTO();
        bordereauDTO1.setId("id1");
        BordereauDTO bordereauDTO2 = new BordereauDTO();
        assertThat(bordereauDTO1).isNotEqualTo(bordereauDTO2);
        bordereauDTO2.setId(bordereauDTO1.getId());
        assertThat(bordereauDTO1).isEqualTo(bordereauDTO2);
        bordereauDTO2.setId("id2");
        assertThat(bordereauDTO1).isNotEqualTo(bordereauDTO2);
        bordereauDTO1.setId(null);
        assertThat(bordereauDTO1).isNotEqualTo(bordereauDTO2);
    }
}
