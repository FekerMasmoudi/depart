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
import tn.soretras.depart.domain.Route;
import tn.soretras.depart.repository.RouteRepository;
import tn.soretras.depart.service.dto.RouteDTO;
import tn.soretras.depart.service.mapper.RouteMapper;

/**
 * Integration tests for the {@link RouteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RouteResourceIT {

    private static final String DEFAULT_AGENCY_ID = "AAAAAAAAAA";
    private static final String UPDATED_AGENCY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_SHORT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_SHORT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_LONG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_LONG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ROUTE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ROUTE_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/routes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private MockMvc restRouteMockMvc;

    private Route route;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Route createEntity() {
        Route route = new Route()
            .agency_id(DEFAULT_AGENCY_ID)
            .route_short_name(DEFAULT_ROUTE_SHORT_NAME)
            .route_long_name(DEFAULT_ROUTE_LONG_NAME)
            .route_desc(DEFAULT_ROUTE_DESC)
            .route_type(DEFAULT_ROUTE_TYPE);
        return route;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Route createUpdatedEntity() {
        Route route = new Route()
            .agency_id(UPDATED_AGENCY_ID)
            .route_short_name(UPDATED_ROUTE_SHORT_NAME)
            .route_long_name(UPDATED_ROUTE_LONG_NAME)
            .route_desc(UPDATED_ROUTE_DESC)
            .route_type(UPDATED_ROUTE_TYPE);
        return route;
    }

    @BeforeEach
    public void initTest() {
        routeRepository.deleteAll();
        route = createEntity();
    }

    @Test
    void createRoute() throws Exception {
        int databaseSizeBeforeCreate = routeRepository.findAll().size();
        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);
        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isCreated());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeCreate + 1);
        Route testRoute = routeList.get(routeList.size() - 1);
        assertThat(testRoute.getAgency_id()).isEqualTo(DEFAULT_AGENCY_ID);
        assertThat(testRoute.getRoute_short_name()).isEqualTo(DEFAULT_ROUTE_SHORT_NAME);
        assertThat(testRoute.getRoute_long_name()).isEqualTo(DEFAULT_ROUTE_LONG_NAME);
        assertThat(testRoute.getRoute_desc()).isEqualTo(DEFAULT_ROUTE_DESC);
        assertThat(testRoute.getRoute_type()).isEqualTo(DEFAULT_ROUTE_TYPE);
    }

    @Test
    void createRouteWithExistingId() throws Exception {
        // Create the Route with an existing ID
        route.setId("existing_id");
        RouteDTO routeDTO = routeMapper.toDto(route);

        int databaseSizeBeforeCreate = routeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkAgency_idIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeRepository.findAll().size();
        // set the field null
        route.setAgency_id(null);

        // Create the Route, which fails.
        RouteDTO routeDTO = routeMapper.toDto(route);

        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRoute_short_nameIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeRepository.findAll().size();
        // set the field null
        route.setRoute_short_name(null);

        // Create the Route, which fails.
        RouteDTO routeDTO = routeMapper.toDto(route);

        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRoute_long_nameIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeRepository.findAll().size();
        // set the field null
        route.setRoute_long_name(null);

        // Create the Route, which fails.
        RouteDTO routeDTO = routeMapper.toDto(route);

        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRoute_descIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeRepository.findAll().size();
        // set the field null
        route.setRoute_desc(null);

        // Create the Route, which fails.
        RouteDTO routeDTO = routeMapper.toDto(route);

        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRoute_typeIsRequired() throws Exception {
        int databaseSizeBeforeTest = routeRepository.findAll().size();
        // set the field null
        route.setRoute_type(null);

        // Create the Route, which fails.
        RouteDTO routeDTO = routeMapper.toDto(route);

        restRouteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isBadRequest());

        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRoutes() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        // Get all the routeList
        restRouteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(route.getId())))
            .andExpect(jsonPath("$.[*].agency_id").value(hasItem(DEFAULT_AGENCY_ID)))
            .andExpect(jsonPath("$.[*].route_short_name").value(hasItem(DEFAULT_ROUTE_SHORT_NAME)))
            .andExpect(jsonPath("$.[*].route_long_name").value(hasItem(DEFAULT_ROUTE_LONG_NAME)))
            .andExpect(jsonPath("$.[*].route_desc").value(hasItem(DEFAULT_ROUTE_DESC)))
            .andExpect(jsonPath("$.[*].route_type").value(hasItem(DEFAULT_ROUTE_TYPE)));
    }

    @Test
    void getRoute() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        // Get the route
        restRouteMockMvc
            .perform(get(ENTITY_API_URL_ID, route.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(route.getId()))
            .andExpect(jsonPath("$.agency_id").value(DEFAULT_AGENCY_ID))
            .andExpect(jsonPath("$.route_short_name").value(DEFAULT_ROUTE_SHORT_NAME))
            .andExpect(jsonPath("$.route_long_name").value(DEFAULT_ROUTE_LONG_NAME))
            .andExpect(jsonPath("$.route_desc").value(DEFAULT_ROUTE_DESC))
            .andExpect(jsonPath("$.route_type").value(DEFAULT_ROUTE_TYPE));
    }

    @Test
    void getNonExistingRoute() throws Exception {
        // Get the route
        restRouteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingRoute() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        int databaseSizeBeforeUpdate = routeRepository.findAll().size();

        // Update the route
        Route updatedRoute = routeRepository.findById(route.getId()).get();
        updatedRoute
            .agency_id(UPDATED_AGENCY_ID)
            .route_short_name(UPDATED_ROUTE_SHORT_NAME)
            .route_long_name(UPDATED_ROUTE_LONG_NAME)
            .route_desc(UPDATED_ROUTE_DESC)
            .route_type(UPDATED_ROUTE_TYPE);
        RouteDTO routeDTO = routeMapper.toDto(updatedRoute);

        restRouteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, routeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(routeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
        Route testRoute = routeList.get(routeList.size() - 1);
        assertThat(testRoute.getAgency_id()).isEqualTo(UPDATED_AGENCY_ID);
        assertThat(testRoute.getRoute_short_name()).isEqualTo(UPDATED_ROUTE_SHORT_NAME);
        assertThat(testRoute.getRoute_long_name()).isEqualTo(UPDATED_ROUTE_LONG_NAME);
        assertThat(testRoute.getRoute_desc()).isEqualTo(UPDATED_ROUTE_DESC);
        assertThat(testRoute.getRoute_type()).isEqualTo(UPDATED_ROUTE_TYPE);
    }

    @Test
    void putNonExistingRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, routeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(routeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(routeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRouteWithPatch() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        int databaseSizeBeforeUpdate = routeRepository.findAll().size();

        // Update the route using partial update
        Route partialUpdatedRoute = new Route();
        partialUpdatedRoute.setId(route.getId());

        partialUpdatedRoute.route_short_name(UPDATED_ROUTE_SHORT_NAME).route_desc(UPDATED_ROUTE_DESC);

        restRouteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoute.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoute))
            )
            .andExpect(status().isOk());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
        Route testRoute = routeList.get(routeList.size() - 1);
        assertThat(testRoute.getAgency_id()).isEqualTo(DEFAULT_AGENCY_ID);
        assertThat(testRoute.getRoute_short_name()).isEqualTo(UPDATED_ROUTE_SHORT_NAME);
        assertThat(testRoute.getRoute_long_name()).isEqualTo(DEFAULT_ROUTE_LONG_NAME);
        assertThat(testRoute.getRoute_desc()).isEqualTo(UPDATED_ROUTE_DESC);
        assertThat(testRoute.getRoute_type()).isEqualTo(DEFAULT_ROUTE_TYPE);
    }

    @Test
    void fullUpdateRouteWithPatch() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        int databaseSizeBeforeUpdate = routeRepository.findAll().size();

        // Update the route using partial update
        Route partialUpdatedRoute = new Route();
        partialUpdatedRoute.setId(route.getId());

        partialUpdatedRoute
            .agency_id(UPDATED_AGENCY_ID)
            .route_short_name(UPDATED_ROUTE_SHORT_NAME)
            .route_long_name(UPDATED_ROUTE_LONG_NAME)
            .route_desc(UPDATED_ROUTE_DESC)
            .route_type(UPDATED_ROUTE_TYPE);

        restRouteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRoute.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRoute))
            )
            .andExpect(status().isOk());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
        Route testRoute = routeList.get(routeList.size() - 1);
        assertThat(testRoute.getAgency_id()).isEqualTo(UPDATED_AGENCY_ID);
        assertThat(testRoute.getRoute_short_name()).isEqualTo(UPDATED_ROUTE_SHORT_NAME);
        assertThat(testRoute.getRoute_long_name()).isEqualTo(UPDATED_ROUTE_LONG_NAME);
        assertThat(testRoute.getRoute_desc()).isEqualTo(UPDATED_ROUTE_DESC);
        assertThat(testRoute.getRoute_type()).isEqualTo(UPDATED_ROUTE_TYPE);
    }

    @Test
    void patchNonExistingRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, routeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(routeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(routeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRoute() throws Exception {
        int databaseSizeBeforeUpdate = routeRepository.findAll().size();
        route.setId(UUID.randomUUID().toString());

        // Create the Route
        RouteDTO routeDTO = routeMapper.toDto(route);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRouteMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(routeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Route in the database
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRoute() throws Exception {
        // Initialize the database
        routeRepository.save(route);

        int databaseSizeBeforeDelete = routeRepository.findAll().size();

        // Delete the route
        restRouteMockMvc
            .perform(delete(ENTITY_API_URL_ID, route.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Route> routeList = routeRepository.findAll();
        assertThat(routeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
