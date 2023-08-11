import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRhAgent } from '../rh-agent.model';
import { RhAgentService } from '../service/rh-agent.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './rh-agent-delete-dialog.component.html',
})
export class RhAgentDeleteDialogComponent {
  rhAgent?: IRhAgent;

  constructor(protected rhAgentService: RhAgentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.rhAgentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
