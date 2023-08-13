import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BordereauComponent } from '../list/bordereau.component';
import { BordereauDetailComponent } from '../detail/bordereau-detail.component';
import { BordereauUpdateComponent } from '../update/bordereau-update.component';
import { BordereauRoutingResolveService } from './bordereau-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const bordereauRoute: Routes = [
  {
    path: '',
    component: BordereauComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BordereauDetailComponent,
    resolve: {
      bordereau: BordereauRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BordereauUpdateComponent,
    resolve: {
      bordereau: BordereauRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BordereauUpdateComponent,
    resolve: {
      bordereau: BordereauRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(bordereauRoute)],
  exports: [RouterModule],
})
export class BordereauRoutingModule {}
