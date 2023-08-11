import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { AffecAgentComponent } from './list/affec-agent.component';
import { AffecAgentDetailComponent } from './detail/affec-agent-detail.component';
import { AffecAgentUpdateComponent } from './update/affec-agent-update.component';
import { AffecAgentDeleteDialogComponent } from './delete/affec-agent-delete-dialog.component';
import { AffecAgentRoutingModule } from './route/affec-agent-routing.module';

@NgModule({
  imports: [SharedModule, AffecAgentRoutingModule],
  declarations: [AffecAgentComponent, AffecAgentDetailComponent, AffecAgentUpdateComponent, AffecAgentDeleteDialogComponent],
})
export class AffecAgentModule {}
