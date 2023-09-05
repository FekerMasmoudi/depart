import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDisplaybus } from '../displaybus.model';

@Component({
  selector: 'jhi-displaybus-detail',
  templateUrl: './displaybus-detail.component.html',
})
export class DisplaybusDetailComponent implements OnInit {
  displaybus: IDisplaybus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ displaybus }) => {
      this.displaybus = displaybus;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
