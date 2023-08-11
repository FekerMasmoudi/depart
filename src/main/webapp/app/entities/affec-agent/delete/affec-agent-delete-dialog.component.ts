import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAffecAgent } from '../affec-agent.model';
import { AffecAgentService } from '../service/affec-agent.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './affec-agent-delete-dialog.component.html',
})
export class AffecAgentDeleteDialogComponent {
  affecAgent?: IAffecAgent;

  constructor(protected affecAgentService: AffecAgentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.affecAgentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
