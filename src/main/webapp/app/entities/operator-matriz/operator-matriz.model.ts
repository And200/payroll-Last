import { IEmployee } from 'app/entities/employee/employee.model';

export interface IOperatorMatriz {
  id?: number;
  name?: string;
  address?: string;
  city?: string;
  email?: string;
  employees?: IEmployee[] | null;
}

export class OperatorMatriz implements IOperatorMatriz {
  constructor(
    public id?: number,
    public name?: string,
    public address?: string,
    public city?: string,
    public email?: string,
    public employees?: IEmployee[] | null
  ) {}
}

export function getOperatorMatrizIdentifier(operatorMatriz: IOperatorMatriz): number | undefined {
  return operatorMatriz.id;
}
