import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDepart } from '../depart.model';

@Component({
  selector: 'jhi-depart-detail',
  templateUrl: './depart-detail.component.html',
})
export class DepartDetailComponent implements OnInit {
  depart: IDepart | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depart }) => {
      this.depart = depart;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
