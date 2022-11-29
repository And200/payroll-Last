package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.DetailEmployeeSocialPayment} entity.
 */
public class DetailEmployeeSocialPaymentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String description;

    private EmployeeDTO employee;

    private SocialPaymentDTO socialPayment;

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

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public SocialPaymentDTO getSocialPayment() {
        return socialPayment;
    }

    public void setSocialPayment(SocialPaymentDTO socialPayment) {
        this.socialPayment = socialPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailEmployeeSocialPaymentDTO)) {
            return false;
        }

        DetailEmployeeSocialPaymentDTO detailEmployeeSocialPaymentDTO = (DetailEmployeeSocialPaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, detailEmployeeSocialPaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailEmployeeSocialPaymentDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", employee=" + getEmployee() +
            ", socialPayment=" + getSocialPayment() +
            "}";
    }
}
