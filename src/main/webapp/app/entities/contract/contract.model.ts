import { IEmployee } from 'app/entities/employee/employee.model';

export interface IContract {
  id?: number;
  salary?: number;
  employees?: IEmployee[] | null;
}

export class Contract implements IContract {
  constructor(public id?: number, public salary?: number, public employees?: IEmployee[] | null) {}
}

export function getContractIdentifier(contract: IContract): number | undefined {
  return contract.id;
}
