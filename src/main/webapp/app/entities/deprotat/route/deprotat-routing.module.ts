import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DeprotatComponent } from '../list/deprotat.component';
import { DeprotatDetailComponent } from '../detail/deprotat-detail.component';
import { DeprotatUpdateComponent } from '../update/deprotat-update.component';
import { DeprotatRoutingResolveService } from './deprotat-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const deprotatRoute: Routes = [
  {
    path: '',
    component: DeprotatComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DeprotatDetailComponent,
    resolve: {
      deprotat: DeprotatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DeprotatUpdateComponent,
    resolve: {
      deprotat: DeprotatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DeprotatUpdateComponent,
    resolve: {
      deprotat: DeprotatRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(deprotatRoute)],
  exports: [RouterModule],
})
export class DeprotatRoutingModule {}
