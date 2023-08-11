package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ModifDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModifDTO.class);
        ModifDTO modifDTO1 = new ModifDTO();
        modifDTO1.setId("id1");
        ModifDTO modifDTO2 = new ModifDTO();
        assertThat(modifDTO1).isNotEqualTo(modifDTO2);
        modifDTO2.setId(modifDTO1.getId());
        assertThat(modifDTO1).isEqualTo(modifDTO2);
        modifDTO2.setId("id2");
        assertThat(modifDTO1).isNotEqualTo(modifDTO2);
        modifDTO1.setId(null);
        assertThat(modifDTO1).isNotEqualTo(modifDTO2);
    }
}
