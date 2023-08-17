package tn.soretras.depart.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ExternalApiTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExternalApi.class);
        ExternalApi externalApi1 = new ExternalApi();
        externalApi1.setId("id1");
        ExternalApi externalApi2 = new ExternalApi();
        externalApi2.setId(externalApi1.getId());
        assertThat(externalApi1).isEqualTo(externalApi2);
        externalApi2.setId("id2");
        assertThat(externalApi1).isNotEqualTo(externalApi2);
        externalApi1.setId(null);
        assertThat(externalApi1).isNotEqualTo(externalApi2);
    }
}
