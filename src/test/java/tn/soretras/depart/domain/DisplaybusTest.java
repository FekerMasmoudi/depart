package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class DisplaybusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Displaybus.class);
        Displaybus displaybus1 = new Displaybus();
        displaybus1.setId("id1");
        Displaybus displaybus2 = new Displaybus();
        displaybus2.setId(displaybus1.getId());
        assertThat(displaybus1).isEqualTo(displaybus2);
        displaybus2.setId("id2");
        assertThat(displaybus1).isNotEqualTo(displaybus2);
        displaybus1.setId(null);
        assertThat(displaybus1).isNotEqualTo(displaybus2);
    }
}
