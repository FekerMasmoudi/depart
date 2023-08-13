import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CentVehicComponent } from '../list/cent-vehic.component';
import { CentVehicDetailComponent } from '../detail/cent-vehic-detail.component';
import { CentVehicUpdateComponent } from '../update/cent-vehic-update.component';
import { CentVehicRoutingResolveService } from './cent-vehic-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const centVehicRoute: Routes = [
  {
    path: '',
    component: CentVehicComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CentVehicDetailComponent,
    resolve: {
      centVehic: CentVehicRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CentVehicUpdateComponent,
    resolve: {
      centVehic: CentVehicRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CentVehicUpdateComponent,
    resolve: {
      centVehic: CentVehicRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(centVehicRoute)],
  exports: [RouterModule],
})
export class CentVehicRoutingModule {}
