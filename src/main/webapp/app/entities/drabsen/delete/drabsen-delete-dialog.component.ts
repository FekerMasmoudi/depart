import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDrabsen } from '../drabsen.model';
import { DrabsenService } from '../service/drabsen.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './drabsen-delete-dialog.component.html',
})
export class DrabsenDeleteDialogComponent {
  drabsen?: IDrabsen;

  constructor(protected drabsenService: DrabsenService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.drabsenService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
