package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.ProjectMaster} entity.
 */
public class ProjectMasterDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String projectMasterName;

    @NotNull
    @Size(max = 100)
    private String projectDirectorName;

    @NotNull
    @Size(max = 100)
    private String phone;

    private EmployeeDTO employee;

    private CostCenterDTO costCenter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectMasterName() {
        return projectMasterName;
    }

    public void setProjectMasterName(String projectMasterName) {
        this.projectMasterName = projectMasterName;
    }

    public String getProjectDirectorName() {
        return projectDirectorName;
    }

    public void setProjectDirectorName(String projectDirectorName) {
        this.projectDirectorName = projectDirectorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public CostCenterDTO getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(CostCenterDTO costCenter) {
        this.costCenter = costCenter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectMasterDTO)) {
            return false;
        }

        ProjectMasterDTO projectMasterDTO = (ProjectMasterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectMasterDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectMasterDTO{" +
            "id=" + getId() +
            ", projectMasterName='" + getProjectMasterName() + "'" +
            ", projectDirectorName='" + getProjectDirectorName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", employee=" + getEmployee() +
            ", costCenter=" + getCostCenter() +
            "}";
    }
}
