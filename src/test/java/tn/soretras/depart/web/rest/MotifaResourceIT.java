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
import tn.soretras.depart.domain.Motifa;
import tn.soretras.depart.repository.MotifaRepository;
import tn.soretras.depart.service.dto.MotifaDTO;
import tn.soretras.depart.service.mapper.MotifaMapper;

/**
 * Integration tests for the {@link MotifaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MotifaResourceIT {

    private static final Integer DEFAULT_DECMOTIF = 1;
    private static final Integer UPDATED_DECMOTIF = 2;

    private static final String DEFAULT_LIBMOTIF = "AAAAAAAAAA";
    private static final String UPDATED_LIBMOTIF = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/motifas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MotifaRepository motifaRepository;

    @Autowired
    private MotifaMapper motifaMapper;

    @Autowired
    private MockMvc restMotifaMockMvc;

    private Motifa motifa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motifa createEntity() {
        Motifa motifa = new Motifa().decmotif(DEFAULT_DECMOTIF).libmotif(DEFAULT_LIBMOTIF);
        return motifa;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motifa createUpdatedEntity() {
        Motifa motifa = new Motifa().decmotif(UPDATED_DECMOTIF).libmotif(UPDATED_LIBMOTIF);
        return motifa;
    }

    @BeforeEach
    public void initTest() {
        motifaRepository.deleteAll();
        motifa = createEntity();
    }

    @Test
    void createMotifa() throws Exception {
        int databaseSizeBeforeCreate = motifaRepository.findAll().size();
        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);
        restMotifaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifaDTO)))
            .andExpect(status().isCreated());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeCreate + 1);
        Motifa testMotifa = motifaList.get(motifaList.size() - 1);
        assertThat(testMotifa.getDecmotif()).isEqualTo(DEFAULT_DECMOTIF);
        assertThat(testMotifa.getLibmotif()).isEqualTo(DEFAULT_LIBMOTIF);
    }

    @Test
    void createMotifaWithExistingId() throws Exception {
        // Create the Motifa with an existing ID
        motifa.setId("existing_id");
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        int databaseSizeBeforeCreate = motifaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotifaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDecmotifIsRequired() throws Exception {
        int databaseSizeBeforeTest = motifaRepository.findAll().size();
        // set the field null
        motifa.setDecmotif(null);

        // Create the Motifa, which fails.
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        restMotifaMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifaDTO)))
            .andExpect(status().isBadRequest());

        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMotifas() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        // Get all the motifaList
        restMotifaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motifa.getId())))
            .andExpect(jsonPath("$.[*].decmotif").value(hasItem(DEFAULT_DECMOTIF)))
            .andExpect(jsonPath("$.[*].libmotif").value(hasItem(DEFAULT_LIBMOTIF)));
    }

    @Test
    void getMotifa() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        // Get the motifa
        restMotifaMockMvc
            .perform(get(ENTITY_API_URL_ID, motifa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(motifa.getId()))
            .andExpect(jsonPath("$.decmotif").value(DEFAULT_DECMOTIF))
            .andExpect(jsonPath("$.libmotif").value(DEFAULT_LIBMOTIF));
    }

    @Test
    void getNonExistingMotifa() throws Exception {
        // Get the motifa
        restMotifaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingMotifa() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();

        // Update the motifa
        Motifa updatedMotifa = motifaRepository.findById(motifa.getId()).get();
        updatedMotifa.decmotif(UPDATED_DECMOTIF).libmotif(UPDATED_LIBMOTIF);
        MotifaDTO motifaDTO = motifaMapper.toDto(updatedMotifa);

        restMotifaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, motifaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isOk());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
        Motifa testMotifa = motifaList.get(motifaList.size() - 1);
        assertThat(testMotifa.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testMotifa.getLibmotif()).isEqualTo(UPDATED_LIBMOTIF);
    }

    @Test
    void putNonExistingMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, motifaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifaDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMotifaWithPatch() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();

        // Update the motifa using partial update
        Motifa partialUpdatedMotifa = new Motifa();
        partialUpdatedMotifa.setId(motifa.getId());

        restMotifaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMotifa.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMotifa))
            )
            .andExpect(status().isOk());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
        Motifa testMotifa = motifaList.get(motifaList.size() - 1);
        assertThat(testMotifa.getDecmotif()).isEqualTo(DEFAULT_DECMOTIF);
        assertThat(testMotifa.getLibmotif()).isEqualTo(DEFAULT_LIBMOTIF);
    }

    @Test
    void fullUpdateMotifaWithPatch() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();

        // Update the motifa using partial update
        Motifa partialUpdatedMotifa = new Motifa();
        partialUpdatedMotifa.setId(motifa.getId());

        partialUpdatedMotifa.decmotif(UPDATED_DECMOTIF).libmotif(UPDATED_LIBMOTIF);

        restMotifaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMotifa.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMotifa))
            )
            .andExpect(status().isOk());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
        Motifa testMotifa = motifaList.get(motifaList.size() - 1);
        assertThat(testMotifa.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testMotifa.getLibmotif()).isEqualTo(UPDATED_LIBMOTIF);
    }

    @Test
    void patchNonExistingMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, motifaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMotifa() throws Exception {
        int databaseSizeBeforeUpdate = motifaRepository.findAll().size();
        motifa.setId(UUID.randomUUID().toString());

        // Create the Motifa
        MotifaDTO motifaDTO = motifaMapper.toDto(motifa);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifaMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(motifaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Motifa in the database
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMotifa() throws Exception {
        // Initialize the database
        motifaRepository.save(motifa);

        int databaseSizeBeforeDelete = motifaRepository.findAll().size();

        // Delete the motifa
        restMotifaMockMvc
            .perform(delete(ENTITY_API_URL_ID, motifa.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Motifa> motifaList = motifaRepository.findAll();
        assertThat(motifaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
