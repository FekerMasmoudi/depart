import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TraficComponent } from './list/trafic.component';
import { TraficDetailComponent } from './detail/trafic-detail.component';
import { TraficUpdateComponent } from './update/trafic-update.component';
import { TraficDeleteDialogComponent } from './delete/trafic-delete-dialog.component';
import { TraficRoutingModule } from './route/trafic-routing.module';

@NgModule({
  imports: [SharedModule, TraficRoutingModule],
  declarations: [TraficComponent, TraficDetailComponent, TraficUpdateComponent, TraficDeleteDialogComponent],
})
export class TraficModule {}
