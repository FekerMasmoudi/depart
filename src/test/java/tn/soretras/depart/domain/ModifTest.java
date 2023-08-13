package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ModifTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Modif.class);
        Modif modif1 = new Modif();
        modif1.setId("id1");
        Modif modif2 = new Modif();
        modif2.setId(modif1.getId());
        assertThat(modif1).isEqualTo(modif2);
        modif2.setId("id2");
        assertThat(modif1).isNotEqualTo(modif2);
        modif1.setId(null);
        assertThat(modif1).isNotEqualTo(modif2);
    }
}
