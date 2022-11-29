import { IDetailEmployeeSocialPayment } from 'app/entities/detail-employee-social-payment/detail-employee-social-payment.model';

export interface ISocialPayment {
  id?: number;
  socialPaymentName?: string;
  detailEmployeeSocialPayments?: IDetailEmployeeSocialPayment[] | null;
}

export class SocialPayment implements ISocialPayment {
  constructor(
    public id?: number,
    public socialPaymentName?: string,
    public detailEmployeeSocialPayments?: IDetailEmployeeSocialPayment[] | null
  ) {}
}

export function getSocialPaymentIdentifier(socialPayment: ISocialPayment): number | undefined {
  return socialPayment.id;
}
