import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AffecAgentFormService, AffecAgentFormGroup } from './affec-agent-form.service';
import { IAffecAgent } from '../affec-agent.model';
import { AffecAgentService } from '../service/affec-agent.service';

@Component({
  selector: 'jhi-affec-agent-update',
  templateUrl: './affec-agent-update.component.html',
})
export class AffecAgentUpdateComponent implements OnInit {
  isSaving = false;
  affecAgent: IAffecAgent | null = null;

  editForm: AffecAgentFormGroup = this.affecAgentFormService.createAffecAgentFormGroup();

  constructor(
    protected affecAgentService: AffecAgentService,
    protected affecAgentFormService: AffecAgentFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affecAgent }) => {
      this.affecAgent = affecAgent;
      if (affecAgent) {
        this.updateForm(affecAgent);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const affecAgent = this.affecAgentFormService.getAffecAgent(this.editForm);
    if (affecAgent.id !== null) {
      this.subscribeToSaveResponse(this.affecAgentService.update(affecAgent));
    } else {
      this.subscribeToSaveResponse(this.affecAgentService.create(affecAgent));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAffecAgent>>): void {
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

  protected updateForm(affecAgent: IAffecAgent): void {
    this.affecAgent = affecAgent;
    this.affecAgentFormService.resetForm(this.editForm, affecAgent);
  }
}
