import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { AffectagentFormService, AffectagentFormGroup } from './affectagent-form.service';
import { IAffectagent } from '../affectagent.model';
import { AffectagentService } from '../service/affectagent.service';

@Component({
  selector: 'jhi-affectagent-update',
  templateUrl: './affectagent-update.component.html',
})
export class AffectagentUpdateComponent implements OnInit {
  isSaving = false;
  affectagent: IAffectagent | null = null;

  editForm: AffectagentFormGroup = this.affectagentFormService.createAffectagentFormGroup();

  constructor(
    protected affectagentService: AffectagentService,
    protected affectagentFormService: AffectagentFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ affectagent }) => {
      this.affectagent = affectagent;
      if (affectagent) {
        this.updateForm(affectagent);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const affectagent = this.affectagentFormService.getAffectagent(this.editForm);
    if (affectagent.id !== null) {
      this.subscribeToSaveResponse(this.affectagentService.update(affectagent));
    } else {
      this.subscribeToSaveResponse(this.affectagentService.create(affectagent));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAffectagent>>): void {
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

  protected updateForm(affectagent: IAffectagent): void {
    this.affectagent = affectagent;
    this.affectagentFormService.resetForm(this.editForm, affectagent);
  }
}
