package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class LigneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ligne.class);
        Ligne ligne1 = new Ligne();
        ligne1.setId("id1");
        Ligne ligne2 = new Ligne();
        ligne2.setId(ligne1.getId());
        assertThat(ligne1).isEqualTo(ligne2);
        ligne2.setId("id2");
        assertThat(ligne1).isNotEqualTo(ligne2);
        ligne1.setId(null);
        assertThat(ligne1).isNotEqualTo(ligne2);
    }
}
