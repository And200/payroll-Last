import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDetailEmployeeSocialPayment, getDetailEmployeeSocialPaymentIdentifier } from '../detail-employee-social-payment.model';

export type EntityResponseType = HttpResponse<IDetailEmployeeSocialPayment>;
export type EntityArrayResponseType = HttpResponse<IDetailEmployeeSocialPayment[]>;

@Injectable({ providedIn: 'root' })
export class DetailEmployeeSocialPaymentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/detail-employee-social-payments');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(detailEmployeeSocialPayment: IDetailEmployeeSocialPayment): Observable<EntityResponseType> {
    return this.http.post<IDetailEmployeeSocialPayment>(this.resourceUrl, detailEmployeeSocialPayment, { observe: 'response' });
  }

  update(detailEmployeeSocialPayment: IDetailEmployeeSocialPayment): Observable<EntityResponseType> {
    return this.http.put<IDetailEmployeeSocialPayment>(
      `${this.resourceUrl}/${getDetailEmployeeSocialPaymentIdentifier(detailEmployeeSocialPayment) as number}`,
      detailEmployeeSocialPayment,
      { observe: 'response' }
    );
  }

  partialUpdate(detailEmployeeSocialPayment: IDetailEmployeeSocialPayment): Observable<EntityResponseType> {
    return this.http.patch<IDetailEmployeeSocialPayment>(
      `${this.resourceUrl}/${getDetailEmployeeSocialPaymentIdentifier(detailEmployeeSocialPayment) as number}`,
      detailEmployeeSocialPayment,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDetailEmployeeSocialPayment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDetailEmployeeSocialPayment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDetailEmployeeSocialPaymentToCollectionIfMissing(
    detailEmployeeSocialPaymentCollection: IDetailEmployeeSocialPayment[],
    ...detailEmployeeSocialPaymentsToCheck: (IDetailEmployeeSocialPayment | null | undefined)[]
  ): IDetailEmployeeSocialPayment[] {
    const detailEmployeeSocialPayments: IDetailEmployeeSocialPayment[] = detailEmployeeSocialPaymentsToCheck.filter(isPresent);
    if (detailEmployeeSocialPayments.length > 0) {
      const detailEmployeeSocialPaymentCollectionIdentifiers = detailEmployeeSocialPaymentCollection.map(
        detailEmployeeSocialPaymentItem => getDetailEmployeeSocialPaymentIdentifier(detailEmployeeSocialPaymentItem)!
      );
      const detailEmployeeSocialPaymentsToAdd = detailEmployeeSocialPayments.filter(detailEmployeeSocialPaymentItem => {
        const detailEmployeeSocialPaymentIdentifier = getDetailEmployeeSocialPaymentIdentifier(detailEmployeeSocialPaymentItem);
        if (
          detailEmployeeSocialPaymentIdentifier == null ||
          detailEmployeeSocialPaymentCollectionIdentifiers.includes(detailEmployeeSocialPaymentIdentifier)
        ) {
          return false;
        }
        detailEmployeeSocialPaymentCollectionIdentifiers.push(detailEmployeeSocialPaymentIdentifier);
        return true;
      });
      return [...detailEmployeeSocialPaymentsToAdd, ...detailEmployeeSocialPaymentCollection];
    }
    return detailEmployeeSocialPaymentCollection;
  }
}
