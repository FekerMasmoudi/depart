import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TypStatFormService, TypStatFormGroup } from './typ-stat-form.service';
import { ITypStat } from '../typ-stat.model';
import { TypStatService } from '../service/typ-stat.service';

@Component({
  selector: 'jhi-typ-stat-update',
  templateUrl: './typ-stat-update.component.html',
})
export class TypStatUpdateComponent implements OnInit {
  isSaving = false;
  typStat: ITypStat | null = null;

  editForm: TypStatFormGroup = this.typStatFormService.createTypStatFormGroup();

  constructor(
    protected typStatService: TypStatService,
    protected typStatFormService: TypStatFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typStat }) => {
      this.typStat = typStat;
      if (typStat) {
        this.updateForm(typStat);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const typStat = this.typStatFormService.getTypStat(this.editForm);
    if (typStat.id !== null) {
      this.subscribeToSaveResponse(this.typStatService.update(typStat));
    } else {
      this.subscribeToSaveResponse(this.typStatService.create(typStat));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITypStat>>): void {
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

  protected updateForm(typStat: ITypStat): void {
    this.typStat = typStat;
    this.typStatFormService.resetForm(this.editForm, typStat);
  }
}
