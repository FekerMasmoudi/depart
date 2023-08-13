import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { CentVehicComponent } from './list/cent-vehic.component';
import { CentVehicDetailComponent } from './detail/cent-vehic-detail.component';
import { CentVehicUpdateComponent } from './update/cent-vehic-update.component';
import { CentVehicDeleteDialogComponent } from './delete/cent-vehic-delete-dialog.component';
import { CentVehicRoutingModule } from './route/cent-vehic-routing.module';

@NgModule({
  imports: [SharedModule, CentVehicRoutingModule],
  declarations: [CentVehicComponent, CentVehicDetailComponent, CentVehicUpdateComponent, CentVehicDeleteDialogComponent],
})
export class CentVehicModule {}
