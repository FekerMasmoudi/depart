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
import tn.soretras.depart.domain.AffecAgent;
import tn.soretras.depart.repository.AffecAgentRepository;
import tn.soretras.depart.service.dto.AffecAgentDTO;
import tn.soretras.depart.service.mapper.AffecAgentMapper;

/**
 * Integration tests for the {@link AffecAgentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AffecAgentResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final Integer DEFAULT_DECSERV = 1;
    private static final Integer UPDATED_DECSERV = 2;

    private static final String DEFAULT_DECOPER = "AAAAAAAAAA";
    private static final String UPDATED_DECOPER = "BBBBBBBBBB";

    private static final String DEFAULT_DECSEAN = "AAAAAAAAAA";
    private static final String UPDATED_DECSEAN = "BBBBBBBBBB";

    private static final String DEFAULT_CDSOCIE = "AAAAAAAAAA";
    private static final String UPDATED_CDSOCIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECEXER = 1;
    private static final Integer UPDATED_DECEXER = 2;

    private static final Integer DEFAULT_CDMOIS = 1;
    private static final Integer UPDATED_CDMOIS = 2;

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final Integer DEFAULT_MATRIC_2 = 1;
    private static final Integer UPDATED_MATRIC_2 = 2;

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/affec-agents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AffecAgentRepository affecAgentRepository;

    @Autowired
    private AffecAgentMapper affecAgentMapper;

    @Autowired
    private MockMvc restAffecAgentMockMvc;

    private AffecAgent affecAgent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AffecAgent createEntity() {
        AffecAgent affecAgent = new AffecAgent()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .decserv(DEFAULT_DECSERV)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .cdsocie(DEFAULT_CDSOCIE)
            .decexer(DEFAULT_DECEXER)
            .cdmois(DEFAULT_CDMOIS)
            .matric(DEFAULT_MATRIC)
            .matric2(DEFAULT_MATRIC_2)
            .cdmac(DEFAULT_CDMAC);
        return affecAgent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AffecAgent createUpdatedEntity() {
        AffecAgent affecAgent = new AffecAgent()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .cdmois(UPDATED_CDMOIS)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);
        return affecAgent;
    }

    @BeforeEach
    public void initTest() {
        affecAgentRepository.deleteAll();
        affecAgent = createEntity();
    }

    @Test
    void createAffecAgent() throws Exception {
        int databaseSizeBeforeCreate = affecAgentRepository.findAll().size();
        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);
        restAffecAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affecAgentDTO)))
            .andExpect(status().isCreated());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeCreate + 1);
        AffecAgent testAffecAgent = affecAgentList.get(affecAgentList.size() - 1);
        assertThat(testAffecAgent.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testAffecAgent.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testAffecAgent.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testAffecAgent.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testAffecAgent.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testAffecAgent.getCdsocie()).isEqualTo(DEFAULT_CDSOCIE);
        assertThat(testAffecAgent.getDecexer()).isEqualTo(DEFAULT_DECEXER);
        assertThat(testAffecAgent.getCdmois()).isEqualTo(DEFAULT_CDMOIS);
        assertThat(testAffecAgent.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testAffecAgent.getMatric2()).isEqualTo(DEFAULT_MATRIC_2);
        assertThat(testAffecAgent.getCdmac()).isEqualTo(DEFAULT_CDMAC);
    }

    @Test
    void createAffecAgentWithExistingId() throws Exception {
        // Create the AffecAgent with an existing ID
        affecAgent.setId("existing_id");
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        int databaseSizeBeforeCreate = affecAgentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffecAgentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affecAgentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllAffecAgents() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        // Get all the affecAgentList
        restAffecAgentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affecAgent.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].cdsocie").value(hasItem(DEFAULT_CDSOCIE)))
            .andExpect(jsonPath("$.[*].decexer").value(hasItem(DEFAULT_DECEXER)))
            .andExpect(jsonPath("$.[*].cdmois").value(hasItem(DEFAULT_CDMOIS)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].matric2").value(hasItem(DEFAULT_MATRIC_2)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)));
    }

    @Test
    void getAffecAgent() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        // Get the affecAgent
        restAffecAgentMockMvc
            .perform(get(ENTITY_API_URL_ID, affecAgent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affecAgent.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.cdsocie").value(DEFAULT_CDSOCIE))
            .andExpect(jsonPath("$.decexer").value(DEFAULT_DECEXER))
            .andExpect(jsonPath("$.cdmois").value(DEFAULT_CDMOIS))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.matric2").value(DEFAULT_MATRIC_2))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC));
    }

    @Test
    void getNonExistingAffecAgent() throws Exception {
        // Get the affecAgent
        restAffecAgentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAffecAgent() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();

        // Update the affecAgent
        AffecAgent updatedAffecAgent = affecAgentRepository.findById(affecAgent.getId()).get();
        updatedAffecAgent
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .cdmois(UPDATED_CDMOIS)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(updatedAffecAgent);

        restAffecAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, affecAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isOk());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
        AffecAgent testAffecAgent = affecAgentList.get(affecAgentList.size() - 1);
        assertThat(testAffecAgent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAffecAgent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffecAgent.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testAffecAgent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testAffecAgent.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testAffecAgent.getCdsocie()).isEqualTo(UPDATED_CDSOCIE);
        assertThat(testAffecAgent.getDecexer()).isEqualTo(UPDATED_DECEXER);
        assertThat(testAffecAgent.getCdmois()).isEqualTo(UPDATED_CDMOIS);
        assertThat(testAffecAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testAffecAgent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffecAgent.getCdmac()).isEqualTo(UPDATED_CDMAC);
    }

    @Test
    void putNonExistingAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, affecAgentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affecAgentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAffecAgentWithPatch() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();

        // Update the affecAgent using partial update
        AffecAgent partialUpdatedAffecAgent = new AffecAgent();
        partialUpdatedAffecAgent.setId(affecAgent.getId());

        partialUpdatedAffecAgent
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decsean(UPDATED_DECSEAN)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);

        restAffecAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffecAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAffecAgent))
            )
            .andExpect(status().isOk());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
        AffecAgent testAffecAgent = affecAgentList.get(affecAgentList.size() - 1);
        assertThat(testAffecAgent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAffecAgent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffecAgent.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testAffecAgent.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testAffecAgent.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testAffecAgent.getCdsocie()).isEqualTo(UPDATED_CDSOCIE);
        assertThat(testAffecAgent.getDecexer()).isEqualTo(UPDATED_DECEXER);
        assertThat(testAffecAgent.getCdmois()).isEqualTo(DEFAULT_CDMOIS);
        assertThat(testAffecAgent.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testAffecAgent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffecAgent.getCdmac()).isEqualTo(UPDATED_CDMAC);
    }

    @Test
    void fullUpdateAffecAgentWithPatch() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();

        // Update the affecAgent using partial update
        AffecAgent partialUpdatedAffecAgent = new AffecAgent();
        partialUpdatedAffecAgent.setId(affecAgent.getId());

        partialUpdatedAffecAgent
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .cdmois(UPDATED_CDMOIS)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);

        restAffecAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffecAgent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAffecAgent))
            )
            .andExpect(status().isOk());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
        AffecAgent testAffecAgent = affecAgentList.get(affecAgentList.size() - 1);
        assertThat(testAffecAgent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAffecAgent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffecAgent.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testAffecAgent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testAffecAgent.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testAffecAgent.getCdsocie()).isEqualTo(UPDATED_CDSOCIE);
        assertThat(testAffecAgent.getDecexer()).isEqualTo(UPDATED_DECEXER);
        assertThat(testAffecAgent.getCdmois()).isEqualTo(UPDATED_CDMOIS);
        assertThat(testAffecAgent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testAffecAgent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffecAgent.getCdmac()).isEqualTo(UPDATED_CDMAC);
    }

    @Test
    void patchNonExistingAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, affecAgentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAffecAgent() throws Exception {
        int databaseSizeBeforeUpdate = affecAgentRepository.findAll().size();
        affecAgent.setId(UUID.randomUUID().toString());

        // Create the AffecAgent
        AffecAgentDTO affecAgentDTO = affecAgentMapper.toDto(affecAgent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffecAgentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(affecAgentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AffecAgent in the database
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAffecAgent() throws Exception {
        // Initialize the database
        affecAgentRepository.save(affecAgent);

        int databaseSizeBeforeDelete = affecAgentRepository.findAll().size();

        // Delete the affecAgent
        restAffecAgentMockMvc
            .perform(delete(ENTITY_API_URL_ID, affecAgent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AffecAgent> affecAgentList = affecAgentRepository.findAll();
        assertThat(affecAgentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
