package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Income.
 */
@Entity
@Table(name = "income")
public class Income implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @NotNull
    @Column(name = "nocturnal_surchage", nullable = false)
    private Double nocturnalSurchage;

    @NotNull
    @Column(name = "sundays", nullable = false)
    private Double sundays;

    @NotNull
    @Column(name = "holidays", nullable = false)
    private Double holidays;

    @NotNull
    @Column(name = "bonus", nullable = false)
    private Double bonus;

    @OneToMany(mappedBy = "income")
    @JsonIgnoreProperties(value = { "income", "deduction", "positionArl", "employee" }, allowSetters = true)
    private Set<Payroll> payrolls = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "incomes", "deductions" }, allowSetters = true)
    private AccountPlan accountPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Income id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Income description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNocturnalSurchage() {
        return this.nocturnalSurchage;
    }

    public Income nocturnalSurchage(Double nocturnalSurchage) {
        this.setNocturnalSurchage(nocturnalSurchage);
        return this;
    }

    public void setNocturnalSurchage(Double nocturnalSurchage) {
        this.nocturnalSurchage = nocturnalSurchage;
    }

    public Double getSundays() {
        return this.sundays;
    }

    public Income sundays(Double sundays) {
        this.setSundays(sundays);
        return this;
    }

    public void setSundays(Double sundays) {
        this.sundays = sundays;
    }

    public Double getHolidays() {
        return this.holidays;
    }

    public Income holidays(Double holidays) {
        this.setHolidays(holidays);
        return this;
    }

    public void setHolidays(Double holidays) {
        this.holidays = holidays;
    }

    public Double getBonus() {
        return this.bonus;
    }

    public Income bonus(Double bonus) {
        this.setBonus(bonus);
        return this;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Set<Payroll> getPayrolls() {
        return this.payrolls;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
        if (this.payrolls != null) {
            this.payrolls.forEach(i -> i.setIncome(null));
        }
        if (payrolls != null) {
            payrolls.forEach(i -> i.setIncome(this));
        }
        this.payrolls = payrolls;
    }

    public Income payrolls(Set<Payroll> payrolls) {
        this.setPayrolls(payrolls);
        return this;
    }

    public Income addPayroll(Payroll payroll) {
        this.payrolls.add(payroll);
        payroll.setIncome(this);
        return this;
    }

    public Income removePayroll(Payroll payroll) {
        this.payrolls.remove(payroll);
        payroll.setIncome(null);
        return this;
    }

    public AccountPlan getAccountPlan() {
        return this.accountPlan;
    }

    public void setAccountPlan(AccountPlan accountPlan) {
        this.accountPlan = accountPlan;
    }

    public Income accountPlan(AccountPlan accountPlan) {
        this.setAccountPlan(accountPlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Income)) {
            return false;
        }
        return id != null && id.equals(((Income) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Income{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", nocturnalSurchage=" + getNocturnalSurchage() +
            ", sundays=" + getSundays() +
            ", holidays=" + getHolidays() +
            ", bonus=" + getBonus() +
            "}";
    }
}
