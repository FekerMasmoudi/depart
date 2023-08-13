import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBordereau } from '../bordereau.model';

@Component({
  selector: 'jhi-bordereau-detail',
  templateUrl: './bordereau-detail.component.html',
})
export class BordereauDetailComponent implements OnInit {
  bordereau: IBordereau | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bordereau }) => {
      this.bordereau = bordereau;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
