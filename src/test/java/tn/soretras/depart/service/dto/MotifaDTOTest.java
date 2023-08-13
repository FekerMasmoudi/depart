package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class MotifaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotifaDTO.class);
        MotifaDTO motifaDTO1 = new MotifaDTO();
        motifaDTO1.setId("id1");
        MotifaDTO motifaDTO2 = new MotifaDTO();
        assertThat(motifaDTO1).isNotEqualTo(motifaDTO2);
        motifaDTO2.setId(motifaDTO1.getId());
        assertThat(motifaDTO1).isEqualTo(motifaDTO2);
        motifaDTO2.setId("id2");
        assertThat(motifaDTO1).isNotEqualTo(motifaDTO2);
        motifaDTO1.setId(null);
        assertThat(motifaDTO1).isNotEqualTo(motifaDTO2);
    }
}
