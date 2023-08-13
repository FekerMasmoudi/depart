package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class RotRservDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RotRservDTO.class);
        RotRservDTO rotRservDTO1 = new RotRservDTO();
        rotRservDTO1.setId("id1");
        RotRservDTO rotRservDTO2 = new RotRservDTO();
        assertThat(rotRservDTO1).isNotEqualTo(rotRservDTO2);
        rotRservDTO2.setId(rotRservDTO1.getId());
        assertThat(rotRservDTO1).isEqualTo(rotRservDTO2);
        rotRservDTO2.setId("id2");
        assertThat(rotRservDTO1).isNotEqualTo(rotRservDTO2);
        rotRservDTO1.setId(null);
        assertThat(rotRservDTO1).isNotEqualTo(rotRservDTO2);
    }
}
