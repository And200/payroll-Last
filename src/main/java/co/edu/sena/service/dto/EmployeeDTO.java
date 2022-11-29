package co.edu.sena.service.dto;

import co.edu.sena.domain.enumeration.StateEmployee;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Employee} entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String completeName;

    @NotNull
    private Integer documentNumber;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    private ZonedDateTime dateStart;

    @NotNull
    @Size(max = 50)
    private String city;

    @NotNull
    private Integer mobile;

    @NotNull
    private StateEmployee stateEmployee;

    private UserDTO user;

    private ContractDTO contract;

    private PeriodDTO period;

    private OperatorTypeDTO operatorType;

    private SocialSecurityDTO socialSecurity;

    private OperatorMatrizDTO operatorMatriz;

    private DocumentTypeDTO documentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(ZonedDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public StateEmployee getStateEmployee() {
        return stateEmployee;
    }

    public void setStateEmployee(StateEmployee stateEmployee) {
        this.stateEmployee = stateEmployee;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public PeriodDTO getPeriod() {
        return period;
    }

    public void setPeriod(PeriodDTO period) {
        this.period = period;
    }

    public OperatorTypeDTO getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(OperatorTypeDTO operatorType) {
        this.operatorType = operatorType;
    }

    public SocialSecurityDTO getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(SocialSecurityDTO socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public OperatorMatrizDTO getOperatorMatriz() {
        return operatorMatriz;
    }

    public void setOperatorMatriz(OperatorMatrizDTO operatorMatriz) {
        this.operatorMatriz = operatorMatriz;
    }

    public DocumentTypeDTO getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypeDTO documentType) {
        this.documentType = documentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeDTO)) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "id=" + getId() +
            ", completeName='" + getCompleteName() + "'" +
            ", documentNumber=" + getDocumentNumber() +
            ", address='" + getAddress() + "'" +
            ", dateStart='" + getDateStart() + "'" +
            ", city='" + getCity() + "'" +
            ", mobile=" + getMobile() +
            ", stateEmployee='" + getStateEmployee() + "'" +
            ", user=" + getUser() +
            ", contract=" + getContract() +
            ", period=" + getPeriod() +
            ", operatorType=" + getOperatorType() +
            ", socialSecurity=" + getSocialSecurity() +
            ", operatorMatriz=" + getOperatorMatriz() +
            ", documentType=" + getDocumentType() +
            "}";
    }
}
