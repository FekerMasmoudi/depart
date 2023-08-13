package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class LigneDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LigneDTO.class);
        LigneDTO ligneDTO1 = new LigneDTO();
        ligneDTO1.setId("id1");
        LigneDTO ligneDTO2 = new LigneDTO();
        assertThat(ligneDTO1).isNotEqualTo(ligneDTO2);
        ligneDTO2.setId(ligneDTO1.getId());
        assertThat(ligneDTO1).isEqualTo(ligneDTO2);
        ligneDTO2.setId("id2");
        assertThat(ligneDTO1).isNotEqualTo(ligneDTO2);
        ligneDTO1.setId(null);
        assertThat(ligneDTO1).isNotEqualTo(ligneDTO2);
    }
}
