import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceRot } from '../service-rot.model';

@Component({
  selector: 'jhi-service-rot-detail',
  templateUrl: './service-rot-detail.component.html',
})
export class ServiceRotDetailComponent implements OnInit {
  serviceRot: IServiceRot | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceRot }) => {
      this.serviceRot = serviceRot;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
