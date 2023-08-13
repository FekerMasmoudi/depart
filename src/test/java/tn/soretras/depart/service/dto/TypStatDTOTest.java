package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class TypStatDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypStatDTO.class);
        TypStatDTO typStatDTO1 = new TypStatDTO();
        typStatDTO1.setId("id1");
        TypStatDTO typStatDTO2 = new TypStatDTO();
        assertThat(typStatDTO1).isNotEqualTo(typStatDTO2);
        typStatDTO2.setId(typStatDTO1.getId());
        assertThat(typStatDTO1).isEqualTo(typStatDTO2);
        typStatDTO2.setId("id2");
        assertThat(typStatDTO1).isNotEqualTo(typStatDTO2);
        typStatDTO1.setId(null);
        assertThat(typStatDTO1).isNotEqualTo(typStatDTO2);
    }
}
