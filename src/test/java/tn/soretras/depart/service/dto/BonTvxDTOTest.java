package tn.soretras.depart.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.soretras.depart.web.rest.TestUtil;

class BonTvxDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BonTvxDTO.class);
        BonTvxDTO bonTvxDTO1 = new BonTvxDTO();
        bonTvxDTO1.setId("id1");
        BonTvxDTO bonTvxDTO2 = new BonTvxDTO();
        assertThat(bonTvxDTO1).isNotEqualTo(bonTvxDTO2);
        bonTvxDTO2.setId(bonTvxDTO1.getId());
        assertThat(bonTvxDTO1).isEqualTo(bonTvxDTO2);
        bonTvxDTO2.setId("id2");
        assertThat(bonTvxDTO1).isNotEqualTo(bonTvxDTO2);
        bonTvxDTO1.setId(null);
        assertThat(bonTvxDTO1).isNotEqualTo(bonTvxDTO2);
    }
}
