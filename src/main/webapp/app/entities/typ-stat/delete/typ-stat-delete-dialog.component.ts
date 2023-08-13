import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITypStat } from '../typ-stat.model';
import { TypStatService } from '../service/typ-stat.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './typ-stat-delete-dialog.component.html',
})
export class TypStatDeleteDialogComponent {
  typStat?: ITypStat;

  constructor(protected typStatService: TypStatService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.typStatService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
