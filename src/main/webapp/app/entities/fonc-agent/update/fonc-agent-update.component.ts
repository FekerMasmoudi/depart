import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FoncAgentFormService, FoncAgentFormGroup } from './fonc-agent-form.service';
import { IFoncAgent } from '../fonc-agent.model';
import { FoncAgentService } from '../service/fonc-agent.service';

@Component({
  selector: 'jhi-fonc-agent-update',
  templateUrl: './fonc-agent-update.component.html',
})
export class FoncAgentUpdateComponent implements OnInit {
  isSaving = false;
  foncAgent: IFoncAgent | null = null;

  editForm: FoncAgentFormGroup = this.foncAgentFormService.createFoncAgentFormGroup();

  constructor(
    protected foncAgentService: FoncAgentService,
    protected foncAgentFormService: FoncAgentFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foncAgent }) => {
      this.foncAgent = foncAgent;
      if (foncAgent) {
        this.updateForm(foncAgent);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foncAgent = this.foncAgentFormService.getFoncAgent(this.editForm);
    if (foncAgent.id !== null) {
      this.subscribeToSaveResponse(this.foncAgentService.update(foncAgent));
    } else {
      this.subscribeToSaveResponse(this.foncAgentService.create(foncAgent));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoncAgent>>): void {
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

  protected updateForm(foncAgent: IFoncAgent): void {
    this.foncAgent = foncAgent;
    this.foncAgentFormService.resetForm(this.editForm, foncAgent);
  }
}
