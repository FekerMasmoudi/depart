package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class BordereauTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bordereau.class);
        Bordereau bordereau1 = new Bordereau();
        bordereau1.setId("id1");
        Bordereau bordereau2 = new Bordereau();
        bordereau2.setId(bordereau1.getId());
        assertThat(bordereau1).isEqualTo(bordereau2);
        bordereau2.setId("id2");
        assertThat(bordereau1).isNotEqualTo(bordereau2);
        bordereau1.setId(null);
        assertThat(bordereau1).isNotEqualTo(bordereau2);
    }
}
