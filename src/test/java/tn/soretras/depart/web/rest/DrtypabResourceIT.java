package tn.soretras.depart.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tn.soretras.depart.IntegrationTest;
import tn.soretras.depart.domain.Drtypab;
import tn.soretras.depart.repository.DrtypabRepository;
import tn.soretras.depart.service.dto.DrtypabDTO;
import tn.soretras.depart.service.mapper.DrtypabMapper;

/**
 * Integration tests for the {@link DrtypabResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DrtypabResourceIT {

    private static final String DEFAULT_CDTYPAB = "AAAAAAAAAA";
    private static final String UPDATED_CDTYPAB = "BBBBBBBBBB";

    private static final String DEFAULT_LBTYPAB = "AAAAAAAAAA";
    private static final String UPDATED_LBTYPAB = "BBBBBBBBBB";

    private static final String DEFAULT_DABSJT = "AAAAAAAAAA";
    private static final String UPDATED_DABSJT = "BBBBBBBBBB";

    private static final String DEFAULT_DABSJP = "AAAAAAAAAA";
    private static final String UPDATED_DABSJP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/drtypabs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DrtypabRepository drtypabRepository;

    @Autowired
    private DrtypabMapper drtypabMapper;

    @Autowired
    private MockMvc restDrtypabMockMvc;

    private Drtypab drtypab;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Drtypab createEntity() {
        Drtypab drtypab = new Drtypab().cdtypab(DEFAULT_CDTYPAB).lbtypab(DEFAULT_LBTYPAB).dabsjt(DEFAULT_DABSJT).dabsjp(DEFAULT_DABSJP);
        return drtypab;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Drtypab createUpdatedEntity() {
        Drtypab drtypab = new Drtypab().cdtypab(UPDATED_CDTYPAB).lbtypab(UPDATED_LBTYPAB).dabsjt(UPDATED_DABSJT).dabsjp(UPDATED_DABSJP);
        return drtypab;
    }

    @BeforeEach
    public void initTest() {
        drtypabRepository.deleteAll();
        drtypab = createEntity();
    }

    @Test
    void createDrtypab() throws Exception {
        int databaseSizeBeforeCreate = drtypabRepository.findAll().size();
        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);
        restDrtypabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drtypabDTO)))
            .andExpect(status().isCreated());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeCreate + 1);
        Drtypab testDrtypab = drtypabList.get(drtypabList.size() - 1);
        assertThat(testDrtypab.getCdtypab()).isEqualTo(DEFAULT_CDTYPAB);
        assertThat(testDrtypab.getLbtypab()).isEqualTo(DEFAULT_LBTYPAB);
        assertThat(testDrtypab.getDabsjt()).isEqualTo(DEFAULT_DABSJT);
        assertThat(testDrtypab.getDabsjp()).isEqualTo(DEFAULT_DABSJP);
    }

    @Test
    void createDrtypabWithExistingId() throws Exception {
        // Create the Drtypab with an existing ID
        drtypab.setId("existing_id");
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        int databaseSizeBeforeCreate = drtypabRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDrtypabMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drtypabDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDrtypabs() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        // Get all the drtypabList
        restDrtypabMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(drtypab.getId())))
            .andExpect(jsonPath("$.[*].cdtypab").value(hasItem(DEFAULT_CDTYPAB)))
            .andExpect(jsonPath("$.[*].lbtypab").value(hasItem(DEFAULT_LBTYPAB)))
            .andExpect(jsonPath("$.[*].dabsjt").value(hasItem(DEFAULT_DABSJT)))
            .andExpect(jsonPath("$.[*].dabsjp").value(hasItem(DEFAULT_DABSJP)));
    }

    @Test
    void getDrtypab() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        // Get the drtypab
        restDrtypabMockMvc
            .perform(get(ENTITY_API_URL_ID, drtypab.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(drtypab.getId()))
            .andExpect(jsonPath("$.cdtypab").value(DEFAULT_CDTYPAB))
            .andExpect(jsonPath("$.lbtypab").value(DEFAULT_LBTYPAB))
            .andExpect(jsonPath("$.dabsjt").value(DEFAULT_DABSJT))
            .andExpect(jsonPath("$.dabsjp").value(DEFAULT_DABSJP));
    }

    @Test
    void getNonExistingDrtypab() throws Exception {
        // Get the drtypab
        restDrtypabMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDrtypab() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();

        // Update the drtypab
        Drtypab updatedDrtypab = drtypabRepository.findById(drtypab.getId()).get();
        updatedDrtypab.cdtypab(UPDATED_CDTYPAB).lbtypab(UPDATED_LBTYPAB).dabsjt(UPDATED_DABSJT).dabsjp(UPDATED_DABSJP);
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(updatedDrtypab);

        restDrtypabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, drtypabDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isOk());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
        Drtypab testDrtypab = drtypabList.get(drtypabList.size() - 1);
        assertThat(testDrtypab.getCdtypab()).isEqualTo(UPDATED_CDTYPAB);
        assertThat(testDrtypab.getLbtypab()).isEqualTo(UPDATED_LBTYPAB);
        assertThat(testDrtypab.getDabsjt()).isEqualTo(UPDATED_DABSJT);
        assertThat(testDrtypab.getDabsjp()).isEqualTo(UPDATED_DABSJP);
    }

    @Test
    void putNonExistingDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, drtypabDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drtypabDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDrtypabWithPatch() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();

        // Update the drtypab using partial update
        Drtypab partialUpdatedDrtypab = new Drtypab();
        partialUpdatedDrtypab.setId(drtypab.getId());

        partialUpdatedDrtypab.lbtypab(UPDATED_LBTYPAB).dabsjp(UPDATED_DABSJP);

        restDrtypabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDrtypab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDrtypab))
            )
            .andExpect(status().isOk());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
        Drtypab testDrtypab = drtypabList.get(drtypabList.size() - 1);
        assertThat(testDrtypab.getCdtypab()).isEqualTo(DEFAULT_CDTYPAB);
        assertThat(testDrtypab.getLbtypab()).isEqualTo(UPDATED_LBTYPAB);
        assertThat(testDrtypab.getDabsjt()).isEqualTo(DEFAULT_DABSJT);
        assertThat(testDrtypab.getDabsjp()).isEqualTo(UPDATED_DABSJP);
    }

    @Test
    void fullUpdateDrtypabWithPatch() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();

        // Update the drtypab using partial update
        Drtypab partialUpdatedDrtypab = new Drtypab();
        partialUpdatedDrtypab.setId(drtypab.getId());

        partialUpdatedDrtypab.cdtypab(UPDATED_CDTYPAB).lbtypab(UPDATED_LBTYPAB).dabsjt(UPDATED_DABSJT).dabsjp(UPDATED_DABSJP);

        restDrtypabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDrtypab.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDrtypab))
            )
            .andExpect(status().isOk());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
        Drtypab testDrtypab = drtypabList.get(drtypabList.size() - 1);
        assertThat(testDrtypab.getCdtypab()).isEqualTo(UPDATED_CDTYPAB);
        assertThat(testDrtypab.getLbtypab()).isEqualTo(UPDATED_LBTYPAB);
        assertThat(testDrtypab.getDabsjt()).isEqualTo(UPDATED_DABSJT);
        assertThat(testDrtypab.getDabsjp()).isEqualTo(UPDATED_DABSJP);
    }

    @Test
    void patchNonExistingDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, drtypabDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDrtypab() throws Exception {
        int databaseSizeBeforeUpdate = drtypabRepository.findAll().size();
        drtypab.setId(UUID.randomUUID().toString());

        // Create the Drtypab
        DrtypabDTO drtypabDTO = drtypabMapper.toDto(drtypab);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrtypabMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(drtypabDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Drtypab in the database
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDrtypab() throws Exception {
        // Initialize the database
        drtypabRepository.save(drtypab);

        int databaseSizeBeforeDelete = drtypabRepository.findAll().size();

        // Delete the drtypab
        restDrtypabMockMvc
            .perform(delete(ENTITY_API_URL_ID, drtypab.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Drtypab> drtypabList = drtypabRepository.findAll();
        assertThat(drtypabList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
