import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ProjectMasterComponent } from '../list/project-master.component';
import { ProjectMasterDetailComponent } from '../detail/project-master-detail.component';
import { ProjectMasterUpdateComponent } from '../update/project-master-update.component';
import { ProjectMasterRoutingResolveService } from './project-master-routing-resolve.service';
import { Authority } from '../../../config/authority.constants';

const projectMasterRoute: Routes = [
  {
    path: '',
    component: ProjectMasterComponent,
    data: {
      defaultSort: 'id,asc',
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProjectMasterDetailComponent,
    resolve: {
      projectMaster: ProjectMasterRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProjectMasterUpdateComponent,
    resolve: {
      projectMaster: ProjectMasterRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProjectMasterUpdateComponent,
    resolve: {
      projectMaster: ProjectMasterRoutingResolveService,
    },
    data: {
      authorities: [Authority.ADMIN, Authority.MANAGER],
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(projectMasterRoute)],
  exports: [RouterModule],
})
export class ProjectMasterRoutingModule {}
