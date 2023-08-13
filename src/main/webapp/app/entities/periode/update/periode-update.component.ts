import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { PeriodeFormService, PeriodeFormGroup } from './periode-form.service';
import { IPeriode } from '../periode.model';
import { PeriodeService } from '../service/periode.service';

@Component({
  selector: 'jhi-periode-update',
  templateUrl: './periode-update.component.html',
})
export class PeriodeUpdateComponent implements OnInit {
  isSaving = false;
  periode: IPeriode | null = null;

  editForm: PeriodeFormGroup = this.periodeFormService.createPeriodeFormGroup();

  constructor(
    protected periodeService: PeriodeService,
    protected periodeFormService: PeriodeFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ periode }) => {
      this.periode = periode;
      if (periode) {
        this.updateForm(periode);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const periode = this.periodeFormService.getPeriode(this.editForm);
    if (periode.id !== null) {
      this.subscribeToSaveResponse(this.periodeService.update(periode));
    } else {
      this.subscribeToSaveResponse(this.periodeService.create(periode));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPeriode>>): void {
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

  protected updateForm(periode: IPeriode): void {
    this.periode = periode;
    this.periodeFormService.resetForm(this.editForm, periode);
  }
}
