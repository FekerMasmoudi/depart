import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IItineraire } from '../itineraire.model';

@Component({
  selector: 'jhi-itineraire-detail',
  templateUrl: './itineraire-detail.component.html',
})
export class ItineraireDetailComponent implements OnInit {
  itineraire: IItineraire | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itineraire }) => {
      this.itineraire = itineraire;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
