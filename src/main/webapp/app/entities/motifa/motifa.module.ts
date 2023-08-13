import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MotifaComponent } from './list/motifa.component';
import { MotifaDetailComponent } from './detail/motifa-detail.component';
import { MotifaUpdateComponent } from './update/motifa-update.component';
import { MotifaDeleteDialogComponent } from './delete/motifa-delete-dialog.component';
import { MotifaRoutingModule } from './route/motifa-routing.module';

@NgModule({
  imports: [SharedModule, MotifaRoutingModule],
  declarations: [MotifaComponent, MotifaDetailComponent, MotifaUpdateComponent, MotifaDeleteDialogComponent],
})
export class MotifaModule {}
