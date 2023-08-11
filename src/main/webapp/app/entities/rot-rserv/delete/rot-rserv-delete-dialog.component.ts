import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRotRserv } from '../rot-rserv.model';
import { RotRservService } from '../service/rot-rserv.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './rot-rserv-delete-dialog.component.html',
})
export class RotRservDeleteDialogComponent {
  rotRserv?: IRotRserv;

  constructor(protected rotRservService: RotRservService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.rotRservService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
