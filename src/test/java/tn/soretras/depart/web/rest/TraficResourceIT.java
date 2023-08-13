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
import tn.soretras.depart.domain.Trafic;
import tn.soretras.depart.repository.TraficRepository;
import tn.soretras.depart.service.dto.TraficDTO;
import tn.soretras.depart.service.mapper.TraficMapper;

/**
 * Integration tests for the {@link TraficResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TraficResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final LocalDate DEFAULT_DEDATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEDATED = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ANCIEN = 1;
    private static final Integer UPDATED_ANCIEN = 2;

    private static final String DEFAULT_VLDTRAFIC = "AAAAAAAAAA";
    private static final String UPDATED_VLDTRAFIC = "BBBBBBBBBB";

    private static final String DEFAULT_CLOTRAFIC = "AAAAAAAAAA";
    private static final String UPDATED_CLOTRAFIC = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trafics";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private TraficRepository traficRepository;

    @Autowired
    private TraficMapper traficMapper;

    @Autowired
    private MockMvc restTraficMockMvc;

    private Trafic trafic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trafic createEntity() {
        Trafic trafic = new Trafic()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .dedated(DEFAULT_DEDATED)
            .ancien(DEFAULT_ANCIEN)
            .vldtrafic(DEFAULT_VLDTRAFIC)
            .clotrafic(DEFAULT_CLOTRAFIC);
        return trafic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trafic createUpdatedEntity() {
        Trafic trafic = new Trafic()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .ancien(UPDATED_ANCIEN)
            .vldtrafic(UPDATED_VLDTRAFIC)
            .clotrafic(UPDATED_CLOTRAFIC);
        return trafic;
    }

    @BeforeEach
    public void initTest() {
        traficRepository.deleteAll();
        trafic = createEntity();
    }

    @Test
    void createTrafic() throws Exception {
        int databaseSizeBeforeCreate = traficRepository.findAll().size();
        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);
        restTraficMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isCreated());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeCreate + 1);
        Trafic testTrafic = traficList.get(traficList.size() - 1);
        assertThat(testTrafic.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testTrafic.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testTrafic.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testTrafic.getAncien()).isEqualTo(DEFAULT_ANCIEN);
        assertThat(testTrafic.getVldtrafic()).isEqualTo(DEFAULT_VLDTRAFIC);
        assertThat(testTrafic.getClotrafic()).isEqualTo(DEFAULT_CLOTRAFIC);
    }

    @Test
    void createTraficWithExistingId() throws Exception {
        // Create the Trafic with an existing ID
        trafic.setId("existing_id");
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        int databaseSizeBeforeCreate = traficRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraficMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = traficRepository.findAll().size();
        // set the field null
        trafic.setDeccent(null);

        // Create the Trafic, which fails.
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        restTraficMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isBadRequest());

        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = traficRepository.findAll().size();
        // set the field null
        trafic.setDecagenc(null);

        // Create the Trafic, which fails.
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        restTraficMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isBadRequest());

        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDedatedIsRequired() throws Exception {
        int databaseSizeBeforeTest = traficRepository.findAll().size();
        // set the field null
        trafic.setDedated(null);

        // Create the Trafic, which fails.
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        restTraficMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isBadRequest());

        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTrafics() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        // Get all the traficList
        restTraficMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trafic.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].ancien").value(hasItem(DEFAULT_ANCIEN)))
            .andExpect(jsonPath("$.[*].vldtrafic").value(hasItem(DEFAULT_VLDTRAFIC)))
            .andExpect(jsonPath("$.[*].clotrafic").value(hasItem(DEFAULT_CLOTRAFIC)));
    }

    @Test
    void getTrafic() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        // Get the trafic
        restTraficMockMvc
            .perform(get(ENTITY_API_URL_ID, trafic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(trafic.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.ancien").value(DEFAULT_ANCIEN))
            .andExpect(jsonPath("$.vldtrafic").value(DEFAULT_VLDTRAFIC))
            .andExpect(jsonPath("$.clotrafic").value(DEFAULT_CLOTRAFIC));
    }

    @Test
    void getNonExistingTrafic() throws Exception {
        // Get the trafic
        restTraficMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingTrafic() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        int databaseSizeBeforeUpdate = traficRepository.findAll().size();

        // Update the trafic
        Trafic updatedTrafic = traficRepository.findById(trafic.getId()).get();
        updatedTrafic
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .ancien(UPDATED_ANCIEN)
            .vldtrafic(UPDATED_VLDTRAFIC)
            .clotrafic(UPDATED_CLOTRAFIC);
        TraficDTO traficDTO = traficMapper.toDto(updatedTrafic);

        restTraficMockMvc
            .perform(
                put(ENTITY_API_URL_ID, traficDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isOk());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
        Trafic testTrafic = traficList.get(traficList.size() - 1);
        assertThat(testTrafic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testTrafic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testTrafic.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testTrafic.getAncien()).isEqualTo(UPDATED_ANCIEN);
        assertThat(testTrafic.getVldtrafic()).isEqualTo(UPDATED_VLDTRAFIC);
        assertThat(testTrafic.getClotrafic()).isEqualTo(UPDATED_CLOTRAFIC);
    }

    @Test
    void putNonExistingTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(
                put(ENTITY_API_URL_ID, traficDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(traficDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTraficWithPatch() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        int databaseSizeBeforeUpdate = traficRepository.findAll().size();

        // Update the trafic using partial update
        Trafic partialUpdatedTrafic = new Trafic();
        partialUpdatedTrafic.setId(trafic.getId());

        partialUpdatedTrafic
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .ancien(UPDATED_ANCIEN)
            .vldtrafic(UPDATED_VLDTRAFIC)
            .clotrafic(UPDATED_CLOTRAFIC);

        restTraficMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrafic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrafic))
            )
            .andExpect(status().isOk());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
        Trafic testTrafic = traficList.get(traficList.size() - 1);
        assertThat(testTrafic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testTrafic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testTrafic.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testTrafic.getAncien()).isEqualTo(UPDATED_ANCIEN);
        assertThat(testTrafic.getVldtrafic()).isEqualTo(UPDATED_VLDTRAFIC);
        assertThat(testTrafic.getClotrafic()).isEqualTo(UPDATED_CLOTRAFIC);
    }

    @Test
    void fullUpdateTraficWithPatch() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        int databaseSizeBeforeUpdate = traficRepository.findAll().size();

        // Update the trafic using partial update
        Trafic partialUpdatedTrafic = new Trafic();
        partialUpdatedTrafic.setId(trafic.getId());

        partialUpdatedTrafic
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .ancien(UPDATED_ANCIEN)
            .vldtrafic(UPDATED_VLDTRAFIC)
            .clotrafic(UPDATED_CLOTRAFIC);

        restTraficMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTrafic.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTrafic))
            )
            .andExpect(status().isOk());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
        Trafic testTrafic = traficList.get(traficList.size() - 1);
        assertThat(testTrafic.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testTrafic.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testTrafic.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testTrafic.getAncien()).isEqualTo(UPDATED_ANCIEN);
        assertThat(testTrafic.getVldtrafic()).isEqualTo(UPDATED_VLDTRAFIC);
        assertThat(testTrafic.getClotrafic()).isEqualTo(UPDATED_CLOTRAFIC);
    }

    @Test
    void patchNonExistingTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, traficDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTrafic() throws Exception {
        int databaseSizeBeforeUpdate = traficRepository.findAll().size();
        trafic.setId(UUID.randomUUID().toString());

        // Create the Trafic
        TraficDTO traficDTO = traficMapper.toDto(trafic);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTraficMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(traficDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Trafic in the database
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTrafic() throws Exception {
        // Initialize the database
        traficRepository.save(trafic);

        int databaseSizeBeforeDelete = traficRepository.findAll().size();

        // Delete the trafic
        restTraficMockMvc
            .perform(delete(ENTITY_API_URL_ID, trafic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Trafic> traficList = traficRepository.findAll();
        assertThat(traficList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
