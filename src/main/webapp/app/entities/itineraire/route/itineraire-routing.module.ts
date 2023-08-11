import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ItineraireComponent } from '../list/itineraire.component';
import { ItineraireDetailComponent } from '../detail/itineraire-detail.component';
import { ItineraireUpdateComponent } from '../update/itineraire-update.component';
import { ItineraireRoutingResolveService } from './itineraire-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const itineraireRoute: Routes = [
  {
    path: '',
    component: ItineraireComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ItineraireDetailComponent,
    resolve: {
      itineraire: ItineraireRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ItineraireUpdateComponent,
    resolve: {
      itineraire: ItineraireRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ItineraireUpdateComponent,
    resolve: {
      itineraire: ItineraireRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(itineraireRoute)],
  exports: [RouterModule],
})
export class ItineraireRoutingModule {}
