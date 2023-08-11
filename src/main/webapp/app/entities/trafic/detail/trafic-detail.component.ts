import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrafic } from '../trafic.model';

@Component({
  selector: 'jhi-trafic-detail',
  templateUrl: './trafic-detail.component.html',
})
export class TraficDetailComponent implements OnInit {
  trafic: ITrafic | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trafic }) => {
      this.trafic = trafic;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
