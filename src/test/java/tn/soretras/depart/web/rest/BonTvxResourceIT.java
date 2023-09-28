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
import tn.soretras.depart.domain.BonTvx;
import tn.soretras.depart.repository.BonTvxRepository;
import tn.soretras.depart.service.dto.BonTvxDTO;
import tn.soretras.depart.service.mapper.BonTvxMapper;

/**
 * Integration tests for the {@link BonTvxResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BonTvxResourceIT {

    private static final Integer DEFAULT_CDEXERC = 1;
    private static final Integer UPDATED_CDEXERC = 2;

    private static final Integer DEFAULT_NUMBT = 1;
    private static final Integer UPDATED_NUMBT = 2;

    private static final String DEFAULT_CDTIER = "AAAAAAAAAA";
    private static final String UPDATED_CDTIER = "BBBBBBBBBB";

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final String DEFAULT_MACCDMAC = "AAAAAAAAAA";
    private static final String UPDATED_MACCDMAC = "BBBBBBBBBB";

    private static final String DEFAULT_CDSERV = "AAAAAAAAAA";
    private static final String UPDATED_CDSERV = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECAGEN = 1;
    private static final Integer UPDATED_DECAGEN = 2;

    private static final Integer DEFAULT_DRADECAGEN = 1;
    private static final Integer UPDATED_DRADECAGEN = 2;

    private static final String DEFAULT_CDORGA = "AAAAAAAAAA";
    private static final String UPDATED_CDORGA = "BBBBBBBBBB";

    private static final String DEFAULT_REFBT = "AAAAAAAAAA";
    private static final String UPDATED_REFBT = "BBBBBBBBBB";

    private static final String DEFAULT_DATBT = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATBT = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_DATDT = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATDT = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_DATFT = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATFT = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_VLD = "AAAAAAAAAA";
    private static final String UPDATED_VLD = "BBBBBBBBBB";

    private static final String DEFAULT_TYPTVX = "AAAAAAAAAA";
    private static final String UPDATED_TYPTVX = "BBBBBBBBBB";

    private static final String DEFAULT_HEURDB = "2023-08-08T23:00:00Z";
    private static final String UPDATED_HEURDB = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_HEURFI = "2023-08-08T23:00:00Z";
    private static final String UPDATED_HEURFI = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_OBSERV = "AAAAAAAAAA";
    private static final String UPDATED_OBSERV = "BBBBBBBBBB";

    private static final String DEFAULT_DATSRT = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATSRT = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_HEURSR = "2023-08-08T23:00:00Z";
    private static final String UPDATED_HEURSR = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_OBSTEST = "AAAAAAAAAA";
    private static final String UPDATED_OBSTEST = "BBBBBBBBBB";

    private static final Integer DEFAULT_INDEXDEP = 1;
    private static final Integer UPDATED_INDEXDEP = 2;

    private static final Integer DEFAULT_INDEXARR = 1;
    private static final Integer UPDATED_INDEXARR = 2;

    private static final String DEFAULT_IMMATEX = "AAAAAAAAAA";
    private static final String UPDATED_IMMATEX = "BBBBBBBBBB";

    private static final String DEFAULT_NOMCHAUFF = "AAAAAAAAAA";
    private static final String UPDATED_NOMCHAUFF = "BBBBBBBBBB";

    private static final String DEFAULT_NUMPERMIS = "AAAAAAAAAA";
    private static final String UPDATED_NUMPERMIS = "BBBBBBBBBB";

    private static final String DEFAULT_ETAB = "AAAAAAAAAA";
    private static final String UPDATED_ETAB = "BBBBBBBBBB";

    private static final Integer DEFAULT_COMPTEUR = 1;
    private static final Integer UPDATED_COMPTEUR = 2;

    private static final Integer DEFAULT_CPTORG = 1;
    private static final Integer UPDATED_CPTORG = 2;

    private static final String DEFAULT_CDTYPTR = "AAAAAAAAAA";
    private static final String UPDATED_CDTYPTR = "BBBBBBBBBB";

    private static final String DEFAULT_DECSTAT = "AAAAAAAAAA";
    private static final String UPDATED_DECSTAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TESTEUR = 1;
    private static final Integer UPDATED_TESTEUR = 2;

    private static final String DEFAULT_MOTIFDEP = "AAAAAAAAAA";
    private static final String UPDATED_MOTIFDEP = "BBBBBBBBBB";

    private static final String DEFAULT_CDTYPMNT = "AAAAAAAAAA";
    private static final String UPDATED_CDTYPMNT = "BBBBBBBBBB";

    private static final String DEFAULT_DATSORPREV = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATSORPREV = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_DATMNQDU = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATMNQDU = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_DATMNQAU = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATMNQAU = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_DATENTANT = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATENTANT = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_CODSTAT = "AAAAAAAAAA";
    private static final String UPDATED_CODSTAT = "BBBBBBBBBB";

    private static final String DEFAULT_DATVLD = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATVLD = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_OBSERV_1 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERV_1 = "BBBBBBBBBB";

    private static final Integer DEFAULT_TESTEUR_1 = 1;
    private static final Integer UPDATED_TESTEUR_1 = 2;

    private static final Integer DEFAULT_VALIDAG = 1;
    private static final Integer UPDATED_VALIDAG = 2;

    private static final String DEFAULT_DATSAIS = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DATSAIS = "2023-08-08T23:00:00Z";

    private static final String ENTITY_API_URL = "/api/bon-tvxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private BonTvxRepository bonTvxRepository;

    @Autowired
    private BonTvxMapper bonTvxMapper;

    @Autowired
    private MockMvc restBonTvxMockMvc;

    private BonTvx bonTvx;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BonTvx createEntity() {
        BonTvx bonTvx = new BonTvx()
            .cdexerc(DEFAULT_CDEXERC)
            .num_bt(DEFAULT_NUMBT)
            .cdtier(DEFAULT_CDTIER)
            .cdmac(DEFAULT_CDMAC)
            .mac_cdmac(DEFAULT_MACCDMAC)
            .cdserv(DEFAULT_CDSERV)
            .decagen(DEFAULT_DECAGEN)
            .dra_decagen(DEFAULT_DRADECAGEN)
            .cdorga(DEFAULT_CDORGA)
            .ref_bt(DEFAULT_REFBT)
            .dat_bt(DEFAULT_DATBT)
            .dat_dt(DEFAULT_DATDT)
            .dat_ft(DEFAULT_DATFT)
            .vld(DEFAULT_VLD)
            .typ_tvx(DEFAULT_TYPTVX)
            .heurdb(DEFAULT_HEURDB)
            .heurfi(DEFAULT_HEURFI)
            .observ(DEFAULT_OBSERV)
            .dat_srt(DEFAULT_DATSRT)
            .heursr(DEFAULT_HEURSR)
            .obs_test(DEFAULT_OBSTEST)
            .index_dep(DEFAULT_INDEXDEP)
            .index_arr(DEFAULT_INDEXARR)
            .immat_ex(DEFAULT_IMMATEX)
            .nom_chauff(DEFAULT_NOMCHAUFF)
            .num_permis(DEFAULT_NUMPERMIS)
            .etab(DEFAULT_ETAB)
            .compteur(DEFAULT_COMPTEUR)
            .cpt_org(DEFAULT_CPTORG)
            .cdtyptr(DEFAULT_CDTYPTR)
            .decstat(DEFAULT_DECSTAT)
            .testeur(DEFAULT_TESTEUR)
            .motif_dep(DEFAULT_MOTIFDEP)
            .cdtypmnt(DEFAULT_CDTYPMNT)
            .dat_sor_prev(DEFAULT_DATSORPREV)
            .dat_mnq_du(DEFAULT_DATMNQDU)
            .dat_mnq_au(DEFAULT_DATMNQAU)
            .dat_ent_ant(DEFAULT_DATENTANT)
            .codstat(DEFAULT_CODSTAT)
            .dat_vld(DEFAULT_DATVLD)
            .observ1(DEFAULT_OBSERV_1)
            .testeur1(DEFAULT_TESTEUR_1)
            .valid_ag(DEFAULT_VALIDAG)
            .dat_sais(DEFAULT_DATSAIS);
        return bonTvx;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BonTvx createUpdatedEntity() {
        BonTvx bonTvx = new BonTvx()
            .cdexerc(UPDATED_CDEXERC)
            .num_bt(UPDATED_NUMBT)
            .cdtier(UPDATED_CDTIER)
            .cdmac(UPDATED_CDMAC)
            .mac_cdmac(UPDATED_MACCDMAC)
            .cdserv(UPDATED_CDSERV)
            .decagen(UPDATED_DECAGEN)
            .dra_decagen(UPDATED_DRADECAGEN)
            .cdorga(UPDATED_CDORGA)
            .ref_bt(UPDATED_REFBT)
            .dat_bt(UPDATED_DATBT)
            .dat_dt(UPDATED_DATDT)
            .dat_ft(UPDATED_DATFT)
            .vld(UPDATED_VLD)
            .typ_tvx(UPDATED_TYPTVX)
            .heurdb(UPDATED_HEURDB)
            .heurfi(UPDATED_HEURFI)
            .observ(UPDATED_OBSERV)
            .dat_srt(UPDATED_DATSRT)
            .heursr(UPDATED_HEURSR)
            .obs_test(UPDATED_OBSTEST)
            .index_dep(UPDATED_INDEXDEP)
            .index_arr(UPDATED_INDEXARR)
            .immat_ex(UPDATED_IMMATEX)
            .nom_chauff(UPDATED_NOMCHAUFF)
            .num_permis(UPDATED_NUMPERMIS)
            .etab(UPDATED_ETAB)
            .compteur(UPDATED_COMPTEUR)
            .cpt_org(UPDATED_CPTORG)
            .cdtyptr(UPDATED_CDTYPTR)
            .decstat(UPDATED_DECSTAT)
            .testeur(UPDATED_TESTEUR)
            .motif_dep(UPDATED_MOTIFDEP)
            .cdtypmnt(UPDATED_CDTYPMNT)
            .dat_sor_prev(UPDATED_DATSORPREV)
            .dat_mnq_du(UPDATED_DATMNQDU)
            .dat_mnq_au(UPDATED_DATMNQAU)
            .dat_ent_ant(UPDATED_DATENTANT)
            .codstat(UPDATED_CODSTAT)
            .dat_vld(UPDATED_DATVLD)
            .observ1(UPDATED_OBSERV_1)
            .testeur1(UPDATED_TESTEUR_1)
            .valid_ag(UPDATED_VALIDAG)
            .dat_sais(UPDATED_DATSAIS);
        return bonTvx;
    }

    @BeforeEach
    public void initTest() {
        bonTvxRepository.deleteAll();
        bonTvx = createEntity();
    }

    @Test
    void createBonTvx() throws Exception {
        int databaseSizeBeforeCreate = bonTvxRepository.findAll().size();
        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);
        restBonTvxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonTvxDTO)))
            .andExpect(status().isCreated());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeCreate + 1);
        BonTvx testBonTvx = bonTvxList.get(bonTvxList.size() - 1);
        assertThat(testBonTvx.getCdexerc()).isEqualTo(DEFAULT_CDEXERC);
        assertThat(testBonTvx.getNum_bt()).isEqualTo(DEFAULT_NUMBT);
        assertThat(testBonTvx.getCdtier()).isEqualTo(DEFAULT_CDTIER);
        assertThat(testBonTvx.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testBonTvx.getMac_cdmac()).isEqualTo(DEFAULT_MACCDMAC);
        assertThat(testBonTvx.getCdserv()).isEqualTo(DEFAULT_CDSERV);
        assertThat(testBonTvx.getDecagen()).isEqualTo(DEFAULT_DECAGEN);
        assertThat(testBonTvx.getDra_decagen()).isEqualTo(DEFAULT_DRADECAGEN);
        assertThat(testBonTvx.getCdorga()).isEqualTo(DEFAULT_CDORGA);
        assertThat(testBonTvx.getRef_bt()).isEqualTo(DEFAULT_REFBT);
        assertThat(testBonTvx.getDat_bt()).isEqualTo(DEFAULT_DATBT);
        assertThat(testBonTvx.getDat_dt()).isEqualTo(DEFAULT_DATDT);
        assertThat(testBonTvx.getDat_ft()).isEqualTo(DEFAULT_DATFT);
        assertThat(testBonTvx.getVld()).isEqualTo(DEFAULT_VLD);
        assertThat(testBonTvx.getTyp_tvx()).isEqualTo(DEFAULT_TYPTVX);
        assertThat(testBonTvx.getHeurdb()).isEqualTo(DEFAULT_HEURDB);
        assertThat(testBonTvx.getHeurfi()).isEqualTo(DEFAULT_HEURFI);
        assertThat(testBonTvx.getObserv()).isEqualTo(DEFAULT_OBSERV);
        assertThat(testBonTvx.getDat_srt()).isEqualTo(DEFAULT_DATSRT);
        assertThat(testBonTvx.getHeursr()).isEqualTo(DEFAULT_HEURSR);
        assertThat(testBonTvx.getObs_test()).isEqualTo(DEFAULT_OBSTEST);
        assertThat(testBonTvx.getIndex_dep()).isEqualTo(DEFAULT_INDEXDEP);
        assertThat(testBonTvx.getIndex_arr()).isEqualTo(DEFAULT_INDEXARR);
        assertThat(testBonTvx.getImmat_ex()).isEqualTo(DEFAULT_IMMATEX);
        assertThat(testBonTvx.getNom_chauff()).isEqualTo(DEFAULT_NOMCHAUFF);
        assertThat(testBonTvx.getNum_permis()).isEqualTo(DEFAULT_NUMPERMIS);
        assertThat(testBonTvx.getEtab()).isEqualTo(DEFAULT_ETAB);
        assertThat(testBonTvx.getCompteur()).isEqualTo(DEFAULT_COMPTEUR);
        assertThat(testBonTvx.getCpt_org()).isEqualTo(DEFAULT_CPTORG);
        assertThat(testBonTvx.getCdtyptr()).isEqualTo(DEFAULT_CDTYPTR);
        assertThat(testBonTvx.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testBonTvx.getTesteur()).isEqualTo(DEFAULT_TESTEUR);
        assertThat(testBonTvx.getMotif_dep()).isEqualTo(DEFAULT_MOTIFDEP);
        assertThat(testBonTvx.getCdtypmnt()).isEqualTo(DEFAULT_CDTYPMNT);
        assertThat(testBonTvx.getDat_sor_prev()).isEqualTo(DEFAULT_DATSORPREV);
        assertThat(testBonTvx.getDat_mnq_du()).isEqualTo(DEFAULT_DATMNQDU);
        assertThat(testBonTvx.getDat_mnq_au()).isEqualTo(DEFAULT_DATMNQAU);
        assertThat(testBonTvx.getDat_ent_ant()).isEqualTo(DEFAULT_DATENTANT);
        assertThat(testBonTvx.getCodstat()).isEqualTo(DEFAULT_CODSTAT);
        assertThat(testBonTvx.getDat_vld()).isEqualTo(DEFAULT_DATVLD);
        assertThat(testBonTvx.getObserv1()).isEqualTo(DEFAULT_OBSERV_1);
        assertThat(testBonTvx.getTesteur1()).isEqualTo(DEFAULT_TESTEUR_1);
        assertThat(testBonTvx.getValid_ag()).isEqualTo(DEFAULT_VALIDAG);
        assertThat(testBonTvx.getDat_sais()).isEqualTo(DEFAULT_DATSAIS);
    }

    @Test
    void createBonTvxWithExistingId() throws Exception {
        // Create the BonTvx with an existing ID
        bonTvx.setId("existing_id");
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        int databaseSizeBeforeCreate = bonTvxRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBonTvxMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonTvxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllBonTvxes() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        // Get all the bonTvxList
        restBonTvxMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bonTvx.getId())))
            .andExpect(jsonPath("$.[*].cdexerc").value(hasItem(DEFAULT_CDEXERC)))
            .andExpect(jsonPath("$.[*].numbt").value(hasItem(DEFAULT_NUMBT)))
            .andExpect(jsonPath("$.[*].cdtier").value(hasItem(DEFAULT_CDTIER)))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].maccdmac").value(hasItem(DEFAULT_MACCDMAC)))
            .andExpect(jsonPath("$.[*].cdserv").value(hasItem(DEFAULT_CDSERV)))
            .andExpect(jsonPath("$.[*].decagen").value(hasItem(DEFAULT_DECAGEN)))
            .andExpect(jsonPath("$.[*].dradecagen").value(hasItem(DEFAULT_DRADECAGEN)))
            .andExpect(jsonPath("$.[*].cdorga").value(hasItem(DEFAULT_CDORGA)))
            .andExpect(jsonPath("$.[*].refbt").value(hasItem(DEFAULT_REFBT)))
            .andExpect(jsonPath("$.[*].datbt").value(hasItem(DEFAULT_DATBT.toString())))
            .andExpect(jsonPath("$.[*].datdt").value(hasItem(DEFAULT_DATDT.toString())))
            .andExpect(jsonPath("$.[*].datft").value(hasItem(DEFAULT_DATFT.toString())))
            .andExpect(jsonPath("$.[*].vld").value(hasItem(DEFAULT_VLD)))
            .andExpect(jsonPath("$.[*].typtvx").value(hasItem(DEFAULT_TYPTVX)))
            .andExpect(jsonPath("$.[*].heurdb").value(hasItem(DEFAULT_HEURDB.toString())))
            .andExpect(jsonPath("$.[*].heurfi").value(hasItem(DEFAULT_HEURFI.toString())))
            .andExpect(jsonPath("$.[*].observ").value(hasItem(DEFAULT_OBSERV)))
            .andExpect(jsonPath("$.[*].datsrt").value(hasItem(DEFAULT_DATSRT.toString())))
            .andExpect(jsonPath("$.[*].heursr").value(hasItem(DEFAULT_HEURSR.toString())))
            .andExpect(jsonPath("$.[*].obstest").value(hasItem(DEFAULT_OBSTEST)))
            .andExpect(jsonPath("$.[*].indexdep").value(hasItem(DEFAULT_INDEXDEP)))
            .andExpect(jsonPath("$.[*].indexarr").value(hasItem(DEFAULT_INDEXARR)))
            .andExpect(jsonPath("$.[*].immatex").value(hasItem(DEFAULT_IMMATEX)))
            .andExpect(jsonPath("$.[*].nomchauff").value(hasItem(DEFAULT_NOMCHAUFF)))
            .andExpect(jsonPath("$.[*].numpermis").value(hasItem(DEFAULT_NUMPERMIS)))
            .andExpect(jsonPath("$.[*].etab").value(hasItem(DEFAULT_ETAB)))
            .andExpect(jsonPath("$.[*].compteur").value(hasItem(DEFAULT_COMPTEUR)))
            .andExpect(jsonPath("$.[*].cptorg").value(hasItem(DEFAULT_CPTORG)))
            .andExpect(jsonPath("$.[*].cdtyptr").value(hasItem(DEFAULT_CDTYPTR)))
            .andExpect(jsonPath("$.[*].decstat").value(hasItem(DEFAULT_DECSTAT)))
            .andExpect(jsonPath("$.[*].testeur").value(hasItem(DEFAULT_TESTEUR)))
            .andExpect(jsonPath("$.[*].motifdep").value(hasItem(DEFAULT_MOTIFDEP)))
            .andExpect(jsonPath("$.[*].cdtypmnt").value(hasItem(DEFAULT_CDTYPMNT)))
            .andExpect(jsonPath("$.[*].datsorprev").value(hasItem(DEFAULT_DATSORPREV.toString())))
            .andExpect(jsonPath("$.[*].datmnqdu").value(hasItem(DEFAULT_DATMNQDU.toString())))
            .andExpect(jsonPath("$.[*].datmnqau").value(hasItem(DEFAULT_DATMNQAU.toString())))
            .andExpect(jsonPath("$.[*].datentant").value(hasItem(DEFAULT_DATENTANT.toString())))
            .andExpect(jsonPath("$.[*].codstat").value(hasItem(DEFAULT_CODSTAT)))
            .andExpect(jsonPath("$.[*].datvld").value(hasItem(DEFAULT_DATVLD.toString())))
            .andExpect(jsonPath("$.[*].observ1").value(hasItem(DEFAULT_OBSERV_1)))
            .andExpect(jsonPath("$.[*].testeur1").value(hasItem(DEFAULT_TESTEUR_1)))
            .andExpect(jsonPath("$.[*].validag").value(hasItem(DEFAULT_VALIDAG)))
            .andExpect(jsonPath("$.[*].datsais").value(hasItem(DEFAULT_DATSAIS.toString())));
    }

    @Test
    void getBonTvx() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        // Get the bonTvx
        restBonTvxMockMvc
            .perform(get(ENTITY_API_URL_ID, bonTvx.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bonTvx.getId()))
            .andExpect(jsonPath("$.cdexerc").value(DEFAULT_CDEXERC))
            .andExpect(jsonPath("$.numbt").value(DEFAULT_NUMBT))
            .andExpect(jsonPath("$.cdtier").value(DEFAULT_CDTIER))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.maccdmac").value(DEFAULT_MACCDMAC))
            .andExpect(jsonPath("$.cdserv").value(DEFAULT_CDSERV))
            .andExpect(jsonPath("$.decagen").value(DEFAULT_DECAGEN))
            .andExpect(jsonPath("$.dradecagen").value(DEFAULT_DRADECAGEN))
            .andExpect(jsonPath("$.cdorga").value(DEFAULT_CDORGA))
            .andExpect(jsonPath("$.refbt").value(DEFAULT_REFBT))
            .andExpect(jsonPath("$.datbt").value(DEFAULT_DATBT.toString()))
            .andExpect(jsonPath("$.datdt").value(DEFAULT_DATDT.toString()))
            .andExpect(jsonPath("$.datft").value(DEFAULT_DATFT.toString()))
            .andExpect(jsonPath("$.vld").value(DEFAULT_VLD))
            .andExpect(jsonPath("$.typtvx").value(DEFAULT_TYPTVX))
            .andExpect(jsonPath("$.heurdb").value(DEFAULT_HEURDB.toString()))
            .andExpect(jsonPath("$.heurfi").value(DEFAULT_HEURFI.toString()))
            .andExpect(jsonPath("$.observ").value(DEFAULT_OBSERV))
            .andExpect(jsonPath("$.datsrt").value(DEFAULT_DATSRT.toString()))
            .andExpect(jsonPath("$.heursr").value(DEFAULT_HEURSR.toString()))
            .andExpect(jsonPath("$.obstest").value(DEFAULT_OBSTEST))
            .andExpect(jsonPath("$.indexdep").value(DEFAULT_INDEXDEP))
            .andExpect(jsonPath("$.indexarr").value(DEFAULT_INDEXARR))
            .andExpect(jsonPath("$.immatex").value(DEFAULT_IMMATEX))
            .andExpect(jsonPath("$.nomchauff").value(DEFAULT_NOMCHAUFF))
            .andExpect(jsonPath("$.numpermis").value(DEFAULT_NUMPERMIS))
            .andExpect(jsonPath("$.etab").value(DEFAULT_ETAB))
            .andExpect(jsonPath("$.compteur").value(DEFAULT_COMPTEUR))
            .andExpect(jsonPath("$.cptorg").value(DEFAULT_CPTORG))
            .andExpect(jsonPath("$.cdtyptr").value(DEFAULT_CDTYPTR))
            .andExpect(jsonPath("$.decstat").value(DEFAULT_DECSTAT))
            .andExpect(jsonPath("$.testeur").value(DEFAULT_TESTEUR))
            .andExpect(jsonPath("$.motifdep").value(DEFAULT_MOTIFDEP))
            .andExpect(jsonPath("$.cdtypmnt").value(DEFAULT_CDTYPMNT))
            .andExpect(jsonPath("$.datsorprev").value(DEFAULT_DATSORPREV.toString()))
            .andExpect(jsonPath("$.datmnqdu").value(DEFAULT_DATMNQDU.toString()))
            .andExpect(jsonPath("$.datmnqau").value(DEFAULT_DATMNQAU.toString()))
            .andExpect(jsonPath("$.datentant").value(DEFAULT_DATENTANT.toString()))
            .andExpect(jsonPath("$.codstat").value(DEFAULT_CODSTAT))
            .andExpect(jsonPath("$.datvld").value(DEFAULT_DATVLD.toString()))
            .andExpect(jsonPath("$.observ1").value(DEFAULT_OBSERV_1))
            .andExpect(jsonPath("$.testeur1").value(DEFAULT_TESTEUR_1))
            .andExpect(jsonPath("$.validag").value(DEFAULT_VALIDAG))
            .andExpect(jsonPath("$.datsais").value(DEFAULT_DATSAIS.toString()));
    }

    @Test
    void getNonExistingBonTvx() throws Exception {
        // Get the bonTvx
        restBonTvxMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingBonTvx() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();

        // Update the bonTvx
        BonTvx updatedBonTvx = bonTvxRepository.findById(bonTvx.getId()).get();
        updatedBonTvx
            .cdexerc(UPDATED_CDEXERC)
            .num_bt(UPDATED_NUMBT)
            .cdtier(UPDATED_CDTIER)
            .cdmac(UPDATED_CDMAC)
            .mac_cdmac(UPDATED_MACCDMAC)
            .cdserv(UPDATED_CDSERV)
            .decagen(UPDATED_DECAGEN)
            .dra_decagen(UPDATED_DRADECAGEN)
            .cdorga(UPDATED_CDORGA)
            .ref_bt(UPDATED_REFBT)
            .dat_bt(UPDATED_DATBT)
            .dat_dt(UPDATED_DATDT)
            .dat_ft(UPDATED_DATFT)
            .vld(UPDATED_VLD)
            .typ_tvx(UPDATED_TYPTVX)
            .heurdb(UPDATED_HEURDB)
            .heurfi(UPDATED_HEURFI)
            .observ(UPDATED_OBSERV)
            .dat_srt(UPDATED_DATSRT)
            .heursr(UPDATED_HEURSR)
            .obs_test(UPDATED_OBSTEST)
            .index_dep(UPDATED_INDEXDEP)
            .index_arr(UPDATED_INDEXARR)
            .immat_ex(UPDATED_IMMATEX)
            .nom_chauff(UPDATED_NOMCHAUFF)
            .num_permis(UPDATED_NUMPERMIS)
            .etab(UPDATED_ETAB)
            .compteur(UPDATED_COMPTEUR)
            .cpt_org(UPDATED_CPTORG)
            .cdtyptr(UPDATED_CDTYPTR)
            .decstat(UPDATED_DECSTAT)
            .testeur(UPDATED_TESTEUR)
            .motif_dep(UPDATED_MOTIFDEP)
            .cdtypmnt(UPDATED_CDTYPMNT)
            .dat_sor_prev(UPDATED_DATSORPREV)
            .dat_mnq_du(UPDATED_DATMNQDU)
            .dat_mnq_au(UPDATED_DATMNQAU)
            .dat_ent_ant(UPDATED_DATENTANT)
            .codstat(UPDATED_CODSTAT)
            .dat_vld(UPDATED_DATVLD)
            .observ1(UPDATED_OBSERV_1)
            .testeur1(UPDATED_TESTEUR_1)
            .valid_ag(UPDATED_VALIDAG)
            .dat_sais(UPDATED_DATSAIS);
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(updatedBonTvx);

        restBonTvxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bonTvxDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isOk());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
        BonTvx testBonTvx = bonTvxList.get(bonTvxList.size() - 1);
        assertThat(testBonTvx.getCdexerc()).isEqualTo(UPDATED_CDEXERC);
        assertThat(testBonTvx.getNum_bt()).isEqualTo(UPDATED_NUMBT);
        assertThat(testBonTvx.getCdtier()).isEqualTo(UPDATED_CDTIER);
        assertThat(testBonTvx.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testBonTvx.getMac_cdmac()).isEqualTo(UPDATED_MACCDMAC);
        assertThat(testBonTvx.getCdserv()).isEqualTo(UPDATED_CDSERV);
        assertThat(testBonTvx.getDecagen()).isEqualTo(UPDATED_DECAGEN);
        assertThat(testBonTvx.getDra_decagen()).isEqualTo(UPDATED_DRADECAGEN);
        assertThat(testBonTvx.getCdorga()).isEqualTo(UPDATED_CDORGA);
        assertThat(testBonTvx.getRef_bt()).isEqualTo(UPDATED_REFBT);
        assertThat(testBonTvx.getDat_bt()).isEqualTo(UPDATED_DATBT);
        assertThat(testBonTvx.getDat_dt()).isEqualTo(UPDATED_DATDT);
        assertThat(testBonTvx.getDat_ft()).isEqualTo(UPDATED_DATFT);
        assertThat(testBonTvx.getVld()).isEqualTo(UPDATED_VLD);
        assertThat(testBonTvx.getTyp_tvx()).isEqualTo(UPDATED_TYPTVX);
        assertThat(testBonTvx.getHeurdb()).isEqualTo(UPDATED_HEURDB);
        assertThat(testBonTvx.getHeurfi()).isEqualTo(UPDATED_HEURFI);
        assertThat(testBonTvx.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testBonTvx.getDat_srt()).isEqualTo(UPDATED_DATSRT);
        assertThat(testBonTvx.getHeursr()).isEqualTo(UPDATED_HEURSR);
        assertThat(testBonTvx.getObs_test()).isEqualTo(UPDATED_OBSTEST);
        assertThat(testBonTvx.getIndex_dep()).isEqualTo(UPDATED_INDEXDEP);
        assertThat(testBonTvx.getIndex_arr()).isEqualTo(UPDATED_INDEXARR);
        assertThat(testBonTvx.getImmat_ex()).isEqualTo(UPDATED_IMMATEX);
        assertThat(testBonTvx.getNom_chauff()).isEqualTo(UPDATED_NOMCHAUFF);
        assertThat(testBonTvx.getNum_permis()).isEqualTo(UPDATED_NUMPERMIS);
        assertThat(testBonTvx.getEtab()).isEqualTo(UPDATED_ETAB);
        assertThat(testBonTvx.getCompteur()).isEqualTo(UPDATED_COMPTEUR);
        assertThat(testBonTvx.getCpt_org()).isEqualTo(UPDATED_CPTORG);
        assertThat(testBonTvx.getCdtyptr()).isEqualTo(UPDATED_CDTYPTR);
        assertThat(testBonTvx.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testBonTvx.getTesteur()).isEqualTo(UPDATED_TESTEUR);
        assertThat(testBonTvx.getMotif_dep()).isEqualTo(UPDATED_MOTIFDEP);
        assertThat(testBonTvx.getCdtypmnt()).isEqualTo(UPDATED_CDTYPMNT);
        assertThat(testBonTvx.getDat_sor_prev()).isEqualTo(UPDATED_DATSORPREV);
        assertThat(testBonTvx.getDat_mnq_du()).isEqualTo(UPDATED_DATMNQDU);
        assertThat(testBonTvx.getDat_mnq_au()).isEqualTo(UPDATED_DATMNQAU);
        assertThat(testBonTvx.getDat_ent_ant()).isEqualTo(UPDATED_DATENTANT);
        assertThat(testBonTvx.getCodstat()).isEqualTo(UPDATED_CODSTAT);
        assertThat(testBonTvx.getDat_vld()).isEqualTo(UPDATED_DATVLD);
        assertThat(testBonTvx.getObserv1()).isEqualTo(UPDATED_OBSERV_1);
        assertThat(testBonTvx.getTesteur1()).isEqualTo(UPDATED_TESTEUR_1);
        assertThat(testBonTvx.getValid_ag()).isEqualTo(UPDATED_VALIDAG);
        assertThat(testBonTvx.getDat_sais()).isEqualTo(UPDATED_DATSAIS);
    }

    @Test
    void putNonExistingBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bonTvxDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bonTvxDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBonTvxWithPatch() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();

        // Update the bonTvx using partial update
        BonTvx partialUpdatedBonTvx = new BonTvx();
        partialUpdatedBonTvx.setId(bonTvx.getId());

        partialUpdatedBonTvx
            .cdexerc(UPDATED_CDEXERC)
            .num_bt(UPDATED_NUMBT)
            .cdserv(UPDATED_CDSERV)
            .dra_decagen(UPDATED_DRADECAGEN)
            .cdorga(UPDATED_CDORGA)
            .ref_bt(UPDATED_REFBT)
            .dat_dt(UPDATED_DATDT)
            .vld(UPDATED_VLD)
            .heurdb(UPDATED_HEURDB)
            .observ(UPDATED_OBSERV)
            .dat_srt(UPDATED_DATSRT)
            .heursr(UPDATED_HEURSR)
            .nom_chauff(UPDATED_NOMCHAUFF)
            .compteur(UPDATED_COMPTEUR)
            .cpt_org(UPDATED_CPTORG)
            .cdtyptr(UPDATED_CDTYPTR)
            .decstat(UPDATED_DECSTAT)
            .motif_dep(UPDATED_MOTIFDEP)
            .cdtypmnt(UPDATED_CDTYPMNT)
            .dat_ent_ant(UPDATED_DATENTANT)
            .dat_vld(UPDATED_DATVLD)
            .observ1(UPDATED_OBSERV_1)
            .testeur1(UPDATED_TESTEUR_1);

        restBonTvxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBonTvx.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBonTvx))
            )
            .andExpect(status().isOk());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
        BonTvx testBonTvx = bonTvxList.get(bonTvxList.size() - 1);
        assertThat(testBonTvx.getCdexerc()).isEqualTo(UPDATED_CDEXERC);
        assertThat(testBonTvx.getNum_bt()).isEqualTo(UPDATED_NUMBT);
        assertThat(testBonTvx.getCdtier()).isEqualTo(DEFAULT_CDTIER);
        assertThat(testBonTvx.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testBonTvx.getMac_cdmac()).isEqualTo(DEFAULT_MACCDMAC);
        assertThat(testBonTvx.getCdserv()).isEqualTo(UPDATED_CDSERV);
        assertThat(testBonTvx.getDecagen()).isEqualTo(DEFAULT_DECAGEN);
        assertThat(testBonTvx.getDra_decagen()).isEqualTo(UPDATED_DRADECAGEN);
        assertThat(testBonTvx.getCdorga()).isEqualTo(UPDATED_CDORGA);
        assertThat(testBonTvx.getRef_bt()).isEqualTo(UPDATED_REFBT);
        assertThat(testBonTvx.getDat_bt()).isEqualTo(DEFAULT_DATBT);
        assertThat(testBonTvx.getDat_dt()).isEqualTo(UPDATED_DATDT);
        assertThat(testBonTvx.getDat_ft()).isEqualTo(DEFAULT_DATFT);
        assertThat(testBonTvx.getVld()).isEqualTo(UPDATED_VLD);
        assertThat(testBonTvx.getTyp_tvx()).isEqualTo(DEFAULT_TYPTVX);
        assertThat(testBonTvx.getHeurdb()).isEqualTo(UPDATED_HEURDB);
        assertThat(testBonTvx.getHeurfi()).isEqualTo(DEFAULT_HEURFI);
        assertThat(testBonTvx.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testBonTvx.getDat_srt()).isEqualTo(UPDATED_DATSRT);
        assertThat(testBonTvx.getHeursr()).isEqualTo(UPDATED_HEURSR);
        assertThat(testBonTvx.getObs_test()).isEqualTo(DEFAULT_OBSTEST);
        assertThat(testBonTvx.getIndex_dep()).isEqualTo(DEFAULT_INDEXDEP);
        assertThat(testBonTvx.getIndex_arr()).isEqualTo(DEFAULT_INDEXARR);
        assertThat(testBonTvx.getImmat_ex()).isEqualTo(DEFAULT_IMMATEX);
        assertThat(testBonTvx.getNom_chauff()).isEqualTo(UPDATED_NOMCHAUFF);
        assertThat(testBonTvx.getNum_permis()).isEqualTo(DEFAULT_NUMPERMIS);
        assertThat(testBonTvx.getEtab()).isEqualTo(DEFAULT_ETAB);
        assertThat(testBonTvx.getCompteur()).isEqualTo(UPDATED_COMPTEUR);
        assertThat(testBonTvx.getCpt_org()).isEqualTo(UPDATED_CPTORG);
        assertThat(testBonTvx.getCdtyptr()).isEqualTo(UPDATED_CDTYPTR);
        assertThat(testBonTvx.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testBonTvx.getTesteur()).isEqualTo(DEFAULT_TESTEUR);
        assertThat(testBonTvx.getMotif_dep()).isEqualTo(UPDATED_MOTIFDEP);
        assertThat(testBonTvx.getCdtypmnt()).isEqualTo(UPDATED_CDTYPMNT);
        assertThat(testBonTvx.getDat_sor_prev()).isEqualTo(DEFAULT_DATSORPREV);
        assertThat(testBonTvx.getDat_mnq_du()).isEqualTo(DEFAULT_DATMNQDU);
        assertThat(testBonTvx.getDat_mnq_au()).isEqualTo(DEFAULT_DATMNQAU);
        assertThat(testBonTvx.getDat_ent_ant()).isEqualTo(UPDATED_DATENTANT);
        assertThat(testBonTvx.getCodstat()).isEqualTo(DEFAULT_CODSTAT);
        assertThat(testBonTvx.getDat_vld()).isEqualTo(UPDATED_DATVLD);
        assertThat(testBonTvx.getObserv1()).isEqualTo(UPDATED_OBSERV_1);
        assertThat(testBonTvx.getTesteur1()).isEqualTo(UPDATED_TESTEUR_1);
        assertThat(testBonTvx.getValid_ag()).isEqualTo(DEFAULT_VALIDAG);
        assertThat(testBonTvx.getDat_sais()).isEqualTo(DEFAULT_DATSAIS);
    }

    @Test
    void fullUpdateBonTvxWithPatch() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();

        // Update the bonTvx using partial update
        BonTvx partialUpdatedBonTvx = new BonTvx();
        partialUpdatedBonTvx.setId(bonTvx.getId());

        partialUpdatedBonTvx
            .cdexerc(UPDATED_CDEXERC)
            .num_bt(UPDATED_NUMBT)
            .cdtier(UPDATED_CDTIER)
            .cdmac(UPDATED_CDMAC)
            .mac_cdmac(UPDATED_MACCDMAC)
            .cdserv(UPDATED_CDSERV)
            .decagen(UPDATED_DECAGEN)
            .dra_decagen(UPDATED_DRADECAGEN)
            .cdorga(UPDATED_CDORGA)
            .ref_bt(UPDATED_REFBT)
            .dat_bt(UPDATED_DATBT)
            .dat_dt(UPDATED_DATDT)
            .dat_ft(UPDATED_DATFT)
            .vld(UPDATED_VLD)
            .typ_tvx(UPDATED_TYPTVX)
            .heurdb(UPDATED_HEURDB)
            .heurfi(UPDATED_HEURFI)
            .observ(UPDATED_OBSERV)
            .dat_srt(UPDATED_DATSRT)
            .heursr(UPDATED_HEURSR)
            .obs_test(UPDATED_OBSTEST)
            .index_dep(UPDATED_INDEXDEP)
            .index_arr(UPDATED_INDEXARR)
            .immat_ex(UPDATED_IMMATEX)
            .nom_chauff(UPDATED_NOMCHAUFF)
            .num_permis(UPDATED_NUMPERMIS)
            .etab(UPDATED_ETAB)
            .compteur(UPDATED_COMPTEUR)
            .cpt_org(UPDATED_CPTORG)
            .cdtyptr(UPDATED_CDTYPTR)
            .decstat(UPDATED_DECSTAT)
            .testeur(UPDATED_TESTEUR)
            .motif_dep(UPDATED_MOTIFDEP)
            .cdtypmnt(UPDATED_CDTYPMNT)
            .dat_sor_prev(UPDATED_DATSORPREV)
            .dat_mnq_du(UPDATED_DATMNQDU)
            .dat_mnq_au(UPDATED_DATMNQAU)
            .dat_ent_ant(UPDATED_DATENTANT)
            .codstat(UPDATED_CODSTAT)
            .dat_vld(UPDATED_DATVLD)
            .observ1(UPDATED_OBSERV_1)
            .testeur1(UPDATED_TESTEUR_1)
            .valid_ag(UPDATED_VALIDAG)
            .dat_sais(UPDATED_DATSAIS);

        restBonTvxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBonTvx.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBonTvx))
            )
            .andExpect(status().isOk());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
        BonTvx testBonTvx = bonTvxList.get(bonTvxList.size() - 1);
        assertThat(testBonTvx.getCdexerc()).isEqualTo(UPDATED_CDEXERC);
        assertThat(testBonTvx.getNum_bt()).isEqualTo(UPDATED_NUMBT);
        assertThat(testBonTvx.getCdtier()).isEqualTo(UPDATED_CDTIER);
        assertThat(testBonTvx.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testBonTvx.getMac_cdmac()).isEqualTo(UPDATED_MACCDMAC);
        assertThat(testBonTvx.getCdserv()).isEqualTo(UPDATED_CDSERV);
        assertThat(testBonTvx.getDecagen()).isEqualTo(UPDATED_DECAGEN);
        assertThat(testBonTvx.getDra_decagen()).isEqualTo(UPDATED_DRADECAGEN);
        assertThat(testBonTvx.getCdorga()).isEqualTo(UPDATED_CDORGA);
        assertThat(testBonTvx.getRef_bt()).isEqualTo(UPDATED_REFBT);
        assertThat(testBonTvx.getDat_bt()).isEqualTo(UPDATED_DATBT);
        assertThat(testBonTvx.getDat_dt()).isEqualTo(UPDATED_DATDT);
        assertThat(testBonTvx.getDat_ft()).isEqualTo(UPDATED_DATFT);
        assertThat(testBonTvx.getVld()).isEqualTo(UPDATED_VLD);
        assertThat(testBonTvx.getTyp_tvx()).isEqualTo(UPDATED_TYPTVX);
        assertThat(testBonTvx.getHeurdb()).isEqualTo(UPDATED_HEURDB);
        assertThat(testBonTvx.getHeurfi()).isEqualTo(UPDATED_HEURFI);
        assertThat(testBonTvx.getObserv()).isEqualTo(UPDATED_OBSERV);
        assertThat(testBonTvx.getDat_srt()).isEqualTo(UPDATED_DATSRT);
        assertThat(testBonTvx.getHeursr()).isEqualTo(UPDATED_HEURSR);
        assertThat(testBonTvx.getObs_test()).isEqualTo(UPDATED_OBSTEST);
        assertThat(testBonTvx.getIndex_dep()).isEqualTo(UPDATED_INDEXDEP);
        assertThat(testBonTvx.getIndex_arr()).isEqualTo(UPDATED_INDEXARR);
        assertThat(testBonTvx.getImmat_ex()).isEqualTo(UPDATED_IMMATEX);
        assertThat(testBonTvx.getNom_chauff()).isEqualTo(UPDATED_NOMCHAUFF);
        assertThat(testBonTvx.getNum_permis()).isEqualTo(UPDATED_NUMPERMIS);
        assertThat(testBonTvx.getEtab()).isEqualTo(UPDATED_ETAB);
        assertThat(testBonTvx.getCompteur()).isEqualTo(UPDATED_COMPTEUR);
        assertThat(testBonTvx.getCpt_org()).isEqualTo(UPDATED_CPTORG);
        assertThat(testBonTvx.getCdtyptr()).isEqualTo(UPDATED_CDTYPTR);
        assertThat(testBonTvx.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testBonTvx.getTesteur()).isEqualTo(UPDATED_TESTEUR);
        assertThat(testBonTvx.getMotif_dep()).isEqualTo(UPDATED_MOTIFDEP);
        assertThat(testBonTvx.getCdtypmnt()).isEqualTo(UPDATED_CDTYPMNT);
        assertThat(testBonTvx.getDat_sor_prev()).isEqualTo(UPDATED_DATSORPREV);
        assertThat(testBonTvx.getDat_mnq_du()).isEqualTo(UPDATED_DATMNQDU);
        assertThat(testBonTvx.getDat_mnq_au()).isEqualTo(UPDATED_DATMNQAU);
        assertThat(testBonTvx.getDat_ent_ant()).isEqualTo(UPDATED_DATENTANT);
        assertThat(testBonTvx.getCodstat()).isEqualTo(UPDATED_CODSTAT);
        assertThat(testBonTvx.getDat_vld()).isEqualTo(UPDATED_DATVLD);
        assertThat(testBonTvx.getObserv1()).isEqualTo(UPDATED_OBSERV_1);
        assertThat(testBonTvx.getTesteur1()).isEqualTo(UPDATED_TESTEUR_1);
        assertThat(testBonTvx.getValid_ag()).isEqualTo(UPDATED_VALIDAG);
        assertThat(testBonTvx.getDat_sais()).isEqualTo(UPDATED_DATSAIS);
    }

    @Test
    void patchNonExistingBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bonTvxDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBonTvx() throws Exception {
        int databaseSizeBeforeUpdate = bonTvxRepository.findAll().size();
        bonTvx.setId(UUID.randomUUID().toString());

        // Create the BonTvx
        BonTvxDTO bonTvxDTO = bonTvxMapper.toDto(bonTvx);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBonTvxMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(bonTvxDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BonTvx in the database
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBonTvx() throws Exception {
        // Initialize the database
        bonTvxRepository.save(bonTvx);

        int databaseSizeBeforeDelete = bonTvxRepository.findAll().size();

        // Delete the bonTvx
        restBonTvxMockMvc
            .perform(delete(ENTITY_API_URL_ID, bonTvx.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BonTvx> bonTvxList = bonTvxRepository.findAll();
        assertThat(bonTvxList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
