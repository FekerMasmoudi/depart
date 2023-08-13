package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class MotifchservTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Motifchserv.class);
        Motifchserv motifchserv1 = new Motifchserv();
        motifchserv1.setId("id1");
        Motifchserv motifchserv2 = new Motifchserv();
        motifchserv2.setId(motifchserv1.getId());
        assertThat(motifchserv1).isEqualTo(motifchserv2);
        motifchserv2.setId("id2");
        assertThat(motifchserv1).isNotEqualTo(motifchserv2);
        motifchserv1.setId(null);
        assertThat(motifchserv1).isNotEqualTo(motifchserv2);
    }
}
