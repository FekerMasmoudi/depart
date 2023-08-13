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
import tn.soretras.depart.domain.Itineraire;
import tn.soretras.depart.repository.ItineraireRepository;
import tn.soretras.depart.service.dto.ItineraireDTO;
import tn.soretras.depart.service.mapper.ItineraireMapper;

/**
 * Integration tests for the {@link ItineraireResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ItineraireResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DENUMLI = "AAAAAAAAAA";
    private static final String UPDATED_DENUMLI = "BBBBBBBBBB";

    private static final String DEFAULT_DECSTAT = "AAAAAAAAAA";
    private static final String UPDATED_DECSTAT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DENUMLG = 1;
    private static final Integer UPDATED_DENUMLG = 2;

    private static final Integer DEFAULT_DEKMSTA = 1;
    private static final Integer UPDATED_DEKMSTA = 2;

    private static final Integer DEFAULT_DEDURTR = 1;
    private static final Integer UPDATED_DEDURTR = 2;

    private static final Integer DEFAULT_DEESCALE = 1;
    private static final Integer UPDATED_DEESCALE = 2;

    private static final String DEFAULT_EMBRA = "AAAAAAAAAA";
    private static final String UPDATED_EMBRA = "BBBBBBBBBB";

    private static final Integer DEFAULT_SECTION = 1;
    private static final Integer UPDATED_SECTION = 2;

    private static final String DEFAULT_SENS = "AAAAAAAAAA";
    private static final String UPDATED_SENS = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYST = "AAAAAAAAAA";
    private static final String UPDATED_DECTYST = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/itineraires";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ItineraireRepository itineraireRepository;

    @Autowired
    private ItineraireMapper itineraireMapper;

    @Autowired
    private MockMvc restItineraireMockMvc;

    private Itineraire itineraire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Itineraire createEntity() {
        Itineraire itineraire = new Itineraire()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .denumli(DEFAULT_DENUMLI)
            .decstat(DEFAULT_DECSTAT)
            .denumlg(DEFAULT_DENUMLG)
            .dekmsta(DEFAULT_DEKMSTA)
            .dedurtr(DEFAULT_DEDURTR)
            .deescale(DEFAULT_DEESCALE)
            .embra(DEFAULT_EMBRA)
            .section(DEFAULT_SECTION)
            .sens(DEFAULT_SENS)
            .dectyst(DEFAULT_DECTYST);
        return itineraire;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Itineraire createUpdatedEntity() {
        Itineraire itineraire = new Itineraire()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .denumlg(UPDATED_DENUMLG)
            .dekmsta(UPDATED_DEKMSTA)
            .dedurtr(UPDATED_DEDURTR)
            .deescale(UPDATED_DEESCALE)
            .embra(UPDATED_EMBRA)
            .section(UPDATED_SECTION)
            .sens(UPDATED_SENS)
            .dectyst(UPDATED_DECTYST);
        return itineraire;
    }

    @BeforeEach
    public void initTest() {
        itineraireRepository.deleteAll();
        itineraire = createEntity();
    }

    @Test
    void createItineraire() throws Exception {
        int databaseSizeBeforeCreate = itineraireRepository.findAll().size();
        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);
        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isCreated());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeCreate + 1);
        Itineraire testItineraire = itineraireList.get(itineraireList.size() - 1);
        assertThat(testItineraire.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testItineraire.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testItineraire.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testItineraire.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testItineraire.getDenumlg()).isEqualTo(DEFAULT_DENUMLG);
        assertThat(testItineraire.getDekmsta()).isEqualTo(DEFAULT_DEKMSTA);
        assertThat(testItineraire.getDedurtr()).isEqualTo(DEFAULT_DEDURTR);
        assertThat(testItineraire.getDeescale()).isEqualTo(DEFAULT_DEESCALE);
        assertThat(testItineraire.getEmbra()).isEqualTo(DEFAULT_EMBRA);
        assertThat(testItineraire.getSection()).isEqualTo(DEFAULT_SECTION);
        assertThat(testItineraire.getSens()).isEqualTo(DEFAULT_SENS);
        assertThat(testItineraire.getDectyst()).isEqualTo(DEFAULT_DECTYST);
    }

    @Test
    void createItineraireWithExistingId() throws Exception {
        // Create the Itineraire with an existing ID
        itineraire.setId("existing_id");
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        int databaseSizeBeforeCreate = itineraireRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = itineraireRepository.findAll().size();
        // set the field null
        itineraire.setDecagenc(null);

        // Create the Itineraire, which fails.
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isBadRequest());

        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumliIsRequired() throws Exception {
        int databaseSizeBeforeTest = itineraireRepository.findAll().size();
        // set the field null
        itineraire.setDenumli(null);

        // Create the Itineraire, which fails.
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isBadRequest());

        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecstatIsRequired() throws Exception {
        int databaseSizeBeforeTest = itineraireRepository.findAll().size();
        // set the field null
        itineraire.setDecstat(null);

        // Create the Itineraire, which fails.
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isBadRequest());

        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDenumlgIsRequired() throws Exception {
        int databaseSizeBeforeTest = itineraireRepository.findAll().size();
        // set the field null
        itineraire.setDenumlg(null);

        // Create the Itineraire, which fails.
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        restItineraireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isBadRequest());

        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllItineraires() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        // Get all the itineraireList
        restItineraireMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(itineraire.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].denumli").value(hasItem(DEFAULT_DENUMLI)))
            .andExpect(jsonPath("$.[*].decstat").value(hasItem(DEFAULT_DECSTAT)))
            .andExpect(jsonPath("$.[*].denumlg").value(hasItem(DEFAULT_DENUMLG)))
            .andExpect(jsonPath("$.[*].dekmsta").value(hasItem(DEFAULT_DEKMSTA)))
            .andExpect(jsonPath("$.[*].dedurtr").value(hasItem(DEFAULT_DEDURTR)))
            .andExpect(jsonPath("$.[*].deescale").value(hasItem(DEFAULT_DEESCALE)))
            .andExpect(jsonPath("$.[*].embra").value(hasItem(DEFAULT_EMBRA)))
            .andExpect(jsonPath("$.[*].section").value(hasItem(DEFAULT_SECTION)))
            .andExpect(jsonPath("$.[*].sens").value(hasItem(DEFAULT_SENS)))
            .andExpect(jsonPath("$.[*].dectyst").value(hasItem(DEFAULT_DECTYST)));
    }

    @Test
    void getItineraire() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        // Get the itineraire
        restItineraireMockMvc
            .perform(get(ENTITY_API_URL_ID, itineraire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(itineraire.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.denumli").value(DEFAULT_DENUMLI))
            .andExpect(jsonPath("$.decstat").value(DEFAULT_DECSTAT))
            .andExpect(jsonPath("$.denumlg").value(DEFAULT_DENUMLG))
            .andExpect(jsonPath("$.dekmsta").value(DEFAULT_DEKMSTA))
            .andExpect(jsonPath("$.dedurtr").value(DEFAULT_DEDURTR))
            .andExpect(jsonPath("$.deescale").value(DEFAULT_DEESCALE))
            .andExpect(jsonPath("$.embra").value(DEFAULT_EMBRA))
            .andExpect(jsonPath("$.section").value(DEFAULT_SECTION))
            .andExpect(jsonPath("$.sens").value(DEFAULT_SENS))
            .andExpect(jsonPath("$.dectyst").value(DEFAULT_DECTYST));
    }

    @Test
    void getNonExistingItineraire() throws Exception {
        // Get the itineraire
        restItineraireMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingItineraire() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();

        // Update the itineraire
        Itineraire updatedItineraire = itineraireRepository.findById(itineraire.getId()).get();
        updatedItineraire
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .denumlg(UPDATED_DENUMLG)
            .dekmsta(UPDATED_DEKMSTA)
            .dedurtr(UPDATED_DEDURTR)
            .deescale(UPDATED_DEESCALE)
            .embra(UPDATED_EMBRA)
            .section(UPDATED_SECTION)
            .sens(UPDATED_SENS)
            .dectyst(UPDATED_DECTYST);
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(updatedItineraire);

        restItineraireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itineraireDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isOk());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
        Itineraire testItineraire = itineraireList.get(itineraireList.size() - 1);
        assertThat(testItineraire.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testItineraire.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testItineraire.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testItineraire.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testItineraire.getDenumlg()).isEqualTo(UPDATED_DENUMLG);
        assertThat(testItineraire.getDekmsta()).isEqualTo(UPDATED_DEKMSTA);
        assertThat(testItineraire.getDedurtr()).isEqualTo(UPDATED_DEDURTR);
        assertThat(testItineraire.getDeescale()).isEqualTo(UPDATED_DEESCALE);
        assertThat(testItineraire.getEmbra()).isEqualTo(UPDATED_EMBRA);
        assertThat(testItineraire.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testItineraire.getSens()).isEqualTo(UPDATED_SENS);
        assertThat(testItineraire.getDectyst()).isEqualTo(UPDATED_DECTYST);
    }

    @Test
    void putNonExistingItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, itineraireDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(itineraireDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateItineraireWithPatch() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();

        // Update the itineraire using partial update
        Itineraire partialUpdatedItineraire = new Itineraire();
        partialUpdatedItineraire.setId(itineraire.getId());

        partialUpdatedItineraire
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .denumlg(UPDATED_DENUMLG)
            .deescale(UPDATED_DEESCALE)
            .embra(UPDATED_EMBRA)
            .section(UPDATED_SECTION)
            .sens(UPDATED_SENS)
            .dectyst(UPDATED_DECTYST);

        restItineraireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItineraire.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItineraire))
            )
            .andExpect(status().isOk());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
        Itineraire testItineraire = itineraireList.get(itineraireList.size() - 1);
        assertThat(testItineraire.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testItineraire.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testItineraire.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testItineraire.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testItineraire.getDenumlg()).isEqualTo(UPDATED_DENUMLG);
        assertThat(testItineraire.getDekmsta()).isEqualTo(DEFAULT_DEKMSTA);
        assertThat(testItineraire.getDedurtr()).isEqualTo(DEFAULT_DEDURTR);
        assertThat(testItineraire.getDeescale()).isEqualTo(UPDATED_DEESCALE);
        assertThat(testItineraire.getEmbra()).isEqualTo(UPDATED_EMBRA);
        assertThat(testItineraire.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testItineraire.getSens()).isEqualTo(UPDATED_SENS);
        assertThat(testItineraire.getDectyst()).isEqualTo(UPDATED_DECTYST);
    }

    @Test
    void fullUpdateItineraireWithPatch() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();

        // Update the itineraire using partial update
        Itineraire partialUpdatedItineraire = new Itineraire();
        partialUpdatedItineraire.setId(itineraire.getId());

        partialUpdatedItineraire
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .denumli(UPDATED_DENUMLI)
            .decstat(UPDATED_DECSTAT)
            .denumlg(UPDATED_DENUMLG)
            .dekmsta(UPDATED_DEKMSTA)
            .dedurtr(UPDATED_DEDURTR)
            .deescale(UPDATED_DEESCALE)
            .embra(UPDATED_EMBRA)
            .section(UPDATED_SECTION)
            .sens(UPDATED_SENS)
            .dectyst(UPDATED_DECTYST);

        restItineraireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedItineraire.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedItineraire))
            )
            .andExpect(status().isOk());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
        Itineraire testItineraire = itineraireList.get(itineraireList.size() - 1);
        assertThat(testItineraire.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testItineraire.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testItineraire.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testItineraire.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testItineraire.getDenumlg()).isEqualTo(UPDATED_DENUMLG);
        assertThat(testItineraire.getDekmsta()).isEqualTo(UPDATED_DEKMSTA);
        assertThat(testItineraire.getDedurtr()).isEqualTo(UPDATED_DEDURTR);
        assertThat(testItineraire.getDeescale()).isEqualTo(UPDATED_DEESCALE);
        assertThat(testItineraire.getEmbra()).isEqualTo(UPDATED_EMBRA);
        assertThat(testItineraire.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testItineraire.getSens()).isEqualTo(UPDATED_SENS);
        assertThat(testItineraire.getDectyst()).isEqualTo(UPDATED_DECTYST);
    }

    @Test
    void patchNonExistingItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, itineraireDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamItineraire() throws Exception {
        int databaseSizeBeforeUpdate = itineraireRepository.findAll().size();
        itineraire.setId(UUID.randomUUID().toString());

        // Create the Itineraire
        ItineraireDTO itineraireDTO = itineraireMapper.toDto(itineraire);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restItineraireMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(itineraireDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Itineraire in the database
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteItineraire() throws Exception {
        // Initialize the database
        itineraireRepository.save(itineraire);

        int databaseSizeBeforeDelete = itineraireRepository.findAll().size();

        // Delete the itineraire
        restItineraireMockMvc
            .perform(delete(ENTITY_API_URL_ID, itineraire.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Itineraire> itineraireList = itineraireRepository.findAll();
        assertThat(itineraireList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
