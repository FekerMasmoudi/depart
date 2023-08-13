import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TraficComponent } from '../list/trafic.component';
import { TraficDetailComponent } from '../detail/trafic-detail.component';
import { TraficUpdateComponent } from '../update/trafic-update.component';
import { TraficRoutingResolveService } from './trafic-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const traficRoute: Routes = [
  {
    path: '',
    component: TraficComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TraficDetailComponent,
    resolve: {
      trafic: TraficRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TraficUpdateComponent,
    resolve: {
      trafic: TraficRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TraficUpdateComponent,
    resolve: {
      trafic: TraficRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(traficRoute)],
  exports: [RouterModule],
})
export class TraficRoutingModule {}
