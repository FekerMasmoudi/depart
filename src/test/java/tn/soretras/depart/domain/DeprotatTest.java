package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DeprotatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Deprotat.class);
        Deprotat deprotat1 = new Deprotat();
        deprotat1.setId("id1");
        Deprotat deprotat2 = new Deprotat();
        deprotat2.setId(deprotat1.getId());
        assertThat(deprotat1).isEqualTo(deprotat2);
        deprotat2.setId("id2");
        assertThat(deprotat1).isNotEqualTo(deprotat2);
        deprotat1.setId(null);
        assertThat(deprotat1).isNotEqualTo(deprotat2);
    }
}
