import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBordereau } from '../bordereau.model';
import { BordereauService } from '../service/bordereau.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './bordereau-delete-dialog.component.html',
})
export class BordereauDeleteDialogComponent {
  bordereau?: IBordereau;

  constructor(protected bordereauService: BordereauService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.bordereauService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
