package tn.soretras.depart.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.soretras.depart.web.rest.TestUtil.sameInstant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
import tn.soretras.depart.domain.RotRserv;
import tn.soretras.depart.repository.RotRservRepository;
import tn.soretras.depart.service.dto.RotRservDTO;
import tn.soretras.depart.service.mapper.RotRservMapper;

/**
 * Integration tests for the {@link RotRservResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RotRservResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DEDATED = "2016-03-21T23:00:00Z";
    private static final String UPDATED_DEDATED = "2016-03-21T23:00:00Z";

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final String DEFAULT_HEURDEB = "2016-03-01T07:00:00Z";
    private static final String UPDATED_HEURDEB = "2016-03-01T07:00:00Z";

    private static final String DEFAULT_HEURFIN = "2016-03-01T12:05:00Z";
    private static final String UPDATED_HEURFIN = "2016-03-01T12:05:00Z";

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String DEFAULT_LIEEDEB = "2016-03-01T12:05:00Z";
    private static final String UPDATED_LIEEDEB = "2016-03-01T12:05:00Z";

    private static final String DEFAULT_LIEEFIN = "2016-03-01T12:05:00Z";
    private static final String UPDATED_LIEEFIN = "2016-03-01T12:05:00Z";

    private static final String DEFAULT_PROGRAM = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_CD_1 = 1;
    private static final Integer UPDATED_CD_1 = 2;

    private static final Integer DEFAULT_CD_2 = 1;
    private static final Integer UPDATED_CD_2 = 2;

    private static final Integer DEFAULT_CD_3 = 1;
    private static final Integer UPDATED_CD_3 = 2;

    private static final String DEFAULT_ANNUL = "AAAAAAAAAA";
    private static final String UPDATED_ANNUL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/rot-rservs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RotRservRepository rotRservRepository;

    @Autowired
    private RotRservMapper rotRservMapper;

    @Autowired
    private MockMvc restRotRservMockMvc;

    private RotRserv rotRserv;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RotRserv createEntity() {
        RotRserv rotRserv = new RotRserv()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .dedated(DEFAULT_DEDATED)
            .matric(DEFAULT_MATRIC)
            .heur_deb(DEFAULT_HEURDEB)
            .heur_fin(DEFAULT_HEURFIN)
            .statut(DEFAULT_STATUT)
            .liee_deb(DEFAULT_LIEEDEB)
            .liee_fin(DEFAULT_LIEEFIN)
            .program(DEFAULT_PROGRAM)
            .cd1(DEFAULT_CD_1)
            .cd2(DEFAULT_CD_2)
            .cd3(DEFAULT_CD_3)
            .annul(DEFAULT_ANNUL);
        return rotRserv;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RotRserv createUpdatedEntity() {
        RotRserv rotRserv = new RotRserv()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .matric(UPDATED_MATRIC)
            .heur_deb(UPDATED_HEURDEB)
            .heur_fin(UPDATED_HEURFIN)
            .statut(UPDATED_STATUT)
            .liee_deb(UPDATED_LIEEDEB)
            .liee_fin(UPDATED_LIEEFIN)
            .program(UPDATED_PROGRAM)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .annul(UPDATED_ANNUL);
        return rotRserv;
    }

    @BeforeEach
    public void initTest() {
        rotRservRepository.deleteAll();
        rotRserv = createEntity();
    }

    @Test
    void createRotRserv() throws Exception {
        int databaseSizeBeforeCreate = rotRservRepository.findAll().size();
        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);
        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isCreated());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeCreate + 1);
        RotRserv testRotRserv = rotRservList.get(rotRservList.size() - 1);
        assertThat(testRotRserv.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testRotRserv.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testRotRserv.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testRotRserv.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testRotRserv.getHeur_deb()).isEqualTo(DEFAULT_HEURDEB);
        assertThat(testRotRserv.getHeur_fin()).isEqualTo(DEFAULT_HEURFIN);
        assertThat(testRotRserv.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testRotRserv.getLiee_deb()).isEqualTo(DEFAULT_LIEEDEB);
        assertThat(testRotRserv.getLiee_fin()).isEqualTo(DEFAULT_LIEEFIN);
        assertThat(testRotRserv.getProgram()).isEqualTo(DEFAULT_PROGRAM);
        assertThat(testRotRserv.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testRotRserv.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testRotRserv.getCd3()).isEqualTo(DEFAULT_CD_3);
        assertThat(testRotRserv.getAnnul()).isEqualTo(DEFAULT_ANNUL);
    }

    @Test
    void createRotRservWithExistingId() throws Exception {
        // Create the RotRserv with an existing ID
        rotRserv.setId("existing_id");
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        int databaseSizeBeforeCreate = rotRservRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setDeccent(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setDecagenc(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDedatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setDedated(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMatricIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setMatric(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkHeurdebIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setHeur_deb(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkHeurfinIsRequired() throws Exception {
        int databaseSizeBeforeTest = rotRservRepository.findAll().size();
        // set the field null
        rotRserv.setHeur_fin(null);

        // Create the RotRserv, which fails.
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        restRotRservMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isBadRequest());

        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRotRservs() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        // Get all the rotRservList
        restRotRservMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rotRserv.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].heurdeb").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.[*].heurfin").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].lieedeb").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.[*].lieefin").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.[*].program").value(hasItem(DEFAULT_PROGRAM)))
            .andExpect(jsonPath("$.[*].cd1").value(hasItem(DEFAULT_CD_1)))
            .andExpect(jsonPath("$.[*].cd2").value(hasItem(DEFAULT_CD_2)))
            .andExpect(jsonPath("$.[*].cd3").value(hasItem(DEFAULT_CD_3)))
            .andExpect(jsonPath("$.[*].annul").value(hasItem(DEFAULT_ANNUL)));
    }

    @Test
    void getRotRserv() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        // Get the rotRserv
        restRotRservMockMvc
            .perform(get(ENTITY_API_URL_ID, rotRserv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rotRserv.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.heurdeb").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.heurfin").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.lieedeb").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.lieefin").value("2016-03-01T12:05:00Z"))
            .andExpect(jsonPath("$.program").value(DEFAULT_PROGRAM))
            .andExpect(jsonPath("$.cd1").value(DEFAULT_CD_1))
            .andExpect(jsonPath("$.cd2").value(DEFAULT_CD_2))
            .andExpect(jsonPath("$.cd3").value(DEFAULT_CD_3))
            .andExpect(jsonPath("$.annul").value(DEFAULT_ANNUL));
    }

    @Test
    void getNonExistingRotRserv() throws Exception {
        // Get the rotRserv
        restRotRservMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRotRserv() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();

        // Update the rotRserv
        RotRserv updatedRotRserv = rotRservRepository.findById(rotRserv.getId()).get();
        updatedRotRserv
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .matric(UPDATED_MATRIC)
            .heur_deb(UPDATED_HEURDEB)
            .heur_fin(UPDATED_HEURFIN)
            .statut(UPDATED_STATUT)
            .liee_deb(UPDATED_LIEEDEB)
            .liee_fin(UPDATED_LIEEFIN)
            .program(UPDATED_PROGRAM)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .annul(UPDATED_ANNUL);
        RotRservDTO rotRservDTO = rotRservMapper.toDto(updatedRotRserv);

        restRotRservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rotRservDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isOk());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
        RotRserv testRotRserv = rotRservList.get(rotRservList.size() - 1);
        assertThat(testRotRserv.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testRotRserv.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testRotRserv.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testRotRserv.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testRotRserv.getHeur_deb()).isEqualTo(UPDATED_HEURDEB);
        assertThat(testRotRserv.getHeur_fin()).isEqualTo(UPDATED_HEURFIN);
        assertThat(testRotRserv.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testRotRserv.getLiee_deb()).isEqualTo(UPDATED_LIEEDEB);
        assertThat(testRotRserv.getLiee_fin()).isEqualTo(UPDATED_LIEEFIN);
        assertThat(testRotRserv.getProgram()).isEqualTo(UPDATED_PROGRAM);
        assertThat(testRotRserv.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testRotRserv.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testRotRserv.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testRotRserv.getAnnul()).isEqualTo(UPDATED_ANNUL);
    }

    @Test
    void putNonExistingRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rotRservDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rotRservDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRotRservWithPatch() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();

        // Update the rotRserv using partial update
        RotRserv partialUpdatedRotRserv = new RotRserv();
        partialUpdatedRotRserv.setId(rotRserv.getId());

        partialUpdatedRotRserv
            .deccent(UPDATED_DECCENT)
            .dedated(UPDATED_DEDATED)
            .liee_deb(UPDATED_LIEEDEB)
            .liee_fin(UPDATED_LIEEFIN)
            .program(UPDATED_PROGRAM)
            .cd1(UPDATED_CD_1)
            .cd3(UPDATED_CD_3);

        restRotRservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRotRserv.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRotRserv))
            )
            .andExpect(status().isOk());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
        RotRserv testRotRserv = rotRservList.get(rotRservList.size() - 1);
        assertThat(testRotRserv.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testRotRserv.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testRotRserv.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testRotRserv.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testRotRserv.getHeur_deb()).isEqualTo(DEFAULT_HEURDEB);
        assertThat(testRotRserv.getHeur_fin()).isEqualTo(DEFAULT_HEURFIN);
        assertThat(testRotRserv.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testRotRserv.getLiee_deb()).isEqualTo(UPDATED_LIEEDEB);
        assertThat(testRotRserv.getLiee_fin()).isEqualTo(UPDATED_LIEEFIN);
        assertThat(testRotRserv.getProgram()).isEqualTo(UPDATED_PROGRAM);
        assertThat(testRotRserv.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testRotRserv.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testRotRserv.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testRotRserv.getAnnul()).isEqualTo(DEFAULT_ANNUL);
    }

    @Test
    void fullUpdateRotRservWithPatch() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();

        // Update the rotRserv using partial update
        RotRserv partialUpdatedRotRserv = new RotRserv();
        partialUpdatedRotRserv.setId(rotRserv.getId());

        partialUpdatedRotRserv
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .matric(UPDATED_MATRIC)
            .heur_deb(UPDATED_HEURDEB)
            .heur_fin(UPDATED_HEURFIN)
            .statut(UPDATED_STATUT)
            .liee_deb(UPDATED_LIEEDEB)
            .liee_fin(UPDATED_LIEEFIN)
            .program(UPDATED_PROGRAM)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .annul(UPDATED_ANNUL);

        restRotRservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRotRserv.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRotRserv))
            )
            .andExpect(status().isOk());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
        RotRserv testRotRserv = rotRservList.get(rotRservList.size() - 1);
        assertThat(testRotRserv.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testRotRserv.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testRotRserv.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testRotRserv.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testRotRserv.getHeur_deb()).isEqualTo(UPDATED_HEURDEB);
        assertThat(testRotRserv.getHeur_fin()).isEqualTo(UPDATED_HEURFIN);
        assertThat(testRotRserv.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testRotRserv.getLiee_deb()).isEqualTo(UPDATED_LIEEDEB);
        assertThat(testRotRserv.getLiee_fin()).isEqualTo(UPDATED_LIEEFIN);
        assertThat(testRotRserv.getProgram()).isEqualTo(UPDATED_PROGRAM);
        assertThat(testRotRserv.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testRotRserv.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testRotRserv.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testRotRserv.getAnnul()).isEqualTo(UPDATED_ANNUL);
    }

    @Test
    void patchNonExistingRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rotRservDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRotRserv() throws Exception {
        int databaseSizeBeforeUpdate = rotRservRepository.findAll().size();
        rotRserv.setId(UUID.randomUUID().toString());

        // Create the RotRserv
        RotRservDTO rotRservDTO = rotRservMapper.toDto(rotRserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRotRservMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rotRservDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RotRserv in the database
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRotRserv() throws Exception {
        // Initialize the database
        rotRserv.setId(UUID.randomUUID().toString());
        rotRservRepository.save(rotRserv);

        int databaseSizeBeforeDelete = rotRservRepository.findAll().size();

        // Delete the rotRserv
        restRotRservMockMvc
            .perform(delete(ENTITY_API_URL_ID, rotRserv.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RotRserv> rotRservList = rotRservRepository.findAll();
        assertThat(rotRservList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
