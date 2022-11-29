import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDetailEmployeeSocialPayment, DetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';
import { DetailEmployeeSocialPaymentService } from '../service/detail-employee-social-payment.service';

@Injectable({ providedIn: 'root' })
export class DetailEmployeeSocialPaymentRoutingResolveService implements Resolve<IDetailEmployeeSocialPayment> {
  constructor(protected service: DetailEmployeeSocialPaymentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetailEmployeeSocialPayment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((detailEmployeeSocialPayment: HttpResponse<DetailEmployeeSocialPayment>) => {
          if (detailEmployeeSocialPayment.body) {
            return of(detailEmployeeSocialPayment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetailEmployeeSocialPayment());
  }
}
