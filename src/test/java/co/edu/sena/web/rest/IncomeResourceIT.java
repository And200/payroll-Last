package co.edu.sena.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.IntegrationTest;
import co.edu.sena.domain.AccountPlan;
import co.edu.sena.domain.Income;
import co.edu.sena.repository.IncomeRepository;
import co.edu.sena.service.dto.IncomeDTO;
import co.edu.sena.service.mapper.IncomeMapper;
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
 * Integration tests for the {@link IncomeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IncomeResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_NOCTURNAL_SURCHAGE = 1D;
    private static final Double UPDATED_NOCTURNAL_SURCHAGE = 2D;

    private static final Double DEFAULT_SUNDAYS = 1D;
    private static final Double UPDATED_SUNDAYS = 2D;

    private static final Double DEFAULT_HOLIDAYS = 1D;
    private static final Double UPDATED_HOLIDAYS = 2D;

    private static final Double DEFAULT_BONUS = 1D;
    private static final Double UPDATED_BONUS = 2D;

    private static final String ENTITY_API_URL = "/api/incomes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeMapper incomeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIncomeMockMvc;

    private Income income;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Income createEntity(EntityManager em) {
        Income income = new Income()
            .description(DEFAULT_DESCRIPTION)
            .nocturnalSurchage(DEFAULT_NOCTURNAL_SURCHAGE)
            .sundays(DEFAULT_SUNDAYS)
            .holidays(DEFAULT_HOLIDAYS)
            .bonus(DEFAULT_BONUS);
        // Add required entity
        AccountPlan accountPlan;
        if (TestUtil.findAll(em, AccountPlan.class).isEmpty()) {
            accountPlan = AccountPlanResourceIT.createEntity(em);
            em.persist(accountPlan);
            em.flush();
        } else {
            accountPlan = TestUtil.findAll(em, AccountPlan.class).get(0);
        }
        income.setAccountPlan(accountPlan);
        return income;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Income createUpdatedEntity(EntityManager em) {
        Income income = new Income()
            .description(UPDATED_DESCRIPTION)
            .nocturnalSurchage(UPDATED_NOCTURNAL_SURCHAGE)
            .sundays(UPDATED_SUNDAYS)
            .holidays(UPDATED_HOLIDAYS)
            .bonus(UPDATED_BONUS);
        // Add required entity
        AccountPlan accountPlan;
        if (TestUtil.findAll(em, AccountPlan.class).isEmpty()) {
            accountPlan = AccountPlanResourceIT.createUpdatedEntity(em);
            em.persist(accountPlan);
            em.flush();
        } else {
            accountPlan = TestUtil.findAll(em, AccountPlan.class).get(0);
        }
        income.setAccountPlan(accountPlan);
        return income;
    }

    @BeforeEach
    public void initTest() {
        income = createEntity(em);
    }

    @Test
    @Transactional
    void createIncome() throws Exception {
        int databaseSizeBeforeCreate = incomeRepository.findAll().size();
        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);
        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isCreated());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeCreate + 1);
        Income testIncome = incomeList.get(incomeList.size() - 1);
        assertThat(testIncome.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIncome.getNocturnalSurchage()).isEqualTo(DEFAULT_NOCTURNAL_SURCHAGE);
        assertThat(testIncome.getSundays()).isEqualTo(DEFAULT_SUNDAYS);
        assertThat(testIncome.getHolidays()).isEqualTo(DEFAULT_HOLIDAYS);
        assertThat(testIncome.getBonus()).isEqualTo(DEFAULT_BONUS);
    }

    @Test
    @Transactional
    void createIncomeWithExistingId() throws Exception {
        // Create the Income with an existing ID
        income.setId(1L);
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        int databaseSizeBeforeCreate = incomeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeRepository.findAll().size();
        // set the field null
        income.setDescription(null);

        // Create the Income, which fails.
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNocturnalSurchageIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeRepository.findAll().size();
        // set the field null
        income.setNocturnalSurchage(null);

        // Create the Income, which fails.
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSundaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeRepository.findAll().size();
        // set the field null
        income.setSundays(null);

        // Create the Income, which fails.
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkHolidaysIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeRepository.findAll().size();
        // set the field null
        income.setHolidays(null);

        // Create the Income, which fails.
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBonusIsRequired() throws Exception {
        int databaseSizeBeforeTest = incomeRepository.findAll().size();
        // set the field null
        income.setBonus(null);

        // Create the Income, which fails.
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        restIncomeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isBadRequest());

        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllIncomes() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        // Get all the incomeList
        restIncomeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(income.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].nocturnalSurchage").value(hasItem(DEFAULT_NOCTURNAL_SURCHAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].sundays").value(hasItem(DEFAULT_SUNDAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].holidays").value(hasItem(DEFAULT_HOLIDAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].bonus").value(hasItem(DEFAULT_BONUS.doubleValue())));
    }

    @Test
    @Transactional
    void getIncome() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        // Get the income
        restIncomeMockMvc
            .perform(get(ENTITY_API_URL_ID, income.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(income.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.nocturnalSurchage").value(DEFAULT_NOCTURNAL_SURCHAGE.doubleValue()))
            .andExpect(jsonPath("$.sundays").value(DEFAULT_SUNDAYS.doubleValue()))
            .andExpect(jsonPath("$.holidays").value(DEFAULT_HOLIDAYS.doubleValue()))
            .andExpect(jsonPath("$.bonus").value(DEFAULT_BONUS.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingIncome() throws Exception {
        // Get the income
        restIncomeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewIncome() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();

        // Update the income
        Income updatedIncome = incomeRepository.findById(income.getId()).get();
        // Disconnect from session so that the updates on updatedIncome are not directly saved in db
        em.detach(updatedIncome);
        updatedIncome
            .description(UPDATED_DESCRIPTION)
            .nocturnalSurchage(UPDATED_NOCTURNAL_SURCHAGE)
            .sundays(UPDATED_SUNDAYS)
            .holidays(UPDATED_HOLIDAYS)
            .bonus(UPDATED_BONUS);
        IncomeDTO incomeDTO = incomeMapper.toDto(updatedIncome);

        restIncomeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incomeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isOk());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
        Income testIncome = incomeList.get(incomeList.size() - 1);
        assertThat(testIncome.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIncome.getNocturnalSurchage()).isEqualTo(UPDATED_NOCTURNAL_SURCHAGE);
        assertThat(testIncome.getSundays()).isEqualTo(UPDATED_SUNDAYS);
        assertThat(testIncome.getHolidays()).isEqualTo(UPDATED_HOLIDAYS);
        assertThat(testIncome.getBonus()).isEqualTo(UPDATED_BONUS);
    }

    @Test
    @Transactional
    void putNonExistingIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incomeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incomeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIncomeWithPatch() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();

        // Update the income using partial update
        Income partialUpdatedIncome = new Income();
        partialUpdatedIncome.setId(income.getId());

        partialUpdatedIncome.nocturnalSurchage(UPDATED_NOCTURNAL_SURCHAGE).sundays(UPDATED_SUNDAYS);

        restIncomeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncome.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncome))
            )
            .andExpect(status().isOk());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
        Income testIncome = incomeList.get(incomeList.size() - 1);
        assertThat(testIncome.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIncome.getNocturnalSurchage()).isEqualTo(UPDATED_NOCTURNAL_SURCHAGE);
        assertThat(testIncome.getSundays()).isEqualTo(UPDATED_SUNDAYS);
        assertThat(testIncome.getHolidays()).isEqualTo(DEFAULT_HOLIDAYS);
        assertThat(testIncome.getBonus()).isEqualTo(DEFAULT_BONUS);
    }

    @Test
    @Transactional
    void fullUpdateIncomeWithPatch() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();

        // Update the income using partial update
        Income partialUpdatedIncome = new Income();
        partialUpdatedIncome.setId(income.getId());

        partialUpdatedIncome
            .description(UPDATED_DESCRIPTION)
            .nocturnalSurchage(UPDATED_NOCTURNAL_SURCHAGE)
            .sundays(UPDATED_SUNDAYS)
            .holidays(UPDATED_HOLIDAYS)
            .bonus(UPDATED_BONUS);

        restIncomeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncome.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncome))
            )
            .andExpect(status().isOk());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
        Income testIncome = incomeList.get(incomeList.size() - 1);
        assertThat(testIncome.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIncome.getNocturnalSurchage()).isEqualTo(UPDATED_NOCTURNAL_SURCHAGE);
        assertThat(testIncome.getSundays()).isEqualTo(UPDATED_SUNDAYS);
        assertThat(testIncome.getHolidays()).isEqualTo(UPDATED_HOLIDAYS);
        assertThat(testIncome.getBonus()).isEqualTo(UPDATED_BONUS);
    }

    @Test
    @Transactional
    void patchNonExistingIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, incomeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIncome() throws Exception {
        int databaseSizeBeforeUpdate = incomeRepository.findAll().size();
        income.setId(count.incrementAndGet());

        // Create the Income
        IncomeDTO incomeDTO = incomeMapper.toDto(income);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncomeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(incomeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Income in the database
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIncome() throws Exception {
        // Initialize the database
        incomeRepository.saveAndFlush(income);

        int databaseSizeBeforeDelete = incomeRepository.findAll().size();

        // Delete the income
        restIncomeMockMvc
            .perform(delete(ENTITY_API_URL_ID, income.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Income> incomeList = incomeRepository.findAll();
        assertThat(incomeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
