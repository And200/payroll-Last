package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.AccountPlan} entity.
 */
public class AccountPlanDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountPlanDTO)) {
            return false;
        }

        AccountPlanDTO accountPlanDTO = (AccountPlanDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, accountPlanDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountPlanDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
