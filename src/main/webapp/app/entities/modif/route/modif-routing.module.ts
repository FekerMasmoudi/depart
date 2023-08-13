import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ModifComponent } from '../list/modif.component';
import { ModifDetailComponent } from '../detail/modif-detail.component';
import { ModifUpdateComponent } from '../update/modif-update.component';
import { ModifRoutingResolveService } from './modif-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const modifRoute: Routes = [
  {
    path: '',
    component: ModifComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModifDetailComponent,
    resolve: {
      modif: ModifRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModifUpdateComponent,
    resolve: {
      modif: ModifRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModifUpdateComponent,
    resolve: {
      modif: ModifRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(modifRoute)],
  exports: [RouterModule],
})
export class ModifRoutingModule {}
