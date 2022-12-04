package co.edu.sena.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.IntegrationTest;
import co.edu.sena.domain.Deduction;
import co.edu.sena.domain.Employee;
import co.edu.sena.domain.Income;
import co.edu.sena.domain.Payroll;
import co.edu.sena.domain.PositionArl;
import co.edu.sena.repository.PayrollRepository;
import co.edu.sena.security.AuthoritiesConstants;
import co.edu.sena.service.PayrollService;
import co.edu.sena.service.dto.PayrollDTO;
import co.edu.sena.service.mapper.PayrollMapper;
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
 * Integration tests for the {@link PayrollResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
class PayrollResourceIT {

    private static final Integer DEFAULT_WORKED_DAYS = 1;
    private static final Integer UPDATED_WORKED_DAYS = 2;

    private static final Double DEFAULT_BASE_SALARY = 1D;
    private static final Double UPDATED_BASE_SALARY = 2D;

    private static final String ENTITY_API_URL = "/api/payrolls";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PayrollRepository payrollRepository;

    @Mock
    private PayrollRepository payrollRepositoryMock;

    @Autowired
    private PayrollMapper payrollMapper;

    @Mock
    private PayrollService payrollServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPayrollMockMvc;

    private Payroll payroll;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payroll createEntity(EntityManager em) {
        Payroll payroll = new Payroll().workedDays(DEFAULT_WORKED_DAYS).baseSalary(DEFAULT_BASE_SALARY);
        // Add required entity
        Income income;
        if (TestUtil.findAll(em, Income.class).isEmpty()) {
            income = IncomeResourceIT.createEntity(em);
            em.persist(income);
            em.flush();
        } else {
            income = TestUtil.findAll(em, Income.class).get(0);
        }
        payroll.setIncome(income);
        // Add required entity
        Deduction deduction;
        if (TestUtil.findAll(em, Deduction.class).isEmpty()) {
            deduction = DeductionResourceIT.createEntity(em);
            em.persist(deduction);
            em.flush();
        } else {
            deduction = TestUtil.findAll(em, Deduction.class).get(0);
        }
        payroll.setDeduction(deduction);
        // Add required entity
        PositionArl positionArl;
        if (TestUtil.findAll(em, PositionArl.class).isEmpty()) {
            positionArl = PositionArlResourceIT.createEntity(em);
            em.persist(positionArl);
            em.flush();
        } else {
            positionArl = TestUtil.findAll(em, PositionArl.class).get(0);
        }
        payroll.setPositionArl(positionArl);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        payroll.setEmployee(employee);
        return payroll;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Payroll createUpdatedEntity(EntityManager em) {
        Payroll payroll = new Payroll().workedDays(UPDATED_WORKED_DAYS).baseSalary(UPDATED_BASE_SALARY);
        // Add required entity
        Income income;
        if (TestUtil.findAll(em, Income.class).isEmpty()) {
            income = IncomeResourceIT.createUpdatedEntity(em);
            em.persist(income);
            em.flush();
        } else {
            income = TestUtil.findAll(em, Income.class).get(0);
        }
        payroll.setIncome(income);
        // Add required entity
        Deduction deduction;
        if (TestUtil.findAll(em, Deduction.class).isEmpty()) {
            deduction = DeductionResourceIT.createUpdatedEntity(em);
            em.persist(deduction);
            em.flush();
        } else {
            deduction = TestUtil.findAll(em, Deduction.class).get(0);
        }
        payroll.setDeduction(deduction);
        // Add required entity
        PositionArl positionArl;
        if (TestUtil.findAll(em, PositionArl.class).isEmpty()) {
            positionArl = PositionArlResourceIT.createUpdatedEntity(em);
            em.persist(positionArl);
            em.flush();
        } else {
            positionArl = TestUtil.findAll(em, PositionArl.class).get(0);
        }
        payroll.setPositionArl(positionArl);
        // Add required entity
        Employee employee;
        if (TestUtil.findAll(em, Employee.class).isEmpty()) {
            employee = EmployeeResourceIT.createUpdatedEntity(em);
            em.persist(employee);
            em.flush();
        } else {
            employee = TestUtil.findAll(em, Employee.class).get(0);
        }
        payroll.setEmployee(employee);
        return payroll;
    }

    @BeforeEach
    public void initTest() {
        payroll = createEntity(em);
    }

    @Test
    @Transactional
    void createPayroll() throws Exception {
        int databaseSizeBeforeCreate = payrollRepository.findAll().size();
        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);
        restPayrollMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payrollDTO)))
            .andExpect(status().isCreated());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeCreate + 1);
        Payroll testPayroll = payrollList.get(payrollList.size() - 1);
        assertThat(testPayroll.getWorkedDays()).isEqualTo(DEFAULT_WORKED_DAYS);
        assertThat(testPayroll.getBaseSalary()).isEqualTo(DEFAULT_BASE_SALARY);
    }

    @Test
    @Transactional
    void createPayrollWithExistingId() throws Exception {
        // Create the Payroll with an existing ID
        payroll.setId(1L);
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        int databaseSizeBeforeCreate = payrollRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPayrollMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payrollDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkWorkedDaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = payrollRepository.findAll().size();
        // set the field null
        payroll.setWorkedDays(null);

        // Create the Payroll, which fails.
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        restPayrollMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payrollDTO)))
            .andExpect(status().isBadRequest());

        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBaseSalaryIsRequired() throws Exception {
        int databaseSizeBeforeTest = payrollRepository.findAll().size();
        // set the field null
        payroll.setBaseSalary(null);

        // Create the Payroll, which fails.
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        restPayrollMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payrollDTO)))
            .andExpect(status().isBadRequest());

        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPayrolls() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        // Get all the payrollList
        restPayrollMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(payroll.getId().intValue())))
            .andExpect(jsonPath("$.[*].workedDays").value(hasItem(DEFAULT_WORKED_DAYS)))
            .andExpect(jsonPath("$.[*].baseSalary").value(hasItem(DEFAULT_BASE_SALARY.doubleValue())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPayrollsWithEagerRelationshipsIsEnabled() throws Exception {
        when(payrollServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPayrollMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(payrollServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPayrollsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(payrollServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPayrollMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(payrollServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getPayroll() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        // Get the payroll
        restPayrollMockMvc
            .perform(get(ENTITY_API_URL_ID, payroll.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(payroll.getId().intValue()))
            .andExpect(jsonPath("$.workedDays").value(DEFAULT_WORKED_DAYS))
            .andExpect(jsonPath("$.baseSalary").value(DEFAULT_BASE_SALARY.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingPayroll() throws Exception {
        // Get the payroll
        restPayrollMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPayroll() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();

        // Update the payroll
        Payroll updatedPayroll = payrollRepository.findById(payroll.getId()).get();
        // Disconnect from session so that the updates on updatedPayroll are not directly saved in db
        em.detach(updatedPayroll);
        updatedPayroll.workedDays(UPDATED_WORKED_DAYS).baseSalary(UPDATED_BASE_SALARY);
        PayrollDTO payrollDTO = payrollMapper.toDto(updatedPayroll);

        restPayrollMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payrollDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isOk());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
        Payroll testPayroll = payrollList.get(payrollList.size() - 1);
        assertThat(testPayroll.getWorkedDays()).isEqualTo(UPDATED_WORKED_DAYS);
        assertThat(testPayroll.getBaseSalary()).isEqualTo(UPDATED_BASE_SALARY);
    }

    @Test
    @Transactional
    void putNonExistingPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(
                put(ENTITY_API_URL_ID, payrollDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(payrollDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePayrollWithPatch() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();

        // Update the payroll using partial update
        Payroll partialUpdatedPayroll = new Payroll();
        partialUpdatedPayroll.setId(payroll.getId());

        partialUpdatedPayroll.baseSalary(UPDATED_BASE_SALARY);

        restPayrollMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayroll.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayroll))
            )
            .andExpect(status().isOk());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
        Payroll testPayroll = payrollList.get(payrollList.size() - 1);
        assertThat(testPayroll.getWorkedDays()).isEqualTo(DEFAULT_WORKED_DAYS);
        assertThat(testPayroll.getBaseSalary()).isEqualTo(UPDATED_BASE_SALARY);
    }

    @Test
    @Transactional
    void fullUpdatePayrollWithPatch() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();

        // Update the payroll using partial update
        Payroll partialUpdatedPayroll = new Payroll();
        partialUpdatedPayroll.setId(payroll.getId());

        partialUpdatedPayroll.workedDays(UPDATED_WORKED_DAYS).baseSalary(UPDATED_BASE_SALARY);

        restPayrollMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPayroll.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPayroll))
            )
            .andExpect(status().isOk());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
        Payroll testPayroll = payrollList.get(payrollList.size() - 1);
        assertThat(testPayroll.getWorkedDays()).isEqualTo(UPDATED_WORKED_DAYS);
        assertThat(testPayroll.getBaseSalary()).isEqualTo(UPDATED_BASE_SALARY);
    }

    @Test
    @Transactional
    void patchNonExistingPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, payrollDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPayroll() throws Exception {
        int databaseSizeBeforeUpdate = payrollRepository.findAll().size();
        payroll.setId(count.incrementAndGet());

        // Create the Payroll
        PayrollDTO payrollDTO = payrollMapper.toDto(payroll);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPayrollMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(payrollDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Payroll in the database
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePayroll() throws Exception {
        // Initialize the database
        payrollRepository.saveAndFlush(payroll);

        int databaseSizeBeforeDelete = payrollRepository.findAll().size();

        // Delete the payroll
        restPayrollMockMvc
            .perform(delete(ENTITY_API_URL_ID, payroll.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Payroll> payrollList = payrollRepository.findAll();
        assertThat(payrollList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
