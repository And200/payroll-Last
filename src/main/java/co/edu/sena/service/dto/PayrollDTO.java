package co.edu.sena.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link co.edu.sena.domain.Payroll} entity.
 */
public class PayrollDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer workedDays;

    @NotNull
    private Double baseSalary;

    private IncomeDTO income;

    private DeductionDTO deduction;

    private PositionArlDTO positionArl;

    private EmployeeDTO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(Integer workedDays) {
        this.workedDays = workedDays;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public IncomeDTO getIncome() {
        return income;
    }

    public void setIncome(IncomeDTO income) {
        this.income = income;
    }

    public DeductionDTO getDeduction() {
        return deduction;
    }

    public void setDeduction(DeductionDTO deduction) {
        this.deduction = deduction;
    }

    public PositionArlDTO getPositionArl() {
        return positionArl;
    }

    public void setPositionArl(PositionArlDTO positionArl) {
        this.positionArl = positionArl;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayrollDTO)) {
            return false;
        }

        PayrollDTO payrollDTO = (PayrollDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, payrollDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayrollDTO{" +
            "id=" + getId() +
            ", workedDays=" + getWorkedDays() +
            ", baseSalary=" + getBaseSalary() +
            ", income=" + getIncome() +
            ", deduction=" + getDeduction() +
            ", positionArl=" + getPositionArl() +
            ", employee=" + getEmployee() +
            "}";
    }
}
