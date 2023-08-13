import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBonTvx } from '../bon-tvx.model';

@Component({
  selector: 'jhi-bon-tvx-detail',
  templateUrl: './bon-tvx-detail.component.html',
})
export class BonTvxDetailComponent implements OnInit {
  bonTvx: IBonTvx | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bonTvx }) => {
      this.bonTvx = bonTvx;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
