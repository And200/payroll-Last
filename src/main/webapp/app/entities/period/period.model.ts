import { IEmployee } from 'app/entities/employee/employee.model';

export interface IPeriod {
  id?: number;
  description?: string;
  employees?: IEmployee[] | null;
}

export class Period implements IPeriod {
  constructor(public id?: number, public description?: string, public employees?: IEmployee[] | null) {}
}

export function getPeriodIdentifier(period: IPeriod): number | undefined {
  return period.id;
}
