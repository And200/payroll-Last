package co.edu.sena.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.IntegrationTest;
import co.edu.sena.domain.SocialPayment;
import co.edu.sena.repository.SocialPaymentRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.dto.SocialPaymentDTO;
import co.edu.sena.service.mapper.SocialPaymentMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SocialPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
class SocialPaymentResourceIT {

    private static final String DEFAULT_SOCIAL_PAYMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOCIAL_PAYMENT_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/social-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SocialPaymentRepository socialPaymentRepository;

    @Autowired
    private SocialPaymentMapper socialPaymentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSocialPaymentMockMvc;

    private SocialPayment socialPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialPayment createEntity(EntityManager em) {
        SocialPayment socialPayment = new SocialPayment().socialPaymentName(DEFAULT_SOCIAL_PAYMENT_NAME);
        return socialPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SocialPayment createUpdatedEntity(EntityManager em) {
        SocialPayment socialPayment = new SocialPayment().socialPaymentName(UPDATED_SOCIAL_PAYMENT_NAME);
        return socialPayment;
    }

    @BeforeEach
    public void initTest() {
        socialPayment = createEntity(em);
    }

    @Test
    @Transactional
    void createSocialPayment() throws Exception {
        int databaseSizeBeforeCreate = socialPaymentRepository.findAll().size();
        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);
        restSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        SocialPayment testSocialPayment = socialPaymentList.get(socialPaymentList.size() - 1);
        assertThat(testSocialPayment.getSocialPaymentName()).isEqualTo(DEFAULT_SOCIAL_PAYMENT_NAME);
    }

    @Test
    @Transactional
    void createSocialPaymentWithExistingId() throws Exception {
        // Create the SocialPayment with an existing ID
        socialPayment.setId(1L);
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        int databaseSizeBeforeCreate = socialPaymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkSocialPaymentNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = socialPaymentRepository.findAll().size();
        // set the field null
        socialPayment.setSocialPaymentName(null);

        // Create the SocialPayment, which fails.
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        restSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSocialPayments() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        // Get all the socialPaymentList
        restSocialPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(socialPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].socialPaymentName").value(hasItem(DEFAULT_SOCIAL_PAYMENT_NAME)));
    }

    @Test
    @Transactional
    void getSocialPayment() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        // Get the socialPayment
        restSocialPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, socialPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(socialPayment.getId().intValue()))
            .andExpect(jsonPath("$.socialPaymentName").value(DEFAULT_SOCIAL_PAYMENT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingSocialPayment() throws Exception {
        // Get the socialPayment
        restSocialPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSocialPayment() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();

        // Update the socialPayment
        SocialPayment updatedSocialPayment = socialPaymentRepository.findById(socialPayment.getId()).get();
        // Disconnect from session so that the updates on updatedSocialPayment are not directly saved in db
        em.detach(updatedSocialPayment);
        updatedSocialPayment.socialPaymentName(UPDATED_SOCIAL_PAYMENT_NAME);
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(updatedSocialPayment);

        restSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, socialPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isOk());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
        SocialPayment testSocialPayment = socialPaymentList.get(socialPaymentList.size() - 1);
        assertThat(testSocialPayment.getSocialPaymentName()).isEqualTo(UPDATED_SOCIAL_PAYMENT_NAME);
    }

    @Test
    @Transactional
    void putNonExistingSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, socialPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSocialPaymentWithPatch() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();

        // Update the socialPayment using partial update
        SocialPayment partialUpdatedSocialPayment = new SocialPayment();
        partialUpdatedSocialPayment.setId(socialPayment.getId());

        restSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSocialPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSocialPayment))
            )
            .andExpect(status().isOk());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
        SocialPayment testSocialPayment = socialPaymentList.get(socialPaymentList.size() - 1);
        assertThat(testSocialPayment.getSocialPaymentName()).isEqualTo(DEFAULT_SOCIAL_PAYMENT_NAME);
    }

    @Test
    @Transactional
    void fullUpdateSocialPaymentWithPatch() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();

        // Update the socialPayment using partial update
        SocialPayment partialUpdatedSocialPayment = new SocialPayment();
        partialUpdatedSocialPayment.setId(socialPayment.getId());

        partialUpdatedSocialPayment.socialPaymentName(UPDATED_SOCIAL_PAYMENT_NAME);

        restSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSocialPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSocialPayment))
            )
            .andExpect(status().isOk());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
        SocialPayment testSocialPayment = socialPaymentList.get(socialPaymentList.size() - 1);
        assertThat(testSocialPayment.getSocialPaymentName()).isEqualTo(UPDATED_SOCIAL_PAYMENT_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, socialPaymentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = socialPaymentRepository.findAll().size();
        socialPayment.setId(count.incrementAndGet());

        // Create the SocialPayment
        SocialPaymentDTO socialPaymentDTO = socialPaymentMapper.toDto(socialPayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(socialPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SocialPayment in the database
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSocialPayment() throws Exception {
        // Initialize the database
        socialPaymentRepository.saveAndFlush(socialPayment);

        int databaseSizeBeforeDelete = socialPaymentRepository.findAll().size();

        // Delete the socialPayment
        restSocialPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, socialPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SocialPayment> socialPaymentList = socialPaymentRepository.findAll();
        assertThat(socialPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
