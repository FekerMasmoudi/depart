import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DeprotatComponent } from './list/deprotat.component';
import { DeprotatDetailComponent } from './detail/deprotat-detail.component';
import { DeprotatUpdateComponent } from './update/deprotat-update.component';
import { DeprotatDeleteDialogComponent } from './delete/deprotat-delete-dialog.component';
import { DeprotatRoutingModule } from './route/deprotat-routing.module';

@NgModule({
  imports: [SharedModule, DeprotatRoutingModule],
  declarations: [DeprotatComponent, DeprotatDetailComponent, DeprotatUpdateComponent, DeprotatDeleteDialogComponent],
})
export class DeprotatModule {}
