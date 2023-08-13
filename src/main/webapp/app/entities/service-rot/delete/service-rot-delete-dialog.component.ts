import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IServiceRot } from '../service-rot.model';
import { ServiceRotService } from '../service/service-rot.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './service-rot-delete-dialog.component.html',
})
export class ServiceRotDeleteDialogComponent {
  serviceRot?: IServiceRot;

  constructor(protected serviceRotService: ServiceRotService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.serviceRotService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
