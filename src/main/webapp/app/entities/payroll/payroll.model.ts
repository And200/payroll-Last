import { IIncome } from 'app/entities/income/income.model';
import { IDeduction } from 'app/entities/deduction/deduction.model';
import { IPositionArl } from 'app/entities/position-arl/position-arl.model';
import { IEmployee } from 'app/entities/employee/employee.model';

export interface IPayroll {
  id?: number;
  workedDays?: number;
  baseSalary?: number;
  income?: IIncome;
  deduction?: IDeduction;
  positionArl?: IPositionArl;
  employee?: IEmployee;
}

export class Payroll implements IPayroll {
  constructor(
    public id?: number,
    public workedDays?: number,
    public baseSalary?: number,
    public income?: IIncome,
    public deduction?: IDeduction,
    public positionArl?: IPositionArl,
    public employee?: IEmployee
  ) {}
}

export function getPayrollIdentifier(payroll: IPayroll): number | undefined {
  return payroll.id;
}
