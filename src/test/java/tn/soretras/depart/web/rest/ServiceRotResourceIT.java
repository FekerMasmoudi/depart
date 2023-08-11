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
import tn.soretras.depart.domain.ServiceRot;
import tn.soretras.depart.repository.ServiceRotRepository;
import tn.soretras.depart.service.dto.ServiceRotDTO;
import tn.soretras.depart.service.mapper.ServiceRotMapper;

/**
 * Integration tests for the {@link ServiceRotResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceRotResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final Integer DEFAULT_DECSERV = 1;
    private static final Integer UPDATED_DECSERV = 2;

    private static final Integer DEFAULT_CODGRP = 1;
    private static final Integer UPDATED_CODGRP = 2;

    private static final String DEFAULT_DELSERV = "AAAAAAAAAA";
    private static final String UPDATED_DELSERV = "BBBBBBBBBB";

    private static final Integer DEFAULT_ORDSERV = 1;
    private static final Integer UPDATED_ORDSERV = 2;

    private static final String ENTITY_API_URL = "/api/service-rots";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ServiceRotRepository serviceRotRepository;

    @Autowired
    private ServiceRotMapper serviceRotMapper;

    @Autowired
    private MockMvc restServiceRotMockMvc;

    private ServiceRot serviceRot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceRot createEntity() {
        ServiceRot serviceRot = new ServiceRot()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .decserv(DEFAULT_DECSERV)
            .codgrp(DEFAULT_CODGRP)
            .delserv(DEFAULT_DELSERV)
            .ordserv(DEFAULT_ORDSERV);
        return serviceRot;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceRot createUpdatedEntity() {
        ServiceRot serviceRot = new ServiceRot()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .codgrp(UPDATED_CODGRP)
            .delserv(UPDATED_DELSERV)
            .ordserv(UPDATED_ORDSERV);
        return serviceRot;
    }

    @BeforeEach
    public void initTest() {
        serviceRotRepository.deleteAll();
        serviceRot = createEntity();
    }

    @Test
    void createServiceRot() throws Exception {
        int databaseSizeBeforeCreate = serviceRotRepository.findAll().size();
        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);
        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceRot testServiceRot = serviceRotList.get(serviceRotList.size() - 1);
        assertThat(testServiceRot.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testServiceRot.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testServiceRot.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testServiceRot.getCodgrp()).isEqualTo(DEFAULT_CODGRP);
        assertThat(testServiceRot.getDelserv()).isEqualTo(DEFAULT_DELSERV);
        assertThat(testServiceRot.getOrdserv()).isEqualTo(DEFAULT_ORDSERV);
    }

    @Test
    void createServiceRotWithExistingId() throws Exception {
        // Create the ServiceRot with an existing ID
        serviceRot.setId("existing_id");
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        int databaseSizeBeforeCreate = serviceRotRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRotRepository.findAll().size();
        // set the field null
        serviceRot.setDeccent(null);

        // Create the ServiceRot, which fails.
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRotRepository.findAll().size();
        // set the field null
        serviceRot.setDecagenc(null);

        // Create the ServiceRot, which fails.
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecservIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRotRepository.findAll().size();
        // set the field null
        serviceRot.setDecserv(null);

        // Create the ServiceRot, which fails.
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodgrpIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceRotRepository.findAll().size();
        // set the field null
        serviceRot.setCodgrp(null);

        // Create the ServiceRot, which fails.
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        restServiceRotMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllServiceRots() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        // Get all the serviceRotList
        restServiceRotMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceRot.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].codgrp").value(hasItem(DEFAULT_CODGRP)))
            .andExpect(jsonPath("$.[*].delserv").value(hasItem(DEFAULT_DELSERV)))
            .andExpect(jsonPath("$.[*].ordserv").value(hasItem(DEFAULT_ORDSERV)));
    }

    @Test
    void getServiceRot() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        // Get the serviceRot
        restServiceRotMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceRot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceRot.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.codgrp").value(DEFAULT_CODGRP))
            .andExpect(jsonPath("$.delserv").value(DEFAULT_DELSERV))
            .andExpect(jsonPath("$.ordserv").value(DEFAULT_ORDSERV));
    }

    @Test
    void getNonExistingServiceRot() throws Exception {
        // Get the serviceRot
        restServiceRotMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingServiceRot() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();

        // Update the serviceRot
        ServiceRot updatedServiceRot = serviceRotRepository.findById(serviceRot.getId()).get();
        updatedServiceRot
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .codgrp(UPDATED_CODGRP)
            .delserv(UPDATED_DELSERV)
            .ordserv(UPDATED_ORDSERV);
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(updatedServiceRot);

        restServiceRotMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceRotDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
        ServiceRot testServiceRot = serviceRotList.get(serviceRotList.size() - 1);
        assertThat(testServiceRot.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testServiceRot.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testServiceRot.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testServiceRot.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testServiceRot.getDelserv()).isEqualTo(UPDATED_DELSERV);
        assertThat(testServiceRot.getOrdserv()).isEqualTo(UPDATED_ORDSERV);
    }

    @Test
    void putNonExistingServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceRotDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceRotDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateServiceRotWithPatch() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();

        // Update the serviceRot using partial update
        ServiceRot partialUpdatedServiceRot = new ServiceRot();
        partialUpdatedServiceRot.setId(serviceRot.getId());

        partialUpdatedServiceRot.decagenc(UPDATED_DECAGENC).decserv(UPDATED_DECSERV).codgrp(UPDATED_CODGRP).delserv(UPDATED_DELSERV);

        restServiceRotMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceRot.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceRot))
            )
            .andExpect(status().isOk());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
        ServiceRot testServiceRot = serviceRotList.get(serviceRotList.size() - 1);
        assertThat(testServiceRot.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testServiceRot.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testServiceRot.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testServiceRot.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testServiceRot.getDelserv()).isEqualTo(UPDATED_DELSERV);
        assertThat(testServiceRot.getOrdserv()).isEqualTo(DEFAULT_ORDSERV);
    }

    @Test
    void fullUpdateServiceRotWithPatch() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();

        // Update the serviceRot using partial update
        ServiceRot partialUpdatedServiceRot = new ServiceRot();
        partialUpdatedServiceRot.setId(serviceRot.getId());

        partialUpdatedServiceRot
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .decserv(UPDATED_DECSERV)
            .codgrp(UPDATED_CODGRP)
            .delserv(UPDATED_DELSERV)
            .ordserv(UPDATED_ORDSERV);

        restServiceRotMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceRot.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceRot))
            )
            .andExpect(status().isOk());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
        ServiceRot testServiceRot = serviceRotList.get(serviceRotList.size() - 1);
        assertThat(testServiceRot.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testServiceRot.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testServiceRot.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testServiceRot.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testServiceRot.getDelserv()).isEqualTo(UPDATED_DELSERV);
        assertThat(testServiceRot.getOrdserv()).isEqualTo(UPDATED_ORDSERV);
    }

    @Test
    void patchNonExistingServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceRotDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamServiceRot() throws Exception {
        int databaseSizeBeforeUpdate = serviceRotRepository.findAll().size();
        serviceRot.setId(UUID.randomUUID().toString());

        // Create the ServiceRot
        ServiceRotDTO serviceRotDTO = serviceRotMapper.toDto(serviceRot);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceRotMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(serviceRotDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceRot in the database
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteServiceRot() throws Exception {
        // Initialize the database
        serviceRotRepository.save(serviceRot);

        int databaseSizeBeforeDelete = serviceRotRepository.findAll().size();

        // Delete the serviceRot
        restServiceRotMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceRot.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceRot> serviceRotList = serviceRotRepository.findAll();
        assertThat(serviceRotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
