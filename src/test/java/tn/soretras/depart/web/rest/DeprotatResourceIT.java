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
import tn.soretras.depart.domain.Deprotat;
import tn.soretras.depart.repository.DeprotatRepository;

/**
 * Integration tests for the {@link DeprotatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeprotatResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DEDATED = "06/08/2023";
    private static final String UPDATED_DEDATED = "06/08/2023";

    private static final Integer DEFAULT_DENUMDP = 1;
    private static final Integer UPDATED_DENUMDP = 2;

    private static final Integer DEFAULT_DECSERV = 1;
    private static final Integer UPDATED_DECSERV = 2;

    private static final String DEFAULT_DECOPER = "AAAAAAAAAA";
    private static final String UPDATED_DECOPER = "BBBBBBBBBB";

    private static final String DEFAULT_DECSEAN = "AAAAAAAAAA";
    private static final String UPDATED_DECSEAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMROTAT = 1;
    private static final Integer UPDATED_NUMROTAT = 2;

    private static final Integer DEFAULT_LIGDECCENT = 1;
    private static final Integer UPDATED_LIGDECCENT = 2;

    private static final Integer DEFAULT_LIGDECAGENC = 1;
    private static final Integer UPDATED_LIGDECAGENC = 2;

    private static final String DEFAULT_DENUMLI = "AAAAAAAAAA";
    private static final String UPDATED_DENUMLI = "BBBBBBBBBB";

    private static final String DEFAULT_DECSTAT = "AAAAAAAAAA";
    private static final String UPDATED_DECSTAT = "BBBBBBBBBB";

    private static final String DEFAULT_DECSTA_1 = "AAAAAAAAAA";
    private static final String UPDATED_DECSTA_1 = "BBBBBBBBBB";

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final Integer DEFAULT_MATRIC_1 = 1;
    private static final Integer UPDATED_MATRIC_1 = 2;

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final String DEFAULT_HDEPARTE = "00:00";
    private static final String UPDATED_HDEPARTE = "00:00";

    private static final String DEFAULT_HRETOURE = "00:00";
    private static final String UPDATED_HRETOURE = "00:00";

    private static final String DEFAULT_HARRALLE = "00:00";
    private static final String UPDATED_HARRALLE = "00:00";

    private static final String DEFAULT_HARRRETE = "00:00";
    private static final String UPDATED_HARRRETE = "00:00";

    private static final String DEFAULT_RANNUL = "AAAAAAAAAA";
    private static final String UPDATED_RANNUL = "BBBBBBBBBB";

    private static final Double DEFAULT_KM = 1D;
    private static final Double UPDATED_KM = 2D;

    private static final Integer DEFAULT_MOTIFA = 1;
    private static final Integer UPDATED_MOTIFA = 2;

    private static final String DEFAULT_OBSERV = "AAAAAAAAAA";
    private static final String UPDATED_OBSERV = "BBBBBBBBBB";

    private static final Integer DEFAULT_RECETTESVOY = 1;
    private static final Integer UPDATED_RECETTESVOY = 2;

    private static final Integer DEFAULT_NBREVOY = 1;
    private static final Integer UPDATED_NBREVOY = 2;

    private static final Integer DEFAULT_PAYE = 1;
    private static final Integer UPDATED_PAYE = 2;

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

    private static final Integer DEFAULT_IDAPEX = 1;
    private static final Integer UPDATED_IDAPEX = 2;

    private static final String DEFAULT_PLUSMOINS = "AAAAAAAAAA";
    private static final String UPDATED_PLUSMOINS = "BBBBBBBBBB";

    private static final String DEFAULT_A = "AAAAAAAAAA";
    private static final String UPDATED_A = "BBBBBBBBBB";

    private static final String DEFAULT_R = "AAAAAAAAAA";
    private static final String UPDATED_R = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/deprotats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DeprotatRepository deprotatRepository;

    @Autowired
    private MockMvc restDeprotatMockMvc;

    private Deprotat deprotat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deprotat createEntity() {
        Deprotat deprotat = new Deprotat()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .dedated(DEFAULT_DEDATED)
            .denumdp(DEFAULT_DENUMDP)
            .decserv(DEFAULT_DECSERV)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .numrotat(DEFAULT_NUMROTAT)
            .lig_deccent(DEFAULT_LIGDECCENT)
            .lig_decagenc(DEFAULT_LIGDECAGENC)
            .denumli(DEFAULT_DENUMLI)
            .decstat(DEFAULT_DECSTAT)
            .decsta1(DEFAULT_DECSTA_1)
            .matric(DEFAULT_MATRIC)
            .matric1(DEFAULT_MATRIC_1)
            .cdmac(DEFAULT_CDMAC)
            .hdeparte(DEFAULT_HDEPARTE)
            .hretoure(DEFAULT_HRETOURE)
            .harralle(DEFAULT_HARRALLE)
            .harrrete(DEFAULT_HARRRETE)
            .r_annul(DEFAULT_RANNUL)
            .km(DEFAULT_KM)
            .motif_a(DEFAULT_MOTIFA)
            .observ(DEFAULT_OBSERV)
            .recettes_voy(DEFAULT_RECETTESVOY)
            .nbre_voy(DEFAULT_NBREVOY)
            .paye(DEFAULT_PAYE)
            .cd1(DEFAULT_CD_1)
            .cd2(DEFAULT_CD_2)
            .cd3(DEFAULT_CD_3)
            .decmotifcha(DEFAULT_DECMOTIFCHA)
            .decmotifrea(DEFAULT_DECMOTIFREA)
            .id_apex(DEFAULT_IDAPEX)
            .plus_moins(DEFAULT_PLUSMOINS)
            .a(DEFAULT_A)
            .r(DEFAULT_R);
        return deprotat;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deprotat createUpdatedEntity() {
        Deprotat deprotat = new Deprotat()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .lig_deccent(UPDATED_LIGDECCENT)
            .lig_decagenc(UPDATED_LIGDECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .decsta1(UPDATED_DECSTA_1)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .hdeparte(UPDATED_HDEPARTE)
            .hretoure(UPDATED_HRETOURE)
            .harralle(UPDATED_HARRALLE)
            .harrrete(UPDATED_HARRRETE)
            .r_annul(UPDATED_RANNUL)
            .km(UPDATED_KM)
            .motif_a(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes_voy(UPDATED_RECETTESVOY)
            .nbre_voy(UPDATED_NBREVOY)
            .paye(UPDATED_PAYE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA)
            .id_apex(UPDATED_IDAPEX)
            .plus_moins(UPDATED_PLUSMOINS)
            .a(UPDATED_A)
            .r(UPDATED_R);
        return deprotat;
    }

    @BeforeEach
    public void initTest() {
        deprotatRepository.deleteAll();
        deprotat = createEntity();
    }

    @Test
    void createDeprotat() throws Exception {
        int databaseSizeBeforeCreate = deprotatRepository.findAll().size();
        // Create the Deprotat
        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isCreated());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeCreate + 1);
        Deprotat testDeprotat = deprotatList.get(deprotatList.size() - 1);
        assertThat(testDeprotat.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testDeprotat.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testDeprotat.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testDeprotat.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testDeprotat.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testDeprotat.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testDeprotat.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testDeprotat.getNumrotat()).isEqualTo(DEFAULT_NUMROTAT);
        assertThat(testDeprotat.getLig_deccent()).isEqualTo(DEFAULT_LIGDECCENT);
        assertThat(testDeprotat.getLig_decagenc()).isEqualTo(DEFAULT_LIGDECAGENC);
        assertThat(testDeprotat.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testDeprotat.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testDeprotat.getDecsta1()).isEqualTo(DEFAULT_DECSTA_1);
        assertThat(testDeprotat.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testDeprotat.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testDeprotat.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testDeprotat.getHdeparte()).isEqualTo(DEFAULT_HDEPARTE);
        assertThat(testDeprotat.getHretoure()).isEqualTo(DEFAULT_HRETOURE);
        assertThat(testDeprotat.getHarralle()).isEqualTo(DEFAULT_HARRALLE);
        assertThat(testDeprotat.getHarrrete()).isEqualTo(DEFAULT_HARRRETE);
        assertThat(testDeprotat.getR_annul()).isEqualTo(DEFAULT_RANNUL);
        assertThat(testDeprotat.getKm()).isEqualTo(DEFAULT_KM);
        assertThat(testDeprotat.getMotif_a()).isEqualTo(DEFAULT_MOTIFA);
        assertThat(testDeprotat.getObserv()).isEqualTo(DEFAULT_OBSERV);
        assertThat(testDeprotat.getRecettes_voy()).isEqualTo(DEFAULT_RECETTESVOY);
        assertThat(testDeprotat.getNbre_voy()).isEqualTo(DEFAULT_NBREVOY);
        assertThat(testDeprotat.getPaye()).isEqualTo(DEFAULT_PAYE);
        assertThat(testDeprotat.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testDeprotat.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testDeprotat.getCd3()).isEqualTo(DEFAULT_CD_3);
        assertThat(testDeprotat.getDecmotifcha()).isEqualTo(DEFAULT_DECMOTIFCHA);
        assertThat(testDeprotat.getDecmotifrea()).isEqualTo(DEFAULT_DECMOTIFREA);
        assertThat(testDeprotat.getId_apex()).isEqualTo(DEFAULT_IDAPEX);
        assertThat(testDeprotat.getPlus_moins()).isEqualTo(DEFAULT_PLUSMOINS);
        assertThat(testDeprotat.getA()).isEqualTo(DEFAULT_A);
        assertThat(testDeprotat.getR()).isEqualTo(DEFAULT_R);
    }

    @Test
    void createDeprotatWithExistingId() throws Exception {
        // Create the Deprotat with an existing ID
        deprotat.setId("existing_id");

        int databaseSizeBeforeCreate = deprotatRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDeccent(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDecagenc(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDedatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDedated(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumdpIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDenumdp(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecservIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDecserv(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecoperIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDecoper(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecseanIsRequired() throws Exception {
        int databaseSizeBeforeTest = deprotatRepository.findAll().size();
        // set the field null
        deprotat.setDecsean(null);

        // Create the Deprotat, which fails.

        restDeprotatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isBadRequest());

        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDeprotats() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        // Get all the deprotatList
        restDeprotatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deprotat.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].denumdp").value(hasItem(DEFAULT_DENUMDP)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].numrotat").value(hasItem(DEFAULT_NUMROTAT)))
            .andExpect(jsonPath("$.[*].ligdeccent").value(hasItem(DEFAULT_LIGDECCENT)))
            .andExpect(jsonPath("$.[*].ligdecagenc").value(hasItem(DEFAULT_LIGDECAGENC)))
            .andExpect(jsonPath("$.[*].denumli").value(hasItem(DEFAULT_DENUMLI)))
            .andExpect(jsonPath("$.[*].decstat").value(hasItem(DEFAULT_DECSTAT)))
            .andExpect(jsonPath("$.[*].decsta1").value(hasItem(DEFAULT_DECSTA_1)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].matric1").value(hasItem(DEFAULT_MATRIC_1)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].hdeparte").value(hasItem(DEFAULT_HDEPARTE.toString())))
            .andExpect(jsonPath("$.[*].hretoure").value(hasItem(DEFAULT_HRETOURE.toString())))
            .andExpect(jsonPath("$.[*].harralle").value(hasItem(DEFAULT_HARRALLE.toString())))
            .andExpect(jsonPath("$.[*].harrrete").value(hasItem(DEFAULT_HARRRETE.toString())))
            .andExpect(jsonPath("$.[*].rannul").value(hasItem(DEFAULT_RANNUL)))
            .andExpect(jsonPath("$.[*].km").value(hasItem(DEFAULT_KM.doubleValue())))
            .andExpect(jsonPath("$.[*].motifa").value(hasItem(DEFAULT_MOTIFA)))
            .andExpect(jsonPath("$.[*].observ").value(hasItem(DEFAULT_OBSERV)))
            .andExpect(jsonPath("$.[*].recettesvoy").value(hasItem(DEFAULT_RECETTESVOY)))
            .andExpect(jsonPath("$.[*].nbrevoy").value(hasItem(DEFAULT_NBREVOY)))
            .andExpect(jsonPath("$.[*].paye").value(hasItem(DEFAULT_PAYE)))
            .andExpect(jsonPath("$.[*].cd1").value(hasItem(DEFAULT_CD_1)))
            .andExpect(jsonPath("$.[*].cd2").value(hasItem(DEFAULT_CD_2)))
            .andExpect(jsonPath("$.[*].cd3").value(hasItem(DEFAULT_CD_3)))
            .andExpect(jsonPath("$.[*].decmotifcha").value(hasItem(DEFAULT_DECMOTIFCHA)))
            .andExpect(jsonPath("$.[*].decmotifrea").value(hasItem(DEFAULT_DECMOTIFREA)))
            .andExpect(jsonPath("$.[*].idapex").value(hasItem(DEFAULT_IDAPEX)))
            .andExpect(jsonPath("$.[*].plusmoins").value(hasItem(DEFAULT_PLUSMOINS)))
            .andExpect(jsonPath("$.[*].a").value(hasItem(DEFAULT_A)))
            .andExpect(jsonPath("$.[*].r").value(hasItem(DEFAULT_R)));
    }

    @Test
    void getDeprotat() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        // Get the deprotat
        restDeprotatMockMvc
            .perform(get(ENTITY_API_URL_ID, deprotat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deprotat.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.denumdp").value(DEFAULT_DENUMDP))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.numrotat").value(DEFAULT_NUMROTAT))
            .andExpect(jsonPath("$.ligdeccent").value(DEFAULT_LIGDECCENT))
            .andExpect(jsonPath("$.ligdecagenc").value(DEFAULT_LIGDECAGENC))
            .andExpect(jsonPath("$.denumli").value(DEFAULT_DENUMLI))
            .andExpect(jsonPath("$.decstat").value(DEFAULT_DECSTAT))
            .andExpect(jsonPath("$.decsta1").value(DEFAULT_DECSTA_1))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.matric1").value(DEFAULT_MATRIC_1))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.hdeparte").value(DEFAULT_HDEPARTE.toString()))
            .andExpect(jsonPath("$.hretoure").value(DEFAULT_HRETOURE.toString()))
            .andExpect(jsonPath("$.harralle").value(DEFAULT_HARRALLE.toString()))
            .andExpect(jsonPath("$.harrrete").value(DEFAULT_HARRRETE.toString()))
            .andExpect(jsonPath("$.rannul").value(DEFAULT_RANNUL))
            .andExpect(jsonPath("$.km").value(DEFAULT_KM.doubleValue()))
            .andExpect(jsonPath("$.motifa").value(DEFAULT_MOTIFA))
            .andExpect(jsonPath("$.observ").value(DEFAULT_OBSERV))
            .andExpect(jsonPath("$.recettesvoy").value(DEFAULT_RECETTESVOY))
            .andExpect(jsonPath("$.nbrevoy").value(DEFAULT_NBREVOY))
            .andExpect(jsonPath("$.paye").value(DEFAULT_PAYE))
            .andExpect(jsonPath("$.cd1").value(DEFAULT_CD_1))
            .andExpect(jsonPath("$.cd2").value(DEFAULT_CD_2))
            .andExpect(jsonPath("$.cd3").value(DEFAULT_CD_3))
            .andExpect(jsonPath("$.decmotifcha").value(DEFAULT_DECMOTIFCHA))
            .andExpect(jsonPath("$.decmotifrea").value(DEFAULT_DECMOTIFREA))
            .andExpect(jsonPath("$.idapex").value(DEFAULT_IDAPEX))
            .andExpect(jsonPath("$.plusmoins").value(DEFAULT_PLUSMOINS))
            .andExpect(jsonPath("$.a").value(DEFAULT_A))
            .andExpect(jsonPath("$.r").value(DEFAULT_R));
    }

    @Test
    void getNonExistingDeprotat() throws Exception {
        // Get the deprotat
        restDeprotatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDeprotat() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();

        // Update the deprotat
        Deprotat updatedDeprotat = deprotatRepository.findById(deprotat.getId()).get();
        updatedDeprotat
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .lig_deccent(UPDATED_LIGDECCENT)
            .lig_decagenc(UPDATED_LIGDECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .decsta1(UPDATED_DECSTA_1)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .hdeparte(UPDATED_HDEPARTE)
            .hretoure(UPDATED_HRETOURE)
            .harralle(UPDATED_HARRALLE)
            .harrrete(UPDATED_HARRRETE)
            .r_annul(UPDATED_RANNUL)
            .km(UPDATED_KM)
            .motif_a(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes_voy(UPDATED_RECETTESVOY)
            .nbre_voy(UPDATED_NBREVOY)
            .paye(UPDATED_PAYE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA)
            .id_apex(UPDATED_IDAPEX)
            .plus_moins(UPDATED_PLUSMOINS)
            .a(UPDATED_A)
            .r(UPDATED_R);

        restDeprotatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDeprotat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDeprotat))
            )
            .andExpect(status().isOk());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
        Deprotat testDeprotat = deprotatList.get(deprotatList.size() - 1);
        assertThat(testDeprotat.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testDeprotat.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testDeprotat.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testDeprotat.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testDeprotat.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testDeprotat.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDeprotat.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDeprotat.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testDeprotat.getLig_deccent()).isEqualTo(UPDATED_LIGDECCENT);
        assertThat(testDeprotat.getLig_decagenc()).isEqualTo(UPDATED_LIGDECAGENC);
        assertThat(testDeprotat.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testDeprotat.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testDeprotat.getDecsta1()).isEqualTo(UPDATED_DECSTA_1);
        assertThat(testDeprotat.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDeprotat.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testDeprotat.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDeprotat.getHdeparte()).isEqualTo(UPDATED_HDEPARTE);
        assertThat(testDeprotat.getHretoure()).isEqualTo(UPDATED_HRETOURE);
        assertThat(testDeprotat.getHarralle()).isEqualTo(UPDATED_HARRALLE);
        assertThat(testDeprotat.getHarrrete()).isEqualTo(UPDATED_HARRRETE);
        assertThat(testDeprotat.getR_annul()).isEqualTo(UPDATED_RANNUL);
        assertThat(testDeprotat.getKm()).isEqualTo(UPDATED_KM);
        assertThat(testDeprotat.getMotif_a()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDeprotat.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testDeprotat.getRecettes_voy()).isEqualTo(UPDATED_RECETTESVOY);
        assertThat(testDeprotat.getNbre_voy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDeprotat.getPaye()).isEqualTo(UPDATED_PAYE);
        assertThat(testDeprotat.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDeprotat.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDeprotat.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testDeprotat.getDecmotifcha()).isEqualTo(UPDATED_DECMOTIFCHA);
        assertThat(testDeprotat.getDecmotifrea()).isEqualTo(UPDATED_DECMOTIFREA);
        assertThat(testDeprotat.getId_apex()).isEqualTo(UPDATED_IDAPEX);
        assertThat(testDeprotat.getPlus_moins()).isEqualTo(UPDATED_PLUSMOINS);
        assertThat(testDeprotat.getA()).isEqualTo(UPDATED_A);
        assertThat(testDeprotat.getR()).isEqualTo(UPDATED_R);
    }

    @Test
    void putNonExistingDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deprotat.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deprotat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(deprotat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDeprotatWithPatch() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();

        // Update the deprotat using partial update
        Deprotat partialUpdatedDeprotat = new Deprotat();
        partialUpdatedDeprotat.setId(deprotat.getId());

        partialUpdatedDeprotat
            .decagenc(UPDATED_DECAGENC)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .lig_deccent(UPDATED_LIGDECCENT)
            .lig_decagenc(UPDATED_LIGDECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decsta1(UPDATED_DECSTA_1)
            .matric(UPDATED_MATRIC)
            .cdmac(UPDATED_CDMAC)
            .hretoure(UPDATED_HRETOURE)
            .harralle(UPDATED_HARRALLE)
            .r_annul(UPDATED_RANNUL)
            .km(UPDATED_KM)
            .motif_a(UPDATED_MOTIFA)
            .recettes_voy(UPDATED_RECETTESVOY)
            .nbre_voy(UPDATED_NBREVOY)
            .paye(UPDATED_PAYE)
            .cd3(UPDATED_CD_3)
            .id_apex(UPDATED_IDAPEX);

        restDeprotatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeprotat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeprotat))
            )
            .andExpect(status().isOk());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
        Deprotat testDeprotat = deprotatList.get(deprotatList.size() - 1);
        assertThat(testDeprotat.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testDeprotat.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testDeprotat.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testDeprotat.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testDeprotat.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testDeprotat.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDeprotat.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDeprotat.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testDeprotat.getLig_deccent()).isEqualTo(UPDATED_LIGDECCENT);
        assertThat(testDeprotat.getLig_decagenc()).isEqualTo(UPDATED_LIGDECAGENC);
        assertThat(testDeprotat.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testDeprotat.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testDeprotat.getDecsta1()).isEqualTo(UPDATED_DECSTA_1);
        assertThat(testDeprotat.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDeprotat.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testDeprotat.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDeprotat.getHdeparte()).isEqualTo(DEFAULT_HDEPARTE);
        assertThat(testDeprotat.getHretoure()).isEqualTo(UPDATED_HRETOURE);
        assertThat(testDeprotat.getHarralle()).isEqualTo(UPDATED_HARRALLE);
        assertThat(testDeprotat.getHarrrete()).isEqualTo(DEFAULT_HARRRETE);
        assertThat(testDeprotat.getR_annul()).isEqualTo(UPDATED_RANNUL);
        assertThat(testDeprotat.getKm()).isEqualTo(UPDATED_KM);
        assertThat(testDeprotat.getMotif_a()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDeprotat.getObserv()).isEqualTo(DEFAULT_OBSERV);
        assertThat(testDeprotat.getRecettes_voy()).isEqualTo(UPDATED_RECETTESVOY);
        assertThat(testDeprotat.getNbre_voy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDeprotat.getPaye()).isEqualTo(UPDATED_PAYE);
        assertThat(testDeprotat.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testDeprotat.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testDeprotat.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testDeprotat.getDecmotifcha()).isEqualTo(DEFAULT_DECMOTIFCHA);
        assertThat(testDeprotat.getDecmotifrea()).isEqualTo(DEFAULT_DECMOTIFREA);
        assertThat(testDeprotat.getId_apex()).isEqualTo(UPDATED_IDAPEX);
        assertThat(testDeprotat.getPlus_moins()).isEqualTo(DEFAULT_PLUSMOINS);
        assertThat(testDeprotat.getA()).isEqualTo(DEFAULT_A);
        assertThat(testDeprotat.getR()).isEqualTo(DEFAULT_R);
    }

    @Test
    void fullUpdateDeprotatWithPatch() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();

        // Update the deprotat using partial update
        Deprotat partialUpdatedDeprotat = new Deprotat();
        partialUpdatedDeprotat.setId(deprotat.getId());

        partialUpdatedDeprotat
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .lig_deccent(UPDATED_LIGDECCENT)
            .lig_decagenc(UPDATED_LIGDECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .decsta1(UPDATED_DECSTA_1)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .cdmac(UPDATED_CDMAC)
            .hdeparte(UPDATED_HDEPARTE)
            .hretoure(UPDATED_HRETOURE)
            .harralle(UPDATED_HARRALLE)
            .harrrete(UPDATED_HARRRETE)
            .r_annul(UPDATED_RANNUL)
            .km(UPDATED_KM)
            .motif_a(UPDATED_MOTIFA)
            .observ(UPDATED_OBSERV)
            .recettes_voy(UPDATED_RECETTESVOY)
            .nbre_voy(UPDATED_NBREVOY)
            .paye(UPDATED_PAYE)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3)
            .decmotifcha(UPDATED_DECMOTIFCHA)
            .decmotifrea(UPDATED_DECMOTIFREA)
            .id_apex(UPDATED_IDAPEX)
            .plus_moins(UPDATED_PLUSMOINS)
            .a(UPDATED_A)
            .r(UPDATED_R);

        restDeprotatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeprotat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDeprotat))
            )
            .andExpect(status().isOk());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
        Deprotat testDeprotat = deprotatList.get(deprotatList.size() - 1);
        assertThat(testDeprotat.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testDeprotat.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testDeprotat.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testDeprotat.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testDeprotat.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testDeprotat.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testDeprotat.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testDeprotat.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testDeprotat.getLig_deccent()).isEqualTo(UPDATED_LIGDECCENT);
        assertThat(testDeprotat.getLig_decagenc()).isEqualTo(UPDATED_LIGDECAGENC);
        assertThat(testDeprotat.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testDeprotat.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testDeprotat.getDecsta1()).isEqualTo(UPDATED_DECSTA_1);
        assertThat(testDeprotat.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDeprotat.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testDeprotat.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testDeprotat.getHdeparte()).isEqualTo(UPDATED_HDEPARTE);
        assertThat(testDeprotat.getHretoure()).isEqualTo(UPDATED_HRETOURE);
        assertThat(testDeprotat.getHarralle()).isEqualTo(UPDATED_HARRALLE);
        assertThat(testDeprotat.getHarrrete()).isEqualTo(UPDATED_HARRRETE);
        assertThat(testDeprotat.getR_annul()).isEqualTo(UPDATED_RANNUL);
        assertThat(testDeprotat.getKm()).isEqualTo(UPDATED_KM);
        assertThat(testDeprotat.getMotif_a()).isEqualTo(UPDATED_MOTIFA);
        assertThat(testDeprotat.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testDeprotat.getRecettes_voy()).isEqualTo(UPDATED_RECETTESVOY);
        assertThat(testDeprotat.getNbre_voy()).isEqualTo(UPDATED_NBREVOY);
        assertThat(testDeprotat.getPaye()).isEqualTo(UPDATED_PAYE);
        assertThat(testDeprotat.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDeprotat.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDeprotat.getCd3()).isEqualTo(UPDATED_CD_3);
        assertThat(testDeprotat.getDecmotifcha()).isEqualTo(UPDATED_DECMOTIFCHA);
        assertThat(testDeprotat.getDecmotifrea()).isEqualTo(UPDATED_DECMOTIFREA);
        assertThat(testDeprotat.getId_apex()).isEqualTo(UPDATED_IDAPEX);
        assertThat(testDeprotat.getPlus_moins()).isEqualTo(UPDATED_PLUSMOINS);
        assertThat(testDeprotat.getA()).isEqualTo(UPDATED_A);
        assertThat(testDeprotat.getR()).isEqualTo(UPDATED_R);
    }

    @Test
    void patchNonExistingDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deprotat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deprotat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(deprotat))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDeprotat() throws Exception {
        int databaseSizeBeforeUpdate = deprotatRepository.findAll().size();
        deprotat.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeprotatMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(deprotat)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Deprotat in the database
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDeprotat() throws Exception {
        // Initialize the database
        deprotatRepository.save(deprotat);

        int databaseSizeBeforeDelete = deprotatRepository.findAll().size();

        // Delete the deprotat
        restDeprotatMockMvc
            .perform(delete(ENTITY_API_URL_ID, deprotat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Deprotat> deprotatList = deprotatRepository.findAll();
        assertThat(deprotatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
