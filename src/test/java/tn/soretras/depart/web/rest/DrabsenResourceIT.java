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
import tn.soretras.depart.domain.Drabsen;
import tn.soretras.depart.repository.DrabsenRepository;
import tn.soretras.depart.service.dto.DrabsenDTO;
import tn.soretras.depart.service.mapper.DrabsenMapper;

/**
 * Integration tests for the {@link DrabsenResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DrabsenResourceIT {

    private static final String DEFAULT_CDTYPAB = "AAAAAAAAAA";
    private static final String UPDATED_CDTYPAB = "BBBBBBBBBB";

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final String DEFAULT_DATABS = "2023-08-08T22:00:00Z";
    private static final String UPDATED_DATABS = "2023-08-08T22:00:00Z";

    private static final Integer DEFAULT_NUMABS = 1;
    private static final Integer UPDATED_NUMABS = 2;

    private static final Integer DEFAULT_NBRABS = 1;
    private static final Integer UPDATED_NBRABS = 2;

    private static final Integer DEFAULT_VALIDABS = 1;
    private static final Integer UPDATED_VALIDABS = 2;

    private static final String DEFAULT_OBSERVAABS = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVAABS = "BBBBBBBBBB";

    private static final Integer DEFAULT_CD_1 = 1;
    private static final Integer UPDATED_CD_1 = 2;

    private static final Integer DEFAULT_CD_2 = 1;
    private static final Integer UPDATED_CD_2 = 2;

    private static final Integer DEFAULT_CD_3 = 1;
    private static final Integer UPDATED_CD_3 = 2;

    private static final String ENTITY_API_URL = "/api/drabsens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DrabsenRepository drabsenRepository;

    @Autowired
    private DrabsenMapper drabsenMapper;

    @Autowired
    private MockMvc restDrabsenMockMvc;

    private Drabsen drabsen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Drabsen createEntity() {
        Drabsen drabsen = new Drabsen()
            .cdtypab(DEFAULT_CDTYPAB)
            .matric(DEFAULT_MATRIC)
            .dat_abs(DEFAULT_DATABS)
            .num_abs(DEFAULT_NUMABS)
            .nbr_abs(DEFAULT_NBRABS)
            .valid_abs(DEFAULT_VALIDABS)
            .observa_abs(DEFAULT_OBSERVAABS)
            .cd1(DEFAULT_CD_1)
            .cd2(DEFAULT_CD_2)
            .cd3(DEFAULT_CD_3);
        return drabsen;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Drabsen createUpdatedEntity() {
        Drabsen drabsen = new Drabsen()
            .cdtypab(UPDATED_CDTYPAB)
            .matric(UPDATED_MATRIC)
            .dat_abs(UPDATED_DATABS)
            .num_abs(UPDATED_NUMABS)
            .nbr_abs(UPDATED_NBRABS)
            .valid_abs(UPDATED_VALIDABS)
            .observa_abs(UPDATED_OBSERVAABS)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3);
        return drabsen;
    }

    @BeforeEach
    public void initTest() {
        drabsenRepository.deleteAll();
        drabsen = createEntity();
    }

    @Test
    void createDrabsen() throws Exception {
        int databaseSizeBeforeCreate = drabsenRepository.findAll().size();
        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);
        restDrabsenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drabsenDTO)))
            .andExpect(status().isCreated());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeCreate + 1);
        Drabsen testDrabsen = drabsenList.get(drabsenList.size() - 1);
        assertThat(testDrabsen.getCdtypab()).isEqualTo(DEFAULT_CDTYPAB);
        assertThat(testDrabsen.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testDrabsen.getDat_abs()).isEqualTo(DEFAULT_DATABS);
        assertThat(testDrabsen.getNum_abs()).isEqualTo(DEFAULT_NUMABS);
        assertThat(testDrabsen.getNbr_abs()).isEqualTo(DEFAULT_NBRABS);
        assertThat(testDrabsen.getValid_abs()).isEqualTo(DEFAULT_VALIDABS);
        assertThat(testDrabsen.getObserva_abs()).isEqualTo(DEFAULT_OBSERVAABS);
        assertThat(testDrabsen.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testDrabsen.getCd2()).isEqualTo(DEFAULT_CD_2);
        assertThat(testDrabsen.getCd3()).isEqualTo(DEFAULT_CD_3);
    }

    @Test
    void createDrabsenWithExistingId() throws Exception {
        // Create the Drabsen with an existing ID
        drabsen.setId("existing_id");
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        int databaseSizeBeforeCreate = drabsenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDrabsenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drabsenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDrabsens() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        // Get all the drabsenList
        restDrabsenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(drabsen.getId())))
            .andExpect(jsonPath("$.[*].cdtypab").value(hasItem(DEFAULT_CDTYPAB)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].databs").value(hasItem(DEFAULT_DATABS.toString())))
            .andExpect(jsonPath("$.[*].numabs").value(hasItem(DEFAULT_NUMABS)))
            .andExpect(jsonPath("$.[*].nbrabs").value(hasItem(DEFAULT_NBRABS)))
            .andExpect(jsonPath("$.[*].validabs").value(hasItem(DEFAULT_VALIDABS)))
            .andExpect(jsonPath("$.[*].observaabs").value(hasItem(DEFAULT_OBSERVAABS)))
            .andExpect(jsonPath("$.[*].cd1").value(hasItem(DEFAULT_CD_1)))
            .andExpect(jsonPath("$.[*].cd2").value(hasItem(DEFAULT_CD_2)))
            .andExpect(jsonPath("$.[*].cd3").value(hasItem(DEFAULT_CD_3)));
    }

    @Test
    void getDrabsen() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        // Get the drabsen
        restDrabsenMockMvc
            .perform(get(ENTITY_API_URL_ID, drabsen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(drabsen.getId()))
            .andExpect(jsonPath("$.cdtypab").value(DEFAULT_CDTYPAB))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.databs").value(DEFAULT_DATABS.toString()))
            .andExpect(jsonPath("$.numabs").value(DEFAULT_NUMABS))
            .andExpect(jsonPath("$.nbrabs").value(DEFAULT_NBRABS))
            .andExpect(jsonPath("$.validabs").value(DEFAULT_VALIDABS))
            .andExpect(jsonPath("$.observaabs").value(DEFAULT_OBSERVAABS))
            .andExpect(jsonPath("$.cd1").value(DEFAULT_CD_1))
            .andExpect(jsonPath("$.cd2").value(DEFAULT_CD_2))
            .andExpect(jsonPath("$.cd3").value(DEFAULT_CD_3));
    }

    @Test
    void getNonExistingDrabsen() throws Exception {
        // Get the drabsen
        restDrabsenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDrabsen() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();

        // Update the drabsen
        Drabsen updatedDrabsen = drabsenRepository.findById(drabsen.getId()).get();
        updatedDrabsen
            .cdtypab(UPDATED_CDTYPAB)
            .matric(UPDATED_MATRIC)
            .dat_abs(UPDATED_DATABS)
            .num_abs(UPDATED_NUMABS)
            .nbr_abs(UPDATED_NBRABS)
            .valid_abs(UPDATED_VALIDABS)
            .observa_abs(UPDATED_OBSERVAABS)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3);
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(updatedDrabsen);

        restDrabsenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, drabsenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isOk());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
        Drabsen testDrabsen = drabsenList.get(drabsenList.size() - 1);
        assertThat(testDrabsen.getCdtypab()).isEqualTo(UPDATED_CDTYPAB);
        assertThat(testDrabsen.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDrabsen.getDat_abs()).isEqualTo(UPDATED_DATABS);
        assertThat(testDrabsen.getNum_abs()).isEqualTo(UPDATED_NUMABS);
        assertThat(testDrabsen.getNbr_abs()).isEqualTo(UPDATED_NBRABS);
        assertThat(testDrabsen.getValid_abs()).isEqualTo(UPDATED_VALIDABS);
        assertThat(testDrabsen.getObserva_abs()).isEqualTo(UPDATED_OBSERVAABS);
        assertThat(testDrabsen.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDrabsen.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDrabsen.getCd3()).isEqualTo(UPDATED_CD_3);
    }

    @Test
    void putNonExistingDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, drabsenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(drabsenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDrabsenWithPatch() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();

        // Update the drabsen using partial update
        Drabsen partialUpdatedDrabsen = new Drabsen();
        partialUpdatedDrabsen.setId(drabsen.getId());

        partialUpdatedDrabsen
            .matric(UPDATED_MATRIC)
            .dat_abs(UPDATED_DATABS)
            .num_abs(UPDATED_NUMABS)
            .valid_abs(UPDATED_VALIDABS)
            .observa_abs(UPDATED_OBSERVAABS)
            .cd2(UPDATED_CD_2);

        restDrabsenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDrabsen.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDrabsen))
            )
            .andExpect(status().isOk());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
        Drabsen testDrabsen = drabsenList.get(drabsenList.size() - 1);
        assertThat(testDrabsen.getCdtypab()).isEqualTo(DEFAULT_CDTYPAB);
        assertThat(testDrabsen.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDrabsen.getDat_abs()).isEqualTo(UPDATED_DATABS);
        assertThat(testDrabsen.getNum_abs()).isEqualTo(UPDATED_NUMABS);
        assertThat(testDrabsen.getNbr_abs()).isEqualTo(DEFAULT_NBRABS);
        assertThat(testDrabsen.getValid_abs()).isEqualTo(UPDATED_VALIDABS);
        assertThat(testDrabsen.getObserva_abs()).isEqualTo(UPDATED_OBSERVAABS);
        assertThat(testDrabsen.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testDrabsen.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDrabsen.getCd3()).isEqualTo(DEFAULT_CD_3);
    }

    @Test
    void fullUpdateDrabsenWithPatch() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();

        // Update the drabsen using partial update
        Drabsen partialUpdatedDrabsen = new Drabsen();
        partialUpdatedDrabsen.setId(drabsen.getId());

        partialUpdatedDrabsen
            .cdtypab(UPDATED_CDTYPAB)
            .matric(UPDATED_MATRIC)
            .dat_abs(UPDATED_DATABS)
            .num_abs(UPDATED_NUMABS)
            .nbr_abs(UPDATED_NBRABS)
            .valid_abs(UPDATED_VALIDABS)
            .observa_abs(UPDATED_OBSERVAABS)
            .cd1(UPDATED_CD_1)
            .cd2(UPDATED_CD_2)
            .cd3(UPDATED_CD_3);

        restDrabsenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDrabsen.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDrabsen))
            )
            .andExpect(status().isOk());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
        Drabsen testDrabsen = drabsenList.get(drabsenList.size() - 1);
        assertThat(testDrabsen.getCdtypab()).isEqualTo(UPDATED_CDTYPAB);
        assertThat(testDrabsen.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testDrabsen.getDat_abs()).isEqualTo(UPDATED_DATABS);
        assertThat(testDrabsen.getNum_abs()).isEqualTo(UPDATED_NUMABS);
        assertThat(testDrabsen.getNbr_abs()).isEqualTo(UPDATED_NBRABS);
        assertThat(testDrabsen.getValid_abs()).isEqualTo(UPDATED_VALIDABS);
        assertThat(testDrabsen.getObserva_abs()).isEqualTo(UPDATED_OBSERVAABS);
        assertThat(testDrabsen.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testDrabsen.getCd2()).isEqualTo(UPDATED_CD_2);
        assertThat(testDrabsen.getCd3()).isEqualTo(UPDATED_CD_3);
    }

    @Test
    void patchNonExistingDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, drabsenDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDrabsen() throws Exception {
        int databaseSizeBeforeUpdate = drabsenRepository.findAll().size();
        drabsen.setId(UUID.randomUUID().toString());

        // Create the Drabsen
        DrabsenDTO drabsenDTO = drabsenMapper.toDto(drabsen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDrabsenMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(drabsenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Drabsen in the database
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDrabsen() throws Exception {
        // Initialize the database
        drabsenRepository.save(drabsen);

        int databaseSizeBeforeDelete = drabsenRepository.findAll().size();

        // Delete the drabsen
        restDrabsenMockMvc
            .perform(delete(ENTITY_API_URL_ID, drabsen.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Drabsen> drabsenList = drabsenRepository.findAll();
        assertThat(drabsenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
