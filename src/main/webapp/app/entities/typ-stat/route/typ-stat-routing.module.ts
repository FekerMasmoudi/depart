import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TypStatComponent } from '../list/typ-stat.component';
import { TypStatDetailComponent } from '../detail/typ-stat-detail.component';
import { TypStatUpdateComponent } from '../update/typ-stat-update.component';
import { TypStatRoutingResolveService } from './typ-stat-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const typStatRoute: Routes = [
  {
    path: '',
    component: TypStatComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypStatDetailComponent,
    resolve: {
      typStat: TypStatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypStatUpdateComponent,
    resolve: {
      typStat: TypStatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypStatUpdateComponent,
    resolve: {
      typStat: TypStatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(typStatRoute)],
  exports: [RouterModule],
})
export class TypStatRoutingModule {}
