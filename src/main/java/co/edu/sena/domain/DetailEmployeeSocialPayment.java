package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A DetailEmployeeSocialPayment.
 */
@Entity
@Table(name = "detail_employee_social_payment")
public class DetailEmployeeSocialPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "detailEmployeeSocialPayments" }, allowSetters = true)
    private SocialPayment socialPayment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DetailEmployeeSocialPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public DetailEmployeeSocialPayment description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DetailEmployeeSocialPayment employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    public SocialPayment getSocialPayment() {
        return this.socialPayment;
    }

    public void setSocialPayment(SocialPayment socialPayment) {
        this.socialPayment = socialPayment;
    }

    public DetailEmployeeSocialPayment socialPayment(SocialPayment socialPayment) {
        this.setSocialPayment(socialPayment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailEmployeeSocialPayment)) {
            return false;
        }
        return id != null && id.equals(((DetailEmployeeSocialPayment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetailEmployeeSocialPayment{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
