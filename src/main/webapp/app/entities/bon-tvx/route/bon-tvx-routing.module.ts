import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BonTvxComponent } from '../list/bon-tvx.component';
import { BonTvxDetailComponent } from '../detail/bon-tvx-detail.component';
import { BonTvxUpdateComponent } from '../update/bon-tvx-update.component';
import { BonTvxRoutingResolveService } from './bon-tvx-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const bonTvxRoute: Routes = [
  {
    path: '',
    component: BonTvxComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BonTvxDetailComponent,
    resolve: {
      bonTvx: BonTvxRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BonTvxUpdateComponent,
    resolve: {
      bonTvx: BonTvxRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BonTvxUpdateComponent,
    resolve: {
      bonTvx: BonTvxRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(bonTvxRoute)],
  exports: [RouterModule],
})
export class BonTvxRoutingModule {}
