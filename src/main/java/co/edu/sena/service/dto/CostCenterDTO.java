package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.CostCenter} entity.
 */
public class CostCenterDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String costCenterName;

    @NotNull
    @Size(max = 100)
    private String costCenterType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostCenterName() {
        return costCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCostCenterType() {
        return costCenterType;
    }

    public void setCostCenterType(String costCenterType) {
        this.costCenterType = costCenterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostCenterDTO)) {
            return false;
        }

        CostCenterDTO costCenterDTO = (CostCenterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, costCenterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostCenterDTO{" +
            "id=" + getId() +
            ", costCenterName='" + getCostCenterName() + "'" +
            ", costCenterType='" + getCostCenterType() + "'" +
            "}";
    }
}
