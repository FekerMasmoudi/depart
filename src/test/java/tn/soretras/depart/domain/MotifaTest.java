package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class MotifaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Motifa.class);
        Motifa motifa1 = new Motifa();
        motifa1.setId("id1");
        Motifa motifa2 = new Motifa();
        motifa2.setId(motifa1.getId());
        assertThat(motifa1).isEqualTo(motifa2);
        motifa2.setId("id2");
        assertThat(motifa1).isNotEqualTo(motifa2);
        motifa1.setId(null);
        assertThat(motifa1).isNotEqualTo(motifa2);
    }
}
