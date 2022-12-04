import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PositionArlComponent } from '../list/position-arl.component';
import { PositionArlDetailComponent } from '../detail/position-arl-detail.component';
import { PositionArlUpdateComponent } from '../update/position-arl-update.component';
import { PositionArlRoutingResolveService } from './position-arl-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const positionArlRoute: Routes = [
  {
    path: '',
    component: PositionArlComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PositionArlDetailComponent,
    resolve: {
      positionArl: PositionArlRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PositionArlUpdateComponent,
    resolve: {
      positionArl: PositionArlRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PositionArlUpdateComponent,
    resolve: {
      positionArl: PositionArlRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER, Authority.ASSISTANT],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(positionArlRoute)],
  exports: [RouterModule],
})
export class PositionArlRoutingModule {}
