import { IEmployee } from 'app/entities/employee/employee.model';

export interface IOperatorType {
  id?: number;
  description?: string;
  employees?: IEmployee[] | null;
}

export class OperatorType implements IOperatorType {
  constructor(public id?: number, public description?: string, public employees?: IEmployee[] | null) {}
}

export function getOperatorTypeIdentifier(operatorType: IOperatorType): number | undefined {
  return operatorType.id;
}
