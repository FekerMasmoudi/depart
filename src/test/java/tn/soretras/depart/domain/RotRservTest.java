package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class RotRservTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RotRserv.class);
        RotRserv rotRserv1 = new RotRserv();
        rotRserv1.setId("id1");
        RotRserv rotRserv2 = new RotRserv();
        rotRserv2.setId(rotRserv1.getId());
        assertThat(rotRserv1).isEqualTo(rotRserv2);
        rotRserv2.setId("id2");
        assertThat(rotRserv1).isNotEqualTo(rotRserv2);
        rotRserv1.setId(null);
        assertThat(rotRserv1).isNotEqualTo(rotRserv2);
    }
}
