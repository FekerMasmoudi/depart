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
import tn.soretras.depart.domain.ExternalApi;
import tn.soretras.depart.repository.ExternalApiRepository;
import tn.soretras.depart.service.dto.ExternalApiDTO;
import tn.soretras.depart.service.mapper.ExternalApiMapper;

/**
 * Integration tests for the {@link ExternalApiResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExternalApiResourceIT {

    private static final String DEFAULT_IDM = "AAAAAAAAAA";
    private static final String UPDATED_IDM = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDSCHEMA = 1;
    private static final Integer UPDATED_IDSCHEMA = 2;

    private static final LocalDate DEFAULT_DATECREATEDT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATECREATEDT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LASTUPDATEDM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTUPDATEDM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ORIGIN = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_TEMPLATEID = 1;
    private static final Integer UPDATED_TEMPLATEID = 2;

    private static final Integer DEFAULT_IDMODULE = 1;
    private static final Integer UPDATED_IDMODULE = 2;

    private static final String DEFAULT_URITEMPLATE = "AAAAAAAAAA";
    private static final String UPDATED_URITEMPLATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Integer DEFAULT_SCHEMAIDT = 1;
    private static final Integer UPDATED_SCHEMAIDT = 2;

    private static final LocalDate DEFAULT_CREATEDATET = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATEDATET = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LASTUPDATETE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTUPDATETE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ENTITY = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY = "BBBBBBBBBB";

    private static final String DEFAULT_PARAMETRE = "AAAAAAAAAA";
    private static final String UPDATED_PARAMETRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_COUNTROWSREQ = 1;
    private static final Integer UPDATED_COUNTROWSREQ = 2;

    private static final Integer DEFAULT_COUNTROWSRES = 1;
    private static final Integer UPDATED_COUNTROWSRES = 2;

    private static final String DEFAULT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_EMERGENCYCODE = "AAAAAAAAAA";
    private static final String UPDATED_EMERGENCYCODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SATAUSGETAPI = false;
    private static final Boolean UPDATED_SATAUSGETAPI = true;

    private static final String ENTITY_API_URL = "/api/external-apis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ExternalApiRepository externalApiRepository;

    @Autowired
    private ExternalApiMapper externalApiMapper;

    @Autowired
    private MockMvc restExternalApiMockMvc;

    private ExternalApi externalApi;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExternalApi createEntity() {
        ExternalApi externalApi = new ExternalApi()
            .idm(DEFAULT_IDM)
            .name(DEFAULT_NAME)
            .status(DEFAULT_STATUS)
            .comments(DEFAULT_COMMENTS)
            .idschema(DEFAULT_IDSCHEMA)
            .datecreatedt(DEFAULT_DATECREATEDT)
            .lastupdatedm(DEFAULT_LASTUPDATEDM)
            .origin(DEFAULT_ORIGIN)
            .templateid(DEFAULT_TEMPLATEID)
            .idmodule(DEFAULT_IDMODULE)
            .uritemplate(DEFAULT_URITEMPLATE)
            .priority(DEFAULT_PRIORITY)
            .schemaidt(DEFAULT_SCHEMAIDT)
            .createdatet(DEFAULT_CREATEDATET)
            .lastupdatete(DEFAULT_LASTUPDATETE)
            .entity(DEFAULT_ENTITY)
            .parametre(DEFAULT_PARAMETRE)
            .countrowsreq(DEFAULT_COUNTROWSREQ)
            .countrowsres(DEFAULT_COUNTROWSRES)
            .frequency(DEFAULT_FREQUENCY)
            .emergencycode(DEFAULT_EMERGENCYCODE)
            .satausgetapi(DEFAULT_SATAUSGETAPI);
        return externalApi;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExternalApi createUpdatedEntity() {
        ExternalApi externalApi = new ExternalApi()
            .idm(UPDATED_IDM)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .idschema(UPDATED_IDSCHEMA)
            .datecreatedt(UPDATED_DATECREATEDT)
            .lastupdatedm(UPDATED_LASTUPDATEDM)
            .origin(UPDATED_ORIGIN)
            .templateid(UPDATED_TEMPLATEID)
            .idmodule(UPDATED_IDMODULE)
            .uritemplate(UPDATED_URITEMPLATE)
            .priority(UPDATED_PRIORITY)
            .schemaidt(UPDATED_SCHEMAIDT)
            .createdatet(UPDATED_CREATEDATET)
            .lastupdatete(UPDATED_LASTUPDATETE)
            .entity(UPDATED_ENTITY)
            .parametre(UPDATED_PARAMETRE)
            .countrowsreq(UPDATED_COUNTROWSREQ)
            .countrowsres(UPDATED_COUNTROWSRES)
            .frequency(UPDATED_FREQUENCY)
            .emergencycode(UPDATED_EMERGENCYCODE)
            .satausgetapi(UPDATED_SATAUSGETAPI);
        return externalApi;
    }

    @BeforeEach
    public void initTest() {
        externalApiRepository.deleteAll();
        externalApi = createEntity();
    }

    @Test
    void createExternalApi() throws Exception {
        int databaseSizeBeforeCreate = externalApiRepository.findAll().size();
        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);
        restExternalApiMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeCreate + 1);
        ExternalApi testExternalApi = externalApiList.get(externalApiList.size() - 1);
        assertThat(testExternalApi.getIdm()).isEqualTo(DEFAULT_IDM);
        assertThat(testExternalApi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExternalApi.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExternalApi.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testExternalApi.getIdschema()).isEqualTo(DEFAULT_IDSCHEMA);
        assertThat(testExternalApi.getDatecreatedt()).isEqualTo(DEFAULT_DATECREATEDT);
        assertThat(testExternalApi.getLastupdatedm()).isEqualTo(DEFAULT_LASTUPDATEDM);
        assertThat(testExternalApi.getOrigin()).isEqualTo(DEFAULT_ORIGIN);
        assertThat(testExternalApi.getTemplateid()).isEqualTo(DEFAULT_TEMPLATEID);
        assertThat(testExternalApi.getIdmodule()).isEqualTo(DEFAULT_IDMODULE);
        assertThat(testExternalApi.getUritemplate()).isEqualTo(DEFAULT_URITEMPLATE);
        assertThat(testExternalApi.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testExternalApi.getSchemaidt()).isEqualTo(DEFAULT_SCHEMAIDT);
        assertThat(testExternalApi.getCreatedatet()).isEqualTo(DEFAULT_CREATEDATET);
        assertThat(testExternalApi.getLastupdatete()).isEqualTo(DEFAULT_LASTUPDATETE);
        assertThat(testExternalApi.getEntity()).isEqualTo(DEFAULT_ENTITY);
        assertThat(testExternalApi.getParametre()).isEqualTo(DEFAULT_PARAMETRE);
        assertThat(testExternalApi.getCountrowsreq()).isEqualTo(DEFAULT_COUNTROWSREQ);
        assertThat(testExternalApi.getCountrowsres()).isEqualTo(DEFAULT_COUNTROWSRES);
        assertThat(testExternalApi.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testExternalApi.getEmergencycode()).isEqualTo(DEFAULT_EMERGENCYCODE);
        assertThat(testExternalApi.getSatausgetapi()).isEqualTo(DEFAULT_SATAUSGETAPI);
    }

    @Test
    void createExternalApiWithExistingId() throws Exception {
        // Create the ExternalApi with an existing ID
        externalApi.setId("existing_id");
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        int databaseSizeBeforeCreate = externalApiRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExternalApiMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllExternalApis() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        // Get all the externalApiList
        restExternalApiMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(externalApi.getId())))
            .andExpect(jsonPath("$.[*].idm").value(hasItem(DEFAULT_IDM)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].idschema").value(hasItem(DEFAULT_IDSCHEMA)))
            .andExpect(jsonPath("$.[*].datecreatedt").value(hasItem(DEFAULT_DATECREATEDT.toString())))
            .andExpect(jsonPath("$.[*].lastupdatedm").value(hasItem(DEFAULT_LASTUPDATEDM.toString())))
            .andExpect(jsonPath("$.[*].origin").value(hasItem(DEFAULT_ORIGIN)))
            .andExpect(jsonPath("$.[*].templateid").value(hasItem(DEFAULT_TEMPLATEID)))
            .andExpect(jsonPath("$.[*].idmodule").value(hasItem(DEFAULT_IDMODULE)))
            .andExpect(jsonPath("$.[*].uritemplate").value(hasItem(DEFAULT_URITEMPLATE)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].schemaidt").value(hasItem(DEFAULT_SCHEMAIDT)))
            .andExpect(jsonPath("$.[*].createdatet").value(hasItem(DEFAULT_CREATEDATET.toString())))
            .andExpect(jsonPath("$.[*].lastupdatete").value(hasItem(DEFAULT_LASTUPDATETE.toString())))
            .andExpect(jsonPath("$.[*].entity").value(hasItem(DEFAULT_ENTITY)))
            .andExpect(jsonPath("$.[*].parametre").value(hasItem(DEFAULT_PARAMETRE)))
            .andExpect(jsonPath("$.[*].countrowsreq").value(hasItem(DEFAULT_COUNTROWSREQ)))
            .andExpect(jsonPath("$.[*].countrowsres").value(hasItem(DEFAULT_COUNTROWSRES)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].emergencycode").value(hasItem(DEFAULT_EMERGENCYCODE)))
            .andExpect(jsonPath("$.[*].satausgetapi").value(hasItem(DEFAULT_SATAUSGETAPI.booleanValue())));
    }

    @Test
    void getExternalApi() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        // Get the externalApi
        restExternalApiMockMvc
            .perform(get(ENTITY_API_URL_ID, externalApi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(externalApi.getId()))
            .andExpect(jsonPath("$.idm").value(DEFAULT_IDM))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.idschema").value(DEFAULT_IDSCHEMA))
            .andExpect(jsonPath("$.datecreatedt").value(DEFAULT_DATECREATEDT.toString()))
            .andExpect(jsonPath("$.lastupdatedm").value(DEFAULT_LASTUPDATEDM.toString()))
            .andExpect(jsonPath("$.origin").value(DEFAULT_ORIGIN))
            .andExpect(jsonPath("$.templateid").value(DEFAULT_TEMPLATEID))
            .andExpect(jsonPath("$.idmodule").value(DEFAULT_IDMODULE))
            .andExpect(jsonPath("$.uritemplate").value(DEFAULT_URITEMPLATE))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.schemaidt").value(DEFAULT_SCHEMAIDT))
            .andExpect(jsonPath("$.createdatet").value(DEFAULT_CREATEDATET.toString()))
            .andExpect(jsonPath("$.lastupdatete").value(DEFAULT_LASTUPDATETE.toString()))
            .andExpect(jsonPath("$.entity").value(DEFAULT_ENTITY))
            .andExpect(jsonPath("$.parametre").value(DEFAULT_PARAMETRE))
            .andExpect(jsonPath("$.countrowsreq").value(DEFAULT_COUNTROWSREQ))
            .andExpect(jsonPath("$.countrowsres").value(DEFAULT_COUNTROWSRES))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.emergencycode").value(DEFAULT_EMERGENCYCODE))
            .andExpect(jsonPath("$.satausgetapi").value(DEFAULT_SATAUSGETAPI.booleanValue()));
    }

    @Test
    void getNonExistingExternalApi() throws Exception {
        // Get the externalApi
        restExternalApiMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingExternalApi() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();

        // Update the externalApi
        ExternalApi updatedExternalApi = externalApiRepository.findById(externalApi.getId()).get();
        updatedExternalApi
            .idm(UPDATED_IDM)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .idschema(UPDATED_IDSCHEMA)
            .datecreatedt(UPDATED_DATECREATEDT)
            .lastupdatedm(UPDATED_LASTUPDATEDM)
            .origin(UPDATED_ORIGIN)
            .templateid(UPDATED_TEMPLATEID)
            .idmodule(UPDATED_IDMODULE)
            .uritemplate(UPDATED_URITEMPLATE)
            .priority(UPDATED_PRIORITY)
            .schemaidt(UPDATED_SCHEMAIDT)
            .createdatet(UPDATED_CREATEDATET)
            .lastupdatete(UPDATED_LASTUPDATETE)
            .entity(UPDATED_ENTITY)
            .parametre(UPDATED_PARAMETRE)
            .countrowsreq(UPDATED_COUNTROWSREQ)
            .countrowsres(UPDATED_COUNTROWSRES)
            .frequency(UPDATED_FREQUENCY)
            .emergencycode(UPDATED_EMERGENCYCODE)
            .satausgetapi(UPDATED_SATAUSGETAPI);
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(updatedExternalApi);

        restExternalApiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, externalApiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isOk());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
        ExternalApi testExternalApi = externalApiList.get(externalApiList.size() - 1);
        assertThat(testExternalApi.getIdm()).isEqualTo(UPDATED_IDM);
        assertThat(testExternalApi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExternalApi.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExternalApi.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testExternalApi.getIdschema()).isEqualTo(UPDATED_IDSCHEMA);
        assertThat(testExternalApi.getDatecreatedt()).isEqualTo(UPDATED_DATECREATEDT);
        assertThat(testExternalApi.getLastupdatedm()).isEqualTo(UPDATED_LASTUPDATEDM);
        assertThat(testExternalApi.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testExternalApi.getTemplateid()).isEqualTo(UPDATED_TEMPLATEID);
        assertThat(testExternalApi.getIdmodule()).isEqualTo(UPDATED_IDMODULE);
        assertThat(testExternalApi.getUritemplate()).isEqualTo(UPDATED_URITEMPLATE);
        assertThat(testExternalApi.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testExternalApi.getSchemaidt()).isEqualTo(UPDATED_SCHEMAIDT);
        assertThat(testExternalApi.getCreatedatet()).isEqualTo(UPDATED_CREATEDATET);
        assertThat(testExternalApi.getLastupdatete()).isEqualTo(UPDATED_LASTUPDATETE);
        assertThat(testExternalApi.getEntity()).isEqualTo(UPDATED_ENTITY);
        assertThat(testExternalApi.getParametre()).isEqualTo(UPDATED_PARAMETRE);
        assertThat(testExternalApi.getCountrowsreq()).isEqualTo(UPDATED_COUNTROWSREQ);
        assertThat(testExternalApi.getCountrowsres()).isEqualTo(UPDATED_COUNTROWSRES);
        assertThat(testExternalApi.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testExternalApi.getEmergencycode()).isEqualTo(UPDATED_EMERGENCYCODE);
        assertThat(testExternalApi.getSatausgetapi()).isEqualTo(UPDATED_SATAUSGETAPI);
    }

    @Test
    void putNonExistingExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, externalApiDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(externalApiDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateExternalApiWithPatch() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();

        // Update the externalApi using partial update
        ExternalApi partialUpdatedExternalApi = new ExternalApi();
        partialUpdatedExternalApi.setId(externalApi.getId());

        partialUpdatedExternalApi
            .idschema(UPDATED_IDSCHEMA)
            .lastupdatedm(UPDATED_LASTUPDATEDM)
            .origin(UPDATED_ORIGIN)
            .idmodule(UPDATED_IDMODULE)
            .priority(UPDATED_PRIORITY)
            .createdatet(UPDATED_CREATEDATET)
            .lastupdatete(UPDATED_LASTUPDATETE)
            .countrowsreq(UPDATED_COUNTROWSREQ)
            .satausgetapi(UPDATED_SATAUSGETAPI);

        restExternalApiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExternalApi.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExternalApi))
            )
            .andExpect(status().isOk());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
        ExternalApi testExternalApi = externalApiList.get(externalApiList.size() - 1);
        assertThat(testExternalApi.getIdm()).isEqualTo(DEFAULT_IDM);
        assertThat(testExternalApi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testExternalApi.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testExternalApi.getComments()).isEqualTo(DEFAULT_COMMENTS);
        assertThat(testExternalApi.getIdschema()).isEqualTo(UPDATED_IDSCHEMA);
        assertThat(testExternalApi.getDatecreatedt()).isEqualTo(DEFAULT_DATECREATEDT);
        assertThat(testExternalApi.getLastupdatedm()).isEqualTo(UPDATED_LASTUPDATEDM);
        assertThat(testExternalApi.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testExternalApi.getTemplateid()).isEqualTo(DEFAULT_TEMPLATEID);
        assertThat(testExternalApi.getIdmodule()).isEqualTo(UPDATED_IDMODULE);
        assertThat(testExternalApi.getUritemplate()).isEqualTo(DEFAULT_URITEMPLATE);
        assertThat(testExternalApi.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testExternalApi.getSchemaidt()).isEqualTo(DEFAULT_SCHEMAIDT);
        assertThat(testExternalApi.getCreatedatet()).isEqualTo(UPDATED_CREATEDATET);
        assertThat(testExternalApi.getLastupdatete()).isEqualTo(UPDATED_LASTUPDATETE);
        assertThat(testExternalApi.getEntity()).isEqualTo(DEFAULT_ENTITY);
        assertThat(testExternalApi.getParametre()).isEqualTo(DEFAULT_PARAMETRE);
        assertThat(testExternalApi.getCountrowsreq()).isEqualTo(UPDATED_COUNTROWSREQ);
        assertThat(testExternalApi.getCountrowsres()).isEqualTo(DEFAULT_COUNTROWSRES);
        assertThat(testExternalApi.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testExternalApi.getEmergencycode()).isEqualTo(DEFAULT_EMERGENCYCODE);
        assertThat(testExternalApi.getSatausgetapi()).isEqualTo(UPDATED_SATAUSGETAPI);
    }

    @Test
    void fullUpdateExternalApiWithPatch() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();

        // Update the externalApi using partial update
        ExternalApi partialUpdatedExternalApi = new ExternalApi();
        partialUpdatedExternalApi.setId(externalApi.getId());

        partialUpdatedExternalApi
            .idm(UPDATED_IDM)
            .name(UPDATED_NAME)
            .status(UPDATED_STATUS)
            .comments(UPDATED_COMMENTS)
            .idschema(UPDATED_IDSCHEMA)
            .datecreatedt(UPDATED_DATECREATEDT)
            .lastupdatedm(UPDATED_LASTUPDATEDM)
            .origin(UPDATED_ORIGIN)
            .templateid(UPDATED_TEMPLATEID)
            .idmodule(UPDATED_IDMODULE)
            .uritemplate(UPDATED_URITEMPLATE)
            .priority(UPDATED_PRIORITY)
            .schemaidt(UPDATED_SCHEMAIDT)
            .createdatet(UPDATED_CREATEDATET)
            .lastupdatete(UPDATED_LASTUPDATETE)
            .entity(UPDATED_ENTITY)
            .parametre(UPDATED_PARAMETRE)
            .countrowsreq(UPDATED_COUNTROWSREQ)
            .countrowsres(UPDATED_COUNTROWSRES)
            .frequency(UPDATED_FREQUENCY)
            .emergencycode(UPDATED_EMERGENCYCODE)
            .satausgetapi(UPDATED_SATAUSGETAPI);

        restExternalApiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExternalApi.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExternalApi))
            )
            .andExpect(status().isOk());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
        ExternalApi testExternalApi = externalApiList.get(externalApiList.size() - 1);
        assertThat(testExternalApi.getIdm()).isEqualTo(UPDATED_IDM);
        assertThat(testExternalApi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testExternalApi.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testExternalApi.getComments()).isEqualTo(UPDATED_COMMENTS);
        assertThat(testExternalApi.getIdschema()).isEqualTo(UPDATED_IDSCHEMA);
        assertThat(testExternalApi.getDatecreatedt()).isEqualTo(UPDATED_DATECREATEDT);
        assertThat(testExternalApi.getLastupdatedm()).isEqualTo(UPDATED_LASTUPDATEDM);
        assertThat(testExternalApi.getOrigin()).isEqualTo(UPDATED_ORIGIN);
        assertThat(testExternalApi.getTemplateid()).isEqualTo(UPDATED_TEMPLATEID);
        assertThat(testExternalApi.getIdmodule()).isEqualTo(UPDATED_IDMODULE);
        assertThat(testExternalApi.getUritemplate()).isEqualTo(UPDATED_URITEMPLATE);
        assertThat(testExternalApi.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testExternalApi.getSchemaidt()).isEqualTo(UPDATED_SCHEMAIDT);
        assertThat(testExternalApi.getCreatedatet()).isEqualTo(UPDATED_CREATEDATET);
        assertThat(testExternalApi.getLastupdatete()).isEqualTo(UPDATED_LASTUPDATETE);
        assertThat(testExternalApi.getEntity()).isEqualTo(UPDATED_ENTITY);
        assertThat(testExternalApi.getParametre()).isEqualTo(UPDATED_PARAMETRE);
        assertThat(testExternalApi.getCountrowsreq()).isEqualTo(UPDATED_COUNTROWSREQ);
        assertThat(testExternalApi.getCountrowsres()).isEqualTo(UPDATED_COUNTROWSRES);
        assertThat(testExternalApi.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testExternalApi.getEmergencycode()).isEqualTo(UPDATED_EMERGENCYCODE);
        assertThat(testExternalApi.getSatausgetapi()).isEqualTo(UPDATED_SATAUSGETAPI);
    }

    @Test
    void patchNonExistingExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, externalApiDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamExternalApi() throws Exception {
        int databaseSizeBeforeUpdate = externalApiRepository.findAll().size();
        externalApi.setId(UUID.randomUUID().toString());

        // Create the ExternalApi
        ExternalApiDTO externalApiDTO = externalApiMapper.toDto(externalApi);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExternalApiMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(externalApiDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExternalApi in the database
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteExternalApi() throws Exception {
        // Initialize the database
        externalApiRepository.save(externalApi);

        int databaseSizeBeforeDelete = externalApiRepository.findAll().size();

        // Delete the externalApi
        restExternalApiMockMvc
            .perform(delete(ENTITY_API_URL_ID, externalApi.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExternalApi> externalApiList = externalApiRepository.findAll();
        assertThat(externalApiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
