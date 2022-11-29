import { IPayroll } from 'app/entities/payroll/payroll.model';
import { IAccountPlan } from 'app/entities/account-plan/account-plan.model';

export interface IDeduction {
  id?: number;
  description?: string;
  payrolls?: IPayroll[] | null;
  accountPlan?: IAccountPlan;
}

export class Deduction implements IDeduction {
  constructor(public id?: number, public description?: string, public payrolls?: IPayroll[] | null, public accountPlan?: IAccountPlan) {}
}

export function getDeductionIdentifier(deduction: IDeduction): number | undefined {
  return deduction.id;
}
