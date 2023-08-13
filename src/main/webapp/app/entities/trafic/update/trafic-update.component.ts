import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { TraficFormService, TraficFormGroup } from './trafic-form.service';
import { ITrafic } from '../trafic.model';
import { TraficService } from '../service/trafic.service';

@Component({
  selector: 'jhi-trafic-update',
  templateUrl: './trafic-update.component.html',
})
export class TraficUpdateComponent implements OnInit {
  isSaving = false;
  trafic: ITrafic | null = null;

  editForm: TraficFormGroup = this.traficFormService.createTraficFormGroup();

  constructor(
    protected traficService: TraficService,
    protected traficFormService: TraficFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ trafic }) => {
      this.trafic = trafic;
      if (trafic) {
        this.updateForm(trafic);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const trafic = this.traficFormService.getTrafic(this.editForm);
    if (trafic.id !== null) {
      this.subscribeToSaveResponse(this.traficService.update(trafic));
    } else {
      this.subscribeToSaveResponse(this.traficService.create(trafic));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrafic>>): void {
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

  protected updateForm(trafic: ITrafic): void {
    this.trafic = trafic;
    this.traficFormService.resetForm(this.editForm, trafic);
  }
}
