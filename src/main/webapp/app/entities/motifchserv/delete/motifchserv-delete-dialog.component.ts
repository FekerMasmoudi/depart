import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IMotifchserv } from '../motifchserv.model';
import { MotifchservService } from '../service/motifchserv.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './motifchserv-delete-dialog.component.html',
})
export class MotifchservDeleteDialogComponent {
  motifchserv?: IMotifchserv;

  constructor(protected motifchservService: MotifchservService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.motifchservService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
