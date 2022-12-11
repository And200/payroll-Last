import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { IncomeComponent } from '../list/income.component';
import { IncomeDetailComponent } from '../detail/income-detail.component';
import { IncomeUpdateComponent } from '../update/income-update.component';
import { IncomeRoutingResolveService } from './income-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const incomeRoute: Routes = [
  {
    path: '',
    component: IncomeComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IncomeDetailComponent,
    resolve: {
      income: IncomeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IncomeUpdateComponent,
    resolve: {
      income: IncomeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IncomeUpdateComponent,
    resolve: {
      income: IncomeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(incomeRoute)],
  exports: [RouterModule],
})
export class IncomeRoutingModule {}
