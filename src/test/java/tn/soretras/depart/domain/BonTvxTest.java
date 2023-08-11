package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class BonTvxTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BonTvx.class);
        BonTvx bonTvx1 = new BonTvx();
        bonTvx1.setId("id1");
        BonTvx bonTvx2 = new BonTvx();
        bonTvx2.setId(bonTvx1.getId());
        assertThat(bonTvx1).isEqualTo(bonTvx2);
        bonTvx2.setId("id2");
        assertThat(bonTvx1).isNotEqualTo(bonTvx2);
        bonTvx1.setId(null);
        assertThat(bonTvx1).isNotEqualTo(bonTvx2);
    }
}
