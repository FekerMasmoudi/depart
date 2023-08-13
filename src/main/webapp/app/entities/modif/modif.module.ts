import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ModifComponent } from './list/modif.component';
import { ModifDetailComponent } from './detail/modif-detail.component';
import { ModifUpdateComponent } from './update/modif-update.component';
import { ModifDeleteDialogComponent } from './delete/modif-delete-dialog.component';
import { ModifRoutingModule } from './route/modif-routing.module';

@NgModule({
  imports: [SharedModule, ModifRoutingModule],
  declarations: [ModifComponent, ModifDetailComponent, ModifUpdateComponent, ModifDeleteDialogComponent],
})
export class ModifModule {}
