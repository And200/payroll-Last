package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Period.
 */
@Entity
@Table(name = "period")
public class Period implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @OneToMany(mappedBy = "period")
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
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Period id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Period description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.setPeriod(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setPeriod(this));
        }
        this.employees = employees;
    }

    public Period employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Period addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setPeriod(this);
        return this;
    }

    public Period removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setPeriod(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Period)) {
            return false;
        }
        return id != null && id.equals(((Period) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Period{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
