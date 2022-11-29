package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Deduction} entity.
 */
public class DeductionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String description;

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
        if (!(o instanceof DeductionDTO)) {
            return false;
        }

        DeductionDTO deductionDTO = (DeductionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deductionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeductionDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", accountPlan=" + getAccountPlan() +
            "}";
    }
}
