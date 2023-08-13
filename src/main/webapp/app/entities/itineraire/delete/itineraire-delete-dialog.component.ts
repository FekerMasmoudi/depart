import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IItineraire } from '../itineraire.model';
import { ItineraireService } from '../service/itineraire.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './itineraire-delete-dialog.component.html',
})
export class ItineraireDeleteDialogComponent {
  itineraire?: IItineraire;

  constructor(protected itineraireService: ItineraireService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.itineraireService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
