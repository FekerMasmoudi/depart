import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFoncAgent } from '../fonc-agent.model';

@Component({
  selector: 'jhi-fonc-agent-detail',
  templateUrl: './fonc-agent-detail.component.html',
})
export class FoncAgentDetailComponent implements OnInit {
  foncAgent: IFoncAgent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foncAgent }) => {
      this.foncAgent = foncAgent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
