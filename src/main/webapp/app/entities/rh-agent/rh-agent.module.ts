import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RhAgentComponent } from './list/rh-agent.component';
import { RhAgentDetailComponent } from './detail/rh-agent-detail.component';
import { RhAgentUpdateComponent } from './update/rh-agent-update.component';
import { RhAgentDeleteDialogComponent } from './delete/rh-agent-delete-dialog.component';
import { RhAgentRoutingModule } from './route/rh-agent-routing.module';

@NgModule({
  imports: [SharedModule, RhAgentRoutingModule],
  declarations: [RhAgentComponent, RhAgentDetailComponent, RhAgentUpdateComponent, RhAgentDeleteDialogComponent],
})
export class RhAgentModule {}
