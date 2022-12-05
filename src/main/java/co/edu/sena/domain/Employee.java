package co.edu.sena.domain;

import co.edu.sena.domain.enumeration.StateEmployee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "complete_name", length = 100, nullable = false, unique = true)
    private String completeName;

    @NotNull
    @Column(name = "document_number", nullable = false, unique = true)
    private Integer documentNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @NotNull
    @Column(name = "date_start", nullable = false)
    private ZonedDateTime dateStart;

    @NotNull
    @Size(max = 50)
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @NotNull
    @Size(max = 50)
    @Column(name = "mobile", nullable = false, length = 50)
    private String mobile;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "state_employee", nullable = false)
    private StateEmployee stateEmployee;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "employee", "socialPayment" }, allowSetters = true)
    private Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "employee", "costCenter" }, allowSetters = true)
    private Set<ProjectMaster> projectMasters = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties(value = { "income", "deduction", "positionArl", "employee" }, allowSetters = true)
    private Set<Payroll> payrolls = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private Contract contract;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private Period period;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private OperatorType operatorType;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private SocialSecurity socialSecurity;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private OperatorMatriz operatorMatriz;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "employees" }, allowSetters = true)
    private DocumentType documentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompleteName() {
        return this.completeName;
    }

    public Employee completeName(String completeName) {
        this.setCompleteName(completeName);
        return this;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public Integer getDocumentNumber() {
        return this.documentNumber;
    }

    public Employee documentNumber(Integer documentNumber) {
        this.setDocumentNumber(documentNumber);
        return this;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public Employee address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getDateStart() {
        return this.dateStart;
    }

    public Employee dateStart(ZonedDateTime dateStart) {
        this.setDateStart(dateStart);
        return this;
    }

    public void setDateStart(ZonedDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public String getCity() {
        return this.city;
    }

    public Employee city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return this.mobile;
    }

    public Employee mobile(String mobile) {
        this.setMobile(mobile);
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public StateEmployee getStateEmployee() {
        return this.stateEmployee;
    }

    public Employee stateEmployee(StateEmployee stateEmployee) {
        this.setStateEmployee(stateEmployee);
        return this;
    }

    public void setStateEmployee(StateEmployee stateEmployee) {
        this.stateEmployee = stateEmployee;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Employee user(User user) {
        this.setUser(user);
        return this;
    }

    public Set<DetailEmployeeSocialPayment> getDetailEmployeeSocialPayments() {
        return this.detailEmployeeSocialPayments;
    }

    public void setDetailEmployeeSocialPayments(Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments) {
        if (this.detailEmployeeSocialPayments != null) {
            this.detailEmployeeSocialPayments.forEach(i -> i.setEmployee(null));
        }
        if (detailEmployeeSocialPayments != null) {
            detailEmployeeSocialPayments.forEach(i -> i.setEmployee(this));
        }
        this.detailEmployeeSocialPayments = detailEmployeeSocialPayments;
    }

    public Employee detailEmployeeSocialPayments(Set<DetailEmployeeSocialPayment> detailEmployeeSocialPayments) {
        this.setDetailEmployeeSocialPayments(detailEmployeeSocialPayments);
        return this;
    }

    public Employee addDetailEmployeeSocialPayment(DetailEmployeeSocialPayment detailEmployeeSocialPayment) {
        this.detailEmployeeSocialPayments.add(detailEmployeeSocialPayment);
        detailEmployeeSocialPayment.setEmployee(this);
        return this;
    }

    public Employee removeDetailEmployeeSocialPayment(DetailEmployeeSocialPayment detailEmployeeSocialPayment) {
        this.detailEmployeeSocialPayments.remove(detailEmployeeSocialPayment);
        detailEmployeeSocialPayment.setEmployee(null);
        return this;
    }

    public Set<ProjectMaster> getProjectMasters() {
        return this.projectMasters;
    }

    public void setProjectMasters(Set<ProjectMaster> projectMasters) {
        if (this.projectMasters != null) {
            this.projectMasters.forEach(i -> i.setEmployee(null));
        }
        if (projectMasters != null) {
            projectMasters.forEach(i -> i.setEmployee(this));
        }
        this.projectMasters = projectMasters;
    }

    public Employee projectMasters(Set<ProjectMaster> projectMasters) {
        this.setProjectMasters(projectMasters);
        return this;
    }

    public Employee addProjectMaster(ProjectMaster projectMaster) {
        this.projectMasters.add(projectMaster);
        projectMaster.setEmployee(this);
        return this;
    }

    public Employee removeProjectMaster(ProjectMaster projectMaster) {
        this.projectMasters.remove(projectMaster);
        projectMaster.setEmployee(null);
        return this;
    }

    public Set<Payroll> getPayrolls() {
        return this.payrolls;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
        if (this.payrolls != null) {
            this.payrolls.forEach(i -> i.setEmployee(null));
        }
        if (payrolls != null) {
            payrolls.forEach(i -> i.setEmployee(this));
        }
        this.payrolls = payrolls;
    }

    public Employee payrolls(Set<Payroll> payrolls) {
        this.setPayrolls(payrolls);
        return this;
    }

    public Employee addPayroll(Payroll payroll) {
        this.payrolls.add(payroll);
        payroll.setEmployee(this);
        return this;
    }

    public Employee removePayroll(Payroll payroll) {
        this.payrolls.remove(payroll);
        payroll.setEmployee(null);
        return this;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Employee contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public Period getPeriod() {
        return this.period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Employee period(Period period) {
        this.setPeriod(period);
        return this;
    }

    public OperatorType getOperatorType() {
        return this.operatorType;
    }

    public void setOperatorType(OperatorType operatorType) {
        this.operatorType = operatorType;
    }

    public Employee operatorType(OperatorType operatorType) {
        this.setOperatorType(operatorType);
        return this;
    }

    public SocialSecurity getSocialSecurity() {
        return this.socialSecurity;
    }

    public void setSocialSecurity(SocialSecurity socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Employee socialSecurity(SocialSecurity socialSecurity) {
        this.setSocialSecurity(socialSecurity);
        return this;
    }

    public OperatorMatriz getOperatorMatriz() {
        return this.operatorMatriz;
    }

    public void setOperatorMatriz(OperatorMatriz operatorMatriz) {
        this.operatorMatriz = operatorMatriz;
    }

    public Employee operatorMatriz(OperatorMatriz operatorMatriz) {
        this.setOperatorMatriz(operatorMatriz);
        return this;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Employee documentType(DocumentType documentType) {
        this.setDocumentType(documentType);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", completeName='" + getCompleteName() + "'" +
            ", documentNumber=" + getDocumentNumber() +
            ", address='" + getAddress() + "'" +
            ", dateStart='" + getDateStart() + "'" +
            ", city='" + getCity() + "'" +
            ", mobile=" + getMobile() +
            ", stateEmployee='" + getStateEmployee() + "'" +
            "}";
    }
}
