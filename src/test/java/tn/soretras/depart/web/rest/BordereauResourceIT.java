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
import tn.soretras.depart.domain.Bordereau;
import tn.soretras.depart.repository.BordereauRepository;
import tn.soretras.depart.service.dto.BordereauDTO;
import tn.soretras.depart.service.mapper.BordereauMapper;

/**
 * Integration tests for the {@link BordereauResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BordereauResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final Integer DEFAULT_EXERCICE = 1;
    private static final Integer UPDATED_EXERCICE = 2;

    private static final Integer DEFAULT_DENBORD = 1;
    private static final Integer UPDATED_DENBORD = 2;

    private static final Integer DEFAULT_DET_DECCENT = 1;
    private static final Integer UPDATED_DET_DECCENT = 2;

    private static final Integer DEFAULT_DET_DECAGENC = 1;
    private static final Integer UPDATED_DET_DECAGENC = 2;

    private static final Integer DEFAULT_DECSERV = 1;
    private static final Integer UPDATED_DECSERV = 2;

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

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

    private static final String DEFAULT_NATBORD = "AAAAAAAAAA";
    private static final String UPDATED_NATBORD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DETADEDB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DETADEDB = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUMB_PDAT = 1;
    private static final Integer UPDATED_NUMB_PDAT = 2;

    private static final LocalDate DEFAULT_DEHEUPSR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEHEUPSR = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DEMNTTN = 1;
    private static final Integer UPDATED_DEMNTTN = 2;

    private static final Integer DEFAULT_DEMNTTO = 1;
    private static final Integer UPDATED_DEMNTTO = 2;

    private static final String DEFAULT_DECETBR = "AAAAAAAAAA";
    private static final String UPDATED_DECETBR = "BBBBBBBBBB";

    private static final String DEFAULT_DECETCS = "AAAAAAAAAA";
    private static final String UPDATED_DECETCS = "BBBBBBBBBB";

    private static final Integer DEFAULT_DENUMCS = 1;
    private static final Integer UPDATED_DENUMCS = 2;

    private static final Integer DEFAULT_DENUMBR = 1;
    private static final Integer UPDATED_DENUMBR = 2;

    private static final Integer DEFAULT_DENUMVER = 1;
    private static final Integer UPDATED_DENUMVER = 2;

    private static final String DEFAULT_DECLOTE = "AAAAAAAAAA";
    private static final String UPDATED_DECLOTE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_SAISIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_SAISIE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CLOREC = "AAAAAAAAAA";
    private static final String UPDATED_CLOREC = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEMNTVERS = 1;
    private static final Integer UPDATED_DEMNTVERS = 2;

    private static final String ENTITY_API_URL = "/api/bordereaus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private BordereauRepository bordereauRepository;

    @Autowired
    private BordereauMapper bordereauMapper;

    @Autowired
    private MockMvc restBordereauMockMvc;

    private Bordereau bordereau;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bordereau createEntity() {
        Bordereau bordereau = new Bordereau()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .exercice(DEFAULT_EXERCICE)
            .denbord(DEFAULT_DENBORD)
            .det_deccent(DEFAULT_DET_DECCENT)
            .det_decagenc(DEFAULT_DET_DECAGENC)
            .decserv(DEFAULT_DECSERV)
            .cdmac(DEFAULT_CDMAC)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .dedated(DEFAULT_DEDATED)
            .denumdp(DEFAULT_DENUMDP)
            .matric(DEFAULT_MATRIC)
            .matric1(DEFAULT_MATRIC_1)
            .natbord(DEFAULT_NATBORD)
            .detadedb(DEFAULT_DETADEDB)
            .numb_pdat(DEFAULT_NUMB_PDAT)
            .deheupsr(DEFAULT_DEHEUPSR)
            .demnttn(DEFAULT_DEMNTTN)
            .demntto(DEFAULT_DEMNTTO)
            .decetbr(DEFAULT_DECETBR)
            .decetcs(DEFAULT_DECETCS)
            .denumcs(DEFAULT_DENUMCS)
            .denumbr(DEFAULT_DENUMBR)
            .denumver(DEFAULT_DENUMVER)
            .declote(DEFAULT_DECLOTE)
            .date_saisie(DEFAULT_DATE_SAISIE)
            .clorec(DEFAULT_CLOREC)
            .demntvers(DEFAULT_DEMNTVERS);
        return bordereau;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bordereau createUpdatedEntity() {
        Bordereau bordereau = new Bordereau()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .exercice(UPDATED_EXERCICE)
            .denbord(UPDATED_DENBORD)
            .det_deccent(UPDATED_DET_DECCENT)
            .det_decagenc(UPDATED_DET_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .cdmac(UPDATED_CDMAC)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .natbord(UPDATED_NATBORD)
            .detadedb(UPDATED_DETADEDB)
            .numb_pdat(UPDATED_NUMB_PDAT)
            .deheupsr(UPDATED_DEHEUPSR)
            .demnttn(UPDATED_DEMNTTN)
            .demntto(UPDATED_DEMNTTO)
            .decetbr(UPDATED_DECETBR)
            .decetcs(UPDATED_DECETCS)
            .denumcs(UPDATED_DENUMCS)
            .denumbr(UPDATED_DENUMBR)
            .denumver(UPDATED_DENUMVER)
            .declote(UPDATED_DECLOTE)
            .date_saisie(UPDATED_DATE_SAISIE)
            .clorec(UPDATED_CLOREC)
            .demntvers(UPDATED_DEMNTVERS);
        return bordereau;
    }

    @BeforeEach
    public void initTest() {
        bordereauRepository.deleteAll();
        bordereau = createEntity();
    }

    @Test
    void createBordereau() throws Exception {
        int databaseSizeBeforeCreate = bordereauRepository.findAll().size();
        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);
        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isCreated());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeCreate + 1);
        Bordereau testBordereau = bordereauList.get(bordereauList.size() - 1);
        assertThat(testBordereau.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testBordereau.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testBordereau.getExercice()).isEqualTo(DEFAULT_EXERCICE);
        assertThat(testBordereau.getDenbord()).isEqualTo(DEFAULT_DENBORD);
        assertThat(testBordereau.getDet_deccent()).isEqualTo(DEFAULT_DET_DECCENT);
        assertThat(testBordereau.getDet_decagenc()).isEqualTo(DEFAULT_DET_DECAGENC);
        assertThat(testBordereau.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testBordereau.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testBordereau.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testBordereau.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testBordereau.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testBordereau.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testBordereau.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testBordereau.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testBordereau.getNatbord()).isEqualTo(DEFAULT_NATBORD);
        assertThat(testBordereau.getDetadedb()).isEqualTo(DEFAULT_DETADEDB);
        assertThat(testBordereau.getNumb_pdat()).isEqualTo(DEFAULT_NUMB_PDAT);
        assertThat(testBordereau.getDeheupsr()).isEqualTo(DEFAULT_DEHEUPSR);
        assertThat(testBordereau.getDemnttn()).isEqualTo(DEFAULT_DEMNTTN);
        assertThat(testBordereau.getDemntto()).isEqualTo(DEFAULT_DEMNTTO);
        assertThat(testBordereau.getDecetbr()).isEqualTo(DEFAULT_DECETBR);
        assertThat(testBordereau.getDecetcs()).isEqualTo(DEFAULT_DECETCS);
        assertThat(testBordereau.getDenumcs()).isEqualTo(DEFAULT_DENUMCS);
        assertThat(testBordereau.getDenumbr()).isEqualTo(DEFAULT_DENUMBR);
        assertThat(testBordereau.getDenumver()).isEqualTo(DEFAULT_DENUMVER);
        assertThat(testBordereau.getDeclote()).isEqualTo(DEFAULT_DECLOTE);
        assertThat(testBordereau.getDate_saisie()).isEqualTo(DEFAULT_DATE_SAISIE);
        assertThat(testBordereau.getClorec()).isEqualTo(DEFAULT_CLOREC);
        assertThat(testBordereau.getDemntvers()).isEqualTo(DEFAULT_DEMNTVERS);
    }

    @Test
    void createBordereauWithExistingId() throws Exception {
        // Create the Bordereau with an existing ID
        bordereau.setId("existing_id");
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        int databaseSizeBeforeCreate = bordereauRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDeccent(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecagenc(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkExerciceIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setExercice(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenbordIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDenbord(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDet_deccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDet_deccent(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDet_decagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDet_decagenc(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecservIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecserv(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCdmacIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setCdmac(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecoperIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecoper(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecseanIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecsean(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDedatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDedated(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumdpIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDenumdp(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMatricIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setMatric(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMatric1IsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setMatric1(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNatbordIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setNatbord(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDetadedbIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDetadedb(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNumb_pdatIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setNumb_pdat(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDeheupsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDeheupsr(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDemnttnIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDemnttn(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDemnttoIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDemntto(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecetbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecetbr(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecetcsIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDecetcs(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumcsIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDenumcs(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDenumbr(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumverIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDenumver(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecloteIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDeclote(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDate_saisieIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDate_saisie(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkClorecIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setClorec(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDemntversIsRequired() throws Exception {
        int databaseSizeBeforeTest = bordereauRepository.findAll().size();
        // set the field null
        bordereau.setDemntvers(null);

        // Create the Bordereau, which fails.
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        restBordereauMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isBadRequest());

        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllBordereaus() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        // Get all the bordereauList
        restBordereauMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bordereau.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].exercice").value(hasItem(DEFAULT_EXERCICE)))
            .andExpect(jsonPath("$.[*].denbord").value(hasItem(DEFAULT_DENBORD)))
            .andExpect(jsonPath("$.[*].det_deccent").value(hasItem(DEFAULT_DET_DECCENT)))
            .andExpect(jsonPath("$.[*].det_decagenc").value(hasItem(DEFAULT_DET_DECAGENC)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].denumdp").value(hasItem(DEFAULT_DENUMDP)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].matric1").value(hasItem(DEFAULT_MATRIC_1)))
            .andExpect(jsonPath("$.[*].natbord").value(hasItem(DEFAULT_NATBORD)))
            .andExpect(jsonPath("$.[*].detadedb").value(hasItem(DEFAULT_DETADEDB.toString())))
            .andExpect(jsonPath("$.[*].numb_pdat").value(hasItem(DEFAULT_NUMB_PDAT)))
            .andExpect(jsonPath("$.[*].deheupsr").value(hasItem(DEFAULT_DEHEUPSR.toString())))
            .andExpect(jsonPath("$.[*].demnttn").value(hasItem(DEFAULT_DEMNTTN)))
            .andExpect(jsonPath("$.[*].demntto").value(hasItem(DEFAULT_DEMNTTO)))
            .andExpect(jsonPath("$.[*].decetbr").value(hasItem(DEFAULT_DECETBR)))
            .andExpect(jsonPath("$.[*].decetcs").value(hasItem(DEFAULT_DECETCS)))
            .andExpect(jsonPath("$.[*].denumcs").value(hasItem(DEFAULT_DENUMCS)))
            .andExpect(jsonPath("$.[*].denumbr").value(hasItem(DEFAULT_DENUMBR)))
            .andExpect(jsonPath("$.[*].denumver").value(hasItem(DEFAULT_DENUMVER)))
            .andExpect(jsonPath("$.[*].declote").value(hasItem(DEFAULT_DECLOTE)))
            .andExpect(jsonPath("$.[*].date_saisie").value(hasItem(DEFAULT_DATE_SAISIE.toString())))
            .andExpect(jsonPath("$.[*].clorec").value(hasItem(DEFAULT_CLOREC)))
            .andExpect(jsonPath("$.[*].demntvers").value(hasItem(DEFAULT_DEMNTVERS)));
    }

    @Test
    void getBordereau() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        // Get the bordereau
        restBordereauMockMvc
            .perform(get(ENTITY_API_URL_ID, bordereau.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bordereau.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.exercice").value(DEFAULT_EXERCICE))
            .andExpect(jsonPath("$.denbord").value(DEFAULT_DENBORD))
            .andExpect(jsonPath("$.det_deccent").value(DEFAULT_DET_DECCENT))
            .andExpect(jsonPath("$.det_decagenc").value(DEFAULT_DET_DECAGENC))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.denumdp").value(DEFAULT_DENUMDP))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.matric1").value(DEFAULT_MATRIC_1))
            .andExpect(jsonPath("$.natbord").value(DEFAULT_NATBORD))
            .andExpect(jsonPath("$.detadedb").value(DEFAULT_DETADEDB.toString()))
            .andExpect(jsonPath("$.numb_pdat").value(DEFAULT_NUMB_PDAT))
            .andExpect(jsonPath("$.deheupsr").value(DEFAULT_DEHEUPSR.toString()))
            .andExpect(jsonPath("$.demnttn").value(DEFAULT_DEMNTTN))
            .andExpect(jsonPath("$.demntto").value(DEFAULT_DEMNTTO))
            .andExpect(jsonPath("$.decetbr").value(DEFAULT_DECETBR))
            .andExpect(jsonPath("$.decetcs").value(DEFAULT_DECETCS))
            .andExpect(jsonPath("$.denumcs").value(DEFAULT_DENUMCS))
            .andExpect(jsonPath("$.denumbr").value(DEFAULT_DENUMBR))
            .andExpect(jsonPath("$.denumver").value(DEFAULT_DENUMVER))
            .andExpect(jsonPath("$.declote").value(DEFAULT_DECLOTE))
            .andExpect(jsonPath("$.date_saisie").value(DEFAULT_DATE_SAISIE.toString()))
            .andExpect(jsonPath("$.clorec").value(DEFAULT_CLOREC))
            .andExpect(jsonPath("$.demntvers").value(DEFAULT_DEMNTVERS));
    }

    @Test
    void getNonExistingBordereau() throws Exception {
        // Get the bordereau
        restBordereauMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingBordereau() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();

        // Update the bordereau
        Bordereau updatedBordereau = bordereauRepository.findById(bordereau.getId()).get();
        updatedBordereau
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .exercice(UPDATED_EXERCICE)
            .denbord(UPDATED_DENBORD)
            .det_deccent(UPDATED_DET_DECCENT)
            .det_decagenc(UPDATED_DET_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .cdmac(UPDATED_CDMAC)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .natbord(UPDATED_NATBORD)
            .detadedb(UPDATED_DETADEDB)
            .numb_pdat(UPDATED_NUMB_PDAT)
            .deheupsr(UPDATED_DEHEUPSR)
            .demnttn(UPDATED_DEMNTTN)
            .demntto(UPDATED_DEMNTTO)
            .decetbr(UPDATED_DECETBR)
            .decetcs(UPDATED_DECETCS)
            .denumcs(UPDATED_DENUMCS)
            .denumbr(UPDATED_DENUMBR)
            .denumver(UPDATED_DENUMVER)
            .declote(UPDATED_DECLOTE)
            .date_saisie(UPDATED_DATE_SAISIE)
            .clorec(UPDATED_CLOREC)
            .demntvers(UPDATED_DEMNTVERS);
        BordereauDTO bordereauDTO = bordereauMapper.toDto(updatedBordereau);

        restBordereauMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bordereauDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isOk());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
        Bordereau testBordereau = bordereauList.get(bordereauList.size() - 1);
        assertThat(testBordereau.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testBordereau.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testBordereau.getExercice()).isEqualTo(UPDATED_EXERCICE);
        assertThat(testBordereau.getDenbord()).isEqualTo(UPDATED_DENBORD);
        assertThat(testBordereau.getDet_deccent()).isEqualTo(UPDATED_DET_DECCENT);
        assertThat(testBordereau.getDet_decagenc()).isEqualTo(UPDATED_DET_DECAGENC);
        assertThat(testBordereau.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testBordereau.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testBordereau.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testBordereau.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testBordereau.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testBordereau.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testBordereau.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testBordereau.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testBordereau.getNatbord()).isEqualTo(UPDATED_NATBORD);
        assertThat(testBordereau.getDetadedb()).isEqualTo(UPDATED_DETADEDB);
        assertThat(testBordereau.getNumb_pdat()).isEqualTo(UPDATED_NUMB_PDAT);
        assertThat(testBordereau.getDeheupsr()).isEqualTo(UPDATED_DEHEUPSR);
        assertThat(testBordereau.getDemnttn()).isEqualTo(UPDATED_DEMNTTN);
        assertThat(testBordereau.getDemntto()).isEqualTo(UPDATED_DEMNTTO);
        assertThat(testBordereau.getDecetbr()).isEqualTo(UPDATED_DECETBR);
        assertThat(testBordereau.getDecetcs()).isEqualTo(UPDATED_DECETCS);
        assertThat(testBordereau.getDenumcs()).isEqualTo(UPDATED_DENUMCS);
        assertThat(testBordereau.getDenumbr()).isEqualTo(UPDATED_DENUMBR);
        assertThat(testBordereau.getDenumver()).isEqualTo(UPDATED_DENUMVER);
        assertThat(testBordereau.getDeclote()).isEqualTo(UPDATED_DECLOTE);
        assertThat(testBordereau.getDate_saisie()).isEqualTo(UPDATED_DATE_SAISIE);
        assertThat(testBordereau.getClorec()).isEqualTo(UPDATED_CLOREC);
        assertThat(testBordereau.getDemntvers()).isEqualTo(UPDATED_DEMNTVERS);
    }

    @Test
    void putNonExistingBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bordereauDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bordereauDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBordereauWithPatch() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();

        // Update the bordereau using partial update
        Bordereau partialUpdatedBordereau = new Bordereau();
        partialUpdatedBordereau.setId(bordereau.getId());

        partialUpdatedBordereau
            .deccent(UPDATED_DECCENT)
            .exercice(UPDATED_EXERCICE)
            .decserv(UPDATED_DECSERV)
            .cdmac(UPDATED_CDMAC)
            .decoper(UPDATED_DECOPER)
            .dedated(UPDATED_DEDATED)
            .detadedb(UPDATED_DETADEDB)
            .demntto(UPDATED_DEMNTTO)
            .denumcs(UPDATED_DENUMCS)
            .denumver(UPDATED_DENUMVER)
            .clorec(UPDATED_CLOREC)
            .demntvers(UPDATED_DEMNTVERS);

        restBordereauMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBordereau.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBordereau))
            )
            .andExpect(status().isOk());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
        Bordereau testBordereau = bordereauList.get(bordereauList.size() - 1);
        assertThat(testBordereau.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testBordereau.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testBordereau.getExercice()).isEqualTo(UPDATED_EXERCICE);
        assertThat(testBordereau.getDenbord()).isEqualTo(DEFAULT_DENBORD);
        assertThat(testBordereau.getDet_deccent()).isEqualTo(DEFAULT_DET_DECCENT);
        assertThat(testBordereau.getDet_decagenc()).isEqualTo(DEFAULT_DET_DECAGENC);
        assertThat(testBordereau.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testBordereau.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testBordereau.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testBordereau.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testBordereau.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testBordereau.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testBordereau.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testBordereau.getMatric1()).isEqualTo(DEFAULT_MATRIC_1);
        assertThat(testBordereau.getNatbord()).isEqualTo(DEFAULT_NATBORD);
        assertThat(testBordereau.getDetadedb()).isEqualTo(UPDATED_DETADEDB);
        assertThat(testBordereau.getNumb_pdat()).isEqualTo(DEFAULT_NUMB_PDAT);
        assertThat(testBordereau.getDeheupsr()).isEqualTo(DEFAULT_DEHEUPSR);
        assertThat(testBordereau.getDemnttn()).isEqualTo(DEFAULT_DEMNTTN);
        assertThat(testBordereau.getDemntto()).isEqualTo(UPDATED_DEMNTTO);
        assertThat(testBordereau.getDecetbr()).isEqualTo(DEFAULT_DECETBR);
        assertThat(testBordereau.getDecetcs()).isEqualTo(DEFAULT_DECETCS);
        assertThat(testBordereau.getDenumcs()).isEqualTo(UPDATED_DENUMCS);
        assertThat(testBordereau.getDenumbr()).isEqualTo(DEFAULT_DENUMBR);
        assertThat(testBordereau.getDenumver()).isEqualTo(UPDATED_DENUMVER);
        assertThat(testBordereau.getDeclote()).isEqualTo(DEFAULT_DECLOTE);
        assertThat(testBordereau.getDate_saisie()).isEqualTo(DEFAULT_DATE_SAISIE);
        assertThat(testBordereau.getClorec()).isEqualTo(UPDATED_CLOREC);
        assertThat(testBordereau.getDemntvers()).isEqualTo(UPDATED_DEMNTVERS);
    }

    @Test
    void fullUpdateBordereauWithPatch() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();

        // Update the bordereau using partial update
        Bordereau partialUpdatedBordereau = new Bordereau();
        partialUpdatedBordereau.setId(bordereau.getId());

        partialUpdatedBordereau
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .exercice(UPDATED_EXERCICE)
            .denbord(UPDATED_DENBORD)
            .det_deccent(UPDATED_DET_DECCENT)
            .det_decagenc(UPDATED_DET_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .cdmac(UPDATED_CDMAC)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .matric(UPDATED_MATRIC)
            .matric1(UPDATED_MATRIC_1)
            .natbord(UPDATED_NATBORD)
            .detadedb(UPDATED_DETADEDB)
            .numb_pdat(UPDATED_NUMB_PDAT)
            .deheupsr(UPDATED_DEHEUPSR)
            .demnttn(UPDATED_DEMNTTN)
            .demntto(UPDATED_DEMNTTO)
            .decetbr(UPDATED_DECETBR)
            .decetcs(UPDATED_DECETCS)
            .denumcs(UPDATED_DENUMCS)
            .denumbr(UPDATED_DENUMBR)
            .denumver(UPDATED_DENUMVER)
            .declote(UPDATED_DECLOTE)
            .date_saisie(UPDATED_DATE_SAISIE)
            .clorec(UPDATED_CLOREC)
            .demntvers(UPDATED_DEMNTVERS);

        restBordereauMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBordereau.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBordereau))
            )
            .andExpect(status().isOk());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
        Bordereau testBordereau = bordereauList.get(bordereauList.size() - 1);
        assertThat(testBordereau.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testBordereau.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testBordereau.getExercice()).isEqualTo(UPDATED_EXERCICE);
        assertThat(testBordereau.getDenbord()).isEqualTo(UPDATED_DENBORD);
        assertThat(testBordereau.getDet_deccent()).isEqualTo(UPDATED_DET_DECCENT);
        assertThat(testBordereau.getDet_decagenc()).isEqualTo(UPDATED_DET_DECAGENC);
        assertThat(testBordereau.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testBordereau.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testBordereau.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testBordereau.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testBordereau.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testBordereau.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testBordereau.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testBordereau.getMatric1()).isEqualTo(UPDATED_MATRIC_1);
        assertThat(testBordereau.getNatbord()).isEqualTo(UPDATED_NATBORD);
        assertThat(testBordereau.getDetadedb()).isEqualTo(UPDATED_DETADEDB);
        assertThat(testBordereau.getNumb_pdat()).isEqualTo(UPDATED_NUMB_PDAT);
        assertThat(testBordereau.getDeheupsr()).isEqualTo(UPDATED_DEHEUPSR);
        assertThat(testBordereau.getDemnttn()).isEqualTo(UPDATED_DEMNTTN);
        assertThat(testBordereau.getDemntto()).isEqualTo(UPDATED_DEMNTTO);
        assertThat(testBordereau.getDecetbr()).isEqualTo(UPDATED_DECETBR);
        assertThat(testBordereau.getDecetcs()).isEqualTo(UPDATED_DECETCS);
        assertThat(testBordereau.getDenumcs()).isEqualTo(UPDATED_DENUMCS);
        assertThat(testBordereau.getDenumbr()).isEqualTo(UPDATED_DENUMBR);
        assertThat(testBordereau.getDenumver()).isEqualTo(UPDATED_DENUMVER);
        assertThat(testBordereau.getDeclote()).isEqualTo(UPDATED_DECLOTE);
        assertThat(testBordereau.getDate_saisie()).isEqualTo(UPDATED_DATE_SAISIE);
        assertThat(testBordereau.getClorec()).isEqualTo(UPDATED_CLOREC);
        assertThat(testBordereau.getDemntvers()).isEqualTo(UPDATED_DEMNTVERS);
    }

    @Test
    void patchNonExistingBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bordereauDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBordereau() throws Exception {
        int databaseSizeBeforeUpdate = bordereauRepository.findAll().size();
        bordereau.setId(UUID.randomUUID().toString());

        // Create the Bordereau
        BordereauDTO bordereauDTO = bordereauMapper.toDto(bordereau);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBordereauMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bordereauDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bordereau in the database
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBordereau() throws Exception {
        // Initialize the database
        bordereauRepository.save(bordereau);

        int databaseSizeBeforeDelete = bordereauRepository.findAll().size();

        // Delete the bordereau
        restBordereauMockMvc
            .perform(delete(ENTITY_API_URL_ID, bordereau.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bordereau> bordereauList = bordereauRepository.findAll();
        assertThat(bordereauList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
