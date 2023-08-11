import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BordereauComponent } from './list/bordereau.component';
import { BordereauDetailComponent } from './detail/bordereau-detail.component';
import { BordereauUpdateComponent } from './update/bordereau-update.component';
import { BordereauDeleteDialogComponent } from './delete/bordereau-delete-dialog.component';
import { BordereauRoutingModule } from './route/bordereau-routing.module';

@NgModule({
  imports: [SharedModule, BordereauRoutingModule],
  declarations: [BordereauComponent, BordereauDetailComponent, BordereauUpdateComponent, BordereauDeleteDialogComponent],
})
export class BordereauModule {}
