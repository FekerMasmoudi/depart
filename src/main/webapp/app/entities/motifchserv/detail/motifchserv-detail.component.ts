import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMotifchserv } from '../motifchserv.model';

@Component({
  selector: 'jhi-motifchserv-detail',
  templateUrl: './motifchserv-detail.component.html',
})
export class MotifchservDetailComponent implements OnInit {
  motifchserv: IMotifchserv | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motifchserv }) => {
      this.motifchserv = motifchserv;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
