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
import tn.soretras.depart.domain.CentVehic;
import tn.soretras.depart.repository.CentVehicRepository;
import tn.soretras.depart.service.dto.CentVehicDTO;
import tn.soretras.depart.service.mapper.CentVehicMapper;

/**
 * Integration tests for the {@link CentVehicResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CentVehicResourceIT {

    private static final String DEFAULT_CDMAC = "AAAAAAAAAA";
    private static final String UPDATED_CDMAC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATEFF = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEFF = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String ENTITY_API_URL = "/api/cent-vehics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private CentVehicRepository centVehicRepository;

    @Autowired
    private CentVehicMapper centVehicMapper;

    @Autowired
    private MockMvc restCentVehicMockMvc;

    private CentVehic centVehic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentVehic createEntity() {
        CentVehic centVehic = new CentVehic()
            .cdmac(DEFAULT_CDMAC)
            .dateff(DEFAULT_DATEFF)
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC);
        return centVehic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CentVehic createUpdatedEntity() {
        CentVehic centVehic = new CentVehic()
            .cdmac(UPDATED_CDMAC)
            .dateff(UPDATED_DATEFF)
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC);
        return centVehic;
    }

    @BeforeEach
    public void initTest() {
        centVehicRepository.deleteAll();
        centVehic = createEntity();
    }

    @Test
    void createCentVehic() throws Exception {
        int databaseSizeBeforeCreate = centVehicRepository.findAll().size();
        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);
        restCentVehicMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centVehicDTO)))
            .andExpect(status().isCreated());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeCreate + 1);
        CentVehic testCentVehic = centVehicList.get(centVehicList.size() - 1);
        assertThat(testCentVehic.getCdmac()).isEqualTo(DEFAULT_CDMAC);
        assertThat(testCentVehic.getDateff()).isEqualTo(DEFAULT_DATEFF);
        assertThat(testCentVehic.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testCentVehic.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
    }

    @Test
    void createCentVehicWithExistingId() throws Exception {
        // Create the CentVehic with an existing ID
        centVehic.setId("existing_id");
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        int databaseSizeBeforeCreate = centVehicRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCentVehicMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centVehicDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCentVehics() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        // Get all the centVehicList
        restCentVehicMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(centVehic.getId())))
            .andExpect(jsonPath("$.[*].cdmac").value(hasItem(DEFAULT_CDMAC)))
            .andExpect(jsonPath("$.[*].dateff").value(hasItem(DEFAULT_DATEFF.toString())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)));
    }

    @Test
    void getCentVehic() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        // Get the centVehic
        restCentVehicMockMvc
            .perform(get(ENTITY_API_URL_ID, centVehic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(centVehic.getId()))
            .andExpect(jsonPath("$.cdmac").value(DEFAULT_CDMAC))
            .andExpect(jsonPath("$.dateff").value(DEFAULT_DATEFF.toString()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC));
    }

    @Test
    void getNonExistingCentVehic() throws Exception {
        // Get the centVehic
        restCentVehicMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingCentVehic() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();

        // Update the centVehic
        CentVehic updatedCentVehic = centVehicRepository.findById(centVehic.getId()).get();
        updatedCentVehic.cdmac(UPDATED_CDMAC).dateff(UPDATED_DATEFF).deccent(UPDATED_DECCENT).decagenc(UPDATED_DECAGENC);
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(updatedCentVehic);

        restCentVehicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, centVehicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isOk());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
        CentVehic testCentVehic = centVehicList.get(centVehicList.size() - 1);
        assertThat(testCentVehic.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testCentVehic.getDateff()).isEqualTo(UPDATED_DATEFF);
        assertThat(testCentVehic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCentVehic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
    }

    @Test
    void putNonExistingCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, centVehicDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(centVehicDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCentVehicWithPatch() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();

        // Update the centVehic using partial update
        CentVehic partialUpdatedCentVehic = new CentVehic();
        partialUpdatedCentVehic.setId(centVehic.getId());

        partialUpdatedCentVehic.cdmac(UPDATED_CDMAC).deccent(UPDATED_DECCENT).decagenc(UPDATED_DECAGENC);

        restCentVehicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCentVehic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCentVehic))
            )
            .andExpect(status().isOk());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
        CentVehic testCentVehic = centVehicList.get(centVehicList.size() - 1);
        assertThat(testCentVehic.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testCentVehic.getDateff()).isEqualTo(DEFAULT_DATEFF);
        assertThat(testCentVehic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCentVehic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
    }

    @Test
    void fullUpdateCentVehicWithPatch() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();

        // Update the centVehic using partial update
        CentVehic partialUpdatedCentVehic = new CentVehic();
        partialUpdatedCentVehic.setId(centVehic.getId());

        partialUpdatedCentVehic.cdmac(UPDATED_CDMAC).dateff(UPDATED_DATEFF).deccent(UPDATED_DECCENT).decagenc(UPDATED_DECAGENC);

        restCentVehicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCentVehic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCentVehic))
            )
            .andExpect(status().isOk());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
        CentVehic testCentVehic = centVehicList.get(centVehicList.size() - 1);
        assertThat(testCentVehic.getCdmac()).isEqualTo(UPDATED_CDMAC);
        assertThat(testCentVehic.getDateff()).isEqualTo(UPDATED_DATEFF);
        assertThat(testCentVehic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testCentVehic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
    }

    @Test
    void patchNonExistingCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, centVehicDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCentVehic() throws Exception {
        int databaseSizeBeforeUpdate = centVehicRepository.findAll().size();
        centVehic.setId(UUID.randomUUID().toString());

        // Create the CentVehic
        CentVehicDTO centVehicDTO = centVehicMapper.toDto(centVehic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCentVehicMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(centVehicDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CentVehic in the database
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCentVehic() throws Exception {
        // Initialize the database
        centVehicRepository.save(centVehic);

        int databaseSizeBeforeDelete = centVehicRepository.findAll().size();

        // Delete the centVehic
        restCentVehicMockMvc
            .perform(delete(ENTITY_API_URL_ID, centVehic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CentVehic> centVehicList = centVehicRepository.findAll();
        assertThat(centVehicList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
