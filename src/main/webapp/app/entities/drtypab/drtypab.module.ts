import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DrtypabComponent } from './list/drtypab.component';
import { DrtypabDetailComponent } from './detail/drtypab-detail.component';
import { DrtypabUpdateComponent } from './update/drtypab-update.component';
import { DrtypabDeleteDialogComponent } from './delete/drtypab-delete-dialog.component';
import { DrtypabRoutingModule } from './route/drtypab-routing.module';

@NgModule({
  imports: [SharedModule, DrtypabRoutingModule],
  declarations: [DrtypabComponent, DrtypabDetailComponent, DrtypabUpdateComponent, DrtypabDeleteDialogComponent],
})
export class DrtypabModule {}
