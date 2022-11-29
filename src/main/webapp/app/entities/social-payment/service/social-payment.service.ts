import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISocialPayment, getSocialPaymentIdentifier } from '../social-payment.model';

export type EntityResponseType = HttpResponse<ISocialPayment>;
export type EntityArrayResponseType = HttpResponse<ISocialPayment[]>;

@Injectable({ providedIn: 'root' })
export class SocialPaymentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/social-payments');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(socialPayment: ISocialPayment): Observable<EntityResponseType> {
    return this.http.post<ISocialPayment>(this.resourceUrl, socialPayment, { observe: 'response' });
  }

  update(socialPayment: ISocialPayment): Observable<EntityResponseType> {
    return this.http.put<ISocialPayment>(`${this.resourceUrl}/${getSocialPaymentIdentifier(socialPayment) as number}`, socialPayment, {
      observe: 'response',
    });
  }

  partialUpdate(socialPayment: ISocialPayment): Observable<EntityResponseType> {
    return this.http.patch<ISocialPayment>(`${this.resourceUrl}/${getSocialPaymentIdentifier(socialPayment) as number}`, socialPayment, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISocialPayment>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISocialPayment[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSocialPaymentToCollectionIfMissing(
    socialPaymentCollection: ISocialPayment[],
    ...socialPaymentsToCheck: (ISocialPayment | null | undefined)[]
  ): ISocialPayment[] {
    const socialPayments: ISocialPayment[] = socialPaymentsToCheck.filter(isPresent);
    if (socialPayments.length > 0) {
      const socialPaymentCollectionIdentifiers = socialPaymentCollection.map(
        socialPaymentItem => getSocialPaymentIdentifier(socialPaymentItem)!
      );
      const socialPaymentsToAdd = socialPayments.filter(socialPaymentItem => {
        const socialPaymentIdentifier = getSocialPaymentIdentifier(socialPaymentItem);
        if (socialPaymentIdentifier == null || socialPaymentCollectionIdentifiers.includes(socialPaymentIdentifier)) {
          return false;
        }
        socialPaymentCollectionIdentifiers.push(socialPaymentIdentifier);
        return true;
      });
      return [...socialPaymentsToAdd, ...socialPaymentCollection];
    }
    return socialPaymentCollection;
  }
}
