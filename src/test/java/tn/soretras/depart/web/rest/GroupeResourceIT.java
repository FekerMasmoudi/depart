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
import tn.soretras.depart.domain.Groupe;
import tn.soretras.depart.repository.GroupeRepository;
import tn.soretras.depart.service.dto.GroupeDTO;
import tn.soretras.depart.service.mapper.GroupeMapper;

/**
 * Integration tests for the {@link GroupeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GroupeResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final Integer DEFAULT_CODGRP = 1;
    private static final Integer UPDATED_CODGRP = 2;

    private static final String DEFAULT_LIBGRP = "AAAAAAAAAA";
    private static final String UPDATED_LIBGRP = "BBBBBBBBBB";

    private static final String DEFAULT_DECTYLI = "AAAAAAAAAA";
    private static final String UPDATED_DECTYLI = "BBBBBBBBBB";

    private static final String DEFAULT_LIBGRPFR = "AAAAAAAAAA";
    private static final String UPDATED_LIBGRPFR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/groupes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private GroupeMapper groupeMapper;

    @Autowired
    private MockMvc restGroupeMockMvc;

    private Groupe groupe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groupe createEntity() {
        Groupe groupe = new Groupe()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .codgrp(DEFAULT_CODGRP)
            .libgrp(DEFAULT_LIBGRP)
            .dectyli(DEFAULT_DECTYLI)
            .libgrpfr(DEFAULT_LIBGRPFR);
        return groupe;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Groupe createUpdatedEntity() {
        Groupe groupe = new Groupe()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .codgrp(UPDATED_CODGRP)
            .libgrp(UPDATED_LIBGRP)
            .dectyli(UPDATED_DECTYLI)
            .libgrpfr(UPDATED_LIBGRPFR);
        return groupe;
    }

    @BeforeEach
    public void initTest() {
        groupeRepository.deleteAll();
        groupe = createEntity();
    }

    @Test
    void createGroupe() throws Exception {
        int databaseSizeBeforeCreate = groupeRepository.findAll().size();
        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);
        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isCreated());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeCreate + 1);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testGroupe.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testGroupe.getCodgrp()).isEqualTo(DEFAULT_CODGRP);
        assertThat(testGroupe.getLibgrp()).isEqualTo(DEFAULT_LIBGRP);
        assertThat(testGroupe.getDectyli()).isEqualTo(DEFAULT_DECTYLI);
        assertThat(testGroupe.getLibgrpfr()).isEqualTo(DEFAULT_LIBGRPFR);
    }

    @Test
    void createGroupeWithExistingId() throws Exception {
        // Create the Groupe with an existing ID
        groupe.setId("existing_id");
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        int databaseSizeBeforeCreate = groupeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDeccentIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeRepository.findAll().size();
        // set the field null
        groupe.setDeccent(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDecagencIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeRepository.findAll().size();
        // set the field null
        groupe.setDecagenc(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCodgrpIsRequired() throws Exception {
        int databaseSizeBeforeTest = groupeRepository.findAll().size();
        // set the field null
        groupe.setCodgrp(null);

        // Create the Groupe, which fails.
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        restGroupeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isBadRequest());

        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllGroupes() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        // Get all the groupeList
        restGroupeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(groupe.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].codgrp").value(hasItem(DEFAULT_CODGRP)))
            .andExpect(jsonPath("$.[*].libgrp").value(hasItem(DEFAULT_LIBGRP)))
            .andExpect(jsonPath("$.[*].dectyli").value(hasItem(DEFAULT_DECTYLI)))
            .andExpect(jsonPath("$.[*].libgrpfr").value(hasItem(DEFAULT_LIBGRPFR)));
    }

    @Test
    void getGroupe() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        // Get the groupe
        restGroupeMockMvc
            .perform(get(ENTITY_API_URL_ID, groupe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(groupe.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.codgrp").value(DEFAULT_CODGRP))
            .andExpect(jsonPath("$.libgrp").value(DEFAULT_LIBGRP))
            .andExpect(jsonPath("$.dectyli").value(DEFAULT_DECTYLI))
            .andExpect(jsonPath("$.libgrpfr").value(DEFAULT_LIBGRPFR));
    }

    @Test
    void getNonExistingGroupe() throws Exception {
        // Get the groupe
        restGroupeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingGroupe() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();

        // Update the groupe
        Groupe updatedGroupe = groupeRepository.findById(groupe.getId()).get();
        updatedGroupe
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .codgrp(UPDATED_CODGRP)
            .libgrp(UPDATED_LIBGRP)
            .dectyli(UPDATED_DECTYLI)
            .libgrpfr(UPDATED_LIBGRPFR);
        GroupeDTO groupeDTO = groupeMapper.toDto(updatedGroupe);

        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, groupeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testGroupe.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testGroupe.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testGroupe.getLibgrp()).isEqualTo(UPDATED_LIBGRP);
        assertThat(testGroupe.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testGroupe.getLibgrpfr()).isEqualTo(UPDATED_LIBGRPFR);
    }

    @Test
    void putNonExistingGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, groupeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(groupeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateGroupeWithPatch() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();

        // Update the groupe using partial update
        Groupe partialUpdatedGroupe = new Groupe();
        partialUpdatedGroupe.setId(groupe.getId());

        partialUpdatedGroupe.decagenc(UPDATED_DECAGENC).codgrp(UPDATED_CODGRP).dectyli(UPDATED_DECTYLI).libgrpfr(UPDATED_LIBGRPFR);

        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGroupe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupe))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testGroupe.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testGroupe.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testGroupe.getLibgrp()).isEqualTo(DEFAULT_LIBGRP);
        assertThat(testGroupe.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testGroupe.getLibgrpfr()).isEqualTo(UPDATED_LIBGRPFR);
    }

    @Test
    void fullUpdateGroupeWithPatch() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();

        // Update the groupe using partial update
        Groupe partialUpdatedGroupe = new Groupe();
        partialUpdatedGroupe.setId(groupe.getId());

        partialUpdatedGroupe
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .codgrp(UPDATED_CODGRP)
            .libgrp(UPDATED_LIBGRP)
            .dectyli(UPDATED_DECTYLI)
            .libgrpfr(UPDATED_LIBGRPFR);

        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGroupe.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedGroupe))
            )
            .andExpect(status().isOk());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
        Groupe testGroupe = groupeList.get(groupeList.size() - 1);
        assertThat(testGroupe.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testGroupe.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testGroupe.getCodgrp()).isEqualTo(UPDATED_CODGRP);
        assertThat(testGroupe.getLibgrp()).isEqualTo(UPDATED_LIBGRP);
        assertThat(testGroupe.getDectyli()).isEqualTo(UPDATED_DECTYLI);
        assertThat(testGroupe.getLibgrpfr()).isEqualTo(UPDATED_LIBGRPFR);
    }

    @Test
    void patchNonExistingGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, groupeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamGroupe() throws Exception {
        int databaseSizeBeforeUpdate = groupeRepository.findAll().size();
        groupe.setId(UUID.randomUUID().toString());

        // Create the Groupe
        GroupeDTO groupeDTO = groupeMapper.toDto(groupe);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGroupeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(groupeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Groupe in the database
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteGroupe() throws Exception {
        // Initialize the database
        groupeRepository.save(groupe);

        int databaseSizeBeforeDelete = groupeRepository.findAll().size();

        // Delete the groupe
        restGroupeMockMvc
            .perform(delete(ENTITY_API_URL_ID, groupe.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Groupe> groupeList = groupeRepository.findAll();
        assertThat(groupeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
