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
import tn.soretras.depart.domain.FoncAgent;
import tn.soretras.depart.repository.FoncAgentRepository;
import tn.soretras.depart.service.dto.FoncAgentDTO;
import tn.soretras.depart.service.mapper.FoncAgentMapper;

/**
 * Integration tests for the {@link FoncAgentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FoncAgentResourceIT {

    private static final String DEFAULT_CDFONC = "AAAAAAAAAA";
    private static final String UPDATED_CDFONC = "BBBBBBBBBB";

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATEFF = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEFF = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VALIDE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fonc-agents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private FoncAgentRepository foncAgentRepository;

    @Autowired
    private FoncAgentMapper foncAgentMapper;

    @Autowired
    private MockMvc restFoncAgentMockMvc;

    private FoncAgent foncAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoncAgent createEntity() {
        FoncAgent foncAgent = new FoncAgent()
            .cdfonc(DEFAULT_CDFONC)
            .matric(DEFAULT_MATRIC)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .dateff(DEFAULT_DATEFF)
            .valide(DEFAULT_VALIDE);
        return foncAgent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FoncAgent createUpdatedEntity() {
        FoncAgent foncAgent = new FoncAgent()
            .cdfonc(UPDATED_CDFONC)
            .matric(UPDATED_MATRIC)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateff(UPDATED_DATEFF)
            .valide(UPDATED_VALIDE);
        return foncAgent;
    }

    @BeforeEach
    public void initTest() {
        foncAgentRepository.deleteAll();
        foncAgent = createEntity();
    }

    @Test
    void createFoncAgent() throws Exception {
        int databaseSizeBeforeCreate = foncAgentRepository.findAll().size();
        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);
        restFoncAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(foncAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeCreate + 1);
        FoncAgent testFoncAgent = foncAgentList.get(foncAgentList.size() - 1);
        assertThat(testFoncAgent.getCdfonc()).isEqualTo(DEFAULT_CDFONC);
        assertThat(testFoncAgent.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testFoncAgent.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testFoncAgent.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFoncAgent.getDateff()).isEqualTo(DEFAULT_DATEFF);
        assertThat(testFoncAgent.getValide()).isEqualTo(DEFAULT_VALIDE);
    }

    @Test
    void createFoncAgentWithExistingId() throws Exception {
        // Create the FoncAgent with an existing ID
        foncAgent.setId("existing_id");
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        int databaseSizeBeforeCreate = foncAgentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFoncAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(foncAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFoncAgents() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        // Get all the foncAgentList
        restFoncAgentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(foncAgent.getId())))
            .andExpect(jsonPath("$.[*].cdfonc").value(hasItem(DEFAULT_CDFONC)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].dateff").value(hasItem(DEFAULT_DATEFF.toString())))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE)));
    }

    @Test
    void getFoncAgent() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        // Get the foncAgent
        restFoncAgentMockMvc
            .perform(get(ENTITY_API_URL_ID, foncAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(foncAgent.getId()))
            .andExpect(jsonPath("$.cdfonc").value(DEFAULT_CDFONC))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.dateff").value(DEFAULT_DATEFF.toString()))
            .andExpect(jsonPath("$.valide").value(DEFAULT_VALIDE));
    }

    @Test
    void getNonExistingFoncAgent() throws Exception {
        // Get the foncAgent
        restFoncAgentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingFoncAgent() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();

        // Update the foncAgent
        FoncAgent updatedFoncAgent = foncAgentRepository.findById(foncAgent.getId()).get();
        updatedFoncAgent
            .cdfonc(UPDATED_CDFONC)
            .matric(UPDATED_MATRIC)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateff(UPDATED_DATEFF)
            .valide(UPDATED_VALIDE);
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(updatedFoncAgent);

        restFoncAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, foncAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isOk());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
        FoncAgent testFoncAgent = foncAgentList.get(foncAgentList.size() - 1);
        assertThat(testFoncAgent.getCdfonc()).isEqualTo(UPDATED_CDFONC);
        assertThat(testFoncAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testFoncAgent.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFoncAgent.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFoncAgent.getDateff()).isEqualTo(UPDATED_DATEFF);
        assertThat(testFoncAgent.getValide()).isEqualTo(UPDATED_VALIDE);
    }

    @Test
    void putNonExistingFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, foncAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(foncAgentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFoncAgentWithPatch() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();

        // Update the foncAgent using partial update
        FoncAgent partialUpdatedFoncAgent = new FoncAgent();
        partialUpdatedFoncAgent.setId(foncAgent.getId());

        partialUpdatedFoncAgent.matric(UPDATED_MATRIC).nom(UPDATED_NOM);

        restFoncAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFoncAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFoncAgent))
            )
            .andExpect(status().isOk());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
        FoncAgent testFoncAgent = foncAgentList.get(foncAgentList.size() - 1);
        assertThat(testFoncAgent.getCdfonc()).isEqualTo(DEFAULT_CDFONC);
        assertThat(testFoncAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testFoncAgent.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFoncAgent.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFoncAgent.getDateff()).isEqualTo(DEFAULT_DATEFF);
        assertThat(testFoncAgent.getValide()).isEqualTo(DEFAULT_VALIDE);
    }

    @Test
    void fullUpdateFoncAgentWithPatch() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();

        // Update the foncAgent using partial update
        FoncAgent partialUpdatedFoncAgent = new FoncAgent();
        partialUpdatedFoncAgent.setId(foncAgent.getId());

        partialUpdatedFoncAgent
            .cdfonc(UPDATED_CDFONC)
            .matric(UPDATED_MATRIC)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .dateff(UPDATED_DATEFF)
            .valide(UPDATED_VALIDE);

        restFoncAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFoncAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFoncAgent))
            )
            .andExpect(status().isOk());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
        FoncAgent testFoncAgent = foncAgentList.get(foncAgentList.size() - 1);
        assertThat(testFoncAgent.getCdfonc()).isEqualTo(UPDATED_CDFONC);
        assertThat(testFoncAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testFoncAgent.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testFoncAgent.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFoncAgent.getDateff()).isEqualTo(UPDATED_DATEFF);
        assertThat(testFoncAgent.getValide()).isEqualTo(UPDATED_VALIDE);
    }

    @Test
    void patchNonExistingFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, foncAgentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFoncAgent() throws Exception {
        int databaseSizeBeforeUpdate = foncAgentRepository.findAll().size();
        foncAgent.setId(UUID.randomUUID().toString());

        // Create the FoncAgent
        FoncAgentDTO foncAgentDTO = foncAgentMapper.toDto(foncAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFoncAgentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(foncAgentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FoncAgent in the database
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFoncAgent() throws Exception {
        // Initialize the database
        foncAgentRepository.save(foncAgent);

        int databaseSizeBeforeDelete = foncAgentRepository.findAll().size();

        // Delete the foncAgent
        restFoncAgentMockMvc
            .perform(delete(ENTITY_API_URL_ID, foncAgent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FoncAgent> foncAgentList = foncAgentRepository.findAll();
        assertThat(foncAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
