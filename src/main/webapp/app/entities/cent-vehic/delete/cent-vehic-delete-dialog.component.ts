import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICentVehic } from '../cent-vehic.model';
import { CentVehicService } from '../service/cent-vehic.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './cent-vehic-delete-dialog.component.html',
})
export class CentVehicDeleteDialogComponent {
  centVehic?: ICentVehic;

  constructor(protected centVehicService: CentVehicService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.centVehicService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
