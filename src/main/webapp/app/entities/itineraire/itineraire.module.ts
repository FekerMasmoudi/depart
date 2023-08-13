import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ItineraireComponent } from './list/itineraire.component';
import { ItineraireDetailComponent } from './detail/itineraire-detail.component';
import { ItineraireUpdateComponent } from './update/itineraire-update.component';
import { ItineraireDeleteDialogComponent } from './delete/itineraire-delete-dialog.component';
import { ItineraireRoutingModule } from './route/itineraire-routing.module';

@NgModule({
  imports: [SharedModule, ItineraireRoutingModule],
  declarations: [ItineraireComponent, ItineraireDetailComponent, ItineraireUpdateComponent, ItineraireDeleteDialogComponent],
})
export class ItineraireModule {}
