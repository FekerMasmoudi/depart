import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDrtypab } from '../drtypab.model';

@Component({
  selector: 'jhi-drtypab-detail',
  templateUrl: './drtypab-detail.component.html',
})
export class DrtypabDetailComponent implements OnInit {
  drtypab: IDrtypab | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ drtypab }) => {
      this.drtypab = drtypab;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
