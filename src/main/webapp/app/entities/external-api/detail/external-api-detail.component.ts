import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExternalApi } from '../external-api.model';

@Component({
  selector: 'jhi-external-api-detail',
  templateUrl: './external-api-detail.component.html',
})
export class ExternalApiDetailComponent implements OnInit {
  externalApi: IExternalApi | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ externalApi }) => {
      this.externalApi = externalApi;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
