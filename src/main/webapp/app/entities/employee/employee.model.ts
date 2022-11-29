import dayjs from 'dayjs/esm';
import { IUser } from 'app/entities/user/user.model';
import { IDetailEmployeeSocialPayment } from 'app/entities/detail-employee-social-payment/detail-employee-social-payment.model';
import { IProjectMaster } from 'app/entities/project-master/project-master.model';
import { IPayroll } from 'app/entities/payroll/payroll.model';
import { IContract } from 'app/entities/contract/contract.model';
import { IPeriod } from 'app/entities/period/period.model';
import { IOperatorType } from 'app/entities/operator-type/operator-type.model';
import { ISocialSecurity } from 'app/entities/social-security/social-security.model';
import { IOperatorMatriz } from 'app/entities/operator-matriz/operator-matriz.model';
import { IDocumentType } from 'app/entities/document-type/document-type.model';
import { StateEmployee } from 'app/entities/enumerations/state-employee.model';

export interface IEmployee {
  id?: number;
  completeName?: string;
  documentNumber?: number;
  address?: string;
  dateStart?: dayjs.Dayjs;
  city?: string;
  mobile?: number;
  stateEmployee?: StateEmployee;
  user?: IUser;
  detailEmployeeSocialPayments?: IDetailEmployeeSocialPayment[] | null;
  projectMasters?: IProjectMaster[] | null;
  payrolls?: IPayroll[] | null;
  contract?: IContract;
  period?: IPeriod;
  operatorType?: IOperatorType;
  socialSecurity?: ISocialSecurity;
  operatorMatriz?: IOperatorMatriz;
  documentType?: IDocumentType;
}

export class Employee implements IEmployee {
  constructor(
    public id?: number,
    public completeName?: string,
    public documentNumber?: number,
    public address?: string,
    public dateStart?: dayjs.Dayjs,
    public city?: string,
    public mobile?: number,
    public stateEmployee?: StateEmployee,
    public user?: IUser,
    public detailEmployeeSocialPayments?: IDetailEmployeeSocialPayment[] | null,
    public projectMasters?: IProjectMaster[] | null,
    public payrolls?: IPayroll[] | null,
    public contract?: IContract,
    public period?: IPeriod,
    public operatorType?: IOperatorType,
    public socialSecurity?: ISocialSecurity,
    public operatorMatriz?: IOperatorMatriz,
    public documentType?: IDocumentType
  ) {}
}

export function getEmployeeIdentifier(employee: IEmployee): number | undefined {
  return employee.id;
}
