package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Payroll.
 */
@Entity
@Table(name = "payroll")
public class Payroll implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "worked_days", nullable = false)
    private Integer workedDays;

    @NotNull
    @Column(name = "base_salary", nullable = false)
    private Double baseSalary;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "payrolls", "accountPlan" }, allowSetters = true)
    private Income income;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "payrolls", "accountPlan" }, allowSetters = true)
    private Deduction deduction;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "payrolls" }, allowSetters = true)
    private PositionArl positionArl;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(
        value = {
            "user",
            "detailEmployeeSocialPayments",
            "projectMasters",
            "payrolls",
            "contract",
            "period",
            "operatorType",
            "socialSecurity",
            "operatorMatriz",
            "documentType",
        },
        allowSetters = true
    )
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Payroll id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkedDays() {
        return this.workedDays;
    }

    public Payroll workedDays(Integer workedDays) {
        this.setWorkedDays(workedDays);
        return this;
    }

    public void setWorkedDays(Integer workedDays) {
        this.workedDays = workedDays;
    }

    public Double getBaseSalary() {
        return this.baseSalary;
    }

    public Payroll baseSalary(Double baseSalary) {
        this.setBaseSalary(baseSalary);
        return this;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Income getIncome() {
        return this.income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Payroll income(Income income) {
        this.setIncome(income);
        return this;
    }

    public Deduction getDeduction() {
        return this.deduction;
    }

    public void setDeduction(Deduction deduction) {
        this.deduction = deduction;
    }

    public Payroll deduction(Deduction deduction) {
        this.setDeduction(deduction);
        return this;
    }

    public PositionArl getPositionArl() {
        return this.positionArl;
    }

    public void setPositionArl(PositionArl positionArl) {
        this.positionArl = positionArl;
    }

    public Payroll positionArl(PositionArl positionArl) {
        this.setPositionArl(positionArl);
        return this;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Payroll employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payroll)) {
            return false;
        }
        return id != null && id.equals(((Payroll) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Payroll{" +
            "id=" + getId() +
            ", workedDays=" + getWorkedDays() +
            ", baseSalary=" + getBaseSalary() +
            "}";
    }
}
