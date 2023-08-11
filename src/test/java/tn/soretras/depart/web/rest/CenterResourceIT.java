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
import tn.soretras.depart.domain.Center;
import tn.soretras.depart.repository.CenterRepository;
import tn.soretras.depart.service.dto.CenterDTO;
import tn.soretras.depart.service.mapper.CenterMapper;

/**
 * Integration tests for the {@link CenterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CenterResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final String DEFAULT_DELCENT = "AAAAAAAAAA";
    private static final String UPDATED_DELCENT = "BBBBBBBBBB";

    private static final String DEFAULT_DEADRCE = "AAAAAAAAAA";
    private static final String UPDATED_DEADRCE = "BBBBBBBBBB";

    private static final String DEFAULT_DEOBSER = "AAAAAAAAAA";
    private static final String UPDATED_DEOBSER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/centers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private CenterMapper centerMapper;

    @Autowired
    private MockMvc restCenterMockMvc;

    private Center center;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Center createEntity() {
        Center center = new Center().deccent(DEFAULT_DECCENT).delcent(DEFAULT_DELCENT).deadrce(DEFAULT_DEADRCE).deobser(DEFAULT_DEOBSER);
        return center;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Center createUpdatedEntity() {
        Center center = new Center().deccent(UPDATED_DECCENT).delcent(UPDATED_DELCENT).deadrce(UPDATED_DEADRCE).deobser(UPDATED_DEOBSER);
        return center;
    }

    @BeforeEach
    public void initTest() {
        centerRepository.deleteAll();
        center = createEntity();
    }

    @Test
    void createCenter() throws Exception {
        int databaseSizeBeforeCreate = centerRepository.findAll().size();
        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);
        restCenterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centerDTO)))
            .andExpect(status().isCreated());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeCreate + 1);
        Center testCenter = centerList.get(centerList.size() - 1);
        assertThat(testCenter.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testCenter.getDelcent()).isEqualTo(DEFAULT_DELCENT);
        assertThat(testCenter.getDeadrce()).isEqualTo(DEFAULT_DEADRCE);
        assertThat(testCenter.getDeobser()).isEqualTo(DEFAULT_DEOBSER);
    }

    @Test
    void createCenterWithExistingId() throws Exception {
        // Create the Center with an existing ID
        center.setId("existing_id");
        CenterDTO centerDTO = centerMapper.toDto(center);

        int databaseSizeBeforeCreate = centerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCenterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = centerRepository.findAll().size();
        // set the field null
        center.setDeccent(null);

        // Create the Center, which fails.
        CenterDTO centerDTO = centerMapper.toDto(center);

        restCenterMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centerDTO)))
            .andExpect(status().isBadRequest());

        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCenters() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        // Get all the centerList
        restCenterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(center.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].delcent").value(hasItem(DEFAULT_DELCENT)))
            .andExpect(jsonPath("$.[*].deadrce").value(hasItem(DEFAULT_DEADRCE)))
            .andExpect(jsonPath("$.[*].deobser").value(hasItem(DEFAULT_DEOBSER)));
    }

    @Test
    void getCenter() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        // Get the center
        restCenterMockMvc
            .perform(get(ENTITY_API_URL_ID, center.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(center.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.delcent").value(DEFAULT_DELCENT))
            .andExpect(jsonPath("$.deadrce").value(DEFAULT_DEADRCE))
            .andExpect(jsonPath("$.deobser").value(DEFAULT_DEOBSER));
    }

    @Test
    void getNonExistingCenter() throws Exception {
        // Get the center
        restCenterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingCenter() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        int databaseSizeBeforeUpdate = centerRepository.findAll().size();

        // Update the center
        Center updatedCenter = centerRepository.findById(center.getId()).get();
        updatedCenter.deccent(UPDATED_DECCENT).delcent(UPDATED_DELCENT).deadrce(UPDATED_DEADRCE).deobser(UPDATED_DEOBSER);
        CenterDTO centerDTO = centerMapper.toDto(updatedCenter);

        restCenterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, centerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isOk());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
        Center testCenter = centerList.get(centerList.size() - 1);
        assertThat(testCenter.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCenter.getDelcent()).isEqualTo(UPDATED_DELCENT);
        assertThat(testCenter.getDeadrce()).isEqualTo(UPDATED_DEADRCE);
        assertThat(testCenter.getDeobser()).isEqualTo(UPDATED_DEOBSER);
    }

    @Test
    void putNonExistingCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, centerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centerDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCenterWithPatch() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        int databaseSizeBeforeUpdate = centerRepository.findAll().size();

        // Update the center using partial update
        Center partialUpdatedCenter = new Center();
        partialUpdatedCenter.setId(center.getId());

        partialUpdatedCenter.deccent(UPDATED_DECCENT).deobser(UPDATED_DEOBSER);

        restCenterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCenter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCenter))
            )
            .andExpect(status().isOk());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
        Center testCenter = centerList.get(centerList.size() - 1);
        assertThat(testCenter.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCenter.getDelcent()).isEqualTo(DEFAULT_DELCENT);
        assertThat(testCenter.getDeadrce()).isEqualTo(DEFAULT_DEADRCE);
        assertThat(testCenter.getDeobser()).isEqualTo(UPDATED_DEOBSER);
    }

    @Test
    void fullUpdateCenterWithPatch() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        int databaseSizeBeforeUpdate = centerRepository.findAll().size();

        // Update the center using partial update
        Center partialUpdatedCenter = new Center();
        partialUpdatedCenter.setId(center.getId());

        partialUpdatedCenter.deccent(UPDATED_DECCENT).delcent(UPDATED_DELCENT).deadrce(UPDATED_DEADRCE).deobser(UPDATED_DEOBSER);

        restCenterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCenter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCenter))
            )
            .andExpect(status().isOk());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
        Center testCenter = centerList.get(centerList.size() - 1);
        assertThat(testCenter.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCenter.getDelcent()).isEqualTo(UPDATED_DELCENT);
        assertThat(testCenter.getDeadrce()).isEqualTo(UPDATED_DEADRCE);
        assertThat(testCenter.getDeobser()).isEqualTo(UPDATED_DEOBSER);
    }

    @Test
    void patchNonExistingCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, centerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCenter() throws Exception {
        int databaseSizeBeforeUpdate = centerRepository.findAll().size();
        center.setId(UUID.randomUUID().toString());

        // Create the Center
        CenterDTO centerDTO = centerMapper.toDto(center);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCenterMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(centerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Center in the database
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCenter() throws Exception {
        // Initialize the database
        centerRepository.save(center);

        int databaseSizeBeforeDelete = centerRepository.findAll().size();

        // Delete the center
        restCenterMockMvc
            .perform(delete(ENTITY_API_URL_ID, center.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Center> centerList = centerRepository.findAll();
        assertThat(centerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
