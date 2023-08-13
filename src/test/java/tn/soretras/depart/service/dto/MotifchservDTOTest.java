package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class MotifchservDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MotifchservDTO.class);
        MotifchservDTO motifchservDTO1 = new MotifchservDTO();
        motifchservDTO1.setId("id1");
        MotifchservDTO motifchservDTO2 = new MotifchservDTO();
        assertThat(motifchservDTO1).isNotEqualTo(motifchservDTO2);
        motifchservDTO2.setId(motifchservDTO1.getId());
        assertThat(motifchservDTO1).isEqualTo(motifchservDTO2);
        motifchservDTO2.setId("id2");
        assertThat(motifchservDTO1).isNotEqualTo(motifchservDTO2);
        motifchservDTO1.setId(null);
        assertThat(motifchservDTO1).isNotEqualTo(motifchservDTO2);
    }
}
