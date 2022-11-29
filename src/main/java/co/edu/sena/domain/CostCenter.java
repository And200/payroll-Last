package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A CostCenter.
 */
@Entity
@Table(name = "cost_center")
public class CostCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "cost_center_name", length = 100, nullable = false, unique = true)
    private String costCenterName;

    @NotNull
    @Size(max = 100)
    @Column(name = "cost_center_type", length = 100, nullable = false)
    private String costCenterType;

    @OneToMany(mappedBy = "costCenter")
    @JsonIgnoreProperties(value = { "employee", "costCenter" }, allowSetters = true)
    private Set<ProjectMaster> projectMasters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CostCenter id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCostCenterName() {
        return this.costCenterName;
    }

    public CostCenter costCenterName(String costCenterName) {
        this.setCostCenterName(costCenterName);
        return this;
    }

    public void setCostCenterName(String costCenterName) {
        this.costCenterName = costCenterName;
    }

    public String getCostCenterType() {
        return this.costCenterType;
    }

    public CostCenter costCenterType(String costCenterType) {
        this.setCostCenterType(costCenterType);
        return this;
    }

    public void setCostCenterType(String costCenterType) {
        this.costCenterType = costCenterType;
    }

    public Set<ProjectMaster> getProjectMasters() {
        return this.projectMasters;
    }

    public void setProjectMasters(Set<ProjectMaster> projectMasters) {
        if (this.projectMasters != null) {
            this.projectMasters.forEach(i -> i.setCostCenter(null));
        }
        if (projectMasters != null) {
            projectMasters.forEach(i -> i.setCostCenter(this));
        }
        this.projectMasters = projectMasters;
    }

    public CostCenter projectMasters(Set<ProjectMaster> projectMasters) {
        this.setProjectMasters(projectMasters);
        return this;
    }

    public CostCenter addProjectMaster(ProjectMaster projectMaster) {
        this.projectMasters.add(projectMaster);
        projectMaster.setCostCenter(this);
        return this;
    }

    public CostCenter removeProjectMaster(ProjectMaster projectMaster) {
        this.projectMasters.remove(projectMaster);
        projectMaster.setCostCenter(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostCenter)) {
            return false;
        }
        return id != null && id.equals(((CostCenter) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostCenter{" +
            "id=" + getId() +
            ", costCenterName='" + getCostCenterName() + "'" +
            ", costCenterType='" + getCostCenterType() + "'" +
            "}";
    }
}
