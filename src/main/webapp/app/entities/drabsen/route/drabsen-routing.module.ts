import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DrabsenComponent } from '../list/drabsen.component';
import { DrabsenDetailComponent } from '../detail/drabsen-detail.component';
import { DrabsenUpdateComponent } from '../update/drabsen-update.component';
import { DrabsenRoutingResolveService } from './drabsen-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const drabsenRoute: Routes = [
  {
    path: '',
    component: DrabsenComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DrabsenDetailComponent,
    resolve: {
      drabsen: DrabsenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DrabsenUpdateComponent,
    resolve: {
      drabsen: DrabsenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DrabsenUpdateComponent,
    resolve: {
      drabsen: DrabsenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(drabsenRoute)],
  exports: [RouterModule],
})
export class DrabsenRoutingModule {}
