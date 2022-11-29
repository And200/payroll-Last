package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Period} entity.
 */
public class PeriodDTO implements Serializable {

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
        if (!(o instanceof PeriodDTO)) {
            return false;
        }

        PeriodDTO periodDTO = (PeriodDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, periodDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PeriodDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
