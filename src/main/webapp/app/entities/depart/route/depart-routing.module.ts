import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DepartComponent } from '../list/depart.component';
import { DepartDetailComponent } from '../detail/depart-detail.component';
import { DepartUpdateComponent } from '../update/depart-update.component';
import { DepartRoutingResolveService } from './depart-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const departRoute: Routes = [
  {
    path: '',
    component: DepartComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DepartDetailComponent,
    resolve: {
      depart: DepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DepartUpdateComponent,
    resolve: {
      depart: DepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DepartUpdateComponent,
    resolve: {
      depart: DepartRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(departRoute)],
  exports: [RouterModule],
})
export class DepartRoutingModule {}
