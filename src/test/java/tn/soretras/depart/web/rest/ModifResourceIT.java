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
import tn.soretras.depart.domain.Modif;
import tn.soretras.depart.repository.ModifRepository;
import tn.soretras.depart.service.dto.ModifDTO;
import tn.soretras.depart.service.mapper.ModifMapper;

/**
 * Integration tests for the {@link ModifResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ModifResourceIT {

    private static final Integer DEFAULT_DECCENT = 1;
    private static final Integer UPDATED_DECCENT = 2;

    private static final Integer DEFAULT_DECAGENC = 1;
    private static final Integer UPDATED_DECAGENC = 2;

    private static final String DEFAULT_DEDATED = "2023-08-08T23:00:00Z";
    private static final String UPDATED_DEDATED = "2023-08-08T23:00:00Z";

    private static final Integer DEFAULT_DENUMDP = 1;
    private static final Integer UPDATED_DENUMDP = 2;

    private static final Integer DEFAULT_DECSERV = 1;
    private static final Integer UPDATED_DECSERV = 2;

    private static final String DEFAULT_DECOPER = "AAAAAAAAAA";
    private static final String UPDATED_DECOPER = "BBBBBBBBBB";

    private static final String DEFAULT_DECSEAN = "AAAAAAAAAA";
    private static final String UPDATED_DECSEAN = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMROTAT = 1;
    private static final Integer UPDATED_NUMROTAT = 2;

    private static final Integer DEFAULT_MATRIC = 1;
    private static final Integer UPDATED_MATRIC = 2;

    private static final Integer DEFAULT_CD_1 = 1;
    private static final Integer UPDATED_CD_1 = 2;

    private static final Integer DEFAULT_DECMOTIF = 1;
    private static final Integer UPDATED_DECMOTIF = 2;

    private static final String DEFAULT_HEUR = "2023-08-08T23:00:00Z";
    private static final String UPDATED_HEUR = "2023-08-08T23:00:00Z";

    private static final String DEFAULT_CHRE = "AAAAAAAAAA";
    private static final String UPDATED_CHRE = "BBBBBBBBBB";

    private static final String DEFAULT_TYP = "AAAAAAAAAA";
    private static final String UPDATED_TYP = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/modifs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ModifRepository modifRepository;

    @Autowired
    private ModifMapper modifMapper;

    @Autowired
    private MockMvc restModifMockMvc;

    private Modif modif;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modif createEntity() {
        Modif modif = new Modif()
            .deccent(DEFAULT_DECCENT)
            .decagenc(DEFAULT_DECAGENC)
            .dedated(DEFAULT_DEDATED)
            .denumdp(DEFAULT_DENUMDP)
            .decserv(DEFAULT_DECSERV)
            .decoper(DEFAULT_DECOPER)
            .decsean(DEFAULT_DECSEAN)
            .numrotat(DEFAULT_NUMROTAT)
            .matric(DEFAULT_MATRIC)
            .cd1(DEFAULT_CD_1)
            .decmotif(DEFAULT_DECMOTIF)
            .heur(DEFAULT_HEUR)
            .chre(DEFAULT_CHRE)
            .typ(DEFAULT_TYP);
        return modif;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Modif createUpdatedEntity() {
        Modif modif = new Modif()
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .matric(UPDATED_MATRIC)
            .cd1(UPDATED_CD_1)
            .decmotif(UPDATED_DECMOTIF)
            .heur(UPDATED_HEUR)
            .chre(UPDATED_CHRE)
            .typ(UPDATED_TYP);
        return modif;
    }

    @BeforeEach
    public void initTest() {
        modifRepository.deleteAll();
        modif = createEntity();
    }

    @Test
    void createModif() throws Exception {
        int databaseSizeBeforeCreate = modifRepository.findAll().size();
        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);
        restModifMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modifDTO)))
            .andExpect(status().isCreated());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeCreate + 1);
        Modif testModif = modifList.get(modifList.size() - 1);
        assertThat(testModif.getDeccent()).isEqualTo(DEFAULT_DECCENT);
        assertThat(testModif.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testModif.getDedated()).isEqualTo(DEFAULT_DEDATED);
        assertThat(testModif.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testModif.getDecserv()).isEqualTo(DEFAULT_DECSERV);
        assertThat(testModif.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testModif.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testModif.getNumrotat()).isEqualTo(DEFAULT_NUMROTAT);
        assertThat(testModif.getMatric()).isEqualTo(DEFAULT_MATRIC);
        assertThat(testModif.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testModif.getDecmotif()).isEqualTo(DEFAULT_DECMOTIF);
        assertThat(testModif.getHeur()).isEqualTo(DEFAULT_HEUR);
        assertThat(testModif.getChre()).isEqualTo(DEFAULT_CHRE);
        assertThat(testModif.getTyp()).isEqualTo(DEFAULT_TYP);
    }

    @Test
    void createModifWithExistingId() throws Exception {
        // Create the Modif with an existing ID
        modif.setId("existing_id");
        ModifDTO modifDTO = modifMapper.toDto(modif);

        int databaseSizeBeforeCreate = modifRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restModifMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modifDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllModifs() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        // Get all the modifList
        restModifMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(modif.getId())))
            .andExpect(jsonPath("$.[*].deccent").value(hasItem(DEFAULT_DECCENT)))
            .andExpect(jsonPath("$.[*].decagenc").value(hasItem(DEFAULT_DECAGENC)))
            .andExpect(jsonPath("$.[*].dedated").value(hasItem(DEFAULT_DEDATED.toString())))
            .andExpect(jsonPath("$.[*].denumdp").value(hasItem(DEFAULT_DENUMDP)))
            .andExpect(jsonPath("$.[*].decserv").value(hasItem(DEFAULT_DECSERV)))
            .andExpect(jsonPath("$.[*].decoper").value(hasItem(DEFAULT_DECOPER)))
            .andExpect(jsonPath("$.[*].decsean").value(hasItem(DEFAULT_DECSEAN)))
            .andExpect(jsonPath("$.[*].numrotat").value(hasItem(DEFAULT_NUMROTAT)))
            .andExpect(jsonPath("$.[*].matric").value(hasItem(DEFAULT_MATRIC)))
            .andExpect(jsonPath("$.[*].cd1").value(hasItem(DEFAULT_CD_1)))
            .andExpect(jsonPath("$.[*].decmotif").value(hasItem(DEFAULT_DECMOTIF)))
            .andExpect(jsonPath("$.[*].heur").value(hasItem(DEFAULT_HEUR.toString())))
            .andExpect(jsonPath("$.[*].chre").value(hasItem(DEFAULT_CHRE)))
            .andExpect(jsonPath("$.[*].typ").value(hasItem(DEFAULT_TYP)));
    }

    @Test
    void getModif() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        // Get the modif
        restModifMockMvc
            .perform(get(ENTITY_API_URL_ID, modif.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(modif.getId()))
            .andExpect(jsonPath("$.deccent").value(DEFAULT_DECCENT))
            .andExpect(jsonPath("$.decagenc").value(DEFAULT_DECAGENC))
            .andExpect(jsonPath("$.dedated").value(DEFAULT_DEDATED.toString()))
            .andExpect(jsonPath("$.denumdp").value(DEFAULT_DENUMDP))
            .andExpect(jsonPath("$.decserv").value(DEFAULT_DECSERV))
            .andExpect(jsonPath("$.decoper").value(DEFAULT_DECOPER))
            .andExpect(jsonPath("$.decsean").value(DEFAULT_DECSEAN))
            .andExpect(jsonPath("$.numrotat").value(DEFAULT_NUMROTAT))
            .andExpect(jsonPath("$.matric").value(DEFAULT_MATRIC))
            .andExpect(jsonPath("$.cd1").value(DEFAULT_CD_1))
            .andExpect(jsonPath("$.decmotif").value(DEFAULT_DECMOTIF))
            .andExpect(jsonPath("$.heur").value(DEFAULT_HEUR.toString()))
            .andExpect(jsonPath("$.chre").value(DEFAULT_CHRE))
            .andExpect(jsonPath("$.typ").value(DEFAULT_TYP));
    }

    @Test
    void getNonExistingModif() throws Exception {
        // Get the modif
        restModifMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingModif() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        int databaseSizeBeforeUpdate = modifRepository.findAll().size();

        // Update the modif
        Modif updatedModif = modifRepository.findById(modif.getId()).get();
        updatedModif
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .matric(UPDATED_MATRIC)
            .cd1(UPDATED_CD_1)
            .decmotif(UPDATED_DECMOTIF)
            .heur(UPDATED_HEUR)
            .chre(UPDATED_CHRE)
            .typ(UPDATED_TYP);
        ModifDTO modifDTO = modifMapper.toDto(updatedModif);

        restModifMockMvc
            .perform(
                put(ENTITY_API_URL_ID, modifDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(modifDTO))
            )
            .andExpect(status().isOk());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
        Modif testModif = modifList.get(modifList.size() - 1);
        assertThat(testModif.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testModif.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testModif.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testModif.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testModif.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testModif.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testModif.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testModif.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testModif.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testModif.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testModif.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testModif.getHeur()).isEqualTo(UPDATED_HEUR);
        assertThat(testModif.getChre()).isEqualTo(UPDATED_CHRE);
        assertThat(testModif.getTyp()).isEqualTo(UPDATED_TYP);
    }

    @Test
    void putNonExistingModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(
                put(ENTITY_API_URL_ID, modifDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(modifDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(modifDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(modifDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateModifWithPatch() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        int databaseSizeBeforeUpdate = modifRepository.findAll().size();

        // Update the modif using partial update
        Modif partialUpdatedModif = new Modif();
        partialUpdatedModif.setId(modif.getId());

        partialUpdatedModif
            .deccent(UPDATED_DECCENT)
            .dedated(UPDATED_DEDATED)
            .decserv(UPDATED_DECSERV)
            .numrotat(UPDATED_NUMROTAT)
            .matric(UPDATED_MATRIC)
            .decmotif(UPDATED_DECMOTIF)
            .heur(UPDATED_HEUR)
            .chre(UPDATED_CHRE);

        restModifMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedModif.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedModif))
            )
            .andExpect(status().isOk());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
        Modif testModif = modifList.get(modifList.size() - 1);
        assertThat(testModif.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testModif.getDecagenc()).isEqualTo(DEFAULT_DECAGENC);
        assertThat(testModif.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testModif.getDenumdp()).isEqualTo(DEFAULT_DENUMDP);
        assertThat(testModif.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testModif.getDecoper()).isEqualTo(DEFAULT_DECOPER);
        assertThat(testModif.getDecsean()).isEqualTo(DEFAULT_DECSEAN);
        assertThat(testModif.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testModif.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testModif.getCd1()).isEqualTo(DEFAULT_CD_1);
        assertThat(testModif.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testModif.getHeur()).isEqualTo(UPDATED_HEUR);
        assertThat(testModif.getChre()).isEqualTo(UPDATED_CHRE);
        assertThat(testModif.getTyp()).isEqualTo(DEFAULT_TYP);
    }

    @Test
    void fullUpdateModifWithPatch() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        int databaseSizeBeforeUpdate = modifRepository.findAll().size();

        // Update the modif using partial update
        Modif partialUpdatedModif = new Modif();
        partialUpdatedModif.setId(modif.getId());

        partialUpdatedModif
            .deccent(UPDATED_DECCENT)
            .decagenc(UPDATED_DECAGENC)
            .dedated(UPDATED_DEDATED)
            .denumdp(UPDATED_DENUMDP)
            .decserv(UPDATED_DECSERV)
            .decoper(UPDATED_DECOPER)
            .decsean(UPDATED_DECSEAN)
            .numrotat(UPDATED_NUMROTAT)
            .matric(UPDATED_MATRIC)
            .cd1(UPDATED_CD_1)
            .decmotif(UPDATED_DECMOTIF)
            .heur(UPDATED_HEUR)
            .chre(UPDATED_CHRE)
            .typ(UPDATED_TYP);

        restModifMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedModif.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedModif))
            )
            .andExpect(status().isOk());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
        Modif testModif = modifList.get(modifList.size() - 1);
        assertThat(testModif.getDeccent()).isEqualTo(UPDATED_DECCENT);
        assertThat(testModif.getDecagenc()).isEqualTo(UPDATED_DECAGENC);
        assertThat(testModif.getDedated()).isEqualTo(UPDATED_DEDATED);
        assertThat(testModif.getDenumdp()).isEqualTo(UPDATED_DENUMDP);
        assertThat(testModif.getDecserv()).isEqualTo(UPDATED_DECSERV);
        assertThat(testModif.getDecoper()).isEqualTo(UPDATED_DECOPER);
        assertThat(testModif.getDecsean()).isEqualTo(UPDATED_DECSEAN);
        assertThat(testModif.getNumrotat()).isEqualTo(UPDATED_NUMROTAT);
        assertThat(testModif.getMatric()).isEqualTo(UPDATED_MATRIC);
        assertThat(testModif.getCd1()).isEqualTo(UPDATED_CD_1);
        assertThat(testModif.getDecmotif()).isEqualTo(UPDATED_DECMOTIF);
        assertThat(testModif.getHeur()).isEqualTo(UPDATED_HEUR);
        assertThat(testModif.getChre()).isEqualTo(UPDATED_CHRE);
        assertThat(testModif.getTyp()).isEqualTo(UPDATED_TYP);
    }

    @Test
    void patchNonExistingModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, modifDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(modifDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(modifDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamModif() throws Exception {
        int databaseSizeBeforeUpdate = modifRepository.findAll().size();
        modif.setId(UUID.randomUUID().toString());

        // Create the Modif
        ModifDTO modifDTO = modifMapper.toDto(modif);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restModifMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(modifDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Modif in the database
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteModif() throws Exception {
        // Initialize the database
        modifRepository.save(modif);

        int databaseSizeBeforeDelete = modifRepository.findAll().size();

        // Delete the modif
        restModifMockMvc
            .perform(delete(ENTITY_API_URL_ID, modif.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Modif> modifList = modifRepository.findAll();
        assertThat(modifList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
