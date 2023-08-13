import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMotifa } from '../motifa.model';

@Component({
  selector: 'jhi-motifa-detail',
  templateUrl: './motifa-detail.component.html',
})
export class MotifaDetailComponent implements OnInit {
  motifa: IMotifa | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motifa }) => {
      this.motifa = motifa;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
