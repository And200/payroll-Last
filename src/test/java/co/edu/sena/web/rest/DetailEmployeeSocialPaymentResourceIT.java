package co.edu.sena.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.IntegrationTest;
import co.edu.sena.domain.DetailEmployeeSocialPayment;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.SocialPayment;
import co.edu.sena.repository.DetailEmployeeSocialPaymentRepository;
import co.edu.sena.service.DetailEmployeeSocialPaymentService;
import co.edu.sena.service.dto.DetailEmployeeSocialPaymentDTO;
import co.edu.sena.service.mapper.DetailEmployeeSocialPaymentMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DetailEmployeeSocialPaymentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class DetailEmployeeSocialPaymentResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/detail-employee-social-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepository;

    @Mock
    private DetailEmployeeSocialPaymentRepository detailEmployeeSocialPaymentRepositoryMock;

    @Autowired
    private DetailEmployeeSocialPaymentMapper detailEmployeeSocialPaymentMapper;

    @Mock
    private DetailEmployeeSocialPaymentService detailEmployeeSocialPaymentServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetailEmployeeSocialPaymentMockMvc;

    private DetailEmployeeSocialPayment detailEmployeeSocialPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailEmployeeSocialPayment createEntity(EntityManager em) {
        DetailEmployeeSocialPayment detailEmployeeSocialPayment = new DetailEmployeeSocialPayment().description(DEFAULT_DESCRIPTION);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        detailEmployeeSocialPayment.setEmployee(employee);
        // Add required entity
        SocialPayment socialPayment;
        if (TestUtil.findAll(em, SocialPayment.class).isEmpty()) {
            socialPayment = SocialPaymentResourceIT.createEntity(em);
            em.persist(socialPayment);
            em.flush();
        } else {
            socialPayment = TestUtil.findAll(em, SocialPayment.class).get(0);
        }
        detailEmployeeSocialPayment.setSocialPayment(socialPayment);
        return detailEmployeeSocialPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetailEmployeeSocialPayment createUpdatedEntity(EntityManager em) {
        DetailEmployeeSocialPayment detailEmployeeSocialPayment = new DetailEmployeeSocialPayment().description(UPDATED_DESCRIPTION);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createUpdatedEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        detailEmployeeSocialPayment.setEmployee(employee);
        // Add required entity
        SocialPayment socialPayment;
        if (TestUtil.findAll(em, SocialPayment.class).isEmpty()) {
            socialPayment = SocialPaymentResourceIT.createUpdatedEntity(em);
            em.persist(socialPayment);
            em.flush();
        } else {
            socialPayment = TestUtil.findAll(em, SocialPayment.class).get(0);
        }
        detailEmployeeSocialPayment.setSocialPayment(socialPayment);
        return detailEmployeeSocialPayment;
    }

    @BeforeEach
    public void initTest() {
        detailEmployeeSocialPayment = createEntity(em);
    }

    @Test
    @Transactional
    void createDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeCreate = detailEmployeeSocialPaymentRepository.findAll().size();
        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        DetailEmployeeSocialPayment testDetailEmployeeSocialPayment = detailEmployeeSocialPaymentList.get(
            detailEmployeeSocialPaymentList.size() - 1
        );
        assertThat(testDetailEmployeeSocialPayment.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void createDetailEmployeeSocialPaymentWithExistingId() throws Exception {
        // Create the DetailEmployeeSocialPayment with an existing ID
        detailEmployeeSocialPayment.setId(1L);
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        int databaseSizeBeforeCreate = detailEmployeeSocialPaymentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = detailEmployeeSocialPaymentRepository.findAll().size();
        // set the field null
        detailEmployeeSocialPayment.setDescription(null);

        // Create the DetailEmployeeSocialPayment, which fails.
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDetailEmployeeSocialPayments() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        // Get all the detailEmployeeSocialPaymentList
        restDetailEmployeeSocialPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detailEmployeeSocialPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDetailEmployeeSocialPaymentsWithEagerRelationshipsIsEnabled() throws Exception {
        when(detailEmployeeSocialPaymentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDetailEmployeeSocialPaymentMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(detailEmployeeSocialPaymentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDetailEmployeeSocialPaymentsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(detailEmployeeSocialPaymentServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restDetailEmployeeSocialPaymentMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(detailEmployeeSocialPaymentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getDetailEmployeeSocialPayment() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        // Get the detailEmployeeSocialPayment
        restDetailEmployeeSocialPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, detailEmployeeSocialPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detailEmployeeSocialPayment.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingDetailEmployeeSocialPayment() throws Exception {
        // Get the detailEmployeeSocialPayment
        restDetailEmployeeSocialPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDetailEmployeeSocialPayment() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();

        // Update the detailEmployeeSocialPayment
        DetailEmployeeSocialPayment updatedDetailEmployeeSocialPayment = detailEmployeeSocialPaymentRepository
            .findById(detailEmployeeSocialPayment.getId())
            .get();
        // Disconnect from session so that the updates on updatedDetailEmployeeSocialPayment are not directly saved in db
        em.detach(updatedDetailEmployeeSocialPayment);
        updatedDetailEmployeeSocialPayment.description(UPDATED_DESCRIPTION);
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            updatedDetailEmployeeSocialPayment
        );

        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailEmployeeSocialPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isOk());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
        DetailEmployeeSocialPayment testDetailEmployeeSocialPayment = detailEmployeeSocialPaymentList.get(
            detailEmployeeSocialPaymentList.size() - 1
        );
        assertThat(testDetailEmployeeSocialPayment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void putNonExistingDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, detailEmployeeSocialPaymentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDetailEmployeeSocialPaymentWithPatch() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();

        // Update the detailEmployeeSocialPayment using partial update
        DetailEmployeeSocialPayment partialUpdatedDetailEmployeeSocialPayment = new DetailEmployeeSocialPayment();
        partialUpdatedDetailEmployeeSocialPayment.setId(detailEmployeeSocialPayment.getId());

        partialUpdatedDetailEmployeeSocialPayment.description(UPDATED_DESCRIPTION);

        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailEmployeeSocialPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailEmployeeSocialPayment))
            )
            .andExpect(status().isOk());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
        DetailEmployeeSocialPayment testDetailEmployeeSocialPayment = detailEmployeeSocialPaymentList.get(
            detailEmployeeSocialPaymentList.size() - 1
        );
        assertThat(testDetailEmployeeSocialPayment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateDetailEmployeeSocialPaymentWithPatch() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();

        // Update the detailEmployeeSocialPayment using partial update
        DetailEmployeeSocialPayment partialUpdatedDetailEmployeeSocialPayment = new DetailEmployeeSocialPayment();
        partialUpdatedDetailEmployeeSocialPayment.setId(detailEmployeeSocialPayment.getId());

        partialUpdatedDetailEmployeeSocialPayment.description(UPDATED_DESCRIPTION);

        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDetailEmployeeSocialPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDetailEmployeeSocialPayment))
            )
            .andExpect(status().isOk());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
        DetailEmployeeSocialPayment testDetailEmployeeSocialPayment = detailEmployeeSocialPaymentList.get(
            detailEmployeeSocialPaymentList.size() - 1
        );
        assertThat(testDetailEmployeeSocialPayment.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, detailEmployeeSocialPaymentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDetailEmployeeSocialPayment() throws Exception {
        int databaseSizeBeforeUpdate = detailEmployeeSocialPaymentRepository.findAll().size();
        detailEmployeeSocialPayment.setId(count.incrementAndGet());

        // Create the DetailEmployeeSocialPayment
        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = detailEmployeeSocialPaymentMapper.toDto(
            detailEmployeeSocialPayment
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDetailEmployeeSocialPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(detailEmployeeSocialPaymentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DetailEmployeeSocialPayment in the database
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDetailEmployeeSocialPayment() throws Exception {
        // Initialize the database
        detailEmployeeSocialPaymentRepository.saveAndFlush(detailEmployeeSocialPayment);

        int databaseSizeBeforeDelete = detailEmployeeSocialPaymentRepository.findAll().size();

        // Delete the detailEmployeeSocialPayment
        restDetailEmployeeSocialPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, detailEmployeeSocialPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetailEmployeeSocialPayment> detailEmployeeSocialPaymentList = detailEmployeeSocialPaymentRepository.findAll();
        assertThat(detailEmployeeSocialPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
