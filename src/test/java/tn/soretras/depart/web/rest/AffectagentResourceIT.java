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
import tn.soretras.depart.domain.Affectagent;
import tn.soretras.depart.repository.AffectagentRepository;
import tn.soretras.depart.service.dto.AffectagentDTO;
import tn.soretras.depart.service.mapper.AffectagentMapper;

/**
 * Integration tests for the {@link AffectagentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AffectagentResourceIT {

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

    private static final Integer DEFAULT_CDMOIS = 1;
    private static final Integer UPDATED_CDMOIS = 2;

    private static final String DEFAULT_CDSOCIE = "AAAAAAAAAA";
    private static final String UPDATED_CDSOCIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECEXER = 1;
    private static final Integer UPDATED_DECEXER = 2;

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final Integer DEFAULT_MATRIC_2 = 1;
    private static final Integer UPDATED_MATRIC_2 = 2;

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/affectagents";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private AffectagentRepository affectagentRepository;

    @Autowired
    private AffectagentMapper affectagentMapper;

    @Autowired
    private MockMvc restAffectagentMockMvc;

    private Affectagent affectagent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affectagent createEntity() {
        Affectagent affectagent = new Affectagent()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .decserv(DEFAULT_DECSERV)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .cdmois(DEFAULT_CDMOIS)
            .cdsocie(DEFAULT_CDSOCIE)
            .decexer(DEFAULT_DECEXER)
            .matric(DEFAULT_MATRIC)
            .matric2(DEFAULT_MATRIC_2)
            .cdmac(DEFAULT_CDMAC);
        return affectagent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Affectagent createUpdatedEntity() {
        Affectagent affectagent = new Affectagent()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdmois(UPDATED_CDMOIS)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);
        return affectagent;
    }

    @BeforeEach
    public void initTest() {
        affectagentRepository.deleteAll();
        affectagent = createEntity();
    }

    @Test
    void createAffectagent() throws Exception {
        int databaseSizeBeforeCreate = affectagentRepository.findAll().size();
        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);
        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeCreate + 1);
        Affectagent testAffectagent = affectagentList.get(affectagentList.size() - 1);
        assertThat(testAffectagent.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testAffectagent.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testAffectagent.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testAffectagent.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testAffectagent.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testAffectagent.getCdmois()).isEqualTo(DEFAULT_CDMOIS);
        assertThat(testAffectagent.getCdsocie()).isEqualTo(DEFAULT_CDSOCIE);
        assertThat(testAffectagent.getDecexer()).isEqualTo(DEFAULT_DECEXER);
        assertThat(testAffectagent.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testAffectagent.getMatric2()).isEqualTo(DEFAULT_MATRIC_2);
        assertThat(testAffectagent.getCdmac()).isEqualTo(DEFAULT_CDMAC);
    }

    @Test
    void createAffectagentWithExistingId() throws Exception {
        // Create the Affectagent with an existing ID
        affectagent.setId("existing_id");
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        int databaseSizeBeforeCreate = affectagentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDeccent(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDecagenc(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecservIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDecserv(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecoperIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDecoper(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecseanIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDecsean(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCdmoisIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setCdmois(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCdsocieIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setCdsocie(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecexerIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setDecexer(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMatricIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setMatric(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMatric2IsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setMatric2(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCdmacIsRequired() throws Exception {
        int databaseSizeBeforeTest = affectagentRepository.findAll().size();
        // set the field null
        affectagent.setCdmac(null);

        // Create the Affectagent, which fails.
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        restAffectagentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAffectagents() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        // Get all the affectagentList
        restAffectagentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(affectagent.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].cdmois").value(hasItem(DEFAULT_CDMOIS)))
            .andExpect(jsonPath("$.[*].cdsocie").value(hasItem(DEFAULT_CDSOCIE)))
            .andExpect(jsonPath("$.[*].decexer").value(hasItem(DEFAULT_DECEXER)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].matric2").value(hasItem(DEFAULT_MATRIC_2)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)));
    }

    @Test
    void getAffectagent() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        // Get the affectagent
        restAffectagentMockMvc
            .perform(get(ENTITY_API_URL_ID, affectagent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(affectagent.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.cdmois").value(DEFAULT_CDMOIS))
            .andExpect(jsonPath("$.cdsocie").value(DEFAULT_CDSOCIE))
            .andExpect(jsonPath("$.decexer").value(DEFAULT_DECEXER))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.matric2").value(DEFAULT_MATRIC_2))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC));
    }

    @Test
    void getNonExistingAffectagent() throws Exception {
        // Get the affectagent
        restAffectagentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingAffectagent() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();

        // Update the affectagent
        Affectagent updatedAffectagent = affectagentRepository.findById(affectagent.getId()).get();
        updatedAffectagent
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdmois(UPDATED_CDMOIS)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(updatedAffectagent);

        restAffectagentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, affectagentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isOk());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
        Affectagent testAffectagent = affectagentList.get(affectagentList.size() - 1);
        assertThat(testAffectagent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAffectagent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffectagent.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testAffectagent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testAffectagent.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testAffectagent.getCdmois()).isEqualTo(UPDATED_CDMOIS);
        assertThat(testAffectagent.getCdsocie()).isEqualTo(UPDATED_CDSOCIE);
        assertThat(testAffectagent.getDecexer()).isEqualTo(UPDATED_DECEXER);
        assertThat(testAffectagent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testAffectagent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffectagent.getCdmac()).isEqualTo(UPDATED_CDMAC);
    }

    @Test
    void putNonExistingAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, affectagentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(affectagentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAffectagentWithPatch() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();

        // Update the affectagent using partial update
        Affectagent partialUpdatedAffectagent = new Affectagent();
        partialUpdatedAffectagent.setId(affectagent.getId());

        partialUpdatedAffectagent
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .cdmois(UPDATED_CDMOIS)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2);

        restAffectagentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffectagent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAffectagent))
            )
            .andExpect(status().isOk());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
        Affectagent testAffectagent = affectagentList.get(affectagentList.size() - 1);
        assertThat(testAffectagent.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testAffectagent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffectagent.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testAffectagent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testAffectagent.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testAffectagent.getCdmois()).isEqualTo(UPDATED_CDMOIS);
        assertThat(testAffectagent.getCdsocie()).isEqualTo(DEFAULT_CDSOCIE);
        assertThat(testAffectagent.getDecexer()).isEqualTo(DEFAULT_DECEXER);
        assertThat(testAffectagent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testAffectagent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffectagent.getCdmac()).isEqualTo(DEFAULT_CDMAC);
    }

    @Test
    void fullUpdateAffectagentWithPatch() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();

        // Update the affectagent using partial update
        Affectagent partialUpdatedAffectagent = new Affectagent();
        partialUpdatedAffectagent.setId(affectagent.getId());

        partialUpdatedAffectagent
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .cdmois(UPDATED_CDMOIS)
            .cdsocie(UPDATED_CDSOCIE)
            .decexer(UPDATED_DECEXER)
            .matric(UPDATED_MATRIC)
            .matric2(UPDATED_MATRIC_2)
            .cdmac(UPDATED_CDMAC);

        restAffectagentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAffectagent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAffectagent))
            )
            .andExpect(status().isOk());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
        Affectagent testAffectagent = affectagentList.get(affectagentList.size() - 1);
        assertThat(testAffectagent.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testAffectagent.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testAffectagent.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testAffectagent.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testAffectagent.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testAffectagent.getCdmois()).isEqualTo(UPDATED_CDMOIS);
        assertThat(testAffectagent.getCdsocie()).isEqualTo(UPDATED_CDSOCIE);
        assertThat(testAffectagent.getDecexer()).isEqualTo(UPDATED_DECEXER);
        assertThat(testAffectagent.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testAffectagent.getMatric2()).isEqualTo(UPDATED_MATRIC_2);
        assertThat(testAffectagent.getCdmac()).isEqualTo(UPDATED_CDMAC);
    }

    @Test
    void patchNonExistingAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, affectagentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAffectagent() throws Exception {
        int databaseSizeBeforeUpdate = affectagentRepository.findAll().size();
        affectagent.setId(UUID.randomUUID().toString());

        // Create the Affectagent
        AffectagentDTO affectagentDTO = affectagentMapper.toDto(affectagent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAffectagentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(affectagentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Affectagent in the database
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAffectagent() throws Exception {
        // Initialize the database
        affectagentRepository.save(affectagent);

        int databaseSizeBeforeDelete = affectagentRepository.findAll().size();

        // Delete the affectagent
        restAffectagentMockMvc
            .perform(delete(ENTITY_API_URL_ID, affectagent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Affectagent> affectagentList = affectagentRepository.findAll();
        assertThat(affectagentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
