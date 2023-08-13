import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDeprotat } from '../deprotat.model';
import { DeprotatService } from '../service/deprotat.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './deprotat-delete-dialog.component.html',
})
export class DeprotatDeleteDialogComponent {
  deprotat?: IDeprotat;

  constructor(protected deprotatService: DeprotatService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.deprotatService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
