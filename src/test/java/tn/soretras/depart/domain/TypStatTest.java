package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class TypStatTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypStat.class);
        TypStat typStat1 = new TypStat();
        typStat1.setId("id1");
        TypStat typStat2 = new TypStat();
        typStat2.setId(typStat1.getId());
        assertThat(typStat1).isEqualTo(typStat2);
        typStat2.setId("id2");
        assertThat(typStat1).isNotEqualTo(typStat2);
        typStat1.setId(null);
        assertThat(typStat1).isNotEqualTo(typStat2);
    }
}
