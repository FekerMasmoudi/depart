import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypStat } from '../typ-stat.model';

@Component({
  selector: 'jhi-typ-stat-detail',
  templateUrl: './typ-stat-detail.component.html',
})
export class TypStatDetailComponent implements OnInit {
  typStat: ITypStat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typStat }) => {
      this.typStat = typStat;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
