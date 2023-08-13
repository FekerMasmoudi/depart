import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentVehic } from '../cent-vehic.model';

@Component({
  selector: 'jhi-cent-vehic-detail',
  templateUrl: './cent-vehic-detail.component.html',
})
export class CentVehicDetailComponent implements OnInit {
  centVehic: ICentVehic | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centVehic }) => {
      this.centVehic = centVehic;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
