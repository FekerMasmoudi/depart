import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FoncAgentComponent } from './list/fonc-agent.component';
import { FoncAgentDetailComponent } from './detail/fonc-agent-detail.component';
import { FoncAgentUpdateComponent } from './update/fonc-agent-update.component';
import { FoncAgentDeleteDialogComponent } from './delete/fonc-agent-delete-dialog.component';
import { FoncAgentRoutingModule } from './route/fonc-agent-routing.module';

@NgModule({
  imports: [SharedModule, FoncAgentRoutingModule],
  declarations: [FoncAgentComponent, FoncAgentDetailComponent, FoncAgentUpdateComponent, FoncAgentDeleteDialogComponent],
})
export class FoncAgentModule {}
