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
import org.springframework.util.Base64Utils;
import tn.soretras.depart.IntegrationTest;
import tn.soretras.depart.domain.Ligne;
import tn.soretras.depart.repository.LigneRepository;
import tn.soretras.depart.service.dto.LigneDTO;
import tn.soretras.depart.service.mapper.LigneMapper;

/**
 * Integration tests for the {@link LigneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LigneResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DENUMLI = "AAAAAAAAAA";
    private static final String UPDATED_DENUMLI = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYLI = "AAAAAAAAAA";
    private static final String UPDATED_DECTYLI = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYTA = "AAAAAAAAAA";
    private static final String UPDATED_DECTYTA = "BBBBBBBBBB";

    private static final String DEFAULT_DENOMLI = "AAAAAAAAAA";
    private static final String UPDATED_DENOMLI = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYEQ = "AAAAAAAAAA";
    private static final String UPDATED_DECTYEQ = "BBBBBBBBBB";

    private static final Double DEFAULT_DENBRKM = (double) 1;
    private static final Double UPDATED_DENBRKM = (double) 2;

    private static final String DEFAULT_DETPARC = "1";
    private static final String UPDATED_DETPARC = "2";

    private static final Integer DEFAULT_DEDURAL = 1;
    private static final Integer UPDATED_DEDURAL = 2;

    private static final Integer DEFAULT_DEDURRT = 1;
    private static final Integer UPDATED_DEDURRT = 2;

    private static final Integer DEFAULT_DETRJVA = 1;
    private static final Integer UPDATED_DETRJVA = 2;

    private static final Integer DEFAULT_DETRJVR = 1;
    private static final Integer UPDATED_DETRJVR = 2;

    private static final Double DEFAULT_DEPISTE = (double) 1;
    private static final Double UPDATED_DEPISTE = (double) 2;

    private static final String DEFAULT_STATLIG = "AAAAAAAAAA";
    private static final String UPDATED_STATLIG = "BBBBBBBBBB";

    private static final String DEFAULT_LIG = "AAAAAAAAAA";
    private static final String UPDATED_LIG = "BBBBBBBBBB";

    private static final Integer DEFAULT_LIG_1 = 1;
    private static final Integer UPDATED_LIG_1 = 2;

    private static final String DEFAULT_VALIDE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDE = "BBBBBBBBBB";

    private static final String DEFAULT_DENUMLI_2 = "AAAAAAAAAA";
    private static final String UPDATED_DENUMLI_2 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_KML = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_KML = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_KML_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_KML_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_MIMTYPE = "AAAAAAAAAA";
    private static final String UPDATED_MIMTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_FILENAME = "AAAAAAAAAA";
    private static final String UPDATED_FILENAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHARSET = "AAAAAAAAAA";
    private static final String UPDATED_CHARSET = "BBBBBBBBBB";

    private static final String DEFAULT_LASTUPDATE = null;
    private static final String UPDATED_LASTUPDATE = null;

    private static final String ENTITY_API_URL = "/api/lignes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private LigneRepository ligneRepository;

    @Autowired
    private LigneMapper ligneMapper;

    @Autowired
    private MockMvc restLigneMockMvc;

    private Ligne ligne;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ligne createEntity() {
        Ligne ligne = new Ligne()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .denumli(DEFAULT_DENUMLI)
            .dectyli(DEFAULT_DECTYLI)
            .dectyta(DEFAULT_DECTYTA)
            .denomli(DEFAULT_DENOMLI)
            .dectyeq(DEFAULT_DECTYEQ)
            .denbrkm(DEFAULT_DENBRKM)
            .detparc(DEFAULT_DETPARC)
            .dedural(DEFAULT_DEDURAL)
            .dedurrt(DEFAULT_DEDURRT)
            .detrjva(DEFAULT_DETRJVA)
            .detrjvr(DEFAULT_DETRJVR)
            .depiste(DEFAULT_DEPISTE)
            .stat_lig(DEFAULT_STATLIG)
            .lig(DEFAULT_LIG)
            .lig1(DEFAULT_LIG_1)
            .valide(DEFAULT_VALIDE)
            .denumli2(DEFAULT_DENUMLI_2)
            .kml(DEFAULT_KML)
            .kmlContentType(DEFAULT_KML_CONTENT_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .mim_type(DEFAULT_MIMTYPE)
            .file_name(DEFAULT_FILENAME)
            .char_set(DEFAULT_CHARSET)
            .last_update(DEFAULT_LASTUPDATE);
        return ligne;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ligne createUpdatedEntity() {
        Ligne ligne = new Ligne()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .dectyli(UPDATED_DECTYLI)
            .dectyta(UPDATED_DECTYTA)
            .denomli(UPDATED_DENOMLI)
            .dectyeq(UPDATED_DECTYEQ)
            .denbrkm(UPDATED_DENBRKM)
            .detparc(UPDATED_DETPARC)
            .dedural(UPDATED_DEDURAL)
            .dedurrt(UPDATED_DEDURRT)
            .detrjva(UPDATED_DETRJVA)
            .detrjvr(UPDATED_DETRJVR)
            .depiste(UPDATED_DEPISTE)
            .stat_lig(UPDATED_STATLIG)
            .lig(UPDATED_LIG)
            .lig1(UPDATED_LIG_1)
            .valide(UPDATED_VALIDE)
            .denumli2(UPDATED_DENUMLI_2)
            .kml(UPDATED_KML)
            .kmlContentType(UPDATED_KML_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .mim_type(UPDATED_MIMTYPE)
            .file_name(UPDATED_FILENAME)
            .char_set(UPDATED_CHARSET)
            .last_update(UPDATED_LASTUPDATE);
        return ligne;
    }

    @BeforeEach
    public void initTest() {
        ligneRepository.deleteAll();
        ligne = createEntity();
    }

    @Test
    void createLigne() throws Exception {
        int databaseSizeBeforeCreate = ligneRepository.findAll().size();
        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);
        restLigneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ligneDTO)))
            .andExpect(status().isCreated());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeCreate + 1);
        Ligne testLigne = ligneList.get(ligneList.size() - 1);
        assertThat(testLigne.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testLigne.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testLigne.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testLigne.getDectyli()).isEqualTo(DEFAULT_DECTYLI);
        assertThat(testLigne.getDectyta()).isEqualTo(DEFAULT_DECTYTA);
        assertThat(testLigne.getDenomli()).isEqualTo(DEFAULT_DENOMLI);
        assertThat(testLigne.getDectyeq()).isEqualTo(DEFAULT_DECTYEQ);
        assertThat(testLigne.getDenbrkm()).isEqualTo(DEFAULT_DENBRKM);
        assertThat(testLigne.getDetparc()).isEqualTo(DEFAULT_DETPARC);
        assertThat(testLigne.getDedural()).isEqualTo(DEFAULT_DEDURAL);
        assertThat(testLigne.getDedurrt()).isEqualTo(DEFAULT_DEDURRT);
        assertThat(testLigne.getDetrjva()).isEqualTo(DEFAULT_DETRJVA);
        assertThat(testLigne.getDetrjvr()).isEqualTo(DEFAULT_DETRJVR);
        assertThat(testLigne.getDepiste()).isEqualTo(DEFAULT_DEPISTE);
        assertThat(testLigne.getStat_lig()).isEqualTo(DEFAULT_STATLIG);
        assertThat(testLigne.getLig()).isEqualTo(DEFAULT_LIG);
        assertThat(testLigne.getLig1()).isEqualTo(DEFAULT_LIG_1);
        assertThat(testLigne.getValide()).isEqualTo(DEFAULT_VALIDE);
        assertThat(testLigne.getDenumli2()).isEqualTo(DEFAULT_DENUMLI_2);
        assertThat(testLigne.getKml()).isEqualTo(DEFAULT_KML);
        assertThat(testLigne.getKmlContentType()).isEqualTo(DEFAULT_KML_CONTENT_TYPE);
        assertThat(testLigne.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLigne.getMim_type()).isEqualTo(DEFAULT_MIMTYPE);
        assertThat(testLigne.getFile_name()).isEqualTo(DEFAULT_FILENAME);
        assertThat(testLigne.getChar_set()).isEqualTo(DEFAULT_CHARSET);
        assertThat(testLigne.getLast_update()).isEqualTo(DEFAULT_LASTUPDATE);
    }

    @Test
    void createLigneWithExistingId() throws Exception {
        // Create the Ligne with an existing ID
        ligne.setId("existing_id");
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        int databaseSizeBeforeCreate = ligneRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLigneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ligneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllLignes() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        // Get all the ligneList
        restLigneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ligne.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].denumli").value(hasItem(DEFAULT_DENUMLI)))
            .andExpect(jsonPath("$.[*].dectyli").value(hasItem(DEFAULT_DECTYLI)))
            .andExpect(jsonPath("$.[*].dectyta").value(hasItem(DEFAULT_DECTYTA)))
            .andExpect(jsonPath("$.[*].denomli").value(hasItem(DEFAULT_DENOMLI)))
            .andExpect(jsonPath("$.[*].dectyeq").value(hasItem(DEFAULT_DECTYEQ)))
            .andExpect(jsonPath("$.[*].denbrkm").value(hasItem(DEFAULT_DENBRKM)))
            .andExpect(jsonPath("$.[*].detparc").value(hasItem(DEFAULT_DETPARC)))
            .andExpect(jsonPath("$.[*].dedural").value(hasItem(DEFAULT_DEDURAL)))
            .andExpect(jsonPath("$.[*].dedurrt").value(hasItem(DEFAULT_DEDURRT)))
            .andExpect(jsonPath("$.[*].detrjva").value(hasItem(DEFAULT_DETRJVA)))
            .andExpect(jsonPath("$.[*].detrjvr").value(hasItem(DEFAULT_DETRJVR)))
            .andExpect(jsonPath("$.[*].depiste").value(hasItem(DEFAULT_DEPISTE)))
            .andExpect(jsonPath("$.[*].statlig").value(hasItem(DEFAULT_STATLIG)))
            .andExpect(jsonPath("$.[*].lig").value(hasItem(DEFAULT_LIG)))
            .andExpect(jsonPath("$.[*].lig1").value(hasItem(DEFAULT_LIG_1)))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE)))
            .andExpect(jsonPath("$.[*].denumli2").value(hasItem(DEFAULT_DENUMLI_2)))
            .andExpect(jsonPath("$.[*].kmlContentType").value(hasItem(DEFAULT_KML_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].kml").value(hasItem(Base64Utils.encodeToString(DEFAULT_KML))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].mimtype").value(hasItem(DEFAULT_MIMTYPE)))
            .andExpect(jsonPath("$.[*].filename").value(hasItem(DEFAULT_FILENAME)))
            .andExpect(jsonPath("$.[*].charset").value(hasItem(DEFAULT_CHARSET)))
            .andExpect(jsonPath("$.[*].lastupdate").value(hasItem(null)));
    }

    @Test
    void getLigne() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        // Get the ligne
        restLigneMockMvc
            .perform(get(ENTITY_API_URL_ID, ligne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ligne.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.denumli").value(DEFAULT_DENUMLI))
            .andExpect(jsonPath("$.dectyli").value(DEFAULT_DECTYLI))
            .andExpect(jsonPath("$.dectyta").value(DEFAULT_DECTYTA))
            .andExpect(jsonPath("$.denomli").value(DEFAULT_DENOMLI))
            .andExpect(jsonPath("$.dectyeq").value(DEFAULT_DECTYEQ))
            .andExpect(jsonPath("$.denbrkm").value(DEFAULT_DENBRKM))
            .andExpect(jsonPath("$.detparc").value(DEFAULT_DETPARC))
            .andExpect(jsonPath("$.dedural").value(DEFAULT_DEDURAL))
            .andExpect(jsonPath("$.dedurrt").value(DEFAULT_DEDURRT))
            .andExpect(jsonPath("$.detrjva").value(DEFAULT_DETRJVA))
            .andExpect(jsonPath("$.detrjvr").value(DEFAULT_DETRJVR))
            .andExpect(jsonPath("$.depiste").value(DEFAULT_DEPISTE))
            .andExpect(jsonPath("$.statlig").value(DEFAULT_STATLIG))
            .andExpect(jsonPath("$.lig").value(DEFAULT_LIG))
            .andExpect(jsonPath("$.lig1").value(DEFAULT_LIG_1))
            .andExpect(jsonPath("$.valide").value(DEFAULT_VALIDE))
            .andExpect(jsonPath("$.denumli2").value(DEFAULT_DENUMLI_2))
            .andExpect(jsonPath("$.kmlContentType").value(DEFAULT_KML_CONTENT_TYPE))
            .andExpect(jsonPath("$.kml").value(Base64Utils.encodeToString(DEFAULT_KML)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.mimtype").value(DEFAULT_MIMTYPE))
            .andExpect(jsonPath("$.filename").value(DEFAULT_FILENAME))
            .andExpect(jsonPath("$.charset").value(DEFAULT_CHARSET))
            .andExpect(jsonPath("$.lastupdate").value(null));
    }

    @Test
    void getNonExistingLigne() throws Exception {
        // Get the ligne
        restLigneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingLigne() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();

        // Update the ligne
        Ligne updatedLigne = ligneRepository.findById(ligne.getId()).get();
        updatedLigne
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .dectyli(UPDATED_DECTYLI)
            .dectyta(UPDATED_DECTYTA)
            .denomli(UPDATED_DENOMLI)
            .dectyeq(UPDATED_DECTYEQ)
            .denbrkm(UPDATED_DENBRKM)
            .detparc(UPDATED_DETPARC)
            .dedural(UPDATED_DEDURAL)
            .dedurrt(UPDATED_DEDURRT)
            .detrjva(UPDATED_DETRJVA)
            .detrjvr(UPDATED_DETRJVR)
            .depiste(UPDATED_DEPISTE)
            .stat_lig(UPDATED_STATLIG)
            .lig(UPDATED_LIG)
            .lig1(UPDATED_LIG_1)
            .valide(UPDATED_VALIDE)
            .denumli2(UPDATED_DENUMLI_2)
            .kml(UPDATED_KML)
            .kmlContentType(UPDATED_KML_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .mim_type(UPDATED_MIMTYPE)
            .file_name(UPDATED_FILENAME)
            .char_set(UPDATED_CHARSET)
            .last_update(UPDATED_LASTUPDATE);
        LigneDTO ligneDTO = ligneMapper.toDto(updatedLigne);

        restLigneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ligneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ligneDTO))
            )
            .andExpect(status().isOk());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
        Ligne testLigne = ligneList.get(ligneList.size() - 1);
        assertThat(testLigne.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testLigne.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testLigne.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testLigne.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testLigne.getDectyta()).isEqualTo(UPDATED_DECTYTA);
        assertThat(testLigne.getDenomli()).isEqualTo(UPDATED_DENOMLI);
        assertThat(testLigne.getDectyeq()).isEqualTo(UPDATED_DECTYEQ);
        assertThat(testLigne.getDenbrkm()).isEqualTo(UPDATED_DENBRKM);
        assertThat(testLigne.getDetparc()).isEqualTo(UPDATED_DETPARC);
        assertThat(testLigne.getDedural()).isEqualTo(UPDATED_DEDURAL);
        assertThat(testLigne.getDedurrt()).isEqualTo(UPDATED_DEDURRT);
        assertThat(testLigne.getDetrjva()).isEqualTo(UPDATED_DETRJVA);
        assertThat(testLigne.getDetrjvr()).isEqualTo(UPDATED_DETRJVR);
        assertThat(testLigne.getDepiste()).isEqualTo(UPDATED_DEPISTE);
        assertThat(testLigne.getStat_lig()).isEqualTo(UPDATED_STATLIG);
        assertThat(testLigne.getLig()).isEqualTo(UPDATED_LIG);
        assertThat(testLigne.getLig1()).isEqualTo(UPDATED_LIG_1);
        assertThat(testLigne.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testLigne.getDenumli2()).isEqualTo(UPDATED_DENUMLI_2);
        assertThat(testLigne.getKml()).isEqualTo(UPDATED_KML);
        assertThat(testLigne.getKmlContentType()).isEqualTo(UPDATED_KML_CONTENT_TYPE);
        assertThat(testLigne.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLigne.getMim_type()).isEqualTo(UPDATED_MIMTYPE);
        assertThat(testLigne.getFile_name()).isEqualTo(UPDATED_FILENAME);
        assertThat(testLigne.getChar_set()).isEqualTo(UPDATED_CHARSET);
        assertThat(testLigne.getLast_update()).isEqualTo(UPDATED_LASTUPDATE);
    }

    @Test
    void putNonExistingLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ligneDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ligneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ligneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(ligneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLigneWithPatch() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();

        // Update the ligne using partial update
        Ligne partialUpdatedLigne = new Ligne();
        partialUpdatedLigne.setId(ligne.getId());

        partialUpdatedLigne
            .deccent(UPDATED_DECCENT)
            .dectyli(UPDATED_DECTYLI)
            .dectyta(UPDATED_DECTYTA)
            .denomli(UPDATED_DENOMLI)
            .dectyeq(UPDATED_DECTYEQ)
            .denbrkm(UPDATED_DENBRKM)
            .dedurrt(UPDATED_DEDURRT)
            .lig1(UPDATED_LIG_1)
            .denumli2(UPDATED_DENUMLI_2)
            .mim_type(UPDATED_MIMTYPE)
            .char_set(UPDATED_CHARSET);

        restLigneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLigne.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLigne))
            )
            .andExpect(status().isOk());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
        Ligne testLigne = ligneList.get(ligneList.size() - 1);
        assertThat(testLigne.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testLigne.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testLigne.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testLigne.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testLigne.getDectyta()).isEqualTo(UPDATED_DECTYTA);
        assertThat(testLigne.getDenomli()).isEqualTo(UPDATED_DENOMLI);
        assertThat(testLigne.getDectyeq()).isEqualTo(UPDATED_DECTYEQ);
        assertThat(testLigne.getDenbrkm()).isEqualTo(UPDATED_DENBRKM);
        assertThat(testLigne.getDetparc()).isEqualTo(DEFAULT_DETPARC);
        assertThat(testLigne.getDedural()).isEqualTo(DEFAULT_DEDURAL);
        assertThat(testLigne.getDedurrt()).isEqualTo(UPDATED_DEDURRT);
        assertThat(testLigne.getDetrjva()).isEqualTo(DEFAULT_DETRJVA);
        assertThat(testLigne.getDetrjvr()).isEqualTo(DEFAULT_DETRJVR);
        assertThat(testLigne.getDepiste()).isEqualTo(DEFAULT_DEPISTE);
        assertThat(testLigne.getStat_lig()).isEqualTo(DEFAULT_STATLIG);
        assertThat(testLigne.getLig()).isEqualTo(DEFAULT_LIG);
        assertThat(testLigne.getLig1()).isEqualTo(UPDATED_LIG_1);
        assertThat(testLigne.getValide()).isEqualTo(DEFAULT_VALIDE);
        assertThat(testLigne.getDenumli2()).isEqualTo(UPDATED_DENUMLI_2);
        assertThat(testLigne.getKml()).isEqualTo(DEFAULT_KML);
        assertThat(testLigne.getKmlContentType()).isEqualTo(DEFAULT_KML_CONTENT_TYPE);
        assertThat(testLigne.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLigne.getMim_type()).isEqualTo(UPDATED_MIMTYPE);
        assertThat(testLigne.getFile_name()).isEqualTo(DEFAULT_FILENAME);
        assertThat(testLigne.getChar_set()).isEqualTo(UPDATED_CHARSET);
        assertThat(testLigne.getLast_update()).isEqualTo(DEFAULT_LASTUPDATE);
    }

    @Test
    void fullUpdateLigneWithPatch() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();

        // Update the ligne using partial update
        Ligne partialUpdatedLigne = new Ligne();
        partialUpdatedLigne.setId(ligne.getId());

        partialUpdatedLigne
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .dectyli(UPDATED_DECTYLI)
            .dectyta(UPDATED_DECTYTA)
            .denomli(UPDATED_DENOMLI)
            .dectyeq(UPDATED_DECTYEQ)
            .denbrkm(UPDATED_DENBRKM)
            .detparc(UPDATED_DETPARC)
            .dedural(UPDATED_DEDURAL)
            .dedurrt(UPDATED_DEDURRT)
            .detrjva(UPDATED_DETRJVA)
            .detrjvr(UPDATED_DETRJVR)
            .depiste(UPDATED_DEPISTE)
            .stat_lig(UPDATED_STATLIG)
            .lig(UPDATED_LIG)
            .lig1(UPDATED_LIG_1)
            .valide(UPDATED_VALIDE)
            .denumli2(UPDATED_DENUMLI_2)
            .kml(UPDATED_KML)
            .kmlContentType(UPDATED_KML_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION)
            .mim_type(UPDATED_MIMTYPE)
            .file_name(UPDATED_FILENAME)
            .char_set(UPDATED_CHARSET)
            .last_update(UPDATED_LASTUPDATE);

        restLigneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLigne.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLigne))
            )
            .andExpect(status().isOk());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
        Ligne testLigne = ligneList.get(ligneList.size() - 1);
        assertThat(testLigne.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testLigne.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testLigne.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testLigne.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testLigne.getDectyta()).isEqualTo(UPDATED_DECTYTA);
        assertThat(testLigne.getDenomli()).isEqualTo(UPDATED_DENOMLI);
        assertThat(testLigne.getDectyeq()).isEqualTo(UPDATED_DECTYEQ);
        assertThat(testLigne.getDenbrkm()).isEqualTo(UPDATED_DENBRKM);
        assertThat(testLigne.getDetparc()).isEqualTo(UPDATED_DETPARC);
        assertThat(testLigne.getDedural()).isEqualTo(UPDATED_DEDURAL);
        assertThat(testLigne.getDedurrt()).isEqualTo(UPDATED_DEDURRT);
        assertThat(testLigne.getDetrjva()).isEqualTo(UPDATED_DETRJVA);
        assertThat(testLigne.getDetrjvr()).isEqualTo(UPDATED_DETRJVR);
        assertThat(testLigne.getDepiste()).isEqualTo(UPDATED_DEPISTE);
        assertThat(testLigne.getStat_lig()).isEqualTo(UPDATED_STATLIG);
        assertThat(testLigne.getLig()).isEqualTo(UPDATED_LIG);
        assertThat(testLigne.getLig1()).isEqualTo(UPDATED_LIG_1);
        assertThat(testLigne.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testLigne.getDenumli2()).isEqualTo(UPDATED_DENUMLI_2);
        assertThat(testLigne.getKml()).isEqualTo(UPDATED_KML);
        assertThat(testLigne.getKmlContentType()).isEqualTo(UPDATED_KML_CONTENT_TYPE);
        assertThat(testLigne.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLigne.getMim_type()).isEqualTo(UPDATED_MIMTYPE);
        assertThat(testLigne.getFile_name()).isEqualTo(UPDATED_FILENAME);
        assertThat(testLigne.getChar_set()).isEqualTo(UPDATED_CHARSET);
        assertThat(testLigne.getLast_update()).isEqualTo(UPDATED_LASTUPDATE);
    }

    @Test
    void patchNonExistingLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ligneDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ligneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ligneDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLigne() throws Exception {
        int databaseSizeBeforeUpdate = ligneRepository.findAll().size();
        ligne.setId(UUID.randomUUID().toString());

        // Create the Ligne
        LigneDTO ligneDTO = ligneMapper.toDto(ligne);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLigneMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(ligneDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ligne in the database
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLigne() throws Exception {
        // Initialize the database
        ligneRepository.save(ligne);

        int databaseSizeBeforeDelete = ligneRepository.findAll().size();

        // Delete the ligne
        restLigneMockMvc
            .perform(delete(ENTITY_API_URL_ID, ligne.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ligne> ligneList = ligneRepository.findAll();
        assertThat(ligneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
