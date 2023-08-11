package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class RhAgentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RhAgent.class);
        RhAgent rhAgent1 = new RhAgent();
        rhAgent1.setId("id1");
        RhAgent rhAgent2 = new RhAgent();
        rhAgent2.setId(rhAgent1.getId());
        assertThat(rhAgent1).isEqualTo(rhAgent2);
        rhAgent2.setId("id2");
        assertThat(rhAgent1).isNotEqualTo(rhAgent2);
        rhAgent1.setId(null);
        assertThat(rhAgent1).isNotEqualTo(rhAgent2);
    }
}
