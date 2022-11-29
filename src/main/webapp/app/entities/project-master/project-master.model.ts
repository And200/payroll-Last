import { IEmployee } from 'app/entities/employee/employee.model';
import { ICostCenter } from 'app/entities/cost-center/cost-center.model';

export interface IProjectMaster {
  id?: number;
  projectMasterName?: string;
  projectDirectorName?: string;
  phone?: string;
  employee?: IEmployee;
  costCenter?: ICostCenter;
}

export class ProjectMaster implements IProjectMaster {
  constructor(
    public id?: number,
    public projectMasterName?: string,
    public projectDirectorName?: string,
    public phone?: string,
    public employee?: IEmployee,
    public costCenter?: ICostCenter
  ) {}
}

export function getProjectMasterIdentifier(projectMaster: IProjectMaster): number | undefined {
  return projectMaster.id;
}
