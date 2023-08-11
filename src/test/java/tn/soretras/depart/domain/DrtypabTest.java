package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DrtypabTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Drtypab.class);
        Drtypab drtypab1 = new Drtypab();
        drtypab1.setId("id1");
        Drtypab drtypab2 = new Drtypab();
        drtypab2.setId(drtypab1.getId());
        assertThat(drtypab1).isEqualTo(drtypab2);
        drtypab2.setId("id2");
        assertThat(drtypab1).isNotEqualTo(drtypab2);
        drtypab1.setId(null);
        assertThat(drtypab1).isNotEqualTo(drtypab2);
    }
}
