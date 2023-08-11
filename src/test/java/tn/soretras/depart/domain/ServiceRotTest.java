package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ServiceRotTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceRot.class);
        ServiceRot serviceRot1 = new ServiceRot();
        serviceRot1.setId("id1");
        ServiceRot serviceRot2 = new ServiceRot();
        serviceRot2.setId(serviceRot1.getId());
        assertThat(serviceRot1).isEqualTo(serviceRot2);
        serviceRot2.setId("id2");
        assertThat(serviceRot1).isNotEqualTo(serviceRot2);
        serviceRot1.setId(null);
        assertThat(serviceRot1).isNotEqualTo(serviceRot2);
    }
}
