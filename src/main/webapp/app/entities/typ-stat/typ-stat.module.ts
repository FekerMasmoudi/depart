import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TypStatComponent } from './list/typ-stat.component';
import { TypStatDetailComponent } from './detail/typ-stat-detail.component';
import { TypStatUpdateComponent } from './update/typ-stat-update.component';
import { TypStatDeleteDialogComponent } from './delete/typ-stat-delete-dialog.component';
import { TypStatRoutingModule } from './route/typ-stat-routing.module';

@NgModule({
  imports: [SharedModule, TypStatRoutingModule],
  declarations: [TypStatComponent, TypStatDetailComponent, TypStatUpdateComponent, TypStatDeleteDialogComponent],
})
export class TypStatModule {}
