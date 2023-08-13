import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MotifchservComponent } from './list/motifchserv.component';
import { MotifchservDetailComponent } from './detail/motifchserv-detail.component';
import { MotifchservUpdateComponent } from './update/motifchserv-update.component';
import { MotifchservDeleteDialogComponent } from './delete/motifchserv-delete-dialog.component';
import { MotifchservRoutingModule } from './route/motifchserv-routing.module';

@NgModule({
  imports: [SharedModule, MotifchservRoutingModule],
  declarations: [MotifchservComponent, MotifchservDetailComponent, MotifchservUpdateComponent, MotifchservDeleteDialogComponent],
})
export class MotifchservModule {}
