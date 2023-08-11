import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRotRserv } from '../rot-rserv.model';

@Component({
  selector: 'jhi-rot-rserv-detail',
  templateUrl: './rot-rserv-detail.component.html',
})
export class RotRservDetailComponent implements OnInit {
  rotRserv: IRotRserv | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rotRserv }) => {
      this.rotRserv = rotRserv;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
