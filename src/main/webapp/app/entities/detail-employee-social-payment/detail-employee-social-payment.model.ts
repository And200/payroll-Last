import { IEmployee } from 'app/entities/employee/employee.model';
import { ISocialPayment } from 'app/entities/social-payment/social-payment.model';

export interface IDetailEmployeeSocialPayment {
  id?: number;
  description?: string;
  employee?: IEmployee;
  socialPayment?: ISocialPayment;
}

export class DetailEmployeeSocialPayment implements IDetailEmployeeSocialPayment {
  constructor(public id?: number, public description?: string, public employee?: IEmployee, public socialPayment?: ISocialPayment) {}
}

export function getDetailEmployeeSocialPaymentIdentifier(detailEmployeeSocialPayment: IDetailEmployeeSocialPayment): number | undefined {
  return detailEmployeeSocialPayment.id;
}
