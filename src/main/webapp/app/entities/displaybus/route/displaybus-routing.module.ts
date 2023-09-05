import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DisplaybusComponent } from '../list/displaybus.component';
import { DisplaybusDetailComponent } from '../detail/displaybus-detail.component';
import { DisplaybusUpdateComponent } from '../update/displaybus-update.component';
import { DisplaybusRoutingResolveService } from './displaybus-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const displaybusRoute: Routes = [
  {
    path: '',
    component: DisplaybusComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DisplaybusDetailComponent,
    resolve: {
      displaybus: DisplaybusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DisplaybusUpdateComponent,
    resolve: {
      displaybus: DisplaybusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DisplaybusUpdateComponent,
    resolve: {
      displaybus: DisplaybusRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(displaybusRoute)],
  exports: [RouterModule],
})
export class DisplaybusRoutingModule {}
