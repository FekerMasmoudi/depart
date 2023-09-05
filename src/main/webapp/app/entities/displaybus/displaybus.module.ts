import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DisplaybusComponent } from './list/displaybus.component';
import { DisplaybusDetailComponent } from './detail/displaybus-detail.component';
import { DisplaybusUpdateComponent } from './update/displaybus-update.component';
import { DisplaybusDeleteDialogComponent } from './delete/displaybus-delete-dialog.component';
import { DisplaybusRoutingModule } from './route/displaybus-routing.module';

@NgModule({
  imports: [SharedModule, DisplaybusRoutingModule],
  declarations: [DisplaybusComponent, DisplaybusDetailComponent, DisplaybusUpdateComponent, DisplaybusDeleteDialogComponent],
})
export class DisplaybusModule {}
