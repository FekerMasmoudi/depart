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
import tn.soretras.depart.domain.Depart;
import tn.soretras.depart.repository.DepartRepository;
import tn.soretras.depart.service.dto.DepartDTO;
import tn.soretras.depart.service.mapper.DepartMapper;

/**
 * Integration tests for the {@link DepartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DepartResourceIT {

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

    private static final LocalDate DEFAULT_DEDATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEDATED = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DENUMDP = 1;
    private static final Integer UPDATED_DENUMDP = 2;

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final Integer DEFAULT_MATRIC_1 = 1;
    private static final Integer UPDATED_MATRIC_1 = 2;

    private static final Integer DEFAULT_CDMAC = 1;
    private static final Integer UPDATED_CDMAC = 2;

    private static final ZonedDateTime DEFAULT_DEHEUPS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEHEUPS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEHEUFS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEHEUFS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_DENBRRO = 1;
    private static final Integer UPDATED_DENBRRO = 2;

    private static final ZonedDateTime DEFAULT_DEHEUAA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEHEUAA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEHEUDR = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEHEUDR = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEHEUPD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEHEUPD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEAMPLI = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEAMPLI = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_OBSIND = "AAAAAAAAAA";
    private static final String UPDATED_OBSIND = "BBBBBBBBBB";

    private static final String DEFAULT_VLDROUL = "AAAAAAAAAA";
    private static final String UPDATED_VLDROUL = "BBBBBBBBBB";

    private static final String DEFAULT_DEETAT = "AAAAAAAAAA";
    private static final String UPDATED_DEETAT = "BBBBBBBBBB";

    private static final String DEFAULT_DEANNUL = "AAAAAAAAAA";
    private static final String UPDATED_DEANNUL = "BBBBBBBBBB";

    private static final String DEFAULT_DECCLOT = "AAAAAAAAAA";
    private static final String UPDATED_DECCLOT = "BBBBBBBBBB";

    private static final String DEFAULT_EXECUTE = "AAAAAAAAAA";
    private static final String UPDATED_EXECUTE = "BBBBBBBBBB";

    private static final String DEFAULT_MOTIFA = "AAAAAAAAAA";
    private static final String UPDATED_MOTIFA = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERV = "AAAAAAAAAA";
    private static final String UPDATED_OBSERV = "BBBBBBBBBB";

    private static final Float DEFAULT_RECETTES = 1F;
    private static final Float UPDATED_RECETTES = 2F;

    private static final Integer DEFAULT_NBREVOY = 1;
    private static final Integer UPDATED_NBREVOY = 2;

    private static final Integer DEFAULT_DECMOTIFCH = 1;
    private static final Integer UPDATED_DECMOTIFCH = 2;

    private static final Integer DEFAULT_DECMOTIFRE = 1;
    private static final Integer UPDATED_DECMOTIFRE = 2;

    private static final Integer DEFAULT_CD_1 = 1;
    private static final Integer UPDATED_CD_1 = 2;

    private static final Integer DEFAULT_CD_2 = 1;
    private static final Integer UPDATED_CD_2 = 2;

    private static final Integer DEFAULT_CD_3 = 1;
    private static final Integer UPDATED_CD_3 = 2;

    private static final Integer DEFAULT_DECMOTIFCHA = 1;
    private static final Integer UPDATED_DECMOTIFCHA = 2;

    private static final Integer DEFAULT_DECMOTIFREA = 1;
    private static final Integer UPDATED_DECMOTIFREA = 2;

    private static final String ENTITY_API_URL = "/api/departs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DepartRepository departRepository;

    @Autowired
    private DepartMapper departMapper;

    @Autowired
    private MockMvc restDepartMockMvc;

    private Depart depart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depart createEntity() {
        Depart depart = new Depart()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .decserv(DEFAULT_DECSERV)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .dedated(DEFAULT_DEDATED)
            .denumdp(DEFAULT_DENUMDP)
            .matric(DEFAULT_MATRIC)
            .matric1(DEFAULT_MATRIC_1)
            .cdmac(DEFAULT_CDMAC)
            .deheups(DEFAULT_DEHEUPS)
            .deheufs(DEFAULT_DEHEUFS)
            .denbrro(DEFAULT_DENBRRO)
            .deheuaa(DEFAULT_DEHEUAA)
            .deheudr(DEFAULT_DEHEUDR)
            .deheupd(DEFAULT_DEHEUPD)
            .deampli(DEFAULT_DEAMPLI)
            .obsind(DEFAULT_OBSIND)
            .vldroul(DEFAULT_VLDROUL)
            .deetat(DEFAULT_DEETAT)
            .deannul(DEFAULT_DEANNUL)
            .decclot(DEFAULT_DECCLOT)
            .execute(DEFAULT_EXECUTE)
            .motifa(DEFAULT_MOTIFA)
            .observ(DEFAULT_OBSERV)
            .recettes(DEFAULT_RECETTES)
            .nbrevoy(DEFAULT_NBREVOY)
            .decmotifch(DEFAULT_DECMOTIFCH)
            .decmotifre(DEFAULT_DECMOTIFRE)
            .cd1(DEFAULT_CD_1)
            .cd2(DEFAULT_CD_2)
            .cd3(DEFAULT_CD_3)
            .decmotifcha(DEFAULT_DECMOTIFCHA)
            .decmotifrea(DEFAULT_DECMOTIFREA);
        return depart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depart createUpdatedEntity() {
        Depart depart = new Depart()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .deheups(UPDATED_DEHEUPS)
            .deheufs(UPDATED_DEHEUFS)
            .denbrro(UPDATED_DENBRRO)
            .deheuaa(UPDATED_DEHEUAA)
            .deheudr(UPDATED_DEHEUDR)
            .deheupd(UPDATED_DEHEUPD)
            .deampli(UPDATED_DEAMPLI)
            .obsind(UPDATED_OBSIND)
            .vldroul(UPDATED_VLDROUL)
            .deetat(UPDATED_DEETAT)
            .deannul(UPDATED_DEANNUL)
            .decclot(UPDATED_DECCLOT)
            .execute(UPDATED_EXECUTE)
            .motifa(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes(UPDATED_RECETTES)
            .nbrevoy(UPDATED_NBREVOY)
            .decmotifch(UPDATED_DECMOTIFCH)
            .decmotifre(UPDATED_DECMOTIFRE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA);
        return depart;
    }

    @BeforeEach
    public void initTest() {
        departRepository.deleteAll();
        depart = createEntity();
    }

    @Test
    void createDepart() throws Exception {
        int databaseSizeBeforeCreate = departRepository.findAll().size();
        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);
        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isCreated());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeCreate + 1);
        Depart testDepart = departList.get(departList.size() - 1);
        assertThat(testDepart.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testDepart.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testDepart.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testDepart.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testDepart.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testDepart.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testDepart.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testDepart.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testDepart.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testDepart.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testDepart.getDeheups()).isEqualTo(DEFAULT_DEHEUPS);
        assertThat(testDepart.getDeheufs()).isEqualTo(DEFAULT_DEHEUFS);
        assertThat(testDepart.getDenbrro()).isEqualTo(DEFAULT_DENBRRO);
        assertThat(testDepart.getDeheuaa()).isEqualTo(DEFAULT_DEHEUAA);
        assertThat(testDepart.getDeheudr()).isEqualTo(DEFAULT_DEHEUDR);
        assertThat(testDepart.getDeheupd()).isEqualTo(DEFAULT_DEHEUPD);
        assertThat(testDepart.getDeampli()).isEqualTo(DEFAULT_DEAMPLI);
        assertThat(testDepart.getObsind()).isEqualTo(DEFAULT_OBSIND);
        assertThat(testDepart.getVldroul()).isEqualTo(DEFAULT_VLDROUL);
        assertThat(testDepart.getDeetat()).isEqualTo(DEFAULT_DEETAT);
        assertThat(testDepart.getDeannul()).isEqualTo(DEFAULT_DEANNUL);
        assertThat(testDepart.getDecclot()).isEqualTo(DEFAULT_DECCLOT);
        assertThat(testDepart.getExecute()).isEqualTo(DEFAULT_EXECUTE);
        assertThat(testDepart.getMotifa()).isEqualTo(DEFAULT_MOTIFA);
        assertThat(testDepart.getObserv()).isEqualTo(DEFAULT_OBSERV);
        assertThat(testDepart.getRecettes()).isEqualTo(DEFAULT_RECETTES);
        assertThat(testDepart.getNbrevoy()).isEqualTo(DEFAULT_NBREVOY);
        assertThat(testDepart.getDecmotifch()).isEqualTo(DEFAULT_DECMOTIFCH);
        assertThat(testDepart.getDecmotifre()).isEqualTo(DEFAULT_DECMOTIFRE);
        assertThat(testDepart.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testDepart.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testDepart.getCd3()).isEqualTo(DEFAULT_CD_3);
        assertThat(testDepart.getDecmotifcha()).isEqualTo(DEFAULT_DECMOTIFCHA);
        assertThat(testDepart.getDecmotifrea()).isEqualTo(DEFAULT_DECMOTIFREA);
    }

    @Test
    void createDepartWithExistingId() throws Exception {
        // Create the Depart with an existing ID
        depart.setId("existing_id");
        DepartDTO departDTO = departMapper.toDto(depart);

        int databaseSizeBeforeCreate = departRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDeccent(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDecagenc(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecservIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDecserv(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecoperIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDecoper(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecseanIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDecsean(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDedatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDedated(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumdpIsRequired() throws Exception {
        int databaseSizeBeforeTest = departRepository.findAll().size();
        // set the field null
        depart.setDenumdp(null);

        // Create the Depart, which fails.
        DepartDTO departDTO = departMapper.toDto(depart);

        restDepartMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isBadRequest());

        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDeparts() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        // Get all the departList
        restDepartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depart.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].denumdp").value(hasItem(DEFAULT_DENUMDP)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].matric1").value(hasItem(DEFAULT_MATRIC_1)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].deheups").value(hasItem(sameInstant(DEFAULT_DEHEUPS))))
            .andExpect(jsonPath("$.[*].deheufs").value(hasItem(sameInstant(DEFAULT_DEHEUFS))))
            .andExpect(jsonPath("$.[*].denbrro").value(hasItem(DEFAULT_DENBRRO)))
            .andExpect(jsonPath("$.[*].deheuaa").value(hasItem(sameInstant(DEFAULT_DEHEUAA))))
            .andExpect(jsonPath("$.[*].deheudr").value(hasItem(sameInstant(DEFAULT_DEHEUDR))))
            .andExpect(jsonPath("$.[*].deheupd").value(hasItem(sameInstant(DEFAULT_DEHEUPD))))
            .andExpect(jsonPath("$.[*].deampli").value(hasItem(sameInstant(DEFAULT_DEAMPLI))))
            .andExpect(jsonPath("$.[*].obsind").value(hasItem(DEFAULT_OBSIND)))
            .andExpect(jsonPath("$.[*].vldroul").value(hasItem(DEFAULT_VLDROUL)))
            .andExpect(jsonPath("$.[*].deetat").value(hasItem(DEFAULT_DEETAT)))
            .andExpect(jsonPath("$.[*].deannul").value(hasItem(DEFAULT_DEANNUL)))
            .andExpect(jsonPath("$.[*].decclot").value(hasItem(DEFAULT_DECCLOT)))
            .andExpect(jsonPath("$.[*].execute").value(hasItem(DEFAULT_EXECUTE)))
            .andExpect(jsonPath("$.[*].motifa").value(hasItem(DEFAULT_MOTIFA)))
            .andExpect(jsonPath("$.[*].observ").value(hasItem(DEFAULT_OBSERV)))
            .andExpect(jsonPath("$.[*].recettes").value(hasItem(DEFAULT_RECETTES.doubleValue())))
            .andExpect(jsonPath("$.[*].nbrevoy").value(hasItem(DEFAULT_NBREVOY)))
            .andExpect(jsonPath("$.[*].decmotifch").value(hasItem(DEFAULT_DECMOTIFCH)))
            .andExpect(jsonPath("$.[*].decmotifre").value(hasItem(DEFAULT_DECMOTIFRE)))
            .andExpect(jsonPath("$.[*].cd1").value(hasItem(DEFAULT_CD_1)))
            .andExpect(jsonPath("$.[*].cd2").value(hasItem(DEFAULT_CD_2)))
            .andExpect(jsonPath("$.[*].cd3").value(hasItem(DEFAULT_CD_3)))
            .andExpect(jsonPath("$.[*].decmotifcha").value(hasItem(DEFAULT_DECMOTIFCHA)))
            .andExpect(jsonPath("$.[*].decmotifrea").value(hasItem(DEFAULT_DECMOTIFREA)));
    }

    @Test
    void getDepart() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        // Get the depart
        restDepartMockMvc
            .perform(get(ENTITY_API_URL_ID, depart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(depart.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.denumdp").value(DEFAULT_DENUMDP))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.matric1").value(DEFAULT_MATRIC_1))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.deheups").value(sameInstant(DEFAULT_DEHEUPS)))
            .andExpect(jsonPath("$.deheufs").value(sameInstant(DEFAULT_DEHEUFS)))
            .andExpect(jsonPath("$.denbrro").value(DEFAULT_DENBRRO))
            .andExpect(jsonPath("$.deheuaa").value(sameInstant(DEFAULT_DEHEUAA)))
            .andExpect(jsonPath("$.deheudr").value(sameInstant(DEFAULT_DEHEUDR)))
            .andExpect(jsonPath("$.deheupd").value(sameInstant(DEFAULT_DEHEUPD)))
            .andExpect(jsonPath("$.deampli").value(sameInstant(DEFAULT_DEAMPLI)))
            .andExpect(jsonPath("$.obsind").value(DEFAULT_OBSIND))
            .andExpect(jsonPath("$.vldroul").value(DEFAULT_VLDROUL))
            .andExpect(jsonPath("$.deetat").value(DEFAULT_DEETAT))
            .andExpect(jsonPath("$.deannul").value(DEFAULT_DEANNUL))
            .andExpect(jsonPath("$.decclot").value(DEFAULT_DECCLOT))
            .andExpect(jsonPath("$.execute").value(DEFAULT_EXECUTE))
            .andExpect(jsonPath("$.motifa").value(DEFAULT_MOTIFA))
            .andExpect(jsonPath("$.observ").value(DEFAULT_OBSERV))
            .andExpect(jsonPath("$.recettes").value(DEFAULT_RECETTES.doubleValue()))
            .andExpect(jsonPath("$.nbrevoy").value(DEFAULT_NBREVOY))
            .andExpect(jsonPath("$.decmotifch").value(DEFAULT_DECMOTIFCH))
            .andExpect(jsonPath("$.decmotifre").value(DEFAULT_DECMOTIFRE))
            .andExpect(jsonPath("$.cd1").value(DEFAULT_CD_1))
            .andExpect(jsonPath("$.cd2").value(DEFAULT_CD_2))
            .andExpect(jsonPath("$.cd3").value(DEFAULT_CD_3))
            .andExpect(jsonPath("$.decmotifcha").value(DEFAULT_DECMOTIFCHA))
            .andExpect(jsonPath("$.decmotifrea").value(DEFAULT_DECMOTIFREA));
    }

    @Test
    void getNonExistingDepart() throws Exception {
        // Get the depart
        restDepartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDepart() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        int databaseSizeBeforeUpdate = departRepository.findAll().size();

        // Update the depart
        Depart updatedDepart = departRepository.findById(depart.getId()).get();
        updatedDepart
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .deheups(UPDATED_DEHEUPS)
            .deheufs(UPDATED_DEHEUFS)
            .denbrro(UPDATED_DENBRRO)
            .deheuaa(UPDATED_DEHEUAA)
            .deheudr(UPDATED_DEHEUDR)
            .deheupd(UPDATED_DEHEUPD)
            .deampli(UPDATED_DEAMPLI)
            .obsind(UPDATED_OBSIND)
            .vldroul(UPDATED_VLDROUL)
            .deetat(UPDATED_DEETAT)
            .deannul(UPDATED_DEANNUL)
            .decclot(UPDATED_DECCLOT)
            .execute(UPDATED_EXECUTE)
            .motifa(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes(UPDATED_RECETTES)
            .nbrevoy(UPDATED_NBREVOY)
            .decmotifch(UPDATED_DECMOTIFCH)
            .decmotifre(UPDATED_DECMOTIFRE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA);
        DepartDTO departDTO = departMapper.toDto(updatedDepart);

        restDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isOk());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
        Depart testDepart = departList.get(departList.size() - 1);
        assertThat(testDepart.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testDepart.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testDepart.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testDepart.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDepart.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDepart.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testDepart.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testDepart.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDepart.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testDepart.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDepart.getDeheups()).isEqualTo(UPDATED_DEHEUPS);
        assertThat(testDepart.getDeheufs()).isEqualTo(UPDATED_DEHEUFS);
        assertThat(testDepart.getDenbrro()).isEqualTo(UPDATED_DENBRRO);
        assertThat(testDepart.getDeheuaa()).isEqualTo(UPDATED_DEHEUAA);
        assertThat(testDepart.getDeheudr()).isEqualTo(UPDATED_DEHEUDR);
        assertThat(testDepart.getDeheupd()).isEqualTo(UPDATED_DEHEUPD);
        assertThat(testDepart.getDeampli()).isEqualTo(UPDATED_DEAMPLI);
        assertThat(testDepart.getObsind()).isEqualTo(UPDATED_OBSIND);
        assertThat(testDepart.getVldroul()).isEqualTo(UPDATED_VLDROUL);
        assertThat(testDepart.getDeetat()).isEqualTo(UPDATED_DEETAT);
        assertThat(testDepart.getDeannul()).isEqualTo(UPDATED_DEANNUL);
        assertThat(testDepart.getDecclot()).isEqualTo(UPDATED_DECCLOT);
        assertThat(testDepart.getExecute()).isEqualTo(UPDATED_EXECUTE);
        assertThat(testDepart.getMotifa()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDepart.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testDepart.getRecettes()).isEqualTo(UPDATED_RECETTES);
        assertThat(testDepart.getNbrevoy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDepart.getDecmotifch()).isEqualTo(UPDATED_DECMOTIFCH);
        assertThat(testDepart.getDecmotifre()).isEqualTo(UPDATED_DECMOTIFRE);
        assertThat(testDepart.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDepart.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDepart.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testDepart.getDecmotifcha()).isEqualTo(UPDATED_DECMOTIFCHA);
        assertThat(testDepart.getDecmotifrea()).isEqualTo(UPDATED_DECMOTIFREA);
    }

    @Test
    void putNonExistingDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDepartWithPatch() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        int databaseSizeBeforeUpdate = departRepository.findAll().size();

        // Update the depart using partial update
        Depart partialUpdatedDepart = new Depart();
        partialUpdatedDepart.setId(depart.getId());

        partialUpdatedDepart
            .deccent(UPDATED_DECCENT)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .cdmac(UPDATED_CDMAC)
            .denbrro(UPDATED_DENBRRO)
            .deetat(UPDATED_DEETAT)
            .execute(UPDATED_EXECUTE)
            .motifa(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .nbrevoy(UPDATED_NBREVOY)
            .decmotifch(UPDATED_DECMOTIFCH)
            .decmotifre(UPDATED_DECMOTIFRE)
            .cd1(UPDATED_CD_1)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA);

        restDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepart))
            )
            .andExpect(status().isOk());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
        Depart testDepart = departList.get(departList.size() - 1);
        assertThat(testDepart.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testDepart.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testDepart.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testDepart.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDepart.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDepart.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testDepart.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testDepart.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDepart.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testDepart.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDepart.getDeheups()).isEqualTo(DEFAULT_DEHEUPS);
        assertThat(testDepart.getDeheufs()).isEqualTo(DEFAULT_DEHEUFS);
        assertThat(testDepart.getDenbrro()).isEqualTo(UPDATED_DENBRRO);
        assertThat(testDepart.getDeheuaa()).isEqualTo(DEFAULT_DEHEUAA);
        assertThat(testDepart.getDeheudr()).isEqualTo(DEFAULT_DEHEUDR);
        assertThat(testDepart.getDeheupd()).isEqualTo(DEFAULT_DEHEUPD);
        assertThat(testDepart.getDeampli()).isEqualTo(DEFAULT_DEAMPLI);
        assertThat(testDepart.getObsind()).isEqualTo(DEFAULT_OBSIND);
        assertThat(testDepart.getVldroul()).isEqualTo(DEFAULT_VLDROUL);
        assertThat(testDepart.getDeetat()).isEqualTo(UPDATED_DEETAT);
        assertThat(testDepart.getDeannul()).isEqualTo(DEFAULT_DEANNUL);
        assertThat(testDepart.getDecclot()).isEqualTo(DEFAULT_DECCLOT);
        assertThat(testDepart.getExecute()).isEqualTo(UPDATED_EXECUTE);
        assertThat(testDepart.getMotifa()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDepart.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testDepart.getRecettes()).isEqualTo(DEFAULT_RECETTES);
        assertThat(testDepart.getNbrevoy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDepart.getDecmotifch()).isEqualTo(UPDATED_DECMOTIFCH);
        assertThat(testDepart.getDecmotifre()).isEqualTo(UPDATED_DECMOTIFRE);
        assertThat(testDepart.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDepart.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testDepart.getCd3()).isEqualTo(DEFAULT_CD_3);
        assertThat(testDepart.getDecmotifcha()).isEqualTo(UPDATED_DECMOTIFCHA);
        assertThat(testDepart.getDecmotifrea()).isEqualTo(UPDATED_DECMOTIFREA);
    }

    @Test
    void fullUpdateDepartWithPatch() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        int databaseSizeBeforeUpdate = departRepository.findAll().size();

        // Update the depart using partial update
        Depart partialUpdatedDepart = new Depart();
        partialUpdatedDepart.setId(depart.getId());

        partialUpdatedDepart
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .deheups(UPDATED_DEHEUPS)
            .deheufs(UPDATED_DEHEUFS)
            .denbrro(UPDATED_DENBRRO)
            .deheuaa(UPDATED_DEHEUAA)
            .deheudr(UPDATED_DEHEUDR)
            .deheupd(UPDATED_DEHEUPD)
            .deampli(UPDATED_DEAMPLI)
            .obsind(UPDATED_OBSIND)
            .vldroul(UPDATED_VLDROUL)
            .deetat(UPDATED_DEETAT)
            .deannul(UPDATED_DEANNUL)
            .decclot(UPDATED_DECCLOT)
            .execute(UPDATED_EXECUTE)
            .motifa(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes(UPDATED_RECETTES)
            .nbrevoy(UPDATED_NBREVOY)
            .decmotifch(UPDATED_DECMOTIFCH)
            .decmotifre(UPDATED_DECMOTIFRE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA);

        restDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepart.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepart))
            )
            .andExpect(status().isOk());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
        Depart testDepart = departList.get(departList.size() - 1);
        assertThat(testDepart.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testDepart.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testDepart.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testDepart.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDepart.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDepart.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testDepart.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testDepart.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDepart.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testDepart.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDepart.getDeheups()).isEqualTo(UPDATED_DEHEUPS);
        assertThat(testDepart.getDeheufs()).isEqualTo(UPDATED_DEHEUFS);
        assertThat(testDepart.getDenbrro()).isEqualTo(UPDATED_DENBRRO);
        assertThat(testDepart.getDeheuaa()).isEqualTo(UPDATED_DEHEUAA);
        assertThat(testDepart.getDeheudr()).isEqualTo(UPDATED_DEHEUDR);
        assertThat(testDepart.getDeheupd()).isEqualTo(UPDATED_DEHEUPD);
        assertThat(testDepart.getDeampli()).isEqualTo(UPDATED_DEAMPLI);
        assertThat(testDepart.getObsind()).isEqualTo(UPDATED_OBSIND);
        assertThat(testDepart.getVldroul()).isEqualTo(UPDATED_VLDROUL);
        assertThat(testDepart.getDeetat()).isEqualTo(UPDATED_DEETAT);
        assertThat(testDepart.getDeannul()).isEqualTo(UPDATED_DEANNUL);
        assertThat(testDepart.getDecclot()).isEqualTo(UPDATED_DECCLOT);
        assertThat(testDepart.getExecute()).isEqualTo(UPDATED_EXECUTE);
        assertThat(testDepart.getMotifa()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDepart.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testDepart.getRecettes()).isEqualTo(UPDATED_RECETTES);
        assertThat(testDepart.getNbrevoy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDepart.getDecmotifch()).isEqualTo(UPDATED_DECMOTIFCH);
        assertThat(testDepart.getDecmotifre()).isEqualTo(UPDATED_DECMOTIFRE);
        assertThat(testDepart.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDepart.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDepart.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testDepart.getDecmotifcha()).isEqualTo(UPDATED_DECMOTIFCHA);
        assertThat(testDepart.getDecmotifrea()).isEqualTo(UPDATED_DECMOTIFREA);
    }

    @Test
    void patchNonExistingDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, departDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDepart() throws Exception {
        int databaseSizeBeforeUpdate = departRepository.findAll().size();
        depart.setId(UUID.randomUUID().toString());

        // Create the Depart
        DepartDTO departDTO = departMapper.toDto(depart);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(departDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Depart in the database
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDepart() throws Exception {
        // Initialize the database
        departRepository.save(depart);

        int databaseSizeBeforeDelete = departRepository.findAll().size();

        // Delete the depart
        restDepartMockMvc
            .perform(delete(ENTITY_API_URL_ID, depart.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Depart> departList = departRepository.findAll();
        assertThat(departList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
