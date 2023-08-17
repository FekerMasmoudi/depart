import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IExternalApi } from '../external-api.model';
import { ExternalApiService } from '../service/external-api.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './external-api-delete-dialog.component.html',
})
export class ExternalApiDeleteDialogComponent {
  externalApi?: IExternalApi;

  constructor(protected externalApiService: ExternalApiService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.externalApiService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
