import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ServiceRotComponent } from './list/service-rot.component';
import { ServiceRotDetailComponent } from './detail/service-rot-detail.component';
import { ServiceRotUpdateComponent } from './update/service-rot-update.component';
import { ServiceRotDeleteDialogComponent } from './delete/service-rot-delete-dialog.component';
import { ServiceRotRoutingModule } from './route/service-rot-routing.module';

@NgModule({
  imports: [SharedModule, ServiceRotRoutingModule],
  declarations: [ServiceRotComponent, ServiceRotDetailComponent, ServiceRotUpdateComponent, ServiceRotDeleteDialogComponent],
})
export class ServiceRotModule {}
