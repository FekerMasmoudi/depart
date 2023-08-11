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
import tn.soretras.depart.domain.Machine;
import tn.soretras.depart.repository.MachineRepository;
import tn.soretras.depart.service.dto.MachineDTO;
import tn.soretras.depart.service.mapper.MachineMapper;

/**
 * Integration tests for the {@link MachineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MachineResourceIT {

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final String DEFAULT_CDMOD = "AAAAAAAAAA";
    private static final String UPDATED_CDMOD = "BBBBBBBBBB";

    private static final String DEFAULT_CDMARQUE = "AAAAAAAAAA";
    private static final String UPDATED_CDMARQUE = "BBBBBBBBBB";

    private static final String DEFAULT_LBMAC = "AAAAAAAAAA";
    private static final String UPDATED_LBMAC = "BBBBBBBBBB";

    private static final String DEFAULT_REFMAC = "AAAAAAAAAA";
    private static final String UPDATED_REFMAC = "BBBBBBBBBB";

    private static final String DEFAULT_SERIE = "AAAAAAAAAA";
    private static final String UPDATED_SERIE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATFAB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATFAB = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATACQ = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATACQ = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATMES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATMES = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_VALACQ = 1;
    private static final Integer UPDATED_VALACQ = 2;

    private static final String DEFAULT_OBS = "AAAAAAAAAA";
    private static final String UPDATED_OBS = "BBBBBBBBBB";

    private static final String DEFAULT_NUMPLAN = "AAAAAAAAAA";
    private static final String UPDATED_NUMPLAN = "BBBBBBBBBB";

    private static final String DEFAULT_CDLIPRO = "AAAAAAAAAA";
    private static final String UPDATED_CDLIPRO = "BBBBBBBBBB";

    private static final String DEFAULT_IMMAT = "AAAAAAAAAA";
    private static final String UPDATED_IMMAT = "BBBBBBBBBB";

    private static final String DEFAULT_MARQUE = "AAAAAAAAAA";
    private static final String UPDATED_MARQUE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPEV = "AAAAAAAAAA";
    private static final String UPDATED_TYPEV = "BBBBBBBBBB";

    private static final String DEFAULT_NUMSER = "AAAAAAAAAA";
    private static final String UPDATED_NUMSER = "BBBBBBBBBB";

    private static final String DEFAULT_PUISS = "AAAAAAAAAA";
    private static final String UPDATED_PUISS = "BBBBBBBBBB";

    private static final String DEFAULT_NRJ = "AAAAAAAAAA";
    private static final String UPDATED_NRJ = "BBBBBBBBBB";

    private static final String DEFAULT_GENRE = "AAAAAAAAAA";
    private static final String UPDATED_GENRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CYLIND = 1;
    private static final Integer UPDATED_CYLIND = 2;

    private static final Integer DEFAULT_PDSVID = 1;
    private static final Integer UPDATED_PDSVID = 2;

    private static final Integer DEFAULT_CHARGE = 1;
    private static final Integer UPDATED_CHARGE = 2;

    private static final Integer DEFAULT_PLCASS = 1;
    private static final Integer UPDATED_PLCASS = 2;

    private static final Integer DEFAULT_PLCDEB = 1;
    private static final Integer UPDATED_PLCDEB = 2;

    private static final Integer DEFAULT_CPT = 1;
    private static final Integer UPDATED_CPT = 2;

    private static final Integer DEFAULT_CPTMNT = 1;
    private static final Integer UPDATED_CPTMNT = 2;

    private static final Integer DEFAULT_ACTIF = 1;
    private static final Integer UPDATED_ACTIF = 2;

    private static final LocalDate DEFAULT_DATACT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATACT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CDCATVH = "AAAAAAAAAA";
    private static final String UPDATED_CDCATVH = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAUX = 1;
    private static final Integer UPDATED_TAUX = 2;

    private static final Integer DEFAULT_KMMOY = 1;
    private static final Integer UPDATED_KMMOY = 2;

    private static final Integer DEFAULT_CODSTAT = 1;
    private static final Integer UPDATED_CODSTAT = 2;

    private static final String DEFAULT_EDITION = "AAAAAAAAAA";
    private static final String UPDATED_EDITION = "BBBBBBBBBB";

    private static final Integer DEFAULT_VALASSUR = 1;
    private static final Integer UPDATED_VALASSUR = 2;

    private static final Integer DEFAULT_VALAMORT = 1;
    private static final Integer UPDATED_VALAMORT = 2;

    private static final Integer DEFAULT_CONSOMMODEL = 1;
    private static final Integer UPDATED_CONSOMMODEL = 2;

    private static final String DEFAULT_DECETAT = "AAAAAAAAAA";
    private static final String UPDATED_DECETAT = "BBBBBBBBBB";

    private static final String DEFAULT_CODTYPVOIT = "AAAAAAAAAA";
    private static final String UPDATED_CODTYPVOIT = "BBBBBBBBBB";

    private static final String DEFAULT_CDTYP = "AAAAAAAAAA";
    private static final String UPDATED_CDTYP = "BBBBBBBBBB";

    private static final Integer DEFAULT_CDNAT = 1;
    private static final Integer UPDATED_CDNAT = 2;

    private static final String DEFAULT_TYPBV = "AAAAAAAAAA";
    private static final String UPDATED_TYPBV = "BBBBBBBBBB";

    private static final String DEFAULT_CDTYPBV = "AAAAAAAAAA";
    private static final String UPDATED_CDTYPBV = "BBBBBBBBBB";

    private static final String DEFAULT_PNEU = "AAAAAAAAAA";
    private static final String UPDATED_PNEU = "BBBBBBBBBB";

    private static final String DEFAULT_GPS = "AAAAAAAAAA";
    private static final String UPDATED_GPS = "BBBBBBBBBB";

    private static final String DEFAULT_MARQUEBV = "AAAAAAAAAA";
    private static final String UPDATED_MARQUEBV = "BBBBBBBBBB";

    private static final String DEFAULT_TYPBOITE = "AAAAAAAAAA";
    private static final String UPDATED_TYPBOITE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/machines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private MachineMapper machineMapper;

    @Autowired
    private MockMvc restMachineMockMvc;

    private Machine machine;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Machine createEntity() {
        Machine machine = new Machine()
            .cdmac(DEFAULT_CDMAC)
            .cdmod(DEFAULT_CDMOD)
            .cdmarque(DEFAULT_CDMARQUE)
            .lbmac(DEFAULT_LBMAC)
            .refmac(DEFAULT_REFMAC)
            .serie(DEFAULT_SERIE)
            .datfab(DEFAULT_DATFAB)
            .datacq(DEFAULT_DATACQ)
            .datmes(DEFAULT_DATMES)
            .valacq(DEFAULT_VALACQ)
            .obs(DEFAULT_OBS)
            .numplan(DEFAULT_NUMPLAN)
            .cdlipro(DEFAULT_CDLIPRO)
            .immat(DEFAULT_IMMAT)
            .marque(DEFAULT_MARQUE)
            .typev(DEFAULT_TYPEV)
            .numser(DEFAULT_NUMSER)
            .puiss(DEFAULT_PUISS)
            .nrj(DEFAULT_NRJ)
            .genre(DEFAULT_GENRE)
            .cylind(DEFAULT_CYLIND)
            .pdsvid(DEFAULT_PDSVID)
            .charge(DEFAULT_CHARGE)
            .plcass(DEFAULT_PLCASS)
            .plcdeb(DEFAULT_PLCDEB)
            .cpt(DEFAULT_CPT)
            .cptmnt(DEFAULT_CPTMNT)
            .actif(DEFAULT_ACTIF)
            .datact(DEFAULT_DATACT)
            .cdcatvh(DEFAULT_CDCATVH)
            .taux(DEFAULT_TAUX)
            .kmmoy(DEFAULT_KMMOY)
            .codstat(DEFAULT_CODSTAT)
            .edition(DEFAULT_EDITION)
            .valassur(DEFAULT_VALASSUR)
            .valamort(DEFAULT_VALAMORT)
            .consommodel(DEFAULT_CONSOMMODEL)
            .decetat(DEFAULT_DECETAT)
            .codtypvoit(DEFAULT_CODTYPVOIT)
            .cdtyp(DEFAULT_CDTYP)
            .cdnat(DEFAULT_CDNAT)
            .typbv(DEFAULT_TYPBV)
            .cdtypbv(DEFAULT_CDTYPBV)
            .pneu(DEFAULT_PNEU)
            .gps(DEFAULT_GPS)
            .marquebv(DEFAULT_MARQUEBV)
            .typboite(DEFAULT_TYPBOITE);
        return machine;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Machine createUpdatedEntity() {
        Machine machine = new Machine()
            .cdmac(UPDATED_CDMAC)
            .cdmod(UPDATED_CDMOD)
            .cdmarque(UPDATED_CDMARQUE)
            .lbmac(UPDATED_LBMAC)
            .refmac(UPDATED_REFMAC)
            .serie(UPDATED_SERIE)
            .datfab(UPDATED_DATFAB)
            .datacq(UPDATED_DATACQ)
            .datmes(UPDATED_DATMES)
            .valacq(UPDATED_VALACQ)
            .obs(UPDATED_OBS)
            .numplan(UPDATED_NUMPLAN)
            .cdlipro(UPDATED_CDLIPRO)
            .immat(UPDATED_IMMAT)
            .marque(UPDATED_MARQUE)
            .typev(UPDATED_TYPEV)
            .numser(UPDATED_NUMSER)
            .puiss(UPDATED_PUISS)
            .nrj(UPDATED_NRJ)
            .genre(UPDATED_GENRE)
            .cylind(UPDATED_CYLIND)
            .pdsvid(UPDATED_PDSVID)
            .charge(UPDATED_CHARGE)
            .plcass(UPDATED_PLCASS)
            .plcdeb(UPDATED_PLCDEB)
            .cpt(UPDATED_CPT)
            .cptmnt(UPDATED_CPTMNT)
            .actif(UPDATED_ACTIF)
            .datact(UPDATED_DATACT)
            .cdcatvh(UPDATED_CDCATVH)
            .taux(UPDATED_TAUX)
            .kmmoy(UPDATED_KMMOY)
            .codstat(UPDATED_CODSTAT)
            .edition(UPDATED_EDITION)
            .valassur(UPDATED_VALASSUR)
            .valamort(UPDATED_VALAMORT)
            .consommodel(UPDATED_CONSOMMODEL)
            .decetat(UPDATED_DECETAT)
            .codtypvoit(UPDATED_CODTYPVOIT)
            .cdtyp(UPDATED_CDTYP)
            .cdnat(UPDATED_CDNAT)
            .typbv(UPDATED_TYPBV)
            .cdtypbv(UPDATED_CDTYPBV)
            .pneu(UPDATED_PNEU)
            .gps(UPDATED_GPS)
            .marquebv(UPDATED_MARQUEBV)
            .typboite(UPDATED_TYPBOITE);
        return machine;
    }

    @BeforeEach
    public void initTest() {
        machineRepository.deleteAll();
        machine = createEntity();
    }

    @Test
    void createMachine() throws Exception {
        int databaseSizeBeforeCreate = machineRepository.findAll().size();
        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);
        restMachineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isCreated());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeCreate + 1);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testMachine.getCdmod()).isEqualTo(DEFAULT_CDMOD);
        assertThat(testMachine.getCdmarque()).isEqualTo(DEFAULT_CDMARQUE);
        assertThat(testMachine.getLbmac()).isEqualTo(DEFAULT_LBMAC);
        assertThat(testMachine.getRefmac()).isEqualTo(DEFAULT_REFMAC);
        assertThat(testMachine.getSerie()).isEqualTo(DEFAULT_SERIE);
        assertThat(testMachine.getDatfab()).isEqualTo(DEFAULT_DATFAB);
        assertThat(testMachine.getDatacq()).isEqualTo(DEFAULT_DATACQ);
        assertThat(testMachine.getDatmes()).isEqualTo(DEFAULT_DATMES);
        assertThat(testMachine.getValacq()).isEqualTo(DEFAULT_VALACQ);
        assertThat(testMachine.getObs()).isEqualTo(DEFAULT_OBS);
        assertThat(testMachine.getNumplan()).isEqualTo(DEFAULT_NUMPLAN);
        assertThat(testMachine.getCdlipro()).isEqualTo(DEFAULT_CDLIPRO);
        assertThat(testMachine.getImmat()).isEqualTo(DEFAULT_IMMAT);
        assertThat(testMachine.getMarque()).isEqualTo(DEFAULT_MARQUE);
        assertThat(testMachine.getTypev()).isEqualTo(DEFAULT_TYPEV);
        assertThat(testMachine.getNumser()).isEqualTo(DEFAULT_NUMSER);
        assertThat(testMachine.getPuiss()).isEqualTo(DEFAULT_PUISS);
        assertThat(testMachine.getNrj()).isEqualTo(DEFAULT_NRJ);
        assertThat(testMachine.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testMachine.getCylind()).isEqualTo(DEFAULT_CYLIND);
        assertThat(testMachine.getPdsvid()).isEqualTo(DEFAULT_PDSVID);
        assertThat(testMachine.getCharge()).isEqualTo(DEFAULT_CHARGE);
        assertThat(testMachine.getPlcass()).isEqualTo(DEFAULT_PLCASS);
        assertThat(testMachine.getPlcdeb()).isEqualTo(DEFAULT_PLCDEB);
        assertThat(testMachine.getCpt()).isEqualTo(DEFAULT_CPT);
        assertThat(testMachine.getCptmnt()).isEqualTo(DEFAULT_CPTMNT);
        assertThat(testMachine.getActif()).isEqualTo(DEFAULT_ACTIF);
        assertThat(testMachine.getDatact()).isEqualTo(DEFAULT_DATACT);
        assertThat(testMachine.getCdcatvh()).isEqualTo(DEFAULT_CDCATVH);
        assertThat(testMachine.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testMachine.getKmmoy()).isEqualTo(DEFAULT_KMMOY);
        assertThat(testMachine.getCodstat()).isEqualTo(DEFAULT_CODSTAT);
        assertThat(testMachine.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testMachine.getValassur()).isEqualTo(DEFAULT_VALASSUR);
        assertThat(testMachine.getValamort()).isEqualTo(DEFAULT_VALAMORT);
        assertThat(testMachine.getConsommodel()).isEqualTo(DEFAULT_CONSOMMODEL);
        assertThat(testMachine.getDecetat()).isEqualTo(DEFAULT_DECETAT);
        assertThat(testMachine.getCodtypvoit()).isEqualTo(DEFAULT_CODTYPVOIT);
        assertThat(testMachine.getCdtyp()).isEqualTo(DEFAULT_CDTYP);
        assertThat(testMachine.getCdnat()).isEqualTo(DEFAULT_CDNAT);
        assertThat(testMachine.getTypbv()).isEqualTo(DEFAULT_TYPBV);
        assertThat(testMachine.getCdtypbv()).isEqualTo(DEFAULT_CDTYPBV);
        assertThat(testMachine.getPneu()).isEqualTo(DEFAULT_PNEU);
        assertThat(testMachine.getGps()).isEqualTo(DEFAULT_GPS);
        assertThat(testMachine.getMarquebv()).isEqualTo(DEFAULT_MARQUEBV);
        assertThat(testMachine.getTypboite()).isEqualTo(DEFAULT_TYPBOITE);
    }

    @Test
    void createMachineWithExistingId() throws Exception {
        // Create the Machine with an existing ID
        machine.setId("existing_id");
        MachineDTO machineDTO = machineMapper.toDto(machine);

        int databaseSizeBeforeCreate = machineRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMachineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllMachines() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        // Get all the machineList
        restMachineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(machine.getId())))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].cdmod").value(hasItem(DEFAULT_CDMOD)))
            .andExpect(jsonPath("$.[*].cdmarque").value(hasItem(DEFAULT_CDMARQUE)))
            .andExpect(jsonPath("$.[*].lbmac").value(hasItem(DEFAULT_LBMAC)))
            .andExpect(jsonPath("$.[*].refmac").value(hasItem(DEFAULT_REFMAC)))
            .andExpect(jsonPath("$.[*].serie").value(hasItem(DEFAULT_SERIE)))
            .andExpect(jsonPath("$.[*].datfab").value(hasItem(DEFAULT_DATFAB.toString())))
            .andExpect(jsonPath("$.[*].datacq").value(hasItem(DEFAULT_DATACQ.toString())))
            .andExpect(jsonPath("$.[*].datmes").value(hasItem(DEFAULT_DATMES.toString())))
            .andExpect(jsonPath("$.[*].valacq").value(hasItem(DEFAULT_VALACQ)))
            .andExpect(jsonPath("$.[*].obs").value(hasItem(DEFAULT_OBS)))
            .andExpect(jsonPath("$.[*].numplan").value(hasItem(DEFAULT_NUMPLAN)))
            .andExpect(jsonPath("$.[*].cdlipro").value(hasItem(DEFAULT_CDLIPRO)))
            .andExpect(jsonPath("$.[*].immat").value(hasItem(DEFAULT_IMMAT)))
            .andExpect(jsonPath("$.[*].marque").value(hasItem(DEFAULT_MARQUE)))
            .andExpect(jsonPath("$.[*].typev").value(hasItem(DEFAULT_TYPEV)))
            .andExpect(jsonPath("$.[*].numser").value(hasItem(DEFAULT_NUMSER)))
            .andExpect(jsonPath("$.[*].puiss").value(hasItem(DEFAULT_PUISS)))
            .andExpect(jsonPath("$.[*].nrj").value(hasItem(DEFAULT_NRJ)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].cylind").value(hasItem(DEFAULT_CYLIND)))
            .andExpect(jsonPath("$.[*].pdsvid").value(hasItem(DEFAULT_PDSVID)))
            .andExpect(jsonPath("$.[*].charge").value(hasItem(DEFAULT_CHARGE)))
            .andExpect(jsonPath("$.[*].plcass").value(hasItem(DEFAULT_PLCASS)))
            .andExpect(jsonPath("$.[*].plcdeb").value(hasItem(DEFAULT_PLCDEB)))
            .andExpect(jsonPath("$.[*].cpt").value(hasItem(DEFAULT_CPT)))
            .andExpect(jsonPath("$.[*].cptmnt").value(hasItem(DEFAULT_CPTMNT)))
            .andExpect(jsonPath("$.[*].actif").value(hasItem(DEFAULT_ACTIF)))
            .andExpect(jsonPath("$.[*].datact").value(hasItem(DEFAULT_DATACT.toString())))
            .andExpect(jsonPath("$.[*].cdcatvh").value(hasItem(DEFAULT_CDCATVH)))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX)))
            .andExpect(jsonPath("$.[*].kmmoy").value(hasItem(DEFAULT_KMMOY)))
            .andExpect(jsonPath("$.[*].codstat").value(hasItem(DEFAULT_CODSTAT)))
            .andExpect(jsonPath("$.[*].edition").value(hasItem(DEFAULT_EDITION)))
            .andExpect(jsonPath("$.[*].valassur").value(hasItem(DEFAULT_VALASSUR)))
            .andExpect(jsonPath("$.[*].valamort").value(hasItem(DEFAULT_VALAMORT)))
            .andExpect(jsonPath("$.[*].consommodel").value(hasItem(DEFAULT_CONSOMMODEL)))
            .andExpect(jsonPath("$.[*].decetat").value(hasItem(DEFAULT_DECETAT)))
            .andExpect(jsonPath("$.[*].codtypvoit").value(hasItem(DEFAULT_CODTYPVOIT)))
            .andExpect(jsonPath("$.[*].cdtyp").value(hasItem(DEFAULT_CDTYP)))
            .andExpect(jsonPath("$.[*].cdnat").value(hasItem(DEFAULT_CDNAT)))
            .andExpect(jsonPath("$.[*].typbv").value(hasItem(DEFAULT_TYPBV)))
            .andExpect(jsonPath("$.[*].cdtypbv").value(hasItem(DEFAULT_CDTYPBV)))
            .andExpect(jsonPath("$.[*].pneu").value(hasItem(DEFAULT_PNEU)))
            .andExpect(jsonPath("$.[*].gps").value(hasItem(DEFAULT_GPS)))
            .andExpect(jsonPath("$.[*].marquebv").value(hasItem(DEFAULT_MARQUEBV)))
            .andExpect(jsonPath("$.[*].typboite").value(hasItem(DEFAULT_TYPBOITE)));
    }

    @Test
    void getMachine() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        // Get the machine
        restMachineMockMvc
            .perform(get(ENTITY_API_URL_ID, machine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(machine.getId()))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.cdmod").value(DEFAULT_CDMOD))
            .andExpect(jsonPath("$.cdmarque").value(DEFAULT_CDMARQUE))
            .andExpect(jsonPath("$.lbmac").value(DEFAULT_LBMAC))
            .andExpect(jsonPath("$.refmac").value(DEFAULT_REFMAC))
            .andExpect(jsonPath("$.serie").value(DEFAULT_SERIE))
            .andExpect(jsonPath("$.datfab").value(DEFAULT_DATFAB.toString()))
            .andExpect(jsonPath("$.datacq").value(DEFAULT_DATACQ.toString()))
            .andExpect(jsonPath("$.datmes").value(DEFAULT_DATMES.toString()))
            .andExpect(jsonPath("$.valacq").value(DEFAULT_VALACQ))
            .andExpect(jsonPath("$.obs").value(DEFAULT_OBS))
            .andExpect(jsonPath("$.numplan").value(DEFAULT_NUMPLAN))
            .andExpect(jsonPath("$.cdlipro").value(DEFAULT_CDLIPRO))
            .andExpect(jsonPath("$.immat").value(DEFAULT_IMMAT))
            .andExpect(jsonPath("$.marque").value(DEFAULT_MARQUE))
            .andExpect(jsonPath("$.typev").value(DEFAULT_TYPEV))
            .andExpect(jsonPath("$.numser").value(DEFAULT_NUMSER))
            .andExpect(jsonPath("$.puiss").value(DEFAULT_PUISS))
            .andExpect(jsonPath("$.nrj").value(DEFAULT_NRJ))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.cylind").value(DEFAULT_CYLIND))
            .andExpect(jsonPath("$.pdsvid").value(DEFAULT_PDSVID))
            .andExpect(jsonPath("$.charge").value(DEFAULT_CHARGE))
            .andExpect(jsonPath("$.plcass").value(DEFAULT_PLCASS))
            .andExpect(jsonPath("$.plcdeb").value(DEFAULT_PLCDEB))
            .andExpect(jsonPath("$.cpt").value(DEFAULT_CPT))
            .andExpect(jsonPath("$.cptmnt").value(DEFAULT_CPTMNT))
            .andExpect(jsonPath("$.actif").value(DEFAULT_ACTIF))
            .andExpect(jsonPath("$.datact").value(DEFAULT_DATACT.toString()))
            .andExpect(jsonPath("$.cdcatvh").value(DEFAULT_CDCATVH))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX))
            .andExpect(jsonPath("$.kmmoy").value(DEFAULT_KMMOY))
            .andExpect(jsonPath("$.codstat").value(DEFAULT_CODSTAT))
            .andExpect(jsonPath("$.edition").value(DEFAULT_EDITION))
            .andExpect(jsonPath("$.valassur").value(DEFAULT_VALASSUR))
            .andExpect(jsonPath("$.valamort").value(DEFAULT_VALAMORT))
            .andExpect(jsonPath("$.consommodel").value(DEFAULT_CONSOMMODEL))
            .andExpect(jsonPath("$.decetat").value(DEFAULT_DECETAT))
            .andExpect(jsonPath("$.codtypvoit").value(DEFAULT_CODTYPVOIT))
            .andExpect(jsonPath("$.cdtyp").value(DEFAULT_CDTYP))
            .andExpect(jsonPath("$.cdnat").value(DEFAULT_CDNAT))
            .andExpect(jsonPath("$.typbv").value(DEFAULT_TYPBV))
            .andExpect(jsonPath("$.cdtypbv").value(DEFAULT_CDTYPBV))
            .andExpect(jsonPath("$.pneu").value(DEFAULT_PNEU))
            .andExpect(jsonPath("$.gps").value(DEFAULT_GPS))
            .andExpect(jsonPath("$.marquebv").value(DEFAULT_MARQUEBV))
            .andExpect(jsonPath("$.typboite").value(DEFAULT_TYPBOITE));
    }

    @Test
    void getNonExistingMachine() throws Exception {
        // Get the machine
        restMachineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingMachine() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        int databaseSizeBeforeUpdate = machineRepository.findAll().size();

        // Update the machine
        Machine updatedMachine = machineRepository.findById(machine.getId()).get();
        updatedMachine
            .cdmac(UPDATED_CDMAC)
            .cdmod(UPDATED_CDMOD)
            .cdmarque(UPDATED_CDMARQUE)
            .lbmac(UPDATED_LBMAC)
            .refmac(UPDATED_REFMAC)
            .serie(UPDATED_SERIE)
            .datfab(UPDATED_DATFAB)
            .datacq(UPDATED_DATACQ)
            .datmes(UPDATED_DATMES)
            .valacq(UPDATED_VALACQ)
            .obs(UPDATED_OBS)
            .numplan(UPDATED_NUMPLAN)
            .cdlipro(UPDATED_CDLIPRO)
            .immat(UPDATED_IMMAT)
            .marque(UPDATED_MARQUE)
            .typev(UPDATED_TYPEV)
            .numser(UPDATED_NUMSER)
            .puiss(UPDATED_PUISS)
            .nrj(UPDATED_NRJ)
            .genre(UPDATED_GENRE)
            .cylind(UPDATED_CYLIND)
            .pdsvid(UPDATED_PDSVID)
            .charge(UPDATED_CHARGE)
            .plcass(UPDATED_PLCASS)
            .plcdeb(UPDATED_PLCDEB)
            .cpt(UPDATED_CPT)
            .cptmnt(UPDATED_CPTMNT)
            .actif(UPDATED_ACTIF)
            .datact(UPDATED_DATACT)
            .cdcatvh(UPDATED_CDCATVH)
            .taux(UPDATED_TAUX)
            .kmmoy(UPDATED_KMMOY)
            .codstat(UPDATED_CODSTAT)
            .edition(UPDATED_EDITION)
            .valassur(UPDATED_VALASSUR)
            .valamort(UPDATED_VALAMORT)
            .consommodel(UPDATED_CONSOMMODEL)
            .decetat(UPDATED_DECETAT)
            .codtypvoit(UPDATED_CODTYPVOIT)
            .cdtyp(UPDATED_CDTYP)
            .cdnat(UPDATED_CDNAT)
            .typbv(UPDATED_TYPBV)
            .cdtypbv(UPDATED_CDTYPBV)
            .pneu(UPDATED_PNEU)
            .gps(UPDATED_GPS)
            .marquebv(UPDATED_MARQUEBV)
            .typboite(UPDATED_TYPBOITE);
        MachineDTO machineDTO = machineMapper.toDto(updatedMachine);

        restMachineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, machineDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isOk());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testMachine.getCdmod()).isEqualTo(UPDATED_CDMOD);
        assertThat(testMachine.getCdmarque()).isEqualTo(UPDATED_CDMARQUE);
        assertThat(testMachine.getLbmac()).isEqualTo(UPDATED_LBMAC);
        assertThat(testMachine.getRefmac()).isEqualTo(UPDATED_REFMAC);
        assertThat(testMachine.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testMachine.getDatfab()).isEqualTo(UPDATED_DATFAB);
        assertThat(testMachine.getDatacq()).isEqualTo(UPDATED_DATACQ);
        assertThat(testMachine.getDatmes()).isEqualTo(UPDATED_DATMES);
        assertThat(testMachine.getValacq()).isEqualTo(UPDATED_VALACQ);
        assertThat(testMachine.getObs()).isEqualTo(UPDATED_OBS);
        assertThat(testMachine.getNumplan()).isEqualTo(UPDATED_NUMPLAN);
        assertThat(testMachine.getCdlipro()).isEqualTo(UPDATED_CDLIPRO);
        assertThat(testMachine.getImmat()).isEqualTo(UPDATED_IMMAT);
        assertThat(testMachine.getMarque()).isEqualTo(UPDATED_MARQUE);
        assertThat(testMachine.getTypev()).isEqualTo(UPDATED_TYPEV);
        assertThat(testMachine.getNumser()).isEqualTo(UPDATED_NUMSER);
        assertThat(testMachine.getPuiss()).isEqualTo(UPDATED_PUISS);
        assertThat(testMachine.getNrj()).isEqualTo(UPDATED_NRJ);
        assertThat(testMachine.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testMachine.getCylind()).isEqualTo(UPDATED_CYLIND);
        assertThat(testMachine.getPdsvid()).isEqualTo(UPDATED_PDSVID);
        assertThat(testMachine.getCharge()).isEqualTo(UPDATED_CHARGE);
        assertThat(testMachine.getPlcass()).isEqualTo(UPDATED_PLCASS);
        assertThat(testMachine.getPlcdeb()).isEqualTo(UPDATED_PLCDEB);
        assertThat(testMachine.getCpt()).isEqualTo(UPDATED_CPT);
        assertThat(testMachine.getCptmnt()).isEqualTo(UPDATED_CPTMNT);
        assertThat(testMachine.getActif()).isEqualTo(UPDATED_ACTIF);
        assertThat(testMachine.getDatact()).isEqualTo(UPDATED_DATACT);
        assertThat(testMachine.getCdcatvh()).isEqualTo(UPDATED_CDCATVH);
        assertThat(testMachine.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testMachine.getKmmoy()).isEqualTo(UPDATED_KMMOY);
        assertThat(testMachine.getCodstat()).isEqualTo(UPDATED_CODSTAT);
        assertThat(testMachine.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testMachine.getValassur()).isEqualTo(UPDATED_VALASSUR);
        assertThat(testMachine.getValamort()).isEqualTo(UPDATED_VALAMORT);
        assertThat(testMachine.getConsommodel()).isEqualTo(UPDATED_CONSOMMODEL);
        assertThat(testMachine.getDecetat()).isEqualTo(UPDATED_DECETAT);
        assertThat(testMachine.getCodtypvoit()).isEqualTo(UPDATED_CODTYPVOIT);
        assertThat(testMachine.getCdtyp()).isEqualTo(UPDATED_CDTYP);
        assertThat(testMachine.getCdnat()).isEqualTo(UPDATED_CDNAT);
        assertThat(testMachine.getTypbv()).isEqualTo(UPDATED_TYPBV);
        assertThat(testMachine.getCdtypbv()).isEqualTo(UPDATED_CDTYPBV);
        assertThat(testMachine.getPneu()).isEqualTo(UPDATED_PNEU);
        assertThat(testMachine.getGps()).isEqualTo(UPDATED_GPS);
        assertThat(testMachine.getMarquebv()).isEqualTo(UPDATED_MARQUEBV);
        assertThat(testMachine.getTypboite()).isEqualTo(UPDATED_TYPBOITE);
    }

    @Test
    void putNonExistingMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, machineDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(machineDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMachineWithPatch() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        int databaseSizeBeforeUpdate = machineRepository.findAll().size();

        // Update the machine using partial update
        Machine partialUpdatedMachine = new Machine();
        partialUpdatedMachine.setId(machine.getId());

        partialUpdatedMachine
            .cdmod(UPDATED_CDMOD)
            .refmac(UPDATED_REFMAC)
            .serie(UPDATED_SERIE)
            .cdlipro(UPDATED_CDLIPRO)
            .immat(UPDATED_IMMAT)
            .marque(UPDATED_MARQUE)
            .numser(UPDATED_NUMSER)
            .nrj(UPDATED_NRJ)
            .cptmnt(UPDATED_CPTMNT)
            .datact(UPDATED_DATACT)
            .cdcatvh(UPDATED_CDCATVH)
            .kmmoy(UPDATED_KMMOY)
            .codstat(UPDATED_CODSTAT)
            .consommodel(UPDATED_CONSOMMODEL)
            .decetat(UPDATED_DECETAT)
            .codtypvoit(UPDATED_CODTYPVOIT)
            .cdtyp(UPDATED_CDTYP)
            .typbv(UPDATED_TYPBV)
            .cdtypbv(UPDATED_CDTYPBV)
            .pneu(UPDATED_PNEU)
            .gps(UPDATED_GPS)
            .marquebv(UPDATED_MARQUEBV)
            .typboite(UPDATED_TYPBOITE);

        restMachineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMachine.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMachine))
            )
            .andExpect(status().isOk());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testMachine.getCdmod()).isEqualTo(UPDATED_CDMOD);
        assertThat(testMachine.getCdmarque()).isEqualTo(DEFAULT_CDMARQUE);
        assertThat(testMachine.getLbmac()).isEqualTo(DEFAULT_LBMAC);
        assertThat(testMachine.getRefmac()).isEqualTo(UPDATED_REFMAC);
        assertThat(testMachine.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testMachine.getDatfab()).isEqualTo(DEFAULT_DATFAB);
        assertThat(testMachine.getDatacq()).isEqualTo(DEFAULT_DATACQ);
        assertThat(testMachine.getDatmes()).isEqualTo(DEFAULT_DATMES);
        assertThat(testMachine.getValacq()).isEqualTo(DEFAULT_VALACQ);
        assertThat(testMachine.getObs()).isEqualTo(DEFAULT_OBS);
        assertThat(testMachine.getNumplan()).isEqualTo(DEFAULT_NUMPLAN);
        assertThat(testMachine.getCdlipro()).isEqualTo(UPDATED_CDLIPRO);
        assertThat(testMachine.getImmat()).isEqualTo(UPDATED_IMMAT);
        assertThat(testMachine.getMarque()).isEqualTo(UPDATED_MARQUE);
        assertThat(testMachine.getTypev()).isEqualTo(DEFAULT_TYPEV);
        assertThat(testMachine.getNumser()).isEqualTo(UPDATED_NUMSER);
        assertThat(testMachine.getPuiss()).isEqualTo(DEFAULT_PUISS);
        assertThat(testMachine.getNrj()).isEqualTo(UPDATED_NRJ);
        assertThat(testMachine.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testMachine.getCylind()).isEqualTo(DEFAULT_CYLIND);
        assertThat(testMachine.getPdsvid()).isEqualTo(DEFAULT_PDSVID);
        assertThat(testMachine.getCharge()).isEqualTo(DEFAULT_CHARGE);
        assertThat(testMachine.getPlcass()).isEqualTo(DEFAULT_PLCASS);
        assertThat(testMachine.getPlcdeb()).isEqualTo(DEFAULT_PLCDEB);
        assertThat(testMachine.getCpt()).isEqualTo(DEFAULT_CPT);
        assertThat(testMachine.getCptmnt()).isEqualTo(UPDATED_CPTMNT);
        assertThat(testMachine.getActif()).isEqualTo(DEFAULT_ACTIF);
        assertThat(testMachine.getDatact()).isEqualTo(UPDATED_DATACT);
        assertThat(testMachine.getCdcatvh()).isEqualTo(UPDATED_CDCATVH);
        assertThat(testMachine.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testMachine.getKmmoy()).isEqualTo(UPDATED_KMMOY);
        assertThat(testMachine.getCodstat()).isEqualTo(UPDATED_CODSTAT);
        assertThat(testMachine.getEdition()).isEqualTo(DEFAULT_EDITION);
        assertThat(testMachine.getValassur()).isEqualTo(DEFAULT_VALASSUR);
        assertThat(testMachine.getValamort()).isEqualTo(DEFAULT_VALAMORT);
        assertThat(testMachine.getConsommodel()).isEqualTo(UPDATED_CONSOMMODEL);
        assertThat(testMachine.getDecetat()).isEqualTo(UPDATED_DECETAT);
        assertThat(testMachine.getCodtypvoit()).isEqualTo(UPDATED_CODTYPVOIT);
        assertThat(testMachine.getCdtyp()).isEqualTo(UPDATED_CDTYP);
        assertThat(testMachine.getCdnat()).isEqualTo(DEFAULT_CDNAT);
        assertThat(testMachine.getTypbv()).isEqualTo(UPDATED_TYPBV);
        assertThat(testMachine.getCdtypbv()).isEqualTo(UPDATED_CDTYPBV);
        assertThat(testMachine.getPneu()).isEqualTo(UPDATED_PNEU);
        assertThat(testMachine.getGps()).isEqualTo(UPDATED_GPS);
        assertThat(testMachine.getMarquebv()).isEqualTo(UPDATED_MARQUEBV);
        assertThat(testMachine.getTypboite()).isEqualTo(UPDATED_TYPBOITE);
    }

    @Test
    void fullUpdateMachineWithPatch() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        int databaseSizeBeforeUpdate = machineRepository.findAll().size();

        // Update the machine using partial update
        Machine partialUpdatedMachine = new Machine();
        partialUpdatedMachine.setId(machine.getId());

        partialUpdatedMachine
            .cdmac(UPDATED_CDMAC)
            .cdmod(UPDATED_CDMOD)
            .cdmarque(UPDATED_CDMARQUE)
            .lbmac(UPDATED_LBMAC)
            .refmac(UPDATED_REFMAC)
            .serie(UPDATED_SERIE)
            .datfab(UPDATED_DATFAB)
            .datacq(UPDATED_DATACQ)
            .datmes(UPDATED_DATMES)
            .valacq(UPDATED_VALACQ)
            .obs(UPDATED_OBS)
            .numplan(UPDATED_NUMPLAN)
            .cdlipro(UPDATED_CDLIPRO)
            .immat(UPDATED_IMMAT)
            .marque(UPDATED_MARQUE)
            .typev(UPDATED_TYPEV)
            .numser(UPDATED_NUMSER)
            .puiss(UPDATED_PUISS)
            .nrj(UPDATED_NRJ)
            .genre(UPDATED_GENRE)
            .cylind(UPDATED_CYLIND)
            .pdsvid(UPDATED_PDSVID)
            .charge(UPDATED_CHARGE)
            .plcass(UPDATED_PLCASS)
            .plcdeb(UPDATED_PLCDEB)
            .cpt(UPDATED_CPT)
            .cptmnt(UPDATED_CPTMNT)
            .actif(UPDATED_ACTIF)
            .datact(UPDATED_DATACT)
            .cdcatvh(UPDATED_CDCATVH)
            .taux(UPDATED_TAUX)
            .kmmoy(UPDATED_KMMOY)
            .codstat(UPDATED_CODSTAT)
            .edition(UPDATED_EDITION)
            .valassur(UPDATED_VALASSUR)
            .valamort(UPDATED_VALAMORT)
            .consommodel(UPDATED_CONSOMMODEL)
            .decetat(UPDATED_DECETAT)
            .codtypvoit(UPDATED_CODTYPVOIT)
            .cdtyp(UPDATED_CDTYP)
            .cdnat(UPDATED_CDNAT)
            .typbv(UPDATED_TYPBV)
            .cdtypbv(UPDATED_CDTYPBV)
            .pneu(UPDATED_PNEU)
            .gps(UPDATED_GPS)
            .marquebv(UPDATED_MARQUEBV)
            .typboite(UPDATED_TYPBOITE);

        restMachineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMachine.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMachine))
            )
            .andExpect(status().isOk());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
        Machine testMachine = machineList.get(machineList.size() - 1);
        assertThat(testMachine.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testMachine.getCdmod()).isEqualTo(UPDATED_CDMOD);
        assertThat(testMachine.getCdmarque()).isEqualTo(UPDATED_CDMARQUE);
        assertThat(testMachine.getLbmac()).isEqualTo(UPDATED_LBMAC);
        assertThat(testMachine.getRefmac()).isEqualTo(UPDATED_REFMAC);
        assertThat(testMachine.getSerie()).isEqualTo(UPDATED_SERIE);
        assertThat(testMachine.getDatfab()).isEqualTo(UPDATED_DATFAB);
        assertThat(testMachine.getDatacq()).isEqualTo(UPDATED_DATACQ);
        assertThat(testMachine.getDatmes()).isEqualTo(UPDATED_DATMES);
        assertThat(testMachine.getValacq()).isEqualTo(UPDATED_VALACQ);
        assertThat(testMachine.getObs()).isEqualTo(UPDATED_OBS);
        assertThat(testMachine.getNumplan()).isEqualTo(UPDATED_NUMPLAN);
        assertThat(testMachine.getCdlipro()).isEqualTo(UPDATED_CDLIPRO);
        assertThat(testMachine.getImmat()).isEqualTo(UPDATED_IMMAT);
        assertThat(testMachine.getMarque()).isEqualTo(UPDATED_MARQUE);
        assertThat(testMachine.getTypev()).isEqualTo(UPDATED_TYPEV);
        assertThat(testMachine.getNumser()).isEqualTo(UPDATED_NUMSER);
        assertThat(testMachine.getPuiss()).isEqualTo(UPDATED_PUISS);
        assertThat(testMachine.getNrj()).isEqualTo(UPDATED_NRJ);
        assertThat(testMachine.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testMachine.getCylind()).isEqualTo(UPDATED_CYLIND);
        assertThat(testMachine.getPdsvid()).isEqualTo(UPDATED_PDSVID);
        assertThat(testMachine.getCharge()).isEqualTo(UPDATED_CHARGE);
        assertThat(testMachine.getPlcass()).isEqualTo(UPDATED_PLCASS);
        assertThat(testMachine.getPlcdeb()).isEqualTo(UPDATED_PLCDEB);
        assertThat(testMachine.getCpt()).isEqualTo(UPDATED_CPT);
        assertThat(testMachine.getCptmnt()).isEqualTo(UPDATED_CPTMNT);
        assertThat(testMachine.getActif()).isEqualTo(UPDATED_ACTIF);
        assertThat(testMachine.getDatact()).isEqualTo(UPDATED_DATACT);
        assertThat(testMachine.getCdcatvh()).isEqualTo(UPDATED_CDCATVH);
        assertThat(testMachine.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testMachine.getKmmoy()).isEqualTo(UPDATED_KMMOY);
        assertThat(testMachine.getCodstat()).isEqualTo(UPDATED_CODSTAT);
        assertThat(testMachine.getEdition()).isEqualTo(UPDATED_EDITION);
        assertThat(testMachine.getValassur()).isEqualTo(UPDATED_VALASSUR);
        assertThat(testMachine.getValamort()).isEqualTo(UPDATED_VALAMORT);
        assertThat(testMachine.getConsommodel()).isEqualTo(UPDATED_CONSOMMODEL);
        assertThat(testMachine.getDecetat()).isEqualTo(UPDATED_DECETAT);
        assertThat(testMachine.getCodtypvoit()).isEqualTo(UPDATED_CODTYPVOIT);
        assertThat(testMachine.getCdtyp()).isEqualTo(UPDATED_CDTYP);
        assertThat(testMachine.getCdnat()).isEqualTo(UPDATED_CDNAT);
        assertThat(testMachine.getTypbv()).isEqualTo(UPDATED_TYPBV);
        assertThat(testMachine.getCdtypbv()).isEqualTo(UPDATED_CDTYPBV);
        assertThat(testMachine.getPneu()).isEqualTo(UPDATED_PNEU);
        assertThat(testMachine.getGps()).isEqualTo(UPDATED_GPS);
        assertThat(testMachine.getMarquebv()).isEqualTo(UPDATED_MARQUEBV);
        assertThat(testMachine.getTypboite()).isEqualTo(UPDATED_TYPBOITE);
    }

    @Test
    void patchNonExistingMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, machineDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMachine() throws Exception {
        int databaseSizeBeforeUpdate = machineRepository.findAll().size();
        machine.setId(UUID.randomUUID().toString());

        // Create the Machine
        MachineDTO machineDTO = machineMapper.toDto(machine);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMachineMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(machineDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Machine in the database
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMachine() throws Exception {
        // Initialize the database
        machineRepository.save(machine);

        int databaseSizeBeforeDelete = machineRepository.findAll().size();

        // Delete the machine
        restMachineMockMvc
            .perform(delete(ENTITY_API_URL_ID, machine.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Machine> machineList = machineRepository.findAll();
        assertThat(machineList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
