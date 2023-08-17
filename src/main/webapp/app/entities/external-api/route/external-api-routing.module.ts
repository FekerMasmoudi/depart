import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ExternalApiComponent } from '../list/external-api.component';
import { ExternalApiDetailComponent } from '../detail/external-api-detail.component';
import { ExternalApiUpdateComponent } from '../update/external-api-update.component';
import { ExternalApiRoutingResolveService } from './external-api-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const externalApiRoute: Routes = [
  {
    path: '',
    component: ExternalApiComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ExternalApiDetailComponent,
    resolve: {
      externalApi: ExternalApiRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ExternalApiUpdateComponent,
    resolve: {
      externalApi: ExternalApiRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ExternalApiUpdateComponent,
    resolve: {
      externalApi: ExternalApiRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(externalApiRoute)],
  exports: [RouterModule],
})
export class ExternalApiRoutingModule {}
