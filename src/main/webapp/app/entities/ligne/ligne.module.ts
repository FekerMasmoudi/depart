import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { LigneComponent } from './list/ligne.component';
import { LigneDetailComponent } from './detail/ligne-detail.component';
import { LigneUpdateComponent } from './update/ligne-update.component';
import { LigneDeleteDialogComponent } from './delete/ligne-delete-dialog.component';
import { LigneRoutingModule } from './route/ligne-routing.module';

@NgModule({
  imports: [SharedModule, LigneRoutingModule],
  declarations: [LigneComponent, LigneDetailComponent, LigneUpdateComponent, LigneDeleteDialogComponent],
})
export class LigneModule {}
