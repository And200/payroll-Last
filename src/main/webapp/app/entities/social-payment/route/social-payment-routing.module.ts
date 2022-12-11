import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { SocialPaymentComponent } from '../list/social-payment.component';
import { SocialPaymentDetailComponent } from '../detail/social-payment-detail.component';
import { SocialPaymentUpdateComponent } from '../update/social-payment-update.component';
import { SocialPaymentRoutingResolveService } from './social-payment-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const socialPaymentRoute: Routes = [
  {
    path: '',
    component: SocialPaymentComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SocialPaymentDetailComponent,
    resolve: {
      socialPayment: SocialPaymentRoutingResolveService,
    },
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SocialPaymentUpdateComponent,
    resolve: {
      socialPayment: SocialPaymentRoutingResolveService,
    },
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SocialPaymentUpdateComponent,
    resolve: {
      socialPayment: SocialPaymentRoutingResolveService,
    },
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(socialPaymentRoute)],
  exports: [RouterModule],
})
export class SocialPaymentRoutingModule {}
