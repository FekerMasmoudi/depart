import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModif } from '../modif.model';

@Component({
  selector: 'jhi-modif-detail',
  templateUrl: './modif-detail.component.html',
})
export class ModifDetailComponent implements OnInit {
  modif: IModif | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modif }) => {
      this.modif = modif;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
