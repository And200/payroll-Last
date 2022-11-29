package co.edu.sena.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PositionArl.
 */
@Entity
@Table(name = "position_arl")
public class PositionArl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "risk_level", nullable = false)
    private Integer riskLevel;

    @NotNull
    @Size(max = 10)
    @Column(name = "position_code", length = 10, nullable = false, unique = true)
    private String positionCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "position", length = 100, nullable = false)
    private String position;

    @OneToMany(mappedBy = "positionArl")
    @JsonIgnoreProperties(value = { "income", "deduction", "positionArl", "employee" }, allowSetters = true)
    private Set<Payroll> payrolls = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PositionArl id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRiskLevel() {
        return this.riskLevel;
    }

    public PositionArl riskLevel(Integer riskLevel) {
        this.setRiskLevel(riskLevel);
        return this;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getPositionCode() {
        return this.positionCode;
    }

    public PositionArl positionCode(String positionCode) {
        this.setPositionCode(positionCode);
        return this;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPosition() {
        return this.position;
    }

    public PositionArl position(String position) {
        this.setPosition(position);
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<Payroll> getPayrolls() {
        return this.payrolls;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
        if (this.payrolls != null) {
            this.payrolls.forEach(i -> i.setPositionArl(null));
        }
        if (payrolls != null) {
            payrolls.forEach(i -> i.setPositionArl(this));
        }
        this.payrolls = payrolls;
    }

    public PositionArl payrolls(Set<Payroll> payrolls) {
        this.setPayrolls(payrolls);
        return this;
    }

    public PositionArl addPayroll(Payroll payroll) {
        this.payrolls.add(payroll);
        payroll.setPositionArl(this);
        return this;
    }

    public PositionArl removePayroll(Payroll payroll) {
        this.payrolls.remove(payroll);
        payroll.setPositionArl(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PositionArl)) {
            return false;
        }
        return id != null && id.equals(((PositionArl) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PositionArl{" +
            "id=" + getId() +
            ", riskLevel=" + getRiskLevel() +
            ", positionCode='" + getPositionCode() + "'" +
            ", position='" + getPosition() + "'" +
            "}";
    }
}
