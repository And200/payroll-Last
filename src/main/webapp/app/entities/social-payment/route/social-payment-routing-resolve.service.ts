import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ISocialPayment, SocialPayment } from '../social-payment.model';
import { SocialPaymentService } from '../service/social-payment.service';

@Injectable({ providedIn: 'root' })
export class SocialPaymentRoutingResolveService implements Resolve<ISocialPayment> {
  constructor(protected service: SocialPaymentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISocialPayment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((socialPayment: HttpResponse<SocialPayment>) => {
          if (socialPayment.body) {
            return of(socialPayment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SocialPayment());
  }
}
