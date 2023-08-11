import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFoncAgent } from '../fonc-agent.model';
import { FoncAgentService } from '../service/fonc-agent.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './fonc-agent-delete-dialog.component.html',
})
export class FoncAgentDeleteDialogComponent {
  foncAgent?: IFoncAgent;

  constructor(protected foncAgentService: FoncAgentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.foncAgentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
