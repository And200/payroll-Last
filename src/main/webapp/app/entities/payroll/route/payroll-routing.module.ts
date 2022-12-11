import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PayrollComponent } from '../list/payroll.component';
import { PayrollDetailComponent } from '../detail/payroll-detail.component';
import { PayrollUpdateComponent } from '../update/payroll-update.component';
import { PayrollRoutingResolveService } from './payroll-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const payrollRoute: Routes = [
  {
    path: '',
    component: PayrollComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PayrollDetailComponent,
    resolve: {
      payroll: PayrollRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PayrollUpdateComponent,
    resolve: {
      payroll: PayrollRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PayrollUpdateComponent,
    resolve: {
      payroll: PayrollRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(payrollRoute)],
  exports: [RouterModule],
})
export class PayrollRoutingModule {}
