import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDisplaybus } from '../displaybus.model';
import { DisplaybusService } from '../service/displaybus.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './displaybus-delete-dialog.component.html',
})
export class DisplaybusDeleteDialogComponent {
  displaybus?: IDisplaybus;

  constructor(protected displaybusService: DisplaybusService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.displaybusService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
