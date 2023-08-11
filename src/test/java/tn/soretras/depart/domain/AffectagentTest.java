package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class AffectagentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Affectagent.class);
        Affectagent affectagent1 = new Affectagent();
        affectagent1.setId("id1");
        Affectagent affectagent2 = new Affectagent();
        affectagent2.setId(affectagent1.getId());
        assertThat(affectagent1).isEqualTo(affectagent2);
        affectagent2.setId("id2");
        assertThat(affectagent1).isNotEqualTo(affectagent2);
        affectagent1.setId(null);
        assertThat(affectagent1).isNotEqualTo(affectagent2);
    }
}
