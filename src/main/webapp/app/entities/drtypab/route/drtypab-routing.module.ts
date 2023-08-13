import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DrtypabComponent } from '../list/drtypab.component';
import { DrtypabDetailComponent } from '../detail/drtypab-detail.component';
import { DrtypabUpdateComponent } from '../update/drtypab-update.component';
import { DrtypabRoutingResolveService } from './drtypab-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const drtypabRoute: Routes = [
  {
    path: '',
    component: DrtypabComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DrtypabDetailComponent,
    resolve: {
      drtypab: DrtypabRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DrtypabUpdateComponent,
    resolve: {
      drtypab: DrtypabRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DrtypabUpdateComponent,
    resolve: {
      drtypab: DrtypabRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(drtypabRoute)],
  exports: [RouterModule],
})
export class DrtypabRoutingModule {}
