package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DepartTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Depart.class);
        Depart depart1 = new Depart();
        depart1.setId("id1");
        Depart depart2 = new Depart();
        depart2.setId(depart1.getId());
        assertThat(depart1).isEqualTo(depart2);
        depart2.setId("id2");
        assertThat(depart1).isNotEqualTo(depart2);
        depart1.setId(null);
        assertThat(depart1).isNotEqualTo(depart2);
    }
}
