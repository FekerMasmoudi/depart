import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AffectagentComponent } from '../list/affectagent.component';
import { AffectagentDetailComponent } from '../detail/affectagent-detail.component';
import { AffectagentUpdateComponent } from '../update/affectagent-update.component';
import { AffectagentRoutingResolveService } from './affectagent-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const affectagentRoute: Routes = [
  {
    path: '',
    component: AffectagentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AffectagentDetailComponent,
    resolve: {
      affectagent: AffectagentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AffectagentUpdateComponent,
    resolve: {
      affectagent: AffectagentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AffectagentUpdateComponent,
    resolve: {
      affectagent: AffectagentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(affectagentRoute)],
  exports: [RouterModule],
})
export class AffectagentRoutingModule {}
