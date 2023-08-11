import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { MotifaFormService, MotifaFormGroup } from './motifa-form.service';
import { IMotifa } from '../motifa.model';
import { MotifaService } from '../service/motifa.service';

@Component({
  selector: 'jhi-motifa-update',
  templateUrl: './motifa-update.component.html',
})
export class MotifaUpdateComponent implements OnInit {
  isSaving = false;
  motifa: IMotifa | null = null;

  editForm: MotifaFormGroup = this.motifaFormService.createMotifaFormGroup();

  constructor(
    protected motifaService: MotifaService,
    protected motifaFormService: MotifaFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motifa }) => {
      this.motifa = motifa;
      if (motifa) {
        this.updateForm(motifa);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const motifa = this.motifaFormService.getMotifa(this.editForm);
    if (motifa.id !== null) {
      this.subscribeToSaveResponse(this.motifaService.update(motifa));
    } else {
      this.subscribeToSaveResponse(this.motifaService.create(motifa));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMotifa>>): void {
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

  protected updateForm(motifa: IMotifa): void {
    this.motifa = motifa;
    this.motifaFormService.resetForm(this.editForm, motifa);
  }
}
