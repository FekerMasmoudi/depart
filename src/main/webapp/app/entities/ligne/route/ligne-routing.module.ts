import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { LigneComponent } from '../list/ligne.component';
import { LigneDetailComponent } from '../detail/ligne-detail.component';
import { LigneUpdateComponent } from '../update/ligne-update.component';
import { LigneRoutingResolveService } from './ligne-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const ligneRoute: Routes = [
  {
    path: '',
    component: LigneComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LigneDetailComponent,
    resolve: {
      ligne: LigneRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LigneUpdateComponent,
    resolve: {
      ligne: LigneRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LigneUpdateComponent,
    resolve: {
      ligne: LigneRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ligneRoute)],
  exports: [RouterModule],
})
export class LigneRoutingModule {}
