import { IPayroll } from 'app/entities/payroll/payroll.model';
import { IAccountPlan } from 'app/entities/account-plan/account-plan.model';

export interface IIncome {
  id?: number;
  description?: string;
  nocturnalSurchage?: number;
  sundays?: number;
  holidays?: number;
  bonus?: number;
  payrolls?: IPayroll[] | null;
  accountPlan?: IAccountPlan;
}

export class Income implements IIncome {
  constructor(
    public id?: number,
    public description?: string,
    public nocturnalSurchage?: number,
    public sundays?: number,
    public holidays?: number,
    public bonus?: number,
    public payrolls?: IPayroll[] | null,
    public accountPlan?: IAccountPlan
  ) {}
}

export function getIncomeIdentifier(income: IIncome): number | undefined {
  return income.id;
}
