package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class AffecAgentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AffecAgent.class);
        AffecAgent affecAgent1 = new AffecAgent();
        affecAgent1.setId("id1");
        AffecAgent affecAgent2 = new AffecAgent();
        affecAgent2.setId(affecAgent1.getId());
        assertThat(affecAgent1).isEqualTo(affecAgent2);
        affecAgent2.setId("id2");
        assertThat(affecAgent1).isNotEqualTo(affecAgent2);
        affecAgent1.setId(null);
        assertThat(affecAgent1).isNotEqualTo(affecAgent2);
    }
}
