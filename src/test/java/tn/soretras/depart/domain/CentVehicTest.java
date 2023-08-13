package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class CentVehicTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CentVehic.class);
        CentVehic centVehic1 = new CentVehic();
        centVehic1.setId("id1");
        CentVehic centVehic2 = new CentVehic();
        centVehic2.setId(centVehic1.getId());
        assertThat(centVehic1).isEqualTo(centVehic2);
        centVehic2.setId("id2");
        assertThat(centVehic1).isNotEqualTo(centVehic2);
        centVehic1.setId(null);
        assertThat(centVehic1).isNotEqualTo(centVehic2);
    }
}
