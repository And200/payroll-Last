import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DetailEmployeeSocialPaymentComponent } from '../list/detail-employee-social-payment.component';
import { DetailEmployeeSocialPaymentDetailComponent } from '../detail/detail-employee-social-payment-detail.component';
import { DetailEmployeeSocialPaymentUpdateComponent } from '../update/detail-employee-social-payment-update.component';
import { DetailEmployeeSocialPaymentRoutingResolveService } from './detail-employee-social-payment-routing-resolve.service';

const detailEmployeeSocialPaymentRoute: Routes = [
  {
    path: '',
    component: DetailEmployeeSocialPaymentComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetailEmployeeSocialPaymentDetailComponent,
    resolve: {
      detailEmployeeSocialPayment: DetailEmployeeSocialPaymentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetailEmployeeSocialPaymentUpdateComponent,
    resolve: {
      detailEmployeeSocialPayment: DetailEmployeeSocialPaymentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetailEmployeeSocialPaymentUpdateComponent,
    resolve: {
      detailEmployeeSocialPayment: DetailEmployeeSocialPaymentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(detailEmployeeSocialPaymentRoute)],
  exports: [RouterModule],
})
export class DetailEmployeeSocialPaymentRoutingModule {}
