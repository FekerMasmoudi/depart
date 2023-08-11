import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { AffecAgentComponent } from '../list/affec-agent.component';
import { AffecAgentDetailComponent } from '../detail/affec-agent-detail.component';
import { AffecAgentUpdateComponent } from '../update/affec-agent-update.component';
import { AffecAgentRoutingResolveService } from './affec-agent-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const affecAgentRoute: Routes = [
  {
    path: '',
    component: AffecAgentComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AffecAgentDetailComponent,
    resolve: {
      affecAgent: AffecAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AffecAgentUpdateComponent,
    resolve: {
      affecAgent: AffecAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AffecAgentUpdateComponent,
    resolve: {
      affecAgent: AffecAgentRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(affecAgentRoute)],
  exports: [RouterModule],
})
export class AffecAgentRoutingModule {}
