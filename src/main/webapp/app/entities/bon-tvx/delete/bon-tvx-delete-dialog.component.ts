import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBonTvx } from '../bon-tvx.model';
import { BonTvxService } from '../service/bon-tvx.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './bon-tvx-delete-dialog.component.html',
})
export class BonTvxDeleteDialogComponent {
  bonTvx?: IBonTvx;

  constructor(protected bonTvxService: BonTvxService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.bonTvxService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
