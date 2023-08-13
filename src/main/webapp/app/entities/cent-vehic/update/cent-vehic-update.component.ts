import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { CentVehicFormService, CentVehicFormGroup } from './cent-vehic-form.service';
import { ICentVehic } from '../cent-vehic.model';
import { CentVehicService } from '../service/cent-vehic.service';

@Component({
  selector: 'jhi-cent-vehic-update',
  templateUrl: './cent-vehic-update.component.html',
})
export class CentVehicUpdateComponent implements OnInit {
  isSaving = false;
  centVehic: ICentVehic | null = null;

  editForm: CentVehicFormGroup = this.centVehicFormService.createCentVehicFormGroup();

  constructor(
    protected centVehicService: CentVehicService,
    protected centVehicFormService: CentVehicFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ centVehic }) => {
      this.centVehic = centVehic;
      if (centVehic) {
        this.updateForm(centVehic);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const centVehic = this.centVehicFormService.getCentVehic(this.editForm);
    if (centVehic.id !== null) {
      this.subscribeToSaveResponse(this.centVehicService.update(centVehic));
    } else {
      this.subscribeToSaveResponse(this.centVehicService.create(centVehic));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentVehic>>): void {
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

  protected updateForm(centVehic: ICentVehic): void {
    this.centVehic = centVehic;
    this.centVehicFormService.resetForm(this.editForm, centVehic);
  }
}
