import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeprotat } from '../deprotat.model';

@Component({
  selector: 'jhi-deprotat-detail',
  templateUrl: './deprotat-detail.component.html',
})
export class DeprotatDetailComponent implements OnInit {
  deprotat: IDeprotat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deprotat }) => {
      this.deprotat = deprotat;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
