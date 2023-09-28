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
import tn.soretras.depart.domain.Agence;
import tn.soretras.depart.repository.AgenceRepository;
import tn.soretras.depart.service.dto.AgenceDTO;
import tn.soretras.depart.service.mapper.AgenceMapper;

/**
 * Integration tests for the {@link AgenceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AgenceResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DELAGENC = "AAAAAAAAAA";
    private static final String UPDATED_DELAGENC = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULTAGENC = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULTAGENC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/agences";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AgenceRepository agenceRepository;

    @Autowired
    private AgenceMapper agenceMapper;

    @Autowired
    private MockMvc restAgenceMockMvc;

    private Agence agence;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agence createEntity() {
        Agence agence = new Agence()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .delagenc(DEFAULT_DELAGENC)
            .default_agenc(DEFAULT_DEFAULTAGENC);
        return agence;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agence createUpdatedEntity() {
        Agence agence = new Agence()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .delagenc(UPDATED_DELAGENC)
            .default_agenc(UPDATED_DEFAULTAGENC);
        return agence;
    }

    @BeforeEach
    public void initTest() {
        agenceRepository.deleteAll();
        agence = createEntity();
    }

    @Test
    void createAgence() throws Exception {
        int databaseSizeBeforeCreate = agenceRepository.findAll().size();
        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);
        restAgenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isCreated());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeCreate + 1);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testAgence.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testAgence.getDelagenc()).isEqualTo(DEFAULT_DELAGENC);
        assertThat(testAgence.getDefault_agenc()).isEqualTo(DEFAULT_DEFAULTAGENC);
    }

    @Test
    void createAgenceWithExistingId() throws Exception {
        // Create the Agence with an existing ID
        agence.setId("existing_id");
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        int databaseSizeBeforeCreate = agenceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAgenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = agenceRepository.findAll().size();
        // set the field null
        agence.setDeccent(null);

        // Create the Agence, which fails.
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        restAgenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isBadRequest());

        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = agenceRepository.findAll().size();
        // set the field null
        agence.setDecagenc(null);

        // Create the Agence, which fails.
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        restAgenceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isBadRequest());

        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAgences() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        // Get all the agenceList
        restAgenceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agence.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].delagenc").value(hasItem(DEFAULT_DELAGENC)))
            .andExpect(jsonPath("$.[*].defaultagenc").value(hasItem(DEFAULT_DEFAULTAGENC)));
    }

    @Test
    void getAgence() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        // Get the agence
        restAgenceMockMvc
            .perform(get(ENTITY_API_URL_ID, agence.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agence.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.delagenc").value(DEFAULT_DELAGENC))
            .andExpect(jsonPath("$.defaultagenc").value(DEFAULT_DEFAULTAGENC));
    }

    @Test
    void getNonExistingAgence() throws Exception {
        // Get the agence
        restAgenceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAgence() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();

        // Update the agence
        Agence updatedAgence = agenceRepository.findById(agence.getId()).get();
        updatedAgence.deccent(UPDATED_DECCENT).decagenc(UPDATED_DECAGENC).delagenc(UPDATED_DELAGENC).default_agenc(UPDATED_DEFAULTAGENC);
        AgenceDTO agenceDTO = agenceMapper.toDto(updatedAgence);

        restAgenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, agenceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isOk());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAgence.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAgence.getDelagenc()).isEqualTo(UPDATED_DELAGENC);
        assertThat(testAgence.getDefault_agenc()).isEqualTo(UPDATED_DEFAULTAGENC);
    }

    @Test
    void putNonExistingAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, agenceDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(agenceDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAgenceWithPatch() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();

        // Update the agence using partial update
        Agence partialUpdatedAgence = new Agence();
        partialUpdatedAgence.setId(agence.getId());

        partialUpdatedAgence.delagenc(UPDATED_DELAGENC).default_agenc(UPDATED_DEFAULTAGENC);

        restAgenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAgence.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAgence))
            )
            .andExpect(status().isOk());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testAgence.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testAgence.getDelagenc()).isEqualTo(UPDATED_DELAGENC);
        assertThat(testAgence.getDefault_agenc()).isEqualTo(UPDATED_DEFAULTAGENC);
    }

    @Test
    void fullUpdateAgenceWithPatch() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();

        // Update the agence using partial update
        Agence partialUpdatedAgence = new Agence();
        partialUpdatedAgence.setId(agence.getId());

        partialUpdatedAgence
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .delagenc(UPDATED_DELAGENC)
            .default_agenc(UPDATED_DEFAULTAGENC);

        restAgenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAgence.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAgence))
            )
            .andExpect(status().isOk());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
        Agence testAgence = agenceList.get(agenceList.size() - 1);
        assertThat(testAgence.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAgence.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAgence.getDelagenc()).isEqualTo(UPDATED_DELAGENC);
        assertThat(testAgence.getDefault_agenc()).isEqualTo(UPDATED_DEFAULTAGENC);
    }

    @Test
    void patchNonExistingAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, agenceDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAgence() throws Exception {
        int databaseSizeBeforeUpdate = agenceRepository.findAll().size();
        agence.setId(UUID.randomUUID().toString());

        // Create the Agence
        AgenceDTO agenceDTO = agenceMapper.toDto(agence);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAgenceMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(agenceDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Agence in the database
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAgence() throws Exception {
        // Initialize the database
        agenceRepository.save(agence);

        int databaseSizeBeforeDelete = agenceRepository.findAll().size();

        // Delete the agence
        restAgenceMockMvc
            .perform(delete(ENTITY_API_URL_ID, agence.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Agence> agenceList = agenceRepository.findAll();
        assertThat(agenceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
