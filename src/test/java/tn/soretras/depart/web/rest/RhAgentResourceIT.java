package tn.soretras.depart.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
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
import tn.soretras.depart.domain.RhAgent;
import tn.soretras.depart.repository.RhAgentRepository;
import tn.soretras.depart.service.dto.RhAgentDTO;
import tn.soretras.depart.service.mapper.RhAgentMapper;

/**
 * Integration tests for the {@link RhAgentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RhAgentResourceIT {

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final String DEFAULT_DECJOUR = "AAAAAAAAAA";
    private static final String UPDATED_DECJOUR = "BBBBBBBBBB";

    private static final String DEFAULT_DATEFFRH = "2022-08-22T23:00:00Z";
    private static final String UPDATED_DATEFFRH = "2022-08-22T23:00:00Z";

    private static final String DEFAULT_DECOPER = "AAAAAAAAAA";
    private static final String UPDATED_DECOPER = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String ENTITY_API_URL = "/api/rh-agents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RhAgentRepository rhAgentRepository;

    @Autowired
    private RhAgentMapper rhAgentMapper;

    @Autowired
    private MockMvc restRhAgentMockMvc;

    private RhAgent rhAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RhAgent createEntity() {
        RhAgent rhAgent = new RhAgent()
            .matric(DEFAULT_MATRIC)
            .decjour(DEFAULT_DECJOUR)
            .dat_eff_rh(DEFAULT_DATEFFRH)
            .decoper(DEFAULT_DECOPER)
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC);
        return rhAgent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RhAgent createUpdatedEntity() {
        RhAgent rhAgent = new RhAgent()
            .matric(UPDATED_MATRIC)
            .decjour(UPDATED_DECJOUR)
            .dat_eff_rh(UPDATED_DATEFFRH)
            .decoper(UPDATED_DECOPER)
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC);
        return rhAgent;
    }

    @BeforeEach
    public void initTest() {
        rhAgentRepository.deleteAll();
        rhAgent = createEntity();
    }

    @Test
    void createRhAgent() throws Exception {
        int databaseSizeBeforeCreate = rhAgentRepository.findAll().size();
        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);
        restRhAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rhAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeCreate + 1);
        RhAgent testRhAgent = rhAgentList.get(rhAgentList.size() - 1);
        assertThat(testRhAgent.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testRhAgent.getDecjour()).isEqualTo(DEFAULT_DECJOUR);
        assertThat(testRhAgent.getDat_eff_rh()).isEqualTo(DEFAULT_DATEFFRH);
        assertThat(testRhAgent.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testRhAgent.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testRhAgent.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
    }

    @Test
    void createRhAgentWithExistingId() throws Exception {
        // Create the RhAgent with an existing ID
        rhAgent.setId("existing_id");
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        int databaseSizeBeforeCreate = rhAgentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRhAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rhAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllRhAgents() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        // Get all the rhAgentList
        restRhAgentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rhAgent.getId())))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].decjour").value(hasItem(DEFAULT_DECJOUR)))
            .andExpect(jsonPath("$.[*].dateffrh").value(hasItem(DEFAULT_DATEFFRH.toString())))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)));
    }

    @Test
    void getRhAgent() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        // Get the rhAgent
        restRhAgentMockMvc
            .perform(get(ENTITY_API_URL_ID, rhAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rhAgent.getId()))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.decjour").value(DEFAULT_DECJOUR))
            .andExpect(jsonPath("$.dateffrh").value(DEFAULT_DATEFFRH.toString()))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC));
    }

    @Test
    void getNonExistingRhAgent() throws Exception {
        // Get the rhAgent
        restRhAgentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRhAgent() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();

        // Update the rhAgent
        RhAgent updatedRhAgent = rhAgentRepository.findById(rhAgent.getId()).get();
        updatedRhAgent
            .matric(UPDATED_MATRIC)
            .decjour(UPDATED_DECJOUR)
            .dat_eff_rh(UPDATED_DATEFFRH)
            .decoper(UPDATED_DECOPER)
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC);
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(updatedRhAgent);

        restRhAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rhAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isOk());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
        RhAgent testRhAgent = rhAgentList.get(rhAgentList.size() - 1);
        assertThat(testRhAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testRhAgent.getDecjour()).isEqualTo(UPDATED_DECJOUR);
        assertThat(testRhAgent.getDat_eff_rh()).isEqualTo(UPDATED_DATEFFRH);
        assertThat(testRhAgent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testRhAgent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testRhAgent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
    }

    @Test
    void putNonExistingRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rhAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rhAgentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRhAgentWithPatch() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();

        // Update the rhAgent using partial update
        RhAgent partialUpdatedRhAgent = new RhAgent();
        partialUpdatedRhAgent.setId(rhAgent.getId());

        partialUpdatedRhAgent.matric(UPDATED_MATRIC).decjour(UPDATED_DECJOUR).dat_eff_rh(UPDATED_DATEFFRH).decoper(UPDATED_DECOPER);

        restRhAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRhAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRhAgent))
            )
            .andExpect(status().isOk());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
        RhAgent testRhAgent = rhAgentList.get(rhAgentList.size() - 1);
        assertThat(testRhAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testRhAgent.getDecjour()).isEqualTo(UPDATED_DECJOUR);
        assertThat(testRhAgent.getDat_eff_rh()).isEqualTo(UPDATED_DATEFFRH);
        assertThat(testRhAgent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testRhAgent.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testRhAgent.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
    }

    @Test
    void fullUpdateRhAgentWithPatch() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();

        // Update the rhAgent using partial update
        RhAgent partialUpdatedRhAgent = new RhAgent();
        partialUpdatedRhAgent.setId(rhAgent.getId());

        partialUpdatedRhAgent
            .matric(UPDATED_MATRIC)
            .decjour(UPDATED_DECJOUR)
            .dat_eff_rh(UPDATED_DATEFFRH)
            .decoper(UPDATED_DECOPER)
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC);

        restRhAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRhAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRhAgent))
            )
            .andExpect(status().isOk());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
        RhAgent testRhAgent = rhAgentList.get(rhAgentList.size() - 1);
        assertThat(testRhAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testRhAgent.getDecjour()).isEqualTo(UPDATED_DECJOUR);
        assertThat(testRhAgent.getDat_eff_rh()).isEqualTo(UPDATED_DATEFFRH);
        assertThat(testRhAgent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testRhAgent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testRhAgent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
    }

    @Test
    void patchNonExistingRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rhAgentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRhAgent() throws Exception {
        int databaseSizeBeforeUpdate = rhAgentRepository.findAll().size();
        rhAgent.setId(UUID.randomUUID().toString());

        // Create the RhAgent
        RhAgentDTO rhAgentDTO = rhAgentMapper.toDto(rhAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRhAgentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rhAgentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RhAgent in the database
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRhAgent() throws Exception {
        // Initialize the database
        rhAgentRepository.save(rhAgent);

        int databaseSizeBeforeDelete = rhAgentRepository.findAll().size();

        // Delete the rhAgent
        restRhAgentMockMvc
            .perform(delete(ENTITY_API_URL_ID, rhAgent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RhAgent> rhAgentList = rhAgentRepository.findAll();
        assertThat(rhAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
