import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AgenceFormService, AgenceFormGroup } from './agence-form.service';
import { IAgence } from '../agence.model';
import { AgenceService } from '../service/agence.service';

@Component({
  selector: 'jhi-agence-update',
  templateUrl: './agence-update.component.html',
})
export class AgenceUpdateComponent implements OnInit {
  isSaving = false;
  agence: IAgence | null = null;

  editForm: AgenceFormGroup = this.agenceFormService.createAgenceFormGroup();

  constructor(
    protected agenceService: AgenceService,
    protected agenceFormService: AgenceFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ agence }) => {
      this.agence = agence;
      if (agence) {
        this.updateForm(agence);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const agence = this.agenceFormService.getAgence(this.editForm);
    if (agence.id !== null) {
      this.subscribeToSaveResponse(this.agenceService.update(agence));
    } else {
      this.subscribeToSaveResponse(this.agenceService.create(agence));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAgence>>): void {
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

  protected updateForm(agence: IAgence): void {
    this.agence = agence;
    this.agenceFormService.resetForm(this.editForm, agence);
  }
}
