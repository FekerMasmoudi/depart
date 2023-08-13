import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RotRservComponent } from '../list/rot-rserv.component';
import { RotRservDetailComponent } from '../detail/rot-rserv-detail.component';
import { RotRservUpdateComponent } from '../update/rot-rserv-update.component';
import { RotRservRoutingResolveService } from './rot-rserv-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const rotRservRoute: Routes = [
  {
    path: '',
    component: RotRservComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RotRservDetailComponent,
    resolve: {
      rotRserv: RotRservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RotRservUpdateComponent,
    resolve: {
      rotRserv: RotRservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RotRservUpdateComponent,
    resolve: {
      rotRserv: RotRservRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(rotRservRoute)],
  exports: [RouterModule],
})
export class RotRservRoutingModule {}
