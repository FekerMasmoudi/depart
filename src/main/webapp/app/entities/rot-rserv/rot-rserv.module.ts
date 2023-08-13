import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RotRservComponent } from './list/rot-rserv.component';
import { RotRservDetailComponent } from './detail/rot-rserv-detail.component';
import { RotRservUpdateComponent } from './update/rot-rserv-update.component';
import { RotRservDeleteDialogComponent } from './delete/rot-rserv-delete-dialog.component';
import { RotRservRoutingModule } from './route/rot-rserv-routing.module';

@NgModule({
  imports: [SharedModule, RotRservRoutingModule],
  declarations: [RotRservComponent, RotRservDetailComponent, RotRservUpdateComponent, RotRservDeleteDialogComponent],
})
export class RotRservModule {}
