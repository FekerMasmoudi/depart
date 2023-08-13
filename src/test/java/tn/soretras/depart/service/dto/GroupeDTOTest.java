package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class GroupeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GroupeDTO.class);
        GroupeDTO groupeDTO1 = new GroupeDTO();
        groupeDTO1.setId("id1");
        GroupeDTO groupeDTO2 = new GroupeDTO();
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
        groupeDTO2.setId(groupeDTO1.getId());
        assertThat(groupeDTO1).isEqualTo(groupeDTO2);
        groupeDTO2.setId("id2");
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
        groupeDTO1.setId(null);
        assertThat(groupeDTO1).isNotEqualTo(groupeDTO2);
    }
}
