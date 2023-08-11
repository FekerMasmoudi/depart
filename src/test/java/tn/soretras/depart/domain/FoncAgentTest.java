package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class FoncAgentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FoncAgent.class);
        FoncAgent foncAgent1 = new FoncAgent();
        foncAgent1.setId("id1");
        FoncAgent foncAgent2 = new FoncAgent();
        foncAgent2.setId(foncAgent1.getId());
        assertThat(foncAgent1).isEqualTo(foncAgent2);
        foncAgent2.setId("id2");
        assertThat(foncAgent1).isNotEqualTo(foncAgent2);
        foncAgent1.setId(null);
        assertThat(foncAgent1).isNotEqualTo(foncAgent2);
    }
}
