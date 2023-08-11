import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAffecAgent } from '../affec-agent.model';

@Component({
  selector: 'jhi-affec-agent-detail',
  templateUrl: './affec-agent-detail.component.html',
})
export class AffecAgentDetailComponent implements OnInit {
  affecAgent: IAffecAgent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affecAgent }) => {
      this.affecAgent = affecAgent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
