package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Contract} entity.
 */
public class ContractDTO implements Serializable {

    private Long id;

    @NotNull
    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractDTO)) {
            return false;
        }

        ContractDTO contractDTO = (ContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractDTO{" +
            "id=" + getId() +
            ", salary=" + getSalary() +
            "}";
    }
}
