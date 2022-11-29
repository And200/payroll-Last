package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Income} entity.
 */
public class IncomeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String description;

    @NotNull
    private Double nocturnalSurchage;

    @NotNull
    private Double sundays;

    @NotNull
    private Double holidays;

    @NotNull
    private Double bonus;

    private AccountPlanDTO accountPlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getNocturnalSurchage() {
        return nocturnalSurchage;
    }

    public void setNocturnalSurchage(Double nocturnalSurchage) {
        this.nocturnalSurchage = nocturnalSurchage;
    }

    public Double getSundays() {
        return sundays;
    }

    public void setSundays(Double sundays) {
        this.sundays = sundays;
    }

    public Double getHolidays() {
        return holidays;
    }

    public void setHolidays(Double holidays) {
        this.holidays = holidays;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public AccountPlanDTO getAccountPlan() {
        return accountPlan;
    }

    public void setAccountPlan(AccountPlanDTO accountPlan) {
        this.accountPlan = accountPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncomeDTO)) {
            return false;
        }

        IncomeDTO incomeDTO = (IncomeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, incomeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncomeDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", nocturnalSurchage=" + getNocturnalSurchage() +
            ", sundays=" + getSundays() +
            ", holidays=" + getHolidays() +
            ", bonus=" + getBonus() +
            ", accountPlan=" + getAccountPlan() +
            "}";
    }
}
