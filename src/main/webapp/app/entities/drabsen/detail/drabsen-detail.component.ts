import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDrabsen } from '../drabsen.model';

@Component({
  selector: 'jhi-drabsen-detail',
  templateUrl: './drabsen-detail.component.html',
})
export class DrabsenDetailComponent implements OnInit {
  drabsen: IDrabsen | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ drabsen }) => {
      this.drabsen = drabsen;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
