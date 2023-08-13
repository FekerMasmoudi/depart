import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRhAgent } from '../rh-agent.model';

@Component({
  selector: 'jhi-rh-agent-detail',
  templateUrl: './rh-agent-detail.component.html',
})
export class RhAgentDetailComponent implements OnInit {
  rhAgent: IRhAgent | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rhAgent }) => {
      this.rhAgent = rhAgent;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
