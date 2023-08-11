import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { RhAgentFormService, RhAgentFormGroup } from './rh-agent-form.service';
import { IRhAgent } from '../rh-agent.model';
import { RhAgentService } from '../service/rh-agent.service';

@Component({
  selector: 'jhi-rh-agent-update',
  templateUrl: './rh-agent-update.component.html',
})
export class RhAgentUpdateComponent implements OnInit {
  isSaving = false;
  rhAgent: IRhAgent | null = null;

  editForm: RhAgentFormGroup = this.rhAgentFormService.createRhAgentFormGroup();

  constructor(
    protected rhAgentService: RhAgentService,
    protected rhAgentFormService: RhAgentFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rhAgent }) => {
      this.rhAgent = rhAgent;
      if (rhAgent) {
        this.updateForm(rhAgent);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rhAgent = this.rhAgentFormService.getRhAgent(this.editForm);
    if (rhAgent.id !== null) {
      this.subscribeToSaveResponse(this.rhAgentService.update(rhAgent));
    } else {
      this.subscribeToSaveResponse(this.rhAgentService.create(rhAgent));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRhAgent>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(rhAgent: IRhAgent): void {
    this.rhAgent = rhAgent;
    this.rhAgentFormService.resetForm(this.editForm, rhAgent);
  }
}
