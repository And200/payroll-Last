import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { OperatorMatrizComponent } from '../list/operator-matriz.component';
import { OperatorMatrizDetailComponent } from '../detail/operator-matriz-detail.component';
import { OperatorMatrizUpdateComponent } from '../update/operator-matriz-update.component';
import { OperatorMatrizRoutingResolveService } from './operator-matriz-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const operatorMatrizRoute: Routes = [
  {
    path: '',
    component: OperatorMatrizComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OperatorMatrizDetailComponent,
    resolve: {
      operatorMatriz: OperatorMatrizRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OperatorMatrizUpdateComponent,
    resolve: {
      operatorMatriz: OperatorMatrizRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OperatorMatrizUpdateComponent,
    resolve: {
      operatorMatriz: OperatorMatrizRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(operatorMatrizRoute)],
  exports: [RouterModule],
})
export class OperatorMatrizRoutingModule {}
