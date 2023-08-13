import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AffectagentComponent } from './list/affectagent.component';
import { AffectagentDetailComponent } from './detail/affectagent-detail.component';
import { AffectagentUpdateComponent } from './update/affectagent-update.component';
import { AffectagentDeleteDialogComponent } from './delete/affectagent-delete-dialog.component';
import { AffectagentRoutingModule } from './route/affectagent-routing.module';

@NgModule({
  imports: [SharedModule, AffectagentRoutingModule],
  declarations: [AffectagentComponent, AffectagentDetailComponent, AffectagentUpdateComponent, AffectagentDeleteDialogComponent],
})
export class AffectagentModule {}
