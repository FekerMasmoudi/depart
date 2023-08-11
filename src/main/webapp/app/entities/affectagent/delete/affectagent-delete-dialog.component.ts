import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IAffectagent } from '../affectagent.model';
import { AffectagentService } from '../service/affectagent.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './affectagent-delete-dialog.component.html',
})
export class AffectagentDeleteDialogComponent {
  affectagent?: IAffectagent;

  constructor(protected affectagentService: AffectagentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.affectagentService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
