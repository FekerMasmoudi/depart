import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MotifchservComponent } from '../list/motifchserv.component';
import { MotifchservDetailComponent } from '../detail/motifchserv-detail.component';
import { MotifchservUpdateComponent } from '../update/motifchserv-update.component';
import { MotifchservRoutingResolveService } from './motifchserv-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const motifchservRoute: Routes = [
  {
    path: '',
    component: MotifchservComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MotifchservDetailComponent,
    resolve: {
      motifchserv: MotifchservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MotifchservUpdateComponent,
    resolve: {
      motifchserv: MotifchservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MotifchservUpdateComponent,
    resolve: {
      motifchserv: MotifchservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(motifchservRoute)],
  exports: [RouterModule],
})
export class MotifchservRoutingModule {}
