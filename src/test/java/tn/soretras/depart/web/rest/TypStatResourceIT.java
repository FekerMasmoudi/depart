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
import tn.soretras.depart.domain.TypStat;
import tn.soretras.depart.repository.TypStatRepository;
import tn.soretras.depart.service.dto.TypStatDTO;
import tn.soretras.depart.service.mapper.TypStatMapper;

/**
 * Integration tests for the {@link TypStatResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TypStatResourceIT {

    private static final String DEFAULT_DECTYST = "AAAAAAAAAA";
    private static final String UPDATED_DECTYST = "BBBBBBBBBB";

    private static final String DEFAULT_DELTYST = "AAAAAAAAAA";
    private static final String UPDATED_DELTYST = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/typ-stats";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private TypStatRepository typStatRepository;

    @Autowired
    private TypStatMapper typStatMapper;

    @Autowired
    private MockMvc restTypStatMockMvc;

    private TypStat typStat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypStat createEntity() {
        TypStat typStat = new TypStat().dectyst(DEFAULT_DECTYST).deltyst(DEFAULT_DELTYST);
        return typStat;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypStat createUpdatedEntity() {
        TypStat typStat = new TypStat().dectyst(UPDATED_DECTYST).deltyst(UPDATED_DELTYST);
        return typStat;
    }

    @BeforeEach
    public void initTest() {
        typStatRepository.deleteAll();
        typStat = createEntity();
    }

    @Test
    void createTypStat() throws Exception {
        int databaseSizeBeforeCreate = typStatRepository.findAll().size();
        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);
        restTypStatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typStatDTO)))
            .andExpect(status().isCreated());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeCreate + 1);
        TypStat testTypStat = typStatList.get(typStatList.size() - 1);
        assertThat(testTypStat.getDectyst()).isEqualTo(DEFAULT_DECTYST);
        assertThat(testTypStat.getDeltyst()).isEqualTo(DEFAULT_DELTYST);
    }

    @Test
    void createTypStatWithExistingId() throws Exception {
        // Create the TypStat with an existing ID
        typStat.setId("existing_id");
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        int databaseSizeBeforeCreate = typStatRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypStatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typStatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDectystIsRequired() throws Exception {
        int databaseSizeBeforeTest = typStatRepository.findAll().size();
        // set the field null
        typStat.setDectyst(null);

        // Create the TypStat, which fails.
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        restTypStatMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typStatDTO)))
            .andExpect(status().isBadRequest());

        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTypStats() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        // Get all the typStatList
        restTypStatMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typStat.getId())))
            .andExpect(jsonPath("$.[*].dectyst").value(hasItem(DEFAULT_DECTYST)))
            .andExpect(jsonPath("$.[*].deltyst").value(hasItem(DEFAULT_DELTYST)));
    }

    @Test
    void getTypStat() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        // Get the typStat
        restTypStatMockMvc
            .perform(get(ENTITY_API_URL_ID, typStat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typStat.getId()))
            .andExpect(jsonPath("$.dectyst").value(DEFAULT_DECTYST))
            .andExpect(jsonPath("$.deltyst").value(DEFAULT_DELTYST));
    }

    @Test
    void getNonExistingTypStat() throws Exception {
        // Get the typStat
        restTypStatMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingTypStat() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();

        // Update the typStat
        TypStat updatedTypStat = typStatRepository.findById(typStat.getId()).get();
        updatedTypStat.dectyst(UPDATED_DECTYST).deltyst(UPDATED_DELTYST);
        TypStatDTO typStatDTO = typStatMapper.toDto(updatedTypStat);

        restTypStatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, typStatDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isOk());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
        TypStat testTypStat = typStatList.get(typStatList.size() - 1);
        assertThat(testTypStat.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testTypStat.getDeltyst()).isEqualTo(UPDATED_DELTYST);
    }

    @Test
    void putNonExistingTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, typStatDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typStatDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTypStatWithPatch() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();

        // Update the typStat using partial update
        TypStat partialUpdatedTypStat = new TypStat();
        partialUpdatedTypStat.setId(typStat.getId());

        partialUpdatedTypStat.dectyst(UPDATED_DECTYST).deltyst(UPDATED_DELTYST);

        restTypStatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTypStat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypStat))
            )
            .andExpect(status().isOk());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
        TypStat testTypStat = typStatList.get(typStatList.size() - 1);
        assertThat(testTypStat.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testTypStat.getDeltyst()).isEqualTo(UPDATED_DELTYST);
    }

    @Test
    void fullUpdateTypStatWithPatch() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();

        // Update the typStat using partial update
        TypStat partialUpdatedTypStat = new TypStat();
        partialUpdatedTypStat.setId(typStat.getId());

        partialUpdatedTypStat.dectyst(UPDATED_DECTYST).deltyst(UPDATED_DELTYST);

        restTypStatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTypStat.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypStat))
            )
            .andExpect(status().isOk());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
        TypStat testTypStat = typStatList.get(typStatList.size() - 1);
        assertThat(testTypStat.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testTypStat.getDeltyst()).isEqualTo(UPDATED_DELTYST);
    }

    @Test
    void patchNonExistingTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, typStatDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTypStat() throws Exception {
        int databaseSizeBeforeUpdate = typStatRepository.findAll().size();
        typStat.setId(UUID.randomUUID().toString());

        // Create the TypStat
        TypStatDTO typStatDTO = typStatMapper.toDto(typStat);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTypStatMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(typStatDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TypStat in the database
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTypStat() throws Exception {
        // Initialize the database
        typStatRepository.save(typStat);

        int databaseSizeBeforeDelete = typStatRepository.findAll().size();

        // Delete the typStat
        restTypStatMockMvc
            .perform(delete(ENTITY_API_URL_ID, typStat.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypStat> typStatList = typStatRepository.findAll();
        assertThat(typStatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
