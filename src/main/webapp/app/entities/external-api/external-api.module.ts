import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ExternalApiComponent } from './list/external-api.component';
import { ExternalApiDetailComponent } from './detail/external-api-detail.component';
import { ExternalApiUpdateComponent } from './update/external-api-update.component';
import { ExternalApiDeleteDialogComponent } from './delete/external-api-delete-dialog.component';
import { ExternalApiRoutingModule } from './route/external-api-routing.module';

@NgModule({
  imports: [SharedModule, ExternalApiRoutingModule],
  declarations: [ExternalApiComponent, ExternalApiDetailComponent, ExternalApiUpdateComponent, ExternalApiDeleteDialogComponent],
})
export class ExternalApiModule {}
