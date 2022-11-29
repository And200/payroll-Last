import { IProjectMaster } from 'app/entities/project-master/project-master.model';

export interface ICostCenter {
  id?: number;
  costCenterName?: string;
  costCenterType?: string;
  projectMasters?: IProjectMaster[] | null;
}

export class CostCenter implements ICostCenter {
  constructor(
    public id?: number,
    public costCenterName?: string,
    public costCenterType?: string,
    public projectMasters?: IProjectMaster[] | null
  ) {}
}

export function getCostCenterIdentifier(costCenter: ICostCenter): number | undefined {
  return costCenter.id;
}
