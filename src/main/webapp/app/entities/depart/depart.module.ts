import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DepartComponent } from './list/depart.component';
import { DepartDetailComponent } from './detail/depart-detail.component';
import { DepartUpdateComponent } from './update/depart-update.component';
import { DepartDeleteDialogComponent } from './delete/depart-delete-dialog.component';
import { DepartRoutingModule } from './route/depart-routing.module';

@NgModule({
  imports: [SharedModule, DepartRoutingModule],
  declarations: [DepartComponent, DepartDetailComponent, DepartUpdateComponent, DepartDeleteDialogComponent],
})
export class DepartModule {}
