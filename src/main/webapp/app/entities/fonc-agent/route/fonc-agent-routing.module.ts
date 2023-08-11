import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FoncAgentComponent } from '../list/fonc-agent.component';
import { FoncAgentDetailComponent } from '../detail/fonc-agent-detail.component';
import { FoncAgentUpdateComponent } from '../update/fonc-agent-update.component';
import { FoncAgentRoutingResolveService } from './fonc-agent-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const foncAgentRoute: Routes = [
  {
    path: '',
    component: FoncAgentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FoncAgentDetailComponent,
    resolve: {
      foncAgent: FoncAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FoncAgentUpdateComponent,
    resolve: {
      foncAgent: FoncAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FoncAgentUpdateComponent,
    resolve: {
      foncAgent: FoncAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(foncAgentRoute)],
  exports: [RouterModule],
})
export class FoncAgentRoutingModule {}
