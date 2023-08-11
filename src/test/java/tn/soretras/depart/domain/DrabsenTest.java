package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DrabsenTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Drabsen.class);
        Drabsen drabsen1 = new Drabsen();
        drabsen1.setId("id1");
        Drabsen drabsen2 = new Drabsen();
        drabsen2.setId(drabsen1.getId());
        assertThat(drabsen1).isEqualTo(drabsen2);
        drabsen2.setId("id2");
        assertThat(drabsen1).isNotEqualTo(drabsen2);
        drabsen1.setId(null);
        assertThat(drabsen1).isNotEqualTo(drabsen2);
    }
}
