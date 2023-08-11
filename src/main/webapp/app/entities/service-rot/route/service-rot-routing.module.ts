import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ServiceRotComponent } from '../list/service-rot.component';
import { ServiceRotDetailComponent } from '../detail/service-rot-detail.component';
import { ServiceRotUpdateComponent } from '../update/service-rot-update.component';
import { ServiceRotRoutingResolveService } from './service-rot-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const serviceRotRoute: Routes = [
  {
    path: '',
    component: ServiceRotComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServiceRotDetailComponent,
    resolve: {
      serviceRot: ServiceRotRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServiceRotUpdateComponent,
    resolve: {
      serviceRot: ServiceRotRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServiceRotUpdateComponent,
    resolve: {
      serviceRot: ServiceRotRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(serviceRotRoute)],
  exports: [RouterModule],
})
export class ServiceRotRoutingModule {}
