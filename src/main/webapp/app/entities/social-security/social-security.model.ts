import { IEmployee } from 'app/entities/employee/employee.model';

export interface ISocialSecurity {
  id?: number;
  eps?: string;
  afp?: string;
  employees?: IEmployee[] | null;
}

export class SocialSecurity implements ISocialSecurity {
  constructor(public id?: number, public eps?: string, public afp?: string, public employees?: IEmployee[] | null) {}
}

export function getSocialSecurityIdentifier(socialSecurity: ISocialSecurity): number | undefined {
  return socialSecurity.id;
}
