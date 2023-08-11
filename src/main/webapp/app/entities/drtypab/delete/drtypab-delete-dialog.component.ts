import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDrtypab } from '../drtypab.model';
import { DrtypabService } from '../service/drtypab.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './drtypab-delete-dialog.component.html',
})
export class DrtypabDeleteDialogComponent {
  drtypab?: IDrtypab;

  constructor(protected drtypabService: DrtypabService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.drtypabService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
