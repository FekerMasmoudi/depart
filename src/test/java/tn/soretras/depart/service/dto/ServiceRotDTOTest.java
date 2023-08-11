package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class ServiceRotDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceRotDTO.class);
        ServiceRotDTO serviceRotDTO1 = new ServiceRotDTO();
        serviceRotDTO1.setId("id1");
        ServiceRotDTO serviceRotDTO2 = new ServiceRotDTO();
        assertThat(serviceRotDTO1).isNotEqualTo(serviceRotDTO2);
        serviceRotDTO2.setId(serviceRotDTO1.getId());
        assertThat(serviceRotDTO1).isEqualTo(serviceRotDTO2);
        serviceRotDTO2.setId("id2");
        assertThat(serviceRotDTO1).isNotEqualTo(serviceRotDTO2);
        serviceRotDTO1.setId(null);
        assertThat(serviceRotDTO1).isNotEqualTo(serviceRotDTO2);
    }
}
