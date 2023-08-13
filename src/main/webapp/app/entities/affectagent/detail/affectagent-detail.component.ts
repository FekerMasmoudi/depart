import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAffectagent } from '../affectagent.model';

@Component({
  selector: 'jhi-affectagent-detail',
  templateUrl: './affectagent-detail.component.html',
})
export class AffectagentDetailComponent implements OnInit {
  affectagent: IAffectagent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affectagent }) => {
      this.affectagent = affectagent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
