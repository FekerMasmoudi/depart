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
import tn.soretras.depart.domain.Station;
import tn.soretras.depart.repository.StationRepository;
import tn.soretras.depart.service.dto.StationDTO;
import tn.soretras.depart.service.mapper.StationMapper;

/**
 * Integration tests for the {@link StationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StationResourceIT {

    private static final String DEFAULT_DECSTAT = "AAAAAAAAAA";
    private static final String UPDATED_DECSTAT = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYST = "AAAAAAAAAA";
    private static final String UPDATED_DECTYST = "BBBBBBBBBB";

    private static final String DEFAULT_DECROUT = "AAAAAAAAAA";
    private static final String UPDATED_DECROUT = "BBBBBBBBBB";

    private static final String DEFAULT_DELSTAT = "AAAAAAAAAA";
    private static final String UPDATED_DELSTAT = "BBBBBBBBBB";

    private static final String DEFAULT_DELSTATFR = "AAAAAAAAAA";
    private static final String UPDATED_DELSTATFR = "BBBBBBBBBB";

    private static final String DEFAULT_LATTITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATTITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_VALIDE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/stations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private MockMvc restStationMockMvc;

    private Station station;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Station createEntity() {
        Station station = new Station()
            .decstat(DEFAULT_DECSTAT)
            .dectyst(DEFAULT_DECTYST)
            .decrout(DEFAULT_DECROUT)
            .delstat(DEFAULT_DELSTAT)
            .delstat_fr(DEFAULT_DELSTATFR)
            .lattitude(DEFAULT_LATTITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .valide(DEFAULT_VALIDE);
        return station;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Station createUpdatedEntity() {
        Station station = new Station()
            .decstat(UPDATED_DECSTAT)
            .dectyst(UPDATED_DECTYST)
            .decrout(UPDATED_DECROUT)
            .delstat(UPDATED_DELSTAT)
            .delstat_fr(UPDATED_DELSTATFR)
            .lattitude(UPDATED_LATTITUDE)
            .longitude(UPDATED_LONGITUDE)
            .valide(UPDATED_VALIDE);
        return station;
    }

    @BeforeEach
    public void initTest() {
        stationRepository.deleteAll();
        station = createEntity();
    }

    @Test
    void createStation() throws Exception {
        int databaseSizeBeforeCreate = stationRepository.findAll().size();
        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);
        restStationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stationDTO)))
            .andExpect(status().isCreated());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeCreate + 1);
        Station testStation = stationList.get(stationList.size() - 1);
        assertThat(testStation.getDecstat()).isEqualTo(DEFAULT_DECSTAT);
        assertThat(testStation.getDectyst()).isEqualTo(DEFAULT_DECTYST);
        assertThat(testStation.getDecrout()).isEqualTo(DEFAULT_DECROUT);
        assertThat(testStation.getDelstat()).isEqualTo(DEFAULT_DELSTAT);
        assertThat(testStation.getDelstat_fr()).isEqualTo(DEFAULT_DELSTATFR);
        assertThat(testStation.getLattitude()).isEqualTo(DEFAULT_LATTITUDE);
        assertThat(testStation.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testStation.getValide()).isEqualTo(DEFAULT_VALIDE);
    }

    @Test
    void createStationWithExistingId() throws Exception {
        // Create the Station with an existing ID
        station.setId("existing_id");
        StationDTO stationDTO = stationMapper.toDto(station);

        int databaseSizeBeforeCreate = stationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDecstatIsRequired() throws Exception {
        int databaseSizeBeforeTest = stationRepository.findAll().size();
        // set the field null
        station.setDecstat(null);

        // Create the Station, which fails.
        StationDTO stationDTO = stationMapper.toDto(station);

        restStationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stationDTO)))
            .andExpect(status().isBadRequest());

        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllStations() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        // Get all the stationList
        restStationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(station.getId())))
            .andExpect(jsonPath("$.[*].decstat").value(hasItem(DEFAULT_DECSTAT)))
            .andExpect(jsonPath("$.[*].dectyst").value(hasItem(DEFAULT_DECTYST)))
            .andExpect(jsonPath("$.[*].decrout").value(hasItem(DEFAULT_DECROUT)))
            .andExpect(jsonPath("$.[*].delstat").value(hasItem(DEFAULT_DELSTAT)))
            .andExpect(jsonPath("$.[*].delstatfr").value(hasItem(DEFAULT_DELSTATFR)))
            .andExpect(jsonPath("$.[*].lattitude").value(hasItem(DEFAULT_LATTITUDE)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE)))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE)));
    }

    @Test
    void getStation() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        // Get the station
        restStationMockMvc
            .perform(get(ENTITY_API_URL_ID, station.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(station.getId()))
            .andExpect(jsonPath("$.decstat").value(DEFAULT_DECSTAT))
            .andExpect(jsonPath("$.dectyst").value(DEFAULT_DECTYST))
            .andExpect(jsonPath("$.decrout").value(DEFAULT_DECROUT))
            .andExpect(jsonPath("$.delstat").value(DEFAULT_DELSTAT))
            .andExpect(jsonPath("$.delstatfr").value(DEFAULT_DELSTATFR))
            .andExpect(jsonPath("$.lattitude").value(DEFAULT_LATTITUDE))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE))
            .andExpect(jsonPath("$.valide").value(DEFAULT_VALIDE));
    }

    @Test
    void getNonExistingStation() throws Exception {
        // Get the station
        restStationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingStation() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        int databaseSizeBeforeUpdate = stationRepository.findAll().size();

        // Update the station
        Station updatedStation = stationRepository.findById(station.getId()).get();
        updatedStation
            .decstat(UPDATED_DECSTAT)
            .dectyst(UPDATED_DECTYST)
            .decrout(UPDATED_DECROUT)
            .delstat(UPDATED_DELSTAT)
            .delstat_fr(UPDATED_DELSTATFR)
            .lattitude(UPDATED_LATTITUDE)
            .longitude(UPDATED_LONGITUDE)
            .valide(UPDATED_VALIDE);
        StationDTO stationDTO = stationMapper.toDto(updatedStation);

        restStationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isOk());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
        Station testStation = stationList.get(stationList.size() - 1);
        assertThat(testStation.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testStation.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testStation.getDecrout()).isEqualTo(UPDATED_DECROUT);
        assertThat(testStation.getDelstat()).isEqualTo(UPDATED_DELSTAT);
        assertThat(testStation.getDelstat_fr()).isEqualTo(UPDATED_DELSTATFR);
        assertThat(testStation.getLattitude()).isEqualTo(UPDATED_LATTITUDE);
        assertThat(testStation.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testStation.getValide()).isEqualTo(UPDATED_VALIDE);
    }

    @Test
    void putNonExistingStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stationDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(stationDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateStationWithPatch() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        int databaseSizeBeforeUpdate = stationRepository.findAll().size();

        // Update the station using partial update
        Station partialUpdatedStation = new Station();
        partialUpdatedStation.setId(station.getId());

        partialUpdatedStation
            .decstat(UPDATED_DECSTAT)
            .dectyst(UPDATED_DECTYST)
            .decrout(UPDATED_DECROUT)
            .delstat(UPDATED_DELSTAT)
            .lattitude(UPDATED_LATTITUDE);

        restStationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStation))
            )
            .andExpect(status().isOk());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
        Station testStation = stationList.get(stationList.size() - 1);
        assertThat(testStation.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testStation.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testStation.getDecrout()).isEqualTo(UPDATED_DECROUT);
        assertThat(testStation.getDelstat()).isEqualTo(UPDATED_DELSTAT);
        assertThat(testStation.getDelstat_fr()).isEqualTo(DEFAULT_DELSTATFR);
        assertThat(testStation.getLattitude()).isEqualTo(UPDATED_LATTITUDE);
        assertThat(testStation.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testStation.getValide()).isEqualTo(DEFAULT_VALIDE);
    }

    @Test
    void fullUpdateStationWithPatch() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        int databaseSizeBeforeUpdate = stationRepository.findAll().size();

        // Update the station using partial update
        Station partialUpdatedStation = new Station();
        partialUpdatedStation.setId(station.getId());

        partialUpdatedStation
            .decstat(UPDATED_DECSTAT)
            .dectyst(UPDATED_DECTYST)
            .decrout(UPDATED_DECROUT)
            .delstat(UPDATED_DELSTAT)
            .delstat_fr(UPDATED_DELSTATFR)
            .lattitude(UPDATED_LATTITUDE)
            .longitude(UPDATED_LONGITUDE)
            .valide(UPDATED_VALIDE);

        restStationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStation.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedStation))
            )
            .andExpect(status().isOk());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
        Station testStation = stationList.get(stationList.size() - 1);
        assertThat(testStation.getDecstat()).isEqualTo(UPDATED_DECSTAT);
        assertThat(testStation.getDectyst()).isEqualTo(UPDATED_DECTYST);
        assertThat(testStation.getDecrout()).isEqualTo(UPDATED_DECROUT);
        assertThat(testStation.getDelstat()).isEqualTo(UPDATED_DELSTAT);
        assertThat(testStation.getDelstat_fr()).isEqualTo(UPDATED_DELSTATFR);
        assertThat(testStation.getLattitude()).isEqualTo(UPDATED_LATTITUDE);
        assertThat(testStation.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testStation.getValide()).isEqualTo(UPDATED_VALIDE);
    }

    @Test
    void patchNonExistingStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stationDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamStation() throws Exception {
        int databaseSizeBeforeUpdate = stationRepository.findAll().size();
        station.setId(UUID.randomUUID().toString());

        // Create the Station
        StationDTO stationDTO = stationMapper.toDto(station);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(stationDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Station in the database
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteStation() throws Exception {
        // Initialize the database
        stationRepository.save(station);

        int databaseSizeBeforeDelete = stationRepository.findAll().size();

        // Delete the station
        restStationMockMvc
            .perform(delete(ENTITY_API_URL_ID, station.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
