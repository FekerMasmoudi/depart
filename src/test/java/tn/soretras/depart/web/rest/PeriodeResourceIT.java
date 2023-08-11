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
import tn.soretras.depart.domain.Periode;
import tn.soretras.depart.repository.PeriodeRepository;
import tn.soretras.depart.service.dto.PeriodeDTO;
import tn.soretras.depart.service.mapper.PeriodeMapper;

/**
 * Integration tests for the {@link PeriodeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PeriodeResourceIT {

    private static final String DEFAULT_DECOPER = "AAAAAAAAAA";
    private static final String UPDATED_DECOPER = "BBBBBBBBBB";

    private static final String DEFAULT_DENOPER = "AAAAAAAAAA";
    private static final String UPDATED_DENOPER = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMAIRE = "AAAAAAAAAA";
    private static final String UPDATED_PRIMAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_STARTDATE = "AAAAAAAAAA";
    private static final String UPDATED_STARTDATE = "BBBBBBBBBB";

    private static final String DEFAULT_ENDDATE = "AAAAAAAAAA";
    private static final String UPDATED_ENDDATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/periodes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private PeriodeRepository periodeRepository;

    @Autowired
    private PeriodeMapper periodeMapper;

    @Autowired
    private MockMvc restPeriodeMockMvc;

    private Periode periode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createEntity() {
        Periode periode = new Periode()
            .decoper(DEFAULT_DECOPER)
            .denoper(DEFAULT_DENOPER)
            .primaire(DEFAULT_PRIMAIRE)
            .startdate(DEFAULT_STARTDATE)
            .enddate(DEFAULT_ENDDATE);
        return periode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periode createUpdatedEntity() {
        Periode periode = new Periode()
            .decoper(UPDATED_DECOPER)
            .denoper(UPDATED_DENOPER)
            .primaire(UPDATED_PRIMAIRE)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE);
        return periode;
    }

    @BeforeEach
    public void initTest() {
        periodeRepository.deleteAll();
        periode = createEntity();
    }

    @Test
    void createPeriode() throws Exception {
        int databaseSizeBeforeCreate = periodeRepository.findAll().size();
        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);
        restPeriodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isCreated());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate + 1);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testPeriode.getDenoper()).isEqualTo(DEFAULT_DENOPER);
        assertThat(testPeriode.getPrimaire()).isEqualTo(DEFAULT_PRIMAIRE);
        assertThat(testPeriode.getStartdate()).isEqualTo(DEFAULT_STARTDATE);
        assertThat(testPeriode.getEnddate()).isEqualTo(DEFAULT_ENDDATE);
    }

    @Test
    void createPeriodeWithExistingId() throws Exception {
        // Create the Periode with an existing ID
        periode.setId("existing_id");
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        int databaseSizeBeforeCreate = periodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPeriodes() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        // Get all the periodeList
        restPeriodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periode.getId())))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].denoper").value(hasItem(DEFAULT_DENOPER)))
            .andExpect(jsonPath("$.[*].primaire").value(hasItem(DEFAULT_PRIMAIRE)))
            .andExpect(jsonPath("$.[*].startdate").value(hasItem(DEFAULT_STARTDATE)))
            .andExpect(jsonPath("$.[*].enddate").value(hasItem(DEFAULT_ENDDATE)));
    }

    @Test
    void getPeriode() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        // Get the periode
        restPeriodeMockMvc
            .perform(get(ENTITY_API_URL_ID, periode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(periode.getId()))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.denoper").value(DEFAULT_DENOPER))
            .andExpect(jsonPath("$.primaire").value(DEFAULT_PRIMAIRE))
            .andExpect(jsonPath("$.startdate").value(DEFAULT_STARTDATE))
            .andExpect(jsonPath("$.enddate").value(DEFAULT_ENDDATE));
    }

    @Test
    void getNonExistingPeriode() throws Exception {
        // Get the periode
        restPeriodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingPeriode() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Update the periode
        Periode updatedPeriode = periodeRepository.findById(periode.getId()).get();
        updatedPeriode
            .decoper(UPDATED_DECOPER)
            .denoper(UPDATED_DENOPER)
            .primaire(UPDATED_PRIMAIRE)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE);
        PeriodeDTO periodeDTO = periodeMapper.toDto(updatedPeriode);

        restPeriodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, periodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testPeriode.getDenoper()).isEqualTo(UPDATED_DENOPER);
        assertThat(testPeriode.getPrimaire()).isEqualTo(UPDATED_PRIMAIRE);
        assertThat(testPeriode.getStartdate()).isEqualTo(UPDATED_STARTDATE);
        assertThat(testPeriode.getEnddate()).isEqualTo(UPDATED_ENDDATE);
    }

    @Test
    void putNonExistingPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, periodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(periodeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePeriodeWithPatch() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Update the periode using partial update
        Periode partialUpdatedPeriode = new Periode();
        partialUpdatedPeriode.setId(periode.getId());

        partialUpdatedPeriode.decoper(UPDATED_DECOPER).enddate(UPDATED_ENDDATE);

        restPeriodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPeriode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPeriode))
            )
            .andExpect(status().isOk());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testPeriode.getDenoper()).isEqualTo(DEFAULT_DENOPER);
        assertThat(testPeriode.getPrimaire()).isEqualTo(DEFAULT_PRIMAIRE);
        assertThat(testPeriode.getStartdate()).isEqualTo(DEFAULT_STARTDATE);
        assertThat(testPeriode.getEnddate()).isEqualTo(UPDATED_ENDDATE);
    }

    @Test
    void fullUpdatePeriodeWithPatch() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();

        // Update the periode using partial update
        Periode partialUpdatedPeriode = new Periode();
        partialUpdatedPeriode.setId(periode.getId());

        partialUpdatedPeriode
            .decoper(UPDATED_DECOPER)
            .denoper(UPDATED_DENOPER)
            .primaire(UPDATED_PRIMAIRE)
            .startdate(UPDATED_STARTDATE)
            .enddate(UPDATED_ENDDATE);

        restPeriodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPeriode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPeriode))
            )
            .andExpect(status().isOk());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
        Periode testPeriode = periodeList.get(periodeList.size() - 1);
        assertThat(testPeriode.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testPeriode.getDenoper()).isEqualTo(UPDATED_DENOPER);
        assertThat(testPeriode.getPrimaire()).isEqualTo(UPDATED_PRIMAIRE);
        assertThat(testPeriode.getStartdate()).isEqualTo(UPDATED_STARTDATE);
        assertThat(testPeriode.getEnddate()).isEqualTo(UPDATED_ENDDATE);
    }

    @Test
    void patchNonExistingPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, periodeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPeriode() throws Exception {
        int databaseSizeBeforeUpdate = periodeRepository.findAll().size();
        periode.setId(UUID.randomUUID().toString());

        // Create the Periode
        PeriodeDTO periodeDTO = periodeMapper.toDto(periode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPeriodeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(periodeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Periode in the database
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePeriode() throws Exception {
        // Initialize the database
        periodeRepository.save(periode);

        int databaseSizeBeforeDelete = periodeRepository.findAll().size();

        // Delete the periode
        restPeriodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, periode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Periode> periodeList = periodeRepository.findAll();
        assertThat(periodeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
