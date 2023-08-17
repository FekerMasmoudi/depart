package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ExternalApiDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExternalApiDTO.class);
        ExternalApiDTO externalApiDTO1 = new ExternalApiDTO();
        externalApiDTO1.setId("id1");
        ExternalApiDTO externalApiDTO2 = new ExternalApiDTO();
        assertThat(externalApiDTO1).isNotEqualTo(externalApiDTO2);
        externalApiDTO2.setId(externalApiDTO1.getId());
        assertThat(externalApiDTO1).isEqualTo(externalApiDTO2);
        externalApiDTO2.setId("id2");
        assertThat(externalApiDTO1).isNotEqualTo(externalApiDTO2);
        externalApiDTO1.setId(null);
        assertThat(externalApiDTO1).isNotEqualTo(externalApiDTO2);
    }
}
