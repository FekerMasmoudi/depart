package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class TraficTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trafic.class);
        Trafic trafic1 = new Trafic();
        trafic1.setId("id1");
        Trafic trafic2 = new Trafic();
        trafic2.setId(trafic1.getId());
        assertThat(trafic1).isEqualTo(trafic2);
        trafic2.setId("id2");
        assertThat(trafic1).isNotEqualTo(trafic2);
        trafic1.setId(null);
        assertThat(trafic1).isNotEqualTo(trafic2);
    }
}
