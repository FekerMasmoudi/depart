import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BonTvxComponent } from './list/bon-tvx.component';
import { BonTvxDetailComponent } from './detail/bon-tvx-detail.component';
import { BonTvxUpdateComponent } from './update/bon-tvx-update.component';
import { BonTvxDeleteDialogComponent } from './delete/bon-tvx-delete-dialog.component';
import { BonTvxRoutingModule } from './route/bon-tvx-routing.module';

@NgModule({
  imports: [SharedModule, BonTvxRoutingModule],
  declarations: [BonTvxComponent, BonTvxDetailComponent, BonTvxUpdateComponent, BonTvxDeleteDialogComponent],
})
export class BonTvxModule {}
