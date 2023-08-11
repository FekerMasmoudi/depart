import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DrabsenComponent } from './list/drabsen.component';
import { DrabsenDetailComponent } from './detail/drabsen-detail.component';
import { DrabsenUpdateComponent } from './update/drabsen-update.component';
import { DrabsenDeleteDialogComponent } from './delete/drabsen-delete-dialog.component';
import { DrabsenRoutingModule } from './route/drabsen-routing.module';

@NgModule({
  imports: [SharedModule, DrabsenRoutingModule],
  declarations: [DrabsenComponent, DrabsenDetailComponent, DrabsenUpdateComponent, DrabsenDeleteDialogComponent],
})
export class DrabsenModule {}
