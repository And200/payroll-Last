import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { OperatorTypeComponent } from '../list/operator-type.component';
import { OperatorTypeDetailComponent } from '../detail/operator-type-detail.component';
import { OperatorTypeUpdateComponent } from '../update/operator-type-update.component';
import { OperatorTypeRoutingResolveService } from './operator-type-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const operatorTypeRoute: Routes = [
  {
    path: '',
    component: OperatorTypeComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperatorTypeDetailComponent,
    resolve: {
      operatorType: OperatorTypeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperatorTypeUpdateComponent,
    resolve: {
      operatorType: OperatorTypeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperatorTypeUpdateComponent,
    resolve: {
      operatorType: OperatorTypeRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(operatorTypeRoute)],
  exports: [RouterModule],
})
export class OperatorTypeRoutingModule {}
