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
import tn.soretras.depart.domain.Motifchserv;
import tn.soretras.depart.repository.MotifchservRepository;
import tn.soretras.depart.service.dto.MotifchservDTO;
import tn.soretras.depart.service.mapper.MotifchservMapper;

/**
 * Integration tests for the {@link MotifchservResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MotifchservResourceIT {

    private static final Integer DEFAULT_DECMOTIF = 1;
    private static final Integer UPDATED_DECMOTIF = 2;

    private static final String DEFAULT_DELMOTIF = "AAAAAAAAAA";
    private static final String UPDATED_DELMOTIF = "BBBBBBBBBB";

    private static final String DEFAULT_X = "AAAAAAAAAA";
    private static final String UPDATED_X = "BBBBBBBBBB";

    private static final String DEFAULT_VS = "AAAAAAAAAA";
    private static final String UPDATED_VS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/motifchservs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private MotifchservRepository motifchservRepository;

    @Autowired
    private MotifchservMapper motifchservMapper;

    @Autowired
    private MockMvc restMotifchservMockMvc;

    private Motifchserv motifchserv;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motifchserv createEntity() {
        Motifchserv motifchserv = new Motifchserv().decmotif(DEFAULT_DECMOTIF).delmotif(DEFAULT_DELMOTIF).x(DEFAULT_X).vs(DEFAULT_VS);
        return motifchserv;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motifchserv createUpdatedEntity() {
        Motifchserv motifchserv = new Motifchserv().decmotif(UPDATED_DECMOTIF).delmotif(UPDATED_DELMOTIF).x(UPDATED_X).vs(UPDATED_VS);
        return motifchserv;
    }

    @BeforeEach
    public void initTest() {
        motifchservRepository.deleteAll();
        motifchserv = createEntity();
    }

    @Test
    void createMotifchserv() throws Exception {
        int databaseSizeBeforeCreate = motifchservRepository.findAll().size();
        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);
        restMotifchservMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeCreate + 1);
        Motifchserv testMotifchserv = motifchservList.get(motifchservList.size() - 1);
        assertThat(testMotifchserv.getDecmotif()).isEqualTo(DEFAULT_DECMOTIF);
        assertThat(testMotifchserv.getDelmotif()).isEqualTo(DEFAULT_DELMOTIF);
        assertThat(testMotifchserv.getX()).isEqualTo(DEFAULT_X);
        assertThat(testMotifchserv.getVs()).isEqualTo(DEFAULT_VS);
    }

    @Test
    void createMotifchservWithExistingId() throws Exception {
        // Create the Motifchserv with an existing ID
        motifchserv.setId("existing_id");
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        int databaseSizeBeforeCreate = motifchservRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotifchservMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDecmotifIsRequired() throws Exception {
        int databaseSizeBeforeTest = motifchservRepository.findAll().size();
        // set the field null
        motifchserv.setDecmotif(null);

        // Create the Motifchserv, which fails.
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        restMotifchservMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllMotifchservs() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        // Get all the motifchservList
        restMotifchservMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motifchserv.getId())))
            .andExpect(jsonPath("$.[*].decmotif").value(hasItem(DEFAULT_DECMOTIF)))
            .andExpect(jsonPath("$.[*].delmotif").value(hasItem(DEFAULT_DELMOTIF)))
            .andExpect(jsonPath("$.[*].x").value(hasItem(DEFAULT_X)))
            .andExpect(jsonPath("$.[*].vs").value(hasItem(DEFAULT_VS)));
    }

    @Test
    void getMotifchserv() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        // Get the motifchserv
        restMotifchservMockMvc
            .perform(get(ENTITY_API_URL_ID, motifchserv.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(motifchserv.getId()))
            .andExpect(jsonPath("$.decmotif").value(DEFAULT_DECMOTIF))
            .andExpect(jsonPath("$.delmotif").value(DEFAULT_DELMOTIF))
            .andExpect(jsonPath("$.x").value(DEFAULT_X))
            .andExpect(jsonPath("$.vs").value(DEFAULT_VS));
    }

    @Test
    void getNonExistingMotifchserv() throws Exception {
        // Get the motifchserv
        restMotifchservMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingMotifchserv() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();

        // Update the motifchserv
        Motifchserv updatedMotifchserv = motifchservRepository.findById(motifchserv.getId()).get();
        updatedMotifchserv.decmotif(UPDATED_DECMOTIF).delmotif(UPDATED_DELMOTIF).x(UPDATED_X).vs(UPDATED_VS);
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(updatedMotifchserv);

        restMotifchservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, motifchservDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isOk());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
        Motifchserv testMotifchserv = motifchservList.get(motifchservList.size() - 1);
        assertThat(testMotifchserv.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testMotifchserv.getDelmotif()).isEqualTo(UPDATED_DELMOTIF);
        assertThat(testMotifchserv.getX()).isEqualTo(UPDATED_X);
        assertThat(testMotifchserv.getVs()).isEqualTo(UPDATED_VS);
    }

    @Test
    void putNonExistingMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, motifchservDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(motifchservDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateMotifchservWithPatch() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();

        // Update the motifchserv using partial update
        Motifchserv partialUpdatedMotifchserv = new Motifchserv();
        partialUpdatedMotifchserv.setId(motifchserv.getId());

        partialUpdatedMotifchserv.vs(UPDATED_VS);

        restMotifchservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMotifchserv.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMotifchserv))
            )
            .andExpect(status().isOk());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
        Motifchserv testMotifchserv = motifchservList.get(motifchservList.size() - 1);
        assertThat(testMotifchserv.getDecmotif()).isEqualTo(DEFAULT_DECMOTIF);
        assertThat(testMotifchserv.getDelmotif()).isEqualTo(DEFAULT_DELMOTIF);
        assertThat(testMotifchserv.getX()).isEqualTo(DEFAULT_X);
        assertThat(testMotifchserv.getVs()).isEqualTo(UPDATED_VS);
    }

    @Test
    void fullUpdateMotifchservWithPatch() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();

        // Update the motifchserv using partial update
        Motifchserv partialUpdatedMotifchserv = new Motifchserv();
        partialUpdatedMotifchserv.setId(motifchserv.getId());

        partialUpdatedMotifchserv.decmotif(UPDATED_DECMOTIF).delmotif(UPDATED_DELMOTIF).x(UPDATED_X).vs(UPDATED_VS);

        restMotifchservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMotifchserv.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMotifchserv))
            )
            .andExpect(status().isOk());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
        Motifchserv testMotifchserv = motifchservList.get(motifchservList.size() - 1);
        assertThat(testMotifchserv.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testMotifchserv.getDelmotif()).isEqualTo(UPDATED_DELMOTIF);
        assertThat(testMotifchserv.getX()).isEqualTo(UPDATED_X);
        assertThat(testMotifchserv.getVs()).isEqualTo(UPDATED_VS);
    }

    @Test
    void patchNonExistingMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, motifchservDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamMotifchserv() throws Exception {
        int databaseSizeBeforeUpdate = motifchservRepository.findAll().size();
        motifchserv.setId(UUID.randomUUID().toString());

        // Create the Motifchserv
        MotifchservDTO motifchservDTO = motifchservMapper.toDto(motifchserv);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMotifchservMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(motifchservDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Motifchserv in the database
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteMotifchserv() throws Exception {
        // Initialize the database
        motifchservRepository.save(motifchserv);

        int databaseSizeBeforeDelete = motifchservRepository.findAll().size();

        // Delete the motifchserv
        restMotifchservMockMvc
            .perform(delete(ENTITY_API_URL_ID, motifchserv.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Motifchserv> motifchservList = motifchservRepository.findAll();
        assertThat(motifchservList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
