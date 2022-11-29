package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.PositionArl} entity.
 */
public class PositionArlDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer riskLevel;

    @NotNull
    @Size(max = 10)
    private String positionCode;

    @NotNull
    @Size(max = 100)
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PositionArlDTO)) {
            return false;
        }

        PositionArlDTO positionArlDTO = (PositionArlDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, positionArlDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PositionArlDTO{" +
            "id=" + getId() +
            ", riskLevel=" + getRiskLevel() +
            ", positionCode='" + getPositionCode() + "'" +
            ", position='" + getPosition() + "'" +
            "}";
    }
}
