import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RhAgentComponent } from '../list/rh-agent.component';
import { RhAgentDetailComponent } from '../detail/rh-agent-detail.component';
import { RhAgentUpdateComponent } from '../update/rh-agent-update.component';
import { RhAgentRoutingResolveService } from './rh-agent-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const rhAgentRoute: Routes = [
  {
    path: '',
    component: RhAgentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RhAgentDetailComponent,
    resolve: {
      rhAgent: RhAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RhAgentUpdateComponent,
    resolve: {
      rhAgent: RhAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RhAgentUpdateComponent,
    resolve: {
      rhAgent: RhAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(rhAgentRoute)],
  exports: [RouterModule],
})
export class RhAgentRoutingModule {}
