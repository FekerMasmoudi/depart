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
import tn.soretras.depart.domain.Displaybus;
import tn.soretras.depart.repository.DisplaybusRepository;
import tn.soretras.depart.service.dto.DisplaybusDTO;
import tn.soretras.depart.service.mapper.DisplaybusMapper;

/**
 * Integration tests for the {@link DisplaybusResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DisplaybusResourceIT {

    private static final String DEFAULT_LANG = "AAAAAAAAAA";
    private static final String UPDATED_LANG = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICULE = "AAAAAAAAAA";
    private static final String UPDATED_VEHICULE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_APPEL = 1;
    private static final Integer UPDATED_NUM_APPEL = 2;

    private static final String DEFAULT_DETAIL_LIGNE = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL_LIGNE = "BBBBBBBBBB";

    private static final String DEFAULT_LIGNE = "AAAAAAAAAA";
    private static final String UPDATED_LIGNE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECTION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECTION = "BBBBBBBBBB";

    private static final String DEFAULT_DENUMLI = "AAAAAAAAAA";
    private static final String UPDATED_DENUMLI = "BBBBBBBBBB";

    private static final String DEFAULT_DELTYLI = "AAAAAAAAAA";
    private static final String UPDATED_DELTYLI = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/displaybuses";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DisplaybusRepository displaybusRepository;

    @Autowired
    private DisplaybusMapper displaybusMapper;

    @Autowired
    private MockMvc restDisplaybusMockMvc;

    private Displaybus displaybus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Displaybus createEntity() {
        Displaybus displaybus = new Displaybus()
            .lang(DEFAULT_LANG)
            .vehicule(DEFAULT_VEHICULE)
            .num_appel(DEFAULT_NUM_APPEL)
            .detail_ligne(DEFAULT_DETAIL_LIGNE)
            .ligne(DEFAULT_LIGNE)
            .direction(DEFAULT_DIRECTION)
            .denumli(DEFAULT_DENUMLI)
            .deltyli(DEFAULT_DELTYLI);
        return displaybus;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Displaybus createUpdatedEntity() {
        Displaybus displaybus = new Displaybus()
            .lang(UPDATED_LANG)
            .vehicule(UPDATED_VEHICULE)
            .num_appel(UPDATED_NUM_APPEL)
            .detail_ligne(UPDATED_DETAIL_LIGNE)
            .ligne(UPDATED_LIGNE)
            .direction(UPDATED_DIRECTION)
            .denumli(UPDATED_DENUMLI)
            .deltyli(UPDATED_DELTYLI);
        return displaybus;
    }

    @BeforeEach
    public void initTest() {
        displaybusRepository.deleteAll();
        displaybus = createEntity();
    }

    @Test
    void createDisplaybus() throws Exception {
        int databaseSizeBeforeCreate = displaybusRepository.findAll().size();
        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);
        restDisplaybusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(displaybusDTO)))
            .andExpect(status().isCreated());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeCreate + 1);
        Displaybus testDisplaybus = displaybusList.get(displaybusList.size() - 1);
        assertThat(testDisplaybus.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testDisplaybus.getVehicule()).isEqualTo(DEFAULT_VEHICULE);
        assertThat(testDisplaybus.getNum_appel()).isEqualTo(DEFAULT_NUM_APPEL);
        assertThat(testDisplaybus.getDetail_ligne()).isEqualTo(DEFAULT_DETAIL_LIGNE);
        assertThat(testDisplaybus.getLigne()).isEqualTo(DEFAULT_LIGNE);
        assertThat(testDisplaybus.getDirection()).isEqualTo(DEFAULT_DIRECTION);
        assertThat(testDisplaybus.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testDisplaybus.getDeltyli()).isEqualTo(DEFAULT_DELTYLI);
    }

    @Test
    void createDisplaybusWithExistingId() throws Exception {
        // Create the Displaybus with an existing ID
        displaybus.setId("existing_id");
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        int databaseSizeBeforeCreate = displaybusRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisplaybusMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(displaybusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllDisplaybuses() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        // Get all the displaybusList
        restDisplaybusMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(displaybus.getId())))
            .andExpect(jsonPath("$.[*].lang").value(hasItem(DEFAULT_LANG)))
            .andExpect(jsonPath("$.[*].vehicule").value(hasItem(DEFAULT_VEHICULE)))
            .andExpect(jsonPath("$.[*].num_appel").value(hasItem(DEFAULT_NUM_APPEL)))
            .andExpect(jsonPath("$.[*].detail_ligne").value(hasItem(DEFAULT_DETAIL_LIGNE)))
            .andExpect(jsonPath("$.[*].ligne").value(hasItem(DEFAULT_LIGNE)))
            .andExpect(jsonPath("$.[*].direction").value(hasItem(DEFAULT_DIRECTION)))
            .andExpect(jsonPath("$.[*].denumli").value(hasItem(DEFAULT_DENUMLI)))
            .andExpect(jsonPath("$.[*].deltyli").value(hasItem(DEFAULT_DELTYLI)));
    }

    @Test
    void getDisplaybus() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        // Get the displaybus
        restDisplaybusMockMvc
            .perform(get(ENTITY_API_URL_ID, displaybus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(displaybus.getId()))
            .andExpect(jsonPath("$.lang").value(DEFAULT_LANG))
            .andExpect(jsonPath("$.vehicule").value(DEFAULT_VEHICULE))
            .andExpect(jsonPath("$.num_appel").value(DEFAULT_NUM_APPEL))
            .andExpect(jsonPath("$.detail_ligne").value(DEFAULT_DETAIL_LIGNE))
            .andExpect(jsonPath("$.ligne").value(DEFAULT_LIGNE))
            .andExpect(jsonPath("$.direction").value(DEFAULT_DIRECTION))
            .andExpect(jsonPath("$.denumli").value(DEFAULT_DENUMLI))
            .andExpect(jsonPath("$.deltyli").value(DEFAULT_DELTYLI));
    }

    @Test
    void getNonExistingDisplaybus() throws Exception {
        // Get the displaybus
        restDisplaybusMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingDisplaybus() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();

        // Update the displaybus
        Displaybus updatedDisplaybus = displaybusRepository.findById(displaybus.getId()).get();
        updatedDisplaybus
            .lang(UPDATED_LANG)
            .vehicule(UPDATED_VEHICULE)
            .num_appel(UPDATED_NUM_APPEL)
            .detail_ligne(UPDATED_DETAIL_LIGNE)
            .ligne(UPDATED_LIGNE)
            .direction(UPDATED_DIRECTION)
            .denumli(UPDATED_DENUMLI)
            .deltyli(UPDATED_DELTYLI);
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(updatedDisplaybus);

        restDisplaybusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, displaybusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isOk());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
        Displaybus testDisplaybus = displaybusList.get(displaybusList.size() - 1);
        assertThat(testDisplaybus.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testDisplaybus.getVehicule()).isEqualTo(UPDATED_VEHICULE);
        assertThat(testDisplaybus.getNum_appel()).isEqualTo(UPDATED_NUM_APPEL);
        assertThat(testDisplaybus.getDetail_ligne()).isEqualTo(UPDATED_DETAIL_LIGNE);
        assertThat(testDisplaybus.getLigne()).isEqualTo(UPDATED_LIGNE);
        assertThat(testDisplaybus.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testDisplaybus.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testDisplaybus.getDeltyli()).isEqualTo(UPDATED_DELTYLI);
    }

    @Test
    void putNonExistingDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, displaybusDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(displaybusDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDisplaybusWithPatch() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();

        // Update the displaybus using partial update
        Displaybus partialUpdatedDisplaybus = new Displaybus();
        partialUpdatedDisplaybus.setId(displaybus.getId());

        partialUpdatedDisplaybus.vehicule(UPDATED_VEHICULE).num_appel(UPDATED_NUM_APPEL).deltyli(UPDATED_DELTYLI);

        restDisplaybusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisplaybus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDisplaybus))
            )
            .andExpect(status().isOk());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
        Displaybus testDisplaybus = displaybusList.get(displaybusList.size() - 1);
        assertThat(testDisplaybus.getLang()).isEqualTo(DEFAULT_LANG);
        assertThat(testDisplaybus.getVehicule()).isEqualTo(UPDATED_VEHICULE);
        assertThat(testDisplaybus.getNum_appel()).isEqualTo(UPDATED_NUM_APPEL);
        assertThat(testDisplaybus.getDetail_ligne()).isEqualTo(DEFAULT_DETAIL_LIGNE);
        assertThat(testDisplaybus.getLigne()).isEqualTo(DEFAULT_LIGNE);
        assertThat(testDisplaybus.getDirection()).isEqualTo(DEFAULT_DIRECTION);
        assertThat(testDisplaybus.getDenumli()).isEqualTo(DEFAULT_DENUMLI);
        assertThat(testDisplaybus.getDeltyli()).isEqualTo(UPDATED_DELTYLI);
    }

    @Test
    void fullUpdateDisplaybusWithPatch() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();

        // Update the displaybus using partial update
        Displaybus partialUpdatedDisplaybus = new Displaybus();
        partialUpdatedDisplaybus.setId(displaybus.getId());

        partialUpdatedDisplaybus
            .lang(UPDATED_LANG)
            .vehicule(UPDATED_VEHICULE)
            .num_appel(UPDATED_NUM_APPEL)
            .detail_ligne(UPDATED_DETAIL_LIGNE)
            .ligne(UPDATED_LIGNE)
            .direction(UPDATED_DIRECTION)
            .denumli(UPDATED_DENUMLI)
            .deltyli(UPDATED_DELTYLI);

        restDisplaybusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDisplaybus.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDisplaybus))
            )
            .andExpect(status().isOk());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
        Displaybus testDisplaybus = displaybusList.get(displaybusList.size() - 1);
        assertThat(testDisplaybus.getLang()).isEqualTo(UPDATED_LANG);
        assertThat(testDisplaybus.getVehicule()).isEqualTo(UPDATED_VEHICULE);
        assertThat(testDisplaybus.getNum_appel()).isEqualTo(UPDATED_NUM_APPEL);
        assertThat(testDisplaybus.getDetail_ligne()).isEqualTo(UPDATED_DETAIL_LIGNE);
        assertThat(testDisplaybus.getLigne()).isEqualTo(UPDATED_LIGNE);
        assertThat(testDisplaybus.getDirection()).isEqualTo(UPDATED_DIRECTION);
        assertThat(testDisplaybus.getDenumli()).isEqualTo(UPDATED_DENUMLI);
        assertThat(testDisplaybus.getDeltyli()).isEqualTo(UPDATED_DELTYLI);
    }

    @Test
    void patchNonExistingDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, displaybusDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDisplaybus() throws Exception {
        int databaseSizeBeforeUpdate = displaybusRepository.findAll().size();
        displaybus.setId(UUID.randomUUID().toString());

        // Create the Displaybus
        DisplaybusDTO displaybusDTO = displaybusMapper.toDto(displaybus);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDisplaybusMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(displaybusDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Displaybus in the database
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDisplaybus() throws Exception {
        // Initialize the database
        displaybusRepository.save(displaybus);

        int databaseSizeBeforeDelete = displaybusRepository.findAll().size();

        // Delete the displaybus
        restDisplaybusMockMvc
            .perform(delete(ENTITY_API_URL_ID, displaybus.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Displaybus> displaybusList = displaybusRepository.findAll();
        assertThat(displaybusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
