import { IPayroll } from 'app/entities/payroll/payroll.model';

export interface IPositionArl {
  id?: number;
  riskLevel?: number;
  positionCode?: string;
  position?: string;
  payrolls?: IPayroll[] | null;
}

export class PositionArl implements IPositionArl {
  constructor(
    public id?: number,
    public riskLevel?: number,
    public positionCode?: string,
    public position?: string,
    public payrolls?: IPayroll[] | null
  ) {}
}

export function getPositionArlIdentifier(positionArl: IPositionArl): number | undefined {
  return positionArl.id;
}
