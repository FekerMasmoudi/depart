import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { MotifaComponent } from '../list/motifa.component';
import { MotifaDetailComponent } from '../detail/motifa-detail.component';
import { MotifaUpdateComponent } from '../update/motifa-update.component';
import { MotifaRoutingResolveService } from './motifa-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const motifaRoute: Routes = [
  {
    path: '',
    component: MotifaComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MotifaDetailComponent,
    resolve: {
      motifa: MotifaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MotifaUpdateComponent,
    resolve: {
      motifa: MotifaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MotifaUpdateComponent,
    resolve: {
      motifa: MotifaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(motifaRoute)],
  exports: [RouterModule],
})
export class MotifaRoutingModule {}
